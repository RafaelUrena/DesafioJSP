<%-- 
    Document   : recuperar
    Created on : 29-oct-2019, 9:17:18
    Author     : rafa
--%>

<%@page import="Modelo.*"%>
<%@page import="java.util.LinkedList"%>
<%@page import="Auxiliar.*"%>
<%@page import="javax.mail.event.MailEvent"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css"  href="../css/cssregistro.css">
        <title>JSP Page</title>
    </head>
    <body>

        <form name="formu" action="../controladores/ControlUsuario.jsp" method="POST">
            <input type="email" name="email" placeholder="Escriba su email">
            <input type="submit" name="aceptarcorreo" value="Aceptar">
            
            <% 
            if(session.getAttribute("recuperar") != null){ %>
            <script> alert(<%=session.getAttribute("recuperar")%>) </script>
            <%}%>
        </form>
        
        
        <a href="../index.jsp"><input type="button" name="volver" value="Volver"></a>
    </body>
</html>

