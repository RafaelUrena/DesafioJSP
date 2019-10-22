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
        <script src="../js/mijs.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <div id="regis">
            <fieldset>
                <form name="formulario" action="../controladores/ControlPrincipal.jsp" method="POST">
                    <label for="usuario">Email </label>
                    <input type="email" name="usuario" id="usuario" required="" placeholder="Escriba su email"><br>

                    <label for="nombre">Nombre </label>
                    <input type="text" name="nombre" id="nombre" required="" placeholder="Escriba su nombre"><br>
                    
                    <label for="apellido">Apellido </label>
                    <input type="text" name="apellido" id="apellido" required="" placeholder="Escriba su apellido"><br>

                    <label for="edad">Edad </label>
                    <input type="number" name="edad" value="0" required="" id="edad"><br>

                    <label for="clave">Clave </label>
                    <input type="password" name="clave" id="clave" required="" placeholder="Escriba su contraseña"><br>

                    <label for="rclave">Repite la clave </label>
                    <input type="password" name="rclave" id="rclave" required="" placeholder="Repita su contraseña" onblur="comprobarContrasena()"><br>
                    
                    <label for="foto">Suba una foto</label>
                    <input type="file" name="foto" id="foto" accept="image/*"><br>
                    
                    <input type="submit" name="subir" id="acept" value="Aceptar">
                    
                    <%
                        if(session.getAttribute("userexiste") != null){%>
                        <script> alert("<%=session.getAttribute("userexiste")%>");</script>
                        <%}%>

                </form>
            </fieldset>
        </div>
    </body>
</html>
