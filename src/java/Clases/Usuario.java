/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author rafa
 */
public class Usuario {
    private String email;
    private String nombre;
    private String apellido;
    private int edad;
    private String clave;
    private int rol;
    private String foto;
    private boolean estaActivo;
    
    public Usuario() {
        this.email = "";
        this.nombre = "";
        this.apellido = "";
        this.edad = 0;
        this.clave = "";
        this.rol = 0;
        this.foto = "";
        this.estaActivo = false;
    }
    
    public Usuario(String email, String nombre, String apellido, int edad, String clave, int rol, String foto, boolean estaActivo){
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.clave = clave;
        this.rol = rol;
        this.foto = foto;
        this.estaActivo = estaActivo;
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

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public boolean isEstaActivo() {
        return estaActivo;
    }

    public void setEstaActivo(boolean estaActivo) {
        this.estaActivo = estaActivo;
    }

    @Override
    public String toString() {
        return "Usuario{" + "email=" + email + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + ", clave=" + clave + ", rol=" + rol + ", foto=" + foto + ", estaActivo=" + estaActivo + '}';
    }

    
    
    
    
}
