/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author rafa
 */
public class Aula {
    private int ID_aula;
    private String nombre;
    private String descripcion;

    public Aula() {
    }

    public Aula(int ID_aula, String nombre, String descripcion) {
        this.ID_aula = ID_aula;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
    

    public int getID_aula() {
        return ID_aula;
    }

    public void setID_aula(int ID_aula) {
        this.ID_aula = ID_aula;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Aula{" + "ID_aula=" + ID_aula + ", nombre=" + nombre + ", descripcion=" + descripcion + '}';
    }
    
    
    
}
