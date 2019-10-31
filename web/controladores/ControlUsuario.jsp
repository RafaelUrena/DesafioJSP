<%-- 
    Document   : ControlUsuario
    Created on : 29-oct-2019, 21:17:58
    Author     : rafa
--%>

<%@page import="Auxiliar.Email"%>
<%@page import="Auxiliar.Codificar"%>
<%@page import="Auxiliar.Passwords"%>
<%@page import="Modelo.Usuario"%>
<%@page import="Modelo.ConexionEstatica"%>
<%

    if (request.getParameter("boton") != null) {

        if (request.getParameter("boton").equals("Eliminar")) {
            int id = Integer.parseInt(request.getParameter("user"));
            Usuario u = (Usuario) session.getAttribute("useresgistrado");
            
            if(id != u.getId_usuario()){
                ConexionEstatica.borrarUsuario(id);
            }

            response.sendRedirect("../vistas/gestionUsuarios.jsp");
        }

        if (request.getParameter("boton").equals("Modificar")) {
            int id = Integer.parseInt(request.getParameter("user"));
            String nom = request.getParameter("nombre");
            String ape = request.getParameter("apellido");
            int edad = Integer.parseInt(request.getParameter("edad"));

            ConexionEstatica.ModificarUsuarios(id, nom, ape, edad);

            response.sendRedirect("../vistas/gestionUsuarios.jsp");
        }
    }

    if (request.getParameter("vali") != null) {
        int id = Integer.parseInt(request.getParameter("user"));
        String user = request.getParameter("correo");

        Usuario u = ConexionEstatica.existeUsuario(user);

        if (u.isEstaActivo()) {
            ConexionEstatica.ValidarUsuario(id, false);
        } else {
            ConexionEstatica.ValidarUsuario(id, true);
        }

        response.sendRedirect("../vistas/adminGene.jsp");
    }

    if (request.getParameter("aceptarcorreo") != null) {
        Email mail = new Email();
        String email = request.getParameter("email");
        String mensaje = "";
        String pass = "";
        boolean enviado = false;

        pass = Passwords.getPassword(8);

        mensaje = "Hemos cambiado la contraseña del usuario " + email + " por " + pass;

        enviado = mail.enviarCorreo(request.getParameter("email"), mensaje, "Recuperar Contraseña");

        if (enviado) {

            ConexionEstatica.ModificarPass(email, Codificar.codifica(pass));

            session.setAttribute("recuperar", "El mensaje para recuperar la contraseña ha sido enviado");
            response.sendRedirect("../vistas/recuperar.jsp");
        } else {
            session.setAttribute("recuperar", "No hemos podido enviar el correo, vuelva a intentarlo");
            response.sendRedirect("../vistas/recuperar.jsp");
        }
    }


%>
