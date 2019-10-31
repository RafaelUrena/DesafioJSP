package Modelo;

import Auxiliar.Constantes;
import java.sql.*;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConexionEstatica {

    //********************* Atributos *************************//
    private static java.sql.Connection Conex;
    //Atributo a través del cual hacemos la conexión física.
    private static java.sql.Statement Sentencia_SQL;
    //Atributo que nos permite ejecutar una sentencia SQL
    private static java.sql.ResultSet Conj_Registros;

    //********************* Conectar BD*************************//
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

    //********************* Cerrar BD *************************//
    public static void cerrarBD() {
        try {
            // resultado.close();
            Conex.close();
            System.out.println("Desconectado de la Base de Datos"); // Opcional para seguridad
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error de Desconexion", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método para agregar usuarios a la BD.
     *
     * @param email
     * @param nombre
     * @param clave
     * @param edad
     * @param apellido
     * @param foto
     * @param activo
     * @throws SQLException
     */
    public static void AgregarUsuario(Usuario u) throws SQLException {
        conectar();

        //Agregamos al usuario con los parametros que recibimos de Registro.jsp.
        String sql = "INSERT INTO USUARIO (ID_usuario,correo,nombre,password,edad,apellido,foto,activo) VALUES(NULL,?,?,?,?,?,?,?);";
        PreparedStatement ps = null;
        try {
            ps = ConexionEstatica.Conex.prepareStatement(sql);
            ps.setString(1, u.getEmail());
            ps.setString(2, u.getNombre());
            ps.setString(3, u.getClave());
            ps.setInt(4, u.getEdad());
            ps.setString(5, u.getApellido());
            ps.setBytes(6, u.getFoto());
            ps.setBoolean(7, u.isEstaActivo());
            
            ps.executeUpdate();

            String sql2 = "SELECT ID_usuario FROM USUARIO WHERE correo =?";
            
            ps = ConexionEstatica.Conex.prepareStatement(sql2);
            ps.setString(1, u.getEmail());
            
            Conj_Registros = ps.executeQuery();
            
            if(Conj_Registros.next()){
                u.setId_usuario(Conj_Registros.getInt("ID_usuario"));
            }

            String sql3 = "INSERT INTO ROL_ASIGNADO (ID_rol_asig,ID_rol,ID_usuario) VALUES (NULL,?,?)";
            
            ps = ConexionEstatica.Conex.prepareStatement(sql3);
            ps.setInt(1, 1);
            ps.setInt(2, u.getId_usuario());
            
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error de SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error general: " + ex.getMessage());
        } finally {
            try {
                ps.close();
                ConexionEstatica.cerrarBD();
            } catch (Exception ex) {
                System.out.println("Error general: " + ex.getMessage());
            }
        }
    }

    /**
     * Método para comprobar si existe un usuario en la BD.
     *
     * @param email
     * @param pass
     * @return Usuario u.
     */
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
                        Conj_Registros.getInt("ID_rol"),
                        Conj_Registros.getString("correo"),
                        Conj_Registros.getString("nombre"),
                        Conj_Registros.getString("apellido"),
                        Conj_Registros.getInt("edad"),
                        Conj_Registros.getString("password"),
                        Conj_Registros.getBytes("foto"),
                        Conj_Registros.getBlob("foto"),
                        Conj_Registros.getBoolean("activo"));
            }

            if (u != null) {
                rellenarRoles(u);
            }

            cerrarBD();

        } catch (SQLException ex) {
            System.out.println("Error en el acceso a la BD.");
        }

        return u;
    }

    /**
     * Método para comprobar si existe un usuario en la BD.
     *
     * @param email
     * @return Usuario u.
     */
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
                        Conj_Registros.getInt("ID_rol"),
                        Conj_Registros.getString("correo"),
                        Conj_Registros.getString("nombre"),
                        Conj_Registros.getString("apellido"),
                        Conj_Registros.getInt("edad"),
                        Conj_Registros.getString("password"),
                        Conj_Registros.getBytes("foto"),
                        Conj_Registros.getBoolean("activo"));
            }

            cerrarBD();

        } catch (SQLException ex) {
            System.out.println("Error en el acceso a la BD.");
        }

        return u;
    }

    /**
     * Método para sacar en una LinkedList las aulas de la BD.
     *
     * @return Lista de aulas.
     */
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

    /**
     * Método para sacar 1 aula de la BD.
     *
     * @param nombre
     * @return Aula a.
     */
    public static Aula sacarAula(int nombre) {
        Aula a = null;
        try {
            conectar();

            String sentencia = "SELECT * FROM AULA WHERE AULA.nombre='" + nombre + "'";
            Conj_Registros = Sentencia_SQL.executeQuery(sentencia);

            if (Conj_Registros.next()) {

                a = new Aula(
                        Conj_Registros.getInt("ID_aula"),
                        Conj_Registros.getString("nombre"),
                        Conj_Registros.getString("descripcion")
                );

            }

            cerrarBD();

        } catch (SQLException ex) {
            System.out.println("Error en el acceso a la BD.");
        }
        return a;
    }

    /**
     * Método para sacar franjas de BD.
     *
     * @return Lista franjas.
     */
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

    /**
     * Método para sacar reservas de la BD.
     *
     * @param nombre
     * @param fecha
     * @return Lista reservas.
     */
    public static LinkedList sacarReservas(int nombre, String fecha) {
        LinkedList<Reserva> lista = new LinkedList();

        try {
            conectar();

            String sentencia = "SELECT * FROM RESERVA WHERE ID_aula LIKE(SELECT ID_aula FROM AULA WHERE AULA.nombre = " + nombre + ") AND RESERVA.fecha = '" + fecha + "'";
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

    /**
     * Método para crear una reserva en la BD.
     *
     * @param id_franja
     * @param id_aula
     * @param fecha
     * @param id_profe
     * @throws SQLException
     */
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

    /**
     * Método para sacar una lista de Usuarios.
     *
     * @return Lista usuarios.
     */
    public static LinkedList sacarUsuarios() {
        LinkedList<Usuario> lista = new LinkedList();

        conectar();

        String sql = "SELECT ID_usuario,correo,nombre,edad,apellido,activo FROM USUARIO WHERE ID_usuario = ID_usuario;";
        PreparedStatement ps = null;

        try {
            ps = ConexionEstatica.Conex.prepareStatement(sql);
            Conj_Registros = ps.executeQuery();

            while (Conj_Registros.next()) {
                Usuario u = new Usuario();

                u.setId_usuario(Conj_Registros.getInt("ID_usuario"));
                u.setEmail(Conj_Registros.getString("correo"));
                u.setNombre(Conj_Registros.getString("nombre"));
                u.setEdad(Conj_Registros.getInt("edad"));
                u.setApellido(Conj_Registros.getString("apellido"));
                u.setEstaActivo(Conj_Registros.getBoolean("activo"));

                lista.add(u);
            }
        } catch (SQLException ex) {
            System.out.println("Error de SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error general: " + ex.getMessage());
        } finally {
            try {
                ps.close();
                ConexionEstatica.cerrarBD();
            } catch (Exception ex) {
                System.out.println("Error general: " + ex.getMessage());
            }
        }
        return lista;
    }

    /**
     * Método para comprobar reserva BD.
     *
     * @param id_f
     * @param id_aul
     * @param fecha
     * @param id_pro
     * @return
     */
    public static Reserva existeReserva(int id_f, int id_aul, String fecha, int id_pro) {
        Reserva r = null;

        try {

            conectar();

            String Sentencia = "SELECT * FROM RESERVA WHERE ID_franja = " + id_f + " AND ID_aula = " + id_aul + " AND ID_profesor = " + id_pro + " AND fecha = '" + fecha + "'";

            Conj_Registros = Sentencia_SQL.executeQuery(Sentencia);

            if (Conj_Registros.next()) {
                r = new Reserva(
                        Conj_Registros.getInt("ID_reserva"),
                        Conj_Registros.getInt("ID_profesor"),
                        Conj_Registros.getInt("ID_aula"),
                        Conj_Registros.getInt("ID_franja"),
                        Conj_Registros.getString("fecha")
                );
            }

            cerrarBD();
        } catch (SQLException ex) {
            System.out.println("Error al acceder a la BD.");
        }

        return r;
    }

    /**
     * Método para borrar reservas en BD.
     *
     * @param id_reser
     */
    public static void borraReserva(int id_reser) {
        try {
            ConexionEstatica.conectar();
            String Sentencia = "DELETE FROM RESERVA WHERE ID_reserva=" + id_reser + ";";

            Sentencia_SQL.executeUpdate(Sentencia);

            cerrarBD();
        } catch (Exception ex) {
            System.out.println("Fallo en la BD.");
        }
    }

    /**
     * Método para rellenar los roles del usuario.
     *
     * @param u
     */
    private static void rellenarRoles(Usuario u) {
        LinkedList<Integer> roles = new LinkedList();
        try {

            String Sentencia = "SELECT * FROM ROL_ASIGNADO WHERE ID_usuario =" + u.getId_usuario();

            Conj_Registros = Sentencia_SQL.executeQuery(Sentencia);

            while (Conj_Registros.next()) {
                u.setID_rol(Conj_Registros.getInt("ID_rol"));

                roles.add(u.getID_rol());
            }

            u.agregarRoles(roles);

            cerrarBD();

        } catch (SQLException ex) {
            System.out.println("Fallo en la BD.");
        }
    }

    public static void borrarAula(int id) {

        conectar();

        String sql = "DELETE FROM AULA WHERE AULA.ID_aula =?";
        PreparedStatement ps = null;

        try {
            ps = ConexionEstatica.Conex.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error de SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error general: " + ex.getMessage());
        } finally {
            try {
                ps.close();
                ConexionEstatica.cerrarBD();
            } catch (Exception ex) {
                System.out.println("Error general: " + ex.getMessage());
            }
        }
    }

    public static void ModificarAula(int id, String nom, String desc) {

        conectar();

        String sql = "UPDATE AULA SET nombre =?, descripcion =? WHERE ID_aula =?";
        PreparedStatement ps = null;

        try {
            ps = ConexionEstatica.Conex.prepareStatement(sql);
            ps.setString(1, nom);
            ps.setString(2, desc);
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error de SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error general: " + ex.getMessage());
        } finally {
            try {
                ps.close();
                ConexionEstatica.cerrarBD();
            } catch (Exception ex) {
                System.out.println("Error general: " + ex.getMessage());
            }
        }
    }

    public static void InsertarAula(String nom, String desc) {

        conectar();

        String sql = "INSERT INTO AULA (ID_aula, nombre, descripcion) VALUES (NULL,?,?);";
        PreparedStatement ps = null;

        try {
            ps = ConexionEstatica.Conex.prepareStatement(sql);
            ps.setString(1, nom);
            ps.setString(2, desc);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error de SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error general: " + ex.getMessage());
        } finally {
            try {
                ps.close();
                ConexionEstatica.cerrarBD();
            } catch (Exception ex) {
                System.out.println("Error general: " + ex.getMessage());
            }
        }
    }

    public static void ModificarFranjas(int idfran, String hini, String hfin) {
        conectar();

        String sql = "UPDATE FRANJA SET inicio =?, fin =? WHERE ID_franja =?";
        PreparedStatement ps = null;

        try {
            ps = ConexionEstatica.Conex.prepareStatement(sql);
            ps.setString(1, hini);
            ps.setString(2, hfin);
            ps.setInt(3, idfran);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error de SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error general: " + ex.getMessage());
        } finally {
            try {
                ps.close();
                ConexionEstatica.cerrarBD();
            } catch (Exception ex) {
                System.out.println("Error general: " + ex.getMessage());
            }
        }
    }

    public static void borrarFranja(int id) {

        conectar();

        String sql = "DELETE FROM FRANJA WHERE FRANJA.ID_franja =?";
        PreparedStatement ps = null;

        try {
            ps = ConexionEstatica.Conex.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error de SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error general: " + ex.getMessage());
        } finally {
            try {
                ps.close();
                ConexionEstatica.cerrarBD();
            } catch (Exception ex) {
                System.out.println("Error general: " + ex.getMessage());
            }
        }
    }

    public static void InsertarFranjas(String hi, String hf) {

        conectar();

        String sql = "INSERT INTO FRANJA (ID_franja, inicio, fin) VALUES (NULL,?,?);";
        PreparedStatement ps = null;

        try {
            ps = ConexionEstatica.Conex.prepareStatement(sql);
            ps.setString(1, hi);
            ps.setString(2, hf);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error de SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error general: " + ex.getMessage());
        } finally {
            try {
                ps.close();
                ConexionEstatica.cerrarBD();
            } catch (Exception ex) {
                System.out.println("Error general: " + ex.getMessage());
            }
        }
    }

    public static void borrarUsuario(int id) {

        conectar();

        String sql = "DELETE FROM USUARIO WHERE USUARIO.ID_usuario =?";
        PreparedStatement ps = null;

        try {
            ps = ConexionEstatica.Conex.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error de SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error general: " + ex.getMessage());
        } finally {
            try {
                ps.close();
                ConexionEstatica.cerrarBD();
            } catch (Exception ex) {
                System.out.println("Error general: " + ex.getMessage());
            }
        }
    }

    public static void ModificarUsuarios(int iduser, String nom, String apel, int edad) {
        conectar();

        String sql = "UPDATE USUARIO SET nombre =?, apellido =?, edad =? WHERE ID_usuario =?";
        PreparedStatement ps = null;

        try {
            ps = ConexionEstatica.Conex.prepareStatement(sql);
            ps.setString(1, nom);
            ps.setString(2, apel);
            ps.setInt(3, edad);
            ps.setInt(4, iduser);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error de SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error general: " + ex.getMessage());
        } finally {
            try {
                ps.close();
                ConexionEstatica.cerrarBD();
            } catch (Exception ex) {
                System.out.println("Error general: " + ex.getMessage());
            }
        }
    }
    
    public static void ValidarUsuario(int iduser, boolean validar) {
        conectar();

        String sql = "UPDATE USUARIO SET activo =? WHERE ID_usuario =?";
        PreparedStatement ps = null;

        try {
            ps = ConexionEstatica.Conex.prepareStatement(sql);
            ps.setBoolean(1, validar);
            ps.setInt(2, iduser);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error de SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error general: " + ex.getMessage());
        } finally {
            try {
                ps.close();
                ConexionEstatica.cerrarBD();
            } catch (Exception ex) {
                System.out.println("Error general: " + ex.getMessage());
            }
        }
    }
    
    public static void ModificarUsuarios(int iduser, String nom, String apel, int edad, String pass, Blob foto, byte[] fot) {
        conectar();

        String sql = "UPDATE USUARIO SET nombre =?, apellido =?, edad =? WHERE ID_usuario =?";
        PreparedStatement ps = null;

        try {
            ps = ConexionEstatica.Conex.prepareStatement(sql);
            ps.setString(1, nom);
            ps.setString(2, apel);
            ps.setInt(3, edad);
            ps.setInt(4, iduser);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error de SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error general: " + ex.getMessage());
        } finally {
            try {
                ps.close();
                ConexionEstatica.cerrarBD();
            } catch (Exception ex) {
                System.out.println("Error general: " + ex.getMessage());
            }
        }
    }
    
    public static void ModificarPass(String mail,String pass) {
        conectar();

        String sql = "UPDATE USUARIO SET password =?  WHERE correo =?";
        PreparedStatement ps = null;

        try {
            ps = ConexionEstatica.Conex.prepareStatement(sql);
            ps.setString(1, pass);
            ps.setString(2, mail);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error de SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error general: " + ex.getMessage());
        } finally {
            try {
                ps.close();
                ConexionEstatica.cerrarBD();
            } catch (Exception ex) {
                System.out.println("Error general: " + ex.getMessage());
            }
        }
    }
    
    public static void ModificarPerfil(Usuario u){
        conectar();

        String sql = "UPDATE USUARIO SET nombre =?, apellido =?, edad =?, password =?, foto =?  WHERE ID_usuario =?";
        PreparedStatement ps = null;

        try {
            ps = ConexionEstatica.Conex.prepareStatement(sql);
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellido());
            ps.setInt(3, u.getEdad());
            ps.setString(4, u.getClave());
            ps.setBytes(5, u.getFoto());
            ps.setInt(6, u.getId_usuario());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error de SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error general: " + ex.getMessage());
        } finally {
            try {
                ps.close();
                ConexionEstatica.cerrarBD();
            } catch (Exception ex) {
                System.out.println("Error general: " + ex.getMessage());
            }
        }
    }
}
