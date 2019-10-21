<%-- 
    Document   : ControlPrincipal
    Created on : 18-oct-2019, 10:50:33
    Author     : rafa
--%>

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

        ConexionEstatica.AgregarUsuario(user, nom, passCod, edad, ape);

        response.sendRedirect("../index.jsp");
    }
    
//****************************************************************************//
//***************************Comprobar login**********************************//
//****************************************************************************//
    if (request.getParameter("aceptar_index") != null) {
       

    }

%>