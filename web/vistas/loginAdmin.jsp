<%-- 
    Document   : loginAdmin
    Created on : 22-oct-2019, 21:13:54
    Author     : rafa
--%>

<%@page import="Modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../css/micss2.css" type="text/css">
        <title>JSP Page</title>
    </head>
    <body>
        <% Usuario u = (Usuario) session.getAttribute("useresgistrado"); %>
        <div id="login">
            <form name="formu" action="../controladores/ControlPrincipal.jsp" method="POST">
                <div class="container">

                    <div class="box1">
                        <h2>¿Que desea hacer?</h2>
                    </div>

                    <div class="img">
                        <img id="logo" src="../imagenes/logo.png" alt="logo" class="img">
                    </div>

                    <div class="box2">
                        <% if (u.getLength() == 1) { %>
                        <nav>
                            <ul>
                                <li><a href="#">Profesor</a>
                                    <ul>
                                        <li><a href="Profesor.jsp">Ver Cuadrante</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </nav>
                        <%}
                        if (u.getLength() == 2) {%>
                        <nav>
                            <ul>
                                <li><a href="#">Administrador Aulas</a>
                                    <ul>
                                        <li><a href="Profesor.jsp">Ver Cuadrante</a></li>
                                        <li><a href="adminAulas.jsp">Gestionar Aulas</a></li>
                                        <li><a href="gestionFranjas.jsp">Gestionar Franjas</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </nav>
                        <%}
                        if (u.getLength() == 3) {%>
                        <nav>
                            <ul>
                                <li><a href="#">Administrador General</a>
                                    <ul>
                                        <li><a href="Profesor.jsp">Ver Cuadrante</a></li>
                                        <li><a href="adminAulas.jsp">Gestionar Aulas</a></li>
                                        <li><a href="gestionFranjas.jsp">Gestionar Franjas</a></li>
                                        <li><a href="verBitacora.jsp">Ver Bitácora</a></li>
                                        <li><a href="gestionUsuarios.jsp">Gestionar Usuarios</a></li>
                                        <li><a href="adminGene.jsp">Control Usuarios</a></li>
                                    </ul>
                                </li>
                        </nav>
                        <%}%>
                    </div>

                    <div class="box3">
                        <input name="boton" type="submit" value="Cerrar sesion">
                    </div>

                    <div class="box4">
                        <a href="perfilUsuario.jsp"><img src="<%=u.getFotoimgString() %>" alt="imgUser" id="imguser" ></a>
                    </div>

                    <div class="box5">
                        
                    </div>

                    <div class="box7">
                        <p>Todos los derechos reservados</p>
                    </div>

                </div>        
            </form>
        </div>
    </body>
</html>
