<%-- 
    Document   : ControlPrincipal
    Created on : 18-oct-2019, 10:50:33
    Author     : rafa
--%>

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
                response.sendRedirect("../vistas/Profesor.jsp");
            }
            if (u.getRol() == 3) {
                response.sendRedirect("../vistas/Profesor.jsp");
            }
        } else {
            session.setAttribute("loginincorrecto", "El usuario o contraseña son incorrectos");
            response.sendRedirect("../index.jsp");
        }

    }

%>