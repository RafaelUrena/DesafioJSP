<%-- 
    Document   : verBitacora
    Created on : 25-oct-2019, 9:10:54
    Author     : rafa
--%>

<%@page import="Auxiliar.Bitacora"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="../css/micss2.css">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Bitácora</h2>
       
        
        <textarea><%out.print(Bitacora.readFile("Bitacora.txt")); %></textarea>
        
        <a href="loginAdmin.jsp"><input name="volver" type="button" id="volver" value="Volver" ></a>
    </body>
</html>
