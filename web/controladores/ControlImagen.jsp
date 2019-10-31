<%-- 
    Document   : ControlImangen
    Created on : 30-oct-2019, 11:51:29
    Author     : rafa
--%>

<%@page import="Auxiliar.Codificar"%>
<%@page import="Modelo.ConexionEstatica"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="Modelo.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.FileItemFactory"%>
<% 
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

            List items = upload.parseRequest(request);
            Usuario u = new Usuario();
            Usuario us = new Usuario();

            for (Object item : items) {
                FileItem uploaded = (FileItem) item;

                if (!uploaded.isFormField()) {
                    String rutaDestino = "perfiles/";
                    File fichero = null; //El archivo se guardará en 'glassfish5/glassfish/domains/domain1/config/perfiles'.
                    
                    String borrar = uploaded.getName();
                    
                    if(uploaded.getName().isEmpty()){
                        fichero = new File(rutaDestino,"puzzle2.jpg");
                    } else {
                        fichero = new File(rutaDestino, uploaded.getName());
                    }
                    
                     uploaded.write(fichero);
              
                   
                    byte[] icono = new byte[(int) fichero.length()];
                    InputStream input = new FileInputStream(fichero);
                    input.read(icono);
                    u.setFoto(icono);
            
                    out.println("Archivo '" + uploaded.getName() + "' subido correctamente.");
                } else {
                    String key = uploaded.getFieldName();
                    String valor = uploaded.getString();
                    out.println("Valor recuperado con uploaded: " + key + " = " + valor + "</br>");
                   if (key.equals("nombre")){
                       u.setNombre(valor);
                   }
                   if (key.equals("edad")){
                       u.setEdad(Integer.parseInt(valor));
                   }
                   if(key.equals("apellido")){
                       u.setApellido(valor);
                   }
                   if(key.equals("clave")){
                       u.setClave(Codificar.codifica(valor));
                   }
                   if(key.equals("usuario")){
                       u.setEmail(valor);
                   }
                }
            }
            

       us = ConexionEstatica.existeUsuario(u.getEmail());
        //Comprobamos que el usuario que introducimos no exista.
        if (us != null) {
            
            session.setAttribute("userexiste", "El usuario ya existe");
            response.sendRedirect("../vistas/Registro.jsp");
        } else {
            ConexionEstatica.AgregarUsuario(u);

            response.sendRedirect("../index.jsp");
        }

%>
