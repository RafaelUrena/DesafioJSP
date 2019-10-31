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
public class Reserva {
    private int Id_reserva;
    private int Id_profe;
    private int Id_aula;
    private int Id_franja;
    private String fecha;

    public Reserva() {
    }

    public Reserva(int Id_reserva, int Id_profe, int Id_aula, int Id_franja, String fecha) {
        this.Id_reserva = Id_reserva;
        this.Id_profe = Id_profe;
        this.Id_aula = Id_aula;
        this.Id_franja = Id_franja;
        this.fecha = fecha;
    }
    
    

    public int getId_reserva() {
        return Id_reserva;
    }

    public void setId_reserva(int Id_reserva) {
        this.Id_reserva = Id_reserva;
    }

    public int getId_profe() {
        return Id_profe;
    }

    public void setId_profe(int Id_profe) {
        this.Id_profe = Id_profe;
    }

    public int getId_aula() {
        return Id_aula;
    }

    public void setId_aula(int Id_aula) {
        this.Id_aula = Id_aula;
    }

    public int getId_franja() {
        return Id_franja;
    }

    public void setId_franja(int Id_franja) {
        this.Id_franja = Id_franja;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Reserva{" + "Id_reserva=" + Id_reserva + ", Id_profe=" + Id_profe + ", Id_aula=" + Id_aula + ", Id_franja=" + Id_franja + ", fecha=" + fecha + '}';
    }

    
    
    
}
