<%-- 
    Document   : ControlPrincipal
    Created on : 18-oct-2019, 10:50:33
    Author     : rafa
--%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="org.apache.commons.fileupload.FileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Blob"%>
<%@page import="Modelo.Aula"%>
<%@page import="Modelo.Reserva"%>
<%@page import="Modelo.Franjas"%>
<%@page import="java.util.LinkedList"%>
<%@page import="Auxiliar.Bitacora"%>
<%@page import="Modelo.Usuario"%>
<%@page import="Auxiliar.Codificar"%>
<%@page import="Modelo.ConexionEstatica"%>

<%
//****************************************************************************//
//***************************Comprobar login**********************************//
//****************************************************************************//
    if (request.getParameter("aceptar_index") != null) {
        String user = request.getParameter("email");
        String pass = request.getParameter("pass");
        String codPass = Codificar.codifica(pass);
        Usuario u = ConexionEstatica.existeUsuario(user, codPass);

        if (u != null && u.isEstaActivo()) {
            session.setAttribute("useresgistrado", u);
            Bitacora.escribeLinea("Se ha conectado el usuario " + u.getEmail());

            if (u.getLength() == 1) {
                response.sendRedirect("../vistas/loginAdmin.jsp");
            }
            
            if (u.getLength() > 1) {
                response.sendRedirect("../vistas/loginAdmin.jsp");
            }
            
        } else {
            session.setAttribute("loginincorrecto", "El usuario o contraseña son incorrectos");
            response.sendRedirect("../index.jsp");
        }

    }

//****************************************************************************//
//***************************Comprobar profesor*******************************//
//****************************************************************************//
    if (request.getParameter("aceptar_profesor") != null) {
        String fecha = request.getParameter("fecha");
        int aula = Integer.parseInt(request.getParameter("aula"));
        session.setAttribute("aula", aula);
        LinkedList<Franjas> listaf = ConexionEstatica.sacarFranjas();
        LinkedList<Reserva> listar = ConexionEstatica.sacarReservas(aula, fecha);

        for (Reserva r : listar) {
            boolean bandera = false;
            int i = 0;

            do {
                Franjas f = (Franjas) listaf.get(i);
                if (f.getId_franja() == r.getId_franja()) {
                    f.setReservado(true);
                    bandera = true;
                }
                i++;

            } while (!bandera);

        }
        session.setAttribute("reservas", listaf);
        session.setAttribute("fecha", request.getParameter("fecha"));
        response.sendRedirect("../vistas/Profesor.jsp");
    }

//****************************************************************************//
//***************************Comprobar reservas*******************************//
//****************************************************************************//
    if (request.getParameter("reservar") != null) {
        String fecha = (String) session.getAttribute("fecha");
        int aula = (Integer) session.getAttribute("aula");
        int idaula = 0;
        Aula a = null;
        Usuario u = (Usuario) session.getAttribute("useresgistrado");
        int iduser = u.getId_usuario();
        int idf = Integer.parseInt(request.getParameter("idf"));
        
        a = ConexionEstatica.sacarAula(aula);
        
        idaula = a.getID_aula();
        
        if(request.getParameter("reservar").equals("Libre")){
            
            ConexionEstatica.AgregarReserva(idf, idaula, fecha, iduser);
            
        } else {
            Reserva r = new Reserva();
            
           r = ConexionEstatica.existeReserva(idf, idaula, fecha, iduser);
           
           if(r != null){
               if(r.getId_profe() == u.getId_usuario()){
                   ConexionEstatica.borraReserva(r.getId_reserva());
               }
           }
        }
        
        LinkedList<Franjas> listaf = ConexionEstatica.sacarFranjas();
        LinkedList<Reserva> listar = ConexionEstatica.sacarReservas(aula, fecha);

        for (Reserva r : listar) {
            boolean bandera = false;
            int i = 0;

            do {
                Franjas f = (Franjas) listaf.get(i);
                if (f.getId_franja() == r.getId_franja()) {
                    f.setReservado(true);
                    bandera = true;
                }
                i++;

            } while (!bandera);

        }
        
        session.setAttribute("reservas", listaf);
        response.sendRedirect("../vistas/Profesor.jsp");
    }
    
//****************************************************************************//
//***************************Cerrar sesión**********************************//
//****************************************************************************//

if(request.getParameter("boton") != null){
    Usuario u = (Usuario) session.getAttribute("useresgistrado");
    Bitacora.escribeLinea("El usuario "+u.getEmail() + " se ha desconectado");
    session.invalidate();
    response.sendRedirect("../index.jsp");
}

%>