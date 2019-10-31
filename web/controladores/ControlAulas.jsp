<%-- 
    Document   : ControlAulas
    Created on : 24-oct-2019, 19:39:22
    Author     : rafa
--%>

<%@page import="Modelo.ConexionEstatica"%>
<% 

    if(request.getParameter("boton")!= null){
        if(request.getParameter("boton").equals("Eliminar")){
            int id = Integer.parseInt(request.getParameter("idaula"));
            ConexionEstatica.borrarAula(id);
            
            response.sendRedirect("../vistas/adminAulas.jsp");
        }
        
        if(request.getParameter("boton").equals("Modificar")){
            int id = Integer.parseInt(request.getParameter("idaula"));
            String nombre = request.getParameter("nombre");
            String descri = request.getParameter("descri");
            
            ConexionEstatica.ModificarAula(id, nombre, descri);
            
            response.sendRedirect("../vistas/adminAulas.jsp");
        }
        
        if(request.getParameter("boton").equals("Add")){
            String nombre = request.getParameter("nuev");
            String descri = request.getParameter("nuevdescr");
            
            ConexionEstatica.InsertarAula(nombre, descri);
            
            response.sendRedirect("../vistas/adminAulas.jsp");
        }
    }

%>
