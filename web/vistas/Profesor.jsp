<%-- 
    Document   : Profesor
    Created on : 18-oct-2019, 13:12:35
    Author     : rafa
--%>

<%@page import="Clases.Aula"%>
<%@page import="java.util.LinkedList"%>
<%@page import="Clases.ConexionEstatica"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
            LinkedList <Aula>aulas = ConexionEstatica.sacarAulas();
        %>
        <h1>profe</h1>
        <form name="formu" action="../controladores/ControlPrincipal.jsp" method="POST">
                    <label for="fecha">Elige fecha </label>
                    <input type="date" name="fecha" id="fecha" ><br>

                    <label for="aula">Selecciona el aula </label>
                    <select id="aula">
                        <% for (Aula a : aulas) { %>
                        <option value="<%=a.getID_aula()%>" ><%=a.getNombre() %></option>
                                <% }%>
                    </select><br>

                    <input type="submit" name="aceptar_profesor" value="Ver cuadrante">

                </form>
    </body>
</html>
