<%-- 
    Document   : adminGene
    Created on : 22-oct-2019, 10:19:50
    Author     : rafa
--%>

<%@page import="Modelo.ConexionEstatica"%>
<%@page import="java.util.LinkedList"%>
<%@page import="Modelo.Usuario"%>
<%@page import="Modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Control Usuarios</h1>

        <table>
            <thead>
                <tr>
                    <td>Correo</td>
                </tr>
            </thead>
            <tbody>
                <%
                    LinkedList<Usuario> listau = ConexionEstatica.sacarUsuarios();
                    for (Usuario u : listau) {
                %>
            <form name="formu" method="POST" action="../controladores/ControlUsuario.jsp">
                <tr>
                <input name="user" id="iduser" type="hidden" value="<%=u.getId_usuario()%>">
                <td><input name="correo" id="correou" type="text" value="<%=u.getEmail()%>" readonly=""></td>
                <% if(u.isEstaActivo()) {%>
                <td><input name="vali" type="submit" id="validaruser" value="Desactivar" ></td>
                <% }else{%>
                <td><input name="vali" type="submit" id="validaruser" value="Activar" ></td>
                <%}%>
                </tr>
            </form>
            <%}%>
        </tbody>
    </table>
        <a href="loginAdmin.jsp"><input name="volver" type="button" id="volver" value="Volver" ></a>
</body>
</html>
</body>
</html>
