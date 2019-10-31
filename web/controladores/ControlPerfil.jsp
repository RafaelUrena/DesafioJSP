<%-- 
    Document   : ControlPerfil
    Created on : 31-oct-2019, 1:43:02
    Author     : rafa
--%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.FileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Usuario"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.io.File"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="Auxiliar.Codificar"%>
<%@page import="Modelo.ConexionEstatica"%>
<% 
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

            List items = upload.parseRequest(request);
            Usuario u = new Usuario();

            for (Object item : items) {
                FileItem uploaded = (FileItem) item;

                if (!uploaded.isFormField()) {
                    String rutaDestino = "perfiles/";
                    File fichero = new File(rutaDestino, uploaded.getName()); //El archivo se guardará en 'glassfish5/glassfish/domains/domain1/config/perfiles'.
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
                   if(key.equals("user")){
                       u.setId_usuario(Integer.parseInt(valor));
                   }
                }
            }
            

        //Comprobamos que el usuario que introducimos no exista.
        if (u != null) {
            ConexionEstatica.ModificarPerfil(u);

            response.sendRedirect("../vistas/perfilUsuario.jsp");
        } else {
            session.setAttribute("userexiste", "El usuario ya existe");
            response.sendRedirect("../vistas/perfilUsuario.jsp");
        }

%>