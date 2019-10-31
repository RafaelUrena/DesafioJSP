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
        <link type="text/css" rel="stylesheet" href="css/micss.css">
        <title>Aula Virtual</title>
    </head>
    <body>
        <div id="inde">
            
                <form name="formu" action="controladores/ControlPrincipal.jsp" method="POST">
                    <div class="container">

                        <div class="box1">
                            <h2>Aula Virtual</h2>
                        </div>

                        <div class="img">
                            <img id="logo" src="imagenes/logo.png" alt="logo" class="img">
                        </div>

                        <div class="box2">
                            <input type="email" name="email" id="email" placeholder="Escriba su email">
                        </div>

                        <div class="box3">
                            <input type="password" name="pass" id="pass" placeholder="Escriba su contraseña">
                        </div>

                        <div class="box4">
                            <input type="submit" id="enviar" name="aceptar_index" value="Aceptar">
                        </div>

                        <div class="box5">
                            <a href="vistas/Registro.jsp">Registrar</a>
                        </div>

                        <div class="box6">
                            <a href="vistas/recuperar.jsp">He olvidado la contraseña</a>
                        </div>

                        <div class="box7">
                            <p>Todos los derechos reservados</p>
                        </div>

                    </div>        

                    <%
                        if (session.getAttribute("loginincorrecto") != null) {%>
                    <script> alert("<%=session.getAttribute("loginincorrecto")%>");</script>
                    <%}%>
                </form>
        </div>
    </body>
</html>
