<%-- 
    Document   : gestionFranjas
    Created on : 25-oct-2019, 9:10:02
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
        <h1>admin franjas</h1>


        <table>
            <thead>
                <tr>
                    <td>Número</td>
                    <td>Hora inicio</td>
                    <td>Hora fin</td>
                </tr>
            </thead>
            <tbody>
                <%
                    LinkedList<Franjas> listaf = ConexionEstatica.sacarFranjas();
                    for (Franjas f : listaf) {
                %>
            <form name="formu" method="POST" action="../controladores/ControlFranjas.jsp">
                <tr>
                <input name="franja" id="idfranja" type="hidden" value="<%=f.getId_franja()%>">
                <td><input name="fran" id="idfran" type="text" value="<%=f.getId_franja()%>" disabled=""></td>
                <td><input name="hinicio" id="hinicio" type="text" pattern="([01]?[0-9]|2[0-3]):[0-5][0-9]" value="<%=f.getInicio()%>"></td>
                <td><input name="hfin" id="hfin" type="text" pattern="([01]?[0-9]|2[0-3]):[0-5][0-9]" value="<%=f.getFin()%>"></td>
                <td><button name="boton" type="submit" id="modify" value="Modificar" ></button></td>
                <td><button name="boton" type="submit" id="delete" value="Eliminar" ></button></td>
                </tr>
            </form>
            <%}%>
        </tbody>
        <tfoot>
        <form name="formu2" method="POST" action="../controladores/ControlFranjas.jsp">
            <td><input name="fran" id="idfran" type="text" value="" disabled=""></td>
            <td><input name="hinicio" id="hinicio" type="text" pattern="([01]?[0-9]|2[0-3]):[0-5][0-9]" value=""></td>
            <td><input name="hfin" id="hfin" type="text" pattern="([01]?[0-9]|2[0-3]):[0-5][0-9]" value=""></td>
            <td><button name="boton" type="submit" id="add" value="Add" ></button></td>
        </form>
    </tfoot>
</table>
<a href="loginAdmin.jsp"><input name="volver" type="button" id="volver" value="Volver" ></a>
</body>
</html>
