<%-- 
    Document   : ControlFranjas
    Created on : 29-oct-2019, 13:37:51
    Author     : rafa
--%>

<%@page import="Modelo.ConexionEstatica"%>
<% 

    if(request.getParameter("boton")!= null){
        
        if(request.getParameter("boton").equals("Eliminar")){
            int id = Integer.parseInt(request.getParameter("franja"));
            
            ConexionEstatica.borrarFranja(id);
            
            response.sendRedirect("../vistas/gestionFranjas.jsp");
        }
        
        if(request.getParameter("boton").equals("Modificar")){
            int id = Integer.parseInt(request.getParameter("franja"));
            String ini = request.getParameter("hinicio");
            String fin = request.getParameter("hfin");
            
            ConexionEstatica.ModificarFranjas(id, ini, fin);
            
            response.sendRedirect("../vistas/gestionFranjas.jsp");
        }
        
        if(request.getParameter("boton").equals("Add")){
            String ini = request.getParameter("hinicio");
            String fin = request.getParameter("hfin");
            
            ConexionEstatica.InsertarFranjas(ini, fin);
            
            response.sendRedirect("../vistas/gestionFranjas.jsp");
        }
    }

%>