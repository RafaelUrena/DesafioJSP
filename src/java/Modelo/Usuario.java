/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.LinkedList;

/**
 *
 * @author rafa
 */
public class Usuario {
    private int Id_usuario;
    private String email;
    private String nombre;
    private String apellido;
    private int edad;
    private String clave;
    private LinkedList<Integer> rol;
    private byte[] foto;
    private Blob fotoBlob;
    private boolean estaActivo;
    private int ID_rol;
    
    public Usuario() {
        this.Id_usuario = 0;
        this.email = "";
        this.nombre = "";
        this.apellido = "";
        this.edad = 0;
        this.clave = "";
        this.estaActivo = false;
        this.ID_rol = 0;
        this.rol = new LinkedList();
    }

    public Usuario(int Id_usuario,int ID_rol, String email, String nombre, String apellido, int edad, String clave, byte[] foto, boolean estaActivo) {
        this.Id_usuario = Id_usuario;
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.clave = clave;
        this.foto = foto;
        this.estaActivo = estaActivo;
        this.ID_rol = ID_rol;
        this.rol = new LinkedList();
    }

    public Usuario(int Id_usuario,int ID_rol, String email, String nombre, String apellido, int edad, String clave, byte[] foto, Blob fotoBlob, boolean estaActivo) {
        this.Id_usuario = Id_usuario;
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.clave = clave;
        this.foto = foto;
        this.fotoBlob = fotoBlob;
        this.estaActivo = estaActivo;
        this.ID_rol = ID_rol;
    }
   

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getID_rol() {
        return ID_rol;
    }

    public void setID_rol(int ID_rol) {
        this.ID_rol = ID_rol;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Blob getFotoBlob() {
        return fotoBlob;
    }

    public void setFotoBlob(Blob fotoBlob) {
        this.fotoBlob = fotoBlob;
    }

    public boolean isEstaActivo() {
        return estaActivo;
    }

    public void setEstaActivo(boolean estaActivo) {
        this.estaActivo = estaActivo;
    }

    public int getId_usuario() {
        return Id_usuario;
    }

    public void setId_usuario(int Id_usuario) {
        this.Id_usuario = Id_usuario;
    }

    public int getLength(){
        return this.rol.size();
    }

    public void agregarRoles(LinkedList role){
        this.rol = role;
    }
    
    public String getFotoimgString() {
        String image =null;
        try {
            byte[] imageBytes = this.fotoBlob.getBytes(1, (int) this.fotoBlob.length());
            String encodedImage = Base64.getEncoder().encodeToString(imageBytes);
            image = "data:image/jpg;base64," + encodedImage;
            
        } catch (SQLException ex) {
        }
        return image;
    }
    
    
    
}
