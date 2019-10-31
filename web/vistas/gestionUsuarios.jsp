<%-- 
    Document   : gestionUsuarios
    Created on : 25-oct-2019, 9:10:39
    Author     : rafa
--%>

<%@page import="Modelo.*"%>
<%@page import="java.util.LinkedList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="../css/micss3.css">
        <title>JSP Page</title>
    </head>
<body>
        <h1>admin usuarios</h1>

        
            <table>
                <thead>
                    <tr>
                        <td>Correo</td>
                        <td>Nombre</td>
                        <td>Apellido</td>
                        <td>Edad</td>
                    </tr>
                </thead>
                <tbody>
                    <%
                        LinkedList<Usuario> listau = ConexionEstatica.sacarUsuarios();
                        for (Usuario u : listau) {
                    %>
                    <form name="formu" method="POST" action="../controladores/ControlUsuario.jsp">
                    <tr>
                        <input name="user" id="iduser" type="hidden" value="<%=u.getId_usuario() %>">
                        <td><input name="correo" id="correou" type="text" value="<%=u.getEmail() %>" disabled=""></td>
                        <td><input name="nombre" id="nombreu" type="text" value="<%=u.getNombre() %>"></td>
                        <td><input name="apellido" id="apellidou" type="text" value="<%=u.getApellido() %>"></td>
                        <td><input name="edad" id="edadu" type="number" value="<%=u.getEdad() %>"></td>
                        <td><button name="boton" type="submit" id="modify" value="Modificar" ></button></td>
                        <td><button name="boton" type="submit" id="delete" value="Eliminar" ></button></td>
                    </tr>
                    </form>
                    <%}%>
                </tbody>
            </table>
                <a href="loginAdmin.jsp"><input name="volver" type="button" id="volver" value="Volver" ></a>
    </body>
</html>
