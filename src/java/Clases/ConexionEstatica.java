package Clases;

import java.sql.*;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConexionEstatica {

    //********************* Atributos *************************
    private static java.sql.Connection Conex;
    //Atributo a través del cual hacemos la conexión física.
    private static java.sql.Statement Sentencia_SQL;
    //Atributo que nos permite ejecutar una sentencia SQL
    private static java.sql.ResultSet Conj_Registros;

    public static void conectar() {
        try {
            //Cargar el driver/controlador
            //String controlador = "com.mysql.jdbc.Driver";
            //String controlador = "oracle.jdbc.driver.OracleDriver";
            //String controlador = "sun.jdbc.odbc.JdbcOdbcDriver"; 
            String controlador = "org.mariadb.jdbc.Driver"; // MariaDB la version libre de MySQL (requiere incluir la librería jar correspondiente).
            //Class.forName(controlador).newInstance();
            Class.forName(controlador);

            //String URL_BD = "jdbc:mysql://localhost/" + Constantes.BBDD;
            String URL_BD = "jdbc:mariadb://localhost:3306/" + Constantes.BBDD;
            //String URL_BD = "jdbc:oracle:oci:@REPASO";
            //String URL_BD = "jdbc:oracle:oci:@REPASO";
            //String URL_BD = "jdbc:odbc:REPASO";

            //Realizamos la conexión a una BD con un usuario y una clave.
            Conex = java.sql.DriverManager.getConnection(URL_BD, Constantes.usuario, Constantes.password);
            Sentencia_SQL = Conex.createStatement();
            System.out.println("Conexion realizada con éxito");
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }

    }

    public static void cerrarBD() {
        try {
            // resultado.close();
            Conex.close();
            System.out.println("Desconectado de la Base de Datos"); // Opcional para seguridad
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error de Desconexion", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void AgregarUsuario(String email, String nombre, String clave, int edad, String apellido, String foto, boolean activo) throws SQLException {
        try {
            conectar();
            
            //Agregamos al usuario con los parametros que recibimos de Registro.jsp.
            
            String Sentencia = "INSERT INTO USUARIO VALUES (" + 0 + ",'" + email + "', '" + nombre + "', '" + clave + "', " + edad + ", '" + apellido + "' ,'" + foto +"'," + activo + ");";

            Sentencia_SQL.executeUpdate(Sentencia);
            
            //Seleccionamos la ID del usuario para agregarla en el siguiente INSERT.
            
            String SentenciaS = "SELECT ID_usuario FROM USUARIO WHERE correo ='" + email + "'";

            Conj_Registros = Sentencia_SQL.executeQuery(SentenciaS);
            
            int ID_user = 0;

            while (Conj_Registros.next()) {

                ID_user = Conj_Registros.getInt("ID_usuario");
            }

            Sentencia = "INSERT INTO ROL_ASIGNADO VALUES (" + 0 + "," + 1 + ", " + ID_user + ")";

            Sentencia_SQL.executeUpdate(Sentencia);

            cerrarBD();
        } catch (Exception ex) {
            System.out.println("Fallo al ingresar usuario");
        }

    }
    
    public static Usuario existeUsuario(String email, String pass){
        Usuario u = null;
        
        try{
            conectar();
            
            String sentencia = "SELECT * FROM USUARIO,ROL_ASIGNADO WHERE correo =? AND password =?";
            PreparedStatement sentenciaPreparada = ConexionEstatica.Conex.prepareStatement(sentencia);
            sentenciaPreparada.setString(1, email);
            sentenciaPreparada.setString(2, pass);
            ConexionEstatica.Conj_Registros = sentenciaPreparada.executeQuery();
            
            if (ConexionEstatica.Conj_Registros.next())//Si devuelve true es que existe.
            {
                u = new Usuario(Conj_Registros.getString("correo"),
                                Conj_Registros.getString("nombre"),
                                Conj_Registros.getString("apellido"),
                                Conj_Registros.getInt("edad"),
                                Conj_Registros.getString("password"),
                                Conj_Registros.getInt("ID_rol"),
                                Conj_Registros.getString("foto"),
                                Conj_Registros.getBoolean("activo"));
            }
            
            cerrarBD();
            
        } catch(SQLException ex){
            System.out.println("Error en el acceso a la BD.");
        }
        
        return u;
    }
    
    public static Usuario existeUsuario(String email){
        Usuario u = null;
        
        try{
            conectar();
            
            String sentencia = "SELECT * FROM USUARIO,ROL_ASIGNADO WHERE correo =?";
            PreparedStatement sentenciaPreparada = ConexionEstatica.Conex.prepareStatement(sentencia);
            sentenciaPreparada.setString(1, email);
            ConexionEstatica.Conj_Registros = sentenciaPreparada.executeQuery();
            
            if (ConexionEstatica.Conj_Registros.next())//Si devuelve true es que existe.
            {
                u = new Usuario(Conj_Registros.getString("correo"),
                                Conj_Registros.getString("nombre"),
                                Conj_Registros.getString("apellido"),
                                Conj_Registros.getInt("edad"),
                                Conj_Registros.getString("password"),
                                Conj_Registros.getInt("ID_rol"),
                                Conj_Registros.getString("foto"),
                                Conj_Registros.getBoolean("activo"));
            }
            
            cerrarBD();
            
        } catch(SQLException ex){
            System.out.println("Error en el acceso a la BD.");
        }
        
        return u;
    }

    public static LinkedList sacarAulas(){
        LinkedList lista = new LinkedList();
        try{
            conectar();
            
            String sentencia = "SELECT * FROM AULA";
            Conj_Registros = Sentencia_SQL.executeQuery(sentencia);
            
            while(Conj_Registros.next()){
                Aula a = new Aula();
                
                a.setID_aula(Conj_Registros.getInt("ID_aula"));
                a.setNombre(Conj_Registros.getString("nombre"));
                a.setDescripcion(Conj_Registros.getString("descripcion"));
                
                lista.add(a);
            }
            
            
            cerrarBD();
            
        }catch(SQLException ex){
            System.out.println("Error en el acceso a la BD.");
        }
        return lista;
    }

}
