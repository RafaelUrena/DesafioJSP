<%-- 
    Document   : index
    Created on : 18-oct-2019, 10:38:28
    Author     : rafa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div id="inde">
            <fieldset>
                <form name="formu" action="controladores/" method="POST">
                    <label for="email">Usuario </label>
                    <input type="email" name="email" id="email" placeholder="Escriba su email"><br>

                    <label for="pass">Contraseña </label>
                    <input type="password" name="pass" id="pass" placeholder="Escriba su contraseña"><br>

                    <a href="vistas/Registro.jsp">Registrar</a>
                    <!--<a href="vistas/recuperar.jsp">He olvidado la contraseña</a><br>-->

                    <input type="submit" name="aceptar_index" value="Aceptar">

                </form>
            </fieldset>
        </div>
    </body>
</html>
