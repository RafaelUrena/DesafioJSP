<%-- 
    Document   : Registro
    Created on : 18-oct-2019, 10:38:11
    Author     : rafa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="../css/micss1.css">
        <script src="../js/mijs.js"></script>
        <title>Registro</title>
    </head>
    <body>
        <div id="regis">
                <form name="formulario" action="../controladores/ControlImagen.jsp" enctype="multipart/form-data" method="POST">
                    <div class="container">
                        
                        <div class="box1">
                            <h2>Registro</h2>
                        </div>
                        
                        <div class="img">
                            <img id="logo" src="../imagenes/logo.png" alt="logo" class="img">
                        </div>
                        
                        <div class="box2">
                            <input type="email" name="usuario" id="usuario" required="" placeholder="Escriba su email">
                        </div>
                        <div class="box3">
                            <input type="text" name="nombre" id="nombre" required="" placeholder="Escriba su nombre">
                        </div>
                        <div class="box4">
                            <input type="text" name="apellido" id="apellido" required="" placeholder="Escriba su apellido">
                        </div>
                        <div class="box5">
                            <input type="number" name="edad" placeholder="Escriba su edad" required="" id="edad">
                        </div>
                        <div class="box6">
                            <input type="password" name="clave" id="clave" required="" placeholder="Escriba su contraseña">
                        </div>
                        <div class="box7">
                            <input type="password" name="rclave" id="rclave" required="" placeholder="Repita su contraseña" onblur="comprobarContrasena()">
                        </div>
                        <div class="box8">
                            <input type="file" name="foto" id="foto" accept="image/*">
                        </div>
                        <div class="box9">
                            imagen
                        </div>
                        <div class="box10">
                            captcha
                        </div>
                        <div class="box11">
                             <input type="submit" name="subir" id="acept" value="Aceptar">
                        </div>
                        <a href="../index.jsp"><input name="volver" type="button" id="volver" value="Volver" ></a>
                        <div class="box12">
                            <p>Todos los derechos reservados</p>
                        </div>
                    </div>
                    
                    <%
                        if(session.getAttribute("userexiste") != null){%>
                        <script> alert("<%=session.getAttribute("userexiste")%>");</script>
                        <%}%>

                </form> 
        </div>
    </body>
</html>
