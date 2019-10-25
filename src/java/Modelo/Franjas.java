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
public class Franjas {
    private int id_franja;
    private String inicio;
    private String fin;
    private boolean reservado;

    public Franjas() {
    }

    public int getId_franja() {
        return id_franja;
    }

    public void setId_franja(int id_franja) {
        this.id_franja = id_franja;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public boolean isReservado() {
        return reservado;
    }

    public void setReservado(boolean reservado) {
        this.reservado = reservado;
    }
    
    

    @Override
    public String toString() {
        return "Franjas{" + "id_franja=" + id_franja + ", inicio=" + inicio + ", fin=" + fin + '}';
    }
    
    
}
