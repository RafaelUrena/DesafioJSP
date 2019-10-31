<%-- 
    Document   : Profesor
    Created on : 18-oct-2019, 13:12:35
    Author     : rafa
--%>

<%@page import="Modelo.Reserva"%>
<%@page import="Modelo.Franjas"%>
<%@page import="Modelo.Aula"%>
<%@page import="java.util.LinkedList"%>
<%@page import="Modelo.ConexionEstatica"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="../css/micss.css" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            LinkedList<Aula> aulas = ConexionEstatica.sacarAulas();
        %>
        <h1>profe</h1>
        <form name="formu" action="../controladores/ControlPrincipal.jsp" method="POST">
            <label for="fecha">Elige fecha </label>
            <input type="date" name="fecha" id="fecha" required="" ><br>

            <label for="aula">Selecciona el aula </label>
            <select name="aula" id="aula">
                <% for (Aula a : aulas) {%>
                <option value="<%=a.getNombre()%>" ><%=a.getNombre()%></option>
                <% }%>
            </select><br>

            <input type="submit" name="aceptar_profesor" value="Ver cuadrante">

        </form>

        <%
            if(session.getAttribute("reservas") != null){
        %>
        <div id="cuadrante">

            <h3>FECHA <%=session.getAttribute("fecha") %></h3>
            <h3>AULA <%=session.getAttribute("aula")%></h3>

            <table>
                <thead>
                    <tr>
                        <td><h4>HORA COMIENZO</h4></td>
                        <td><h4>HORA FINAL</h4></td>
                        <td><h4>RESERVADO</h4></td>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        LinkedList<Franjas> fran = (LinkedList) session.getAttribute("reservas");
                        for (Franjas f : fran) {       
                    %>
                <form name="formu" method="POST" action="../controladores/ControlPrincipal.jsp">
                    <tr>
                        <input type="hidden" name="idf" value="<%=f.getId_franja()%>" >
                        <td><%=f.getInicio() %></td>
                        <td><%=f.getFin() %></td>
                        <td><input type="submit" name="reservar" value="<% if(f.isReservado()) out.print("Reservado");else out.print("Libre"); %>" ></td>
                    </tr>
                </form>
                <%}%>
                </tbody>


            </table>
                <a href="loginAdmin.jsp"><input name="volver" type="button" id="volver" value="Volver" ></a>
        </div>

<%}%>
    </body>
</html>
