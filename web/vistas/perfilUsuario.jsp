<%-- 
    Document   : perfilUsuario
    Created on : 30-oct-2019, 23:31:27
    Author     : rafa
--%>

<%@page import="Modelo.ConexionEstatica"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.LinkedList"%>
<%@page import="Modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="../css/micss2.css">
        <script src="../js/mijs.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Perfil usuario</h1>

        <table>
            <thead>
                <tr>
                    <td>Nombre</td>
                    <td>Apellido</td>
                    <td>Edad</td>
                    <td>Contraseña</td>
                    <td>Repite contraseña</td>
                    <td>Ponga una foto</td>
                </tr>
            </thead>
            <tbody>
                <%
                    Usuario us = (Usuario) session.getAttribute("useresgistrado");
                    Usuario u = ConexionEstatica.existeUsuario(us.getEmail());
                %>
            <form name="formu" method="POST" action="../controladores/ControlPerfil.jsp" enctype="multipart/form-data">
                <tr>
                <input name="user" id="iduser" type="hidden" value="<%=u.getId_usuario()%>">
                <td><input name="nombre" id="nombreu" type="text" value="<%=u.getNombre()%>"></td>
                <td><input name="apellido" id="apellidou" type="text" value="<%=u.getApellido()%>"></td>
                <td><input name="edad" id="edadu" type="number" value="<%=u.getEdad()%>"></td>
                <td><input type="password" name="clave" id="clave" required="" placeholder="Escriba su contraseña"></td>
                <td><input type="password" name="rclave" id="rclave" required="" placeholder="Repita su contraseña" onblur="comprobarContrasena()"></td>
                <td><input type="file" name="foto" id="foto" accept="image/*" ></td>
                <td><button name="botonperfil" type="submit" id="acept" value="Modificar" ></button></td>
                </tr>
            </form>
        </tbody>
    </table>
    <a href="loginAdmin.jsp"><input name="volver" type="button" id="volver" value="Volver" ></a>       
</body>
</html>