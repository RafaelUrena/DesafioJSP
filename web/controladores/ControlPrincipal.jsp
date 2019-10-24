<%-- 
    Document   : ControlPrincipal
    Created on : 18-oct-2019, 10:50:33
    Author     : rafa
--%>

<%@page import="Clases.Reserva"%>
<%@page import="Clases.Franjas"%>
<%@page import="java.util.LinkedList"%>
<%@page import="Clases.Bitacora"%>
<%@page import="Clases.Usuario"%>
<%@page import="Clases.Codificar"%>
<%@page import="Clases.ConexionEstatica"%>
<%
//****************************************************************************//
//*****************************Crear Usuario**********************************//
//****************************************************************************//
    if (request.getParameter("subir") != null) {
        String nom = request.getParameter("nombre");
        String ape = request.getParameter("apellido");
        String user = request.getParameter("usuario");
        String pass = request.getParameter("clave");
        String passCod = Codificar.codifica(pass);
        int edad = Integer.parseInt(request.getParameter("edad"));
        String foto = request.getParameter("foto");
        boolean activo = false;
        Usuario u = new Usuario();

        u = ConexionEstatica.existeUsuario(user);
        //Comprobamos que el usuario que introducimos no exista.
        if (u == null) {
            ConexionEstatica.AgregarUsuario(user, nom, passCod, edad, ape, foto, activo);

            response.sendRedirect("../index.jsp");
        } else {
            session.setAttribute("userexiste", "El usuario ya existe");
            response.sendRedirect("../vistas/Registro.jsp");
        }

    }

//****************************************************************************//
//***************************Comprobar login**********************************//
//****************************************************************************//
    if (request.getParameter("aceptar_index") != null) {
        String user = request.getParameter("email");
        String pass = request.getParameter("pass");
        String codPass = Codificar.codifica(pass);
        Usuario u = ConexionEstatica.existeUsuario(user, codPass);

        if (u != null) {
            session.setAttribute("useresgistrado", u);
            Bitacora.escribeLinea("Se ha conectado el usuario " + u.getEmail());

            if (u.getRol() == 1) {
                response.sendRedirect("../vistas/Profesor.jsp");
            }
            if (u.getRol() == 2) {
                response.sendRedirect("../vistas/loginAdmin.jsp");
            }
            if (u.getRol() == 3) {
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
        Usuario u = (Usuario) session.getAttribute("useresgistrado");
        int iduser = u.getId_usuario();
        int idf = Integer.parseInt(request.getParameter("idf"));
        
        if(request.getParameter("reservar").equals("Libre")){
            
            ConexionEstatica.AgregarReserva(idf, aula, fecha, iduser);
            
        } else {
            Reserva r = new Reserva();
            
           r = ConexionEstatica.existeReserva(idf, aula, fecha, iduser);
           
           if(r != null){
               if(r.getId_profe() == u.getId_usuario()){
                   ConexionEstatica.borraReserva(r.getId_reserva());
               }
           }
        }
        
        
        
        response.sendRedirect("../vistas/Profesor.jsp");
    }
%>