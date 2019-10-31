<%-- 
    Document   : adminAulas
    Created on : 22-oct-2019, 10:19:37
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
        <h1>admin aula</h1>

        <table>
            <thead>
                <tr>
                    <td>Nombre</td>
                    <td>Descripción</td>
                </tr>
            </thead>
            <tbody>
                <%
                    LinkedList<Aula> listaa = ConexionEstatica.sacarAulas();
                    for (Aula a : listaa) {
                %>
            <form name="formu" method="POST" action="../controladores/ControlAulas.jsp">
                <tr>
                <input name="idaula" id="idaula" type="hidden" value="<%=a.getID_aula()%>">
                <td><input name="nombre" id="nombreaula" type="text" value="<%=a.getNombre()%>"></td>
                <td><input name="descri" id="descripcion" type="text" value="<%=a.getDescripcion()%>"></td>
                <td><button name="boton" type="submit" id="modify" value="Modificar" ></button></td>
                <td><button name="boton" type="submit" id="delete" value="Eliminar" ></button></td>
                </tr>
            </form>
            <%}%>
        </tbody>
        <tfoot>
        <form name="formu2" method="POST" action="../controladores/ControlAulas.jsp">
            <td><input name="nuev" id="nuevaaula" type="text" value="<%%>"></td>
            <td><input name="nuevdescr" id="nuevadescr" type="text" value="<%%>"></td>
            <td><button name="boton" type="submit" id="add" value="Add" ></button></td>
        </form>
    </tfoot>
</table>
            <a href="loginAdmin.jsp"><input name="volver" type="button" id="volver" value="Volver" ></a>
</body>
</html>
