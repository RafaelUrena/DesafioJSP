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
            String Sentencia = "INSERT INTO USUARIO VALUES (" + 0 + ",'" + email + "', '" + nombre + "', '" + clave + "', " + edad + ", '" + apellido + "' ,'" + foto + "'," + activo + ");";

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

    public static Usuario existeUsuario(String email, String pass) {
        Usuario u = null;

        try {
            conectar();

            String sentencia = "SELECT * FROM USUARIO,ROL_ASIGNADO WHERE correo =? AND password =? AND USUARIO.ID_usuario = ROL_ASIGNADO.ID_usuario";
            PreparedStatement sentenciaPreparada = ConexionEstatica.Conex.prepareStatement(sentencia);
            sentenciaPreparada.setString(1, email);
            sentenciaPreparada.setString(2, pass);
            ConexionEstatica.Conj_Registros = sentenciaPreparada.executeQuery();

            if (ConexionEstatica.Conj_Registros.next())//Si devuelve true es que existe.
            {
                u = new Usuario(
                        Conj_Registros.getInt("ID_usuario"),
                        Conj_Registros.getString("correo"),
                        Conj_Registros.getString("nombre"),
                        Conj_Registros.getString("apellido"),
                        Conj_Registros.getInt("edad"),
                        Conj_Registros.getString("password"),
                        Conj_Registros.getInt("ID_rol"),
                        Conj_Registros.getString("foto"),
                        Conj_Registros.getBoolean("activo"));
            }

            cerrarBD();

        } catch (SQLException ex) {
            System.out.println("Error en el acceso a la BD.");
        }

        return u;
    }

    public static Usuario existeUsuario(String email) {
        Usuario u = null;

        try {
            conectar();

            String sentencia = "SELECT * FROM USUARIO,ROL_ASIGNADO WHERE correo =?";
            PreparedStatement sentenciaPreparada = ConexionEstatica.Conex.prepareStatement(sentencia);
            sentenciaPreparada.setString(1, email);
            ConexionEstatica.Conj_Registros = sentenciaPreparada.executeQuery();

            if (ConexionEstatica.Conj_Registros.next())//Si devuelve true es que existe.
            {
                u = new Usuario(
                        Conj_Registros.getInt("ID_usuario"),
                        Conj_Registros.getString("correo"),
                        Conj_Registros.getString("nombre"),
                        Conj_Registros.getString("apellido"),
                        Conj_Registros.getInt("edad"),
                        Conj_Registros.getString("password"),
                        Conj_Registros.getInt("ID_rol"),
                        Conj_Registros.getString("foto"),
                        Conj_Registros.getBoolean("activo"));
            }

            cerrarBD();

        } catch (SQLException ex) {
            System.out.println("Error en el acceso a la BD.");
        }

        return u;
    }

    public static LinkedList sacarAulas() {
        LinkedList lista = new LinkedList();
        try {
            conectar();

            String sentencia = "SELECT * FROM AULA";
            Conj_Registros = Sentencia_SQL.executeQuery(sentencia);

            while (Conj_Registros.next()) {
                Aula a = new Aula();

                a.setID_aula(Conj_Registros.getInt("ID_aula"));
                a.setNombre(Conj_Registros.getString("nombre"));
                a.setDescripcion(Conj_Registros.getString("descripcion"));

                lista.add(a);
            }

            cerrarBD();

        } catch (SQLException ex) {
            System.out.println("Error en el acceso a la BD.");
        }
        return lista;
    }

    public static LinkedList sacarFranjas() {
        LinkedList<Franjas> lista = new LinkedList();

        try {
            conectar();

            String sentencia = "SELECT * FROM FRANJA";
            Conj_Registros = Sentencia_SQL.executeQuery(sentencia);

            while (Conj_Registros.next()) {
                Franjas f = new Franjas();

                f.setId_franja(Integer.parseInt(Conj_Registros.getString("ID_franja")));
                f.setInicio(Conj_Registros.getString("inicio"));
                f.setFin(Conj_Registros.getString("fin"));
                f.setReservado(false);

                lista.add(f);
            }

            cerrarBD();

        } catch (SQLException ex) {
            System.out.println("Error en el acceso a la BD.");
        }
        return lista;
    }

    public static LinkedList sacarReservas(int aula, String fecha) {
        LinkedList<Reserva> lista = new LinkedList();

        try {
            conectar();

            String sentencia = "SELECT * FROM RESERVA WHERE ID_aula LIKE(SELECT ID_aula FROM AULA WHERE AULA.ID_aula = " + aula + ") AND RESERVA.fecha = '" + fecha + "'";
            Conj_Registros = Sentencia_SQL.executeQuery(sentencia);

            while (Conj_Registros.next()) {
                Reserva r = new Reserva();

                r.setId_reserva(Conj_Registros.getInt("ID_reserva"));
                r.setId_franja(Conj_Registros.getInt("ID_franja"));
                r.setId_aula(Conj_Registros.getInt("ID_aula"));
                r.setFecha(Conj_Registros.getString("fecha"));
                r.setId_profe(Conj_Registros.getInt("ID_profesor"));

                lista.add(r);
            }

            cerrarBD();

        } catch (SQLException ex) {
            System.out.println("Error en el acceso a la BD.");
        }
        return lista;
    }

    public static void AgregarReserva(int id_franja, int id_aula, String fecha, int id_profe) throws SQLException {

        try {
            conectar();

            String sentencia = "INSERT INTO RESERVA VALUES(" + 0 + "," + id_franja + "," + id_aula + ",'" + fecha + "'," + id_profe + ")";

            Sentencia_SQL.executeUpdate(sentencia);
            
            cerrarBD();
        } catch (SQLException ex) {
            System.out.println("Error en el acceso a la BD.");
        }

    }
    
    public static LinkedList sacarUsuarios(){
        LinkedList<Usuario> lista = new LinkedList();

        try {
            conectar();

            String sentencia = "SELECT * FROM USUARIO,ROL_ASIGNADO WHERE USUARIO.ID_usuario = ROL_ASIGNADO.ID_usuario";
            Conj_Registros = Sentencia_SQL.executeQuery(sentencia);

            while (Conj_Registros.next()) {
                Usuario u = new Usuario();
                u.setId_usuario(Conj_Registros.getInt("ID_usuario"));
                u.setEmail(Conj_Registros.getString("correo"));
                u.setNombre(Conj_Registros.getString("nombre"));
                u.setApellido(Conj_Registros.getString("apellido"));
                u.setClave(Conj_Registros.getString("password"));
                u.setEdad(Conj_Registros.getInt("edad"));
                u.setEstaActivo(Conj_Registros.getBoolean("activo"));
                u.setRol(Conj_Registros.getInt("ID_rol"));
                u.setFoto(Conj_Registros.getString("foto"));

                lista.add(u);
            }

            cerrarBD();

        } catch (SQLException ex) {
            System.out.println("Error en el acceso a la BD.");
        }
        return lista;
    }
    
    public static Reserva existeReserva(int id_f, int id_aul, String fecha, int id_pro){
        Reserva r = null;
        
        try{
            
            conectar();
            
            String Sentencia  = "SELECT * FROM RESERVA WHERE ID_franja = "+ id_f +" AND ID_aula = " + id_aul +" AND ID_profesor = "+ id_pro + " AND fecha = '"+ fecha + "'";
            
            Conj_Registros = Sentencia_SQL.executeQuery(Sentencia);
            
            if(Conj_Registros.next()){
                r = new Reserva(
                        Conj_Registros.getInt("ID_reserva"),
                        Conj_Registros.getInt("ID_profesor"),
                        Conj_Registros.getInt("ID_aula"),
                        Conj_Registros.getInt("ID_franja"),
                        Conj_Registros.getString("fecha")
                );
            }
            
            cerrarBD();
        }catch(SQLException ex){
            System.out.println("Error al acceder a la BD.");
        }
        
        return r;
    }
    
    public static void borraReserva(int id_reser){
        try {
            ConexionEstatica.conectar();
            String Sentencia = "DELETE FROM RESERVA WHERE ID_reserva=" + id_reser + ";";

            Sentencia_SQL.executeUpdate(Sentencia);

            cerrarBD();
        } catch (Exception ex) {
            System.out.println("Fallo en la BD.");
        }
    }

}
