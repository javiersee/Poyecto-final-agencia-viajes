/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorhoteles.negocio;

/**
 *
 * @author Johe solis
 */
public class Habitacion {
    private int numeroHabitacion;
    private String tipo;
    private String descripcion;
    private int costo;
    private String Hotel;

    public Habitacion() {
    }

    public Habitacion(int numeroHabitacion, String tipo, String descripcion, int precio, String estado) {
        this.numeroHabitacion = numeroHabitacion;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.costo = precio;
    
    }

    public String getHotel() {
        return Hotel;
    }

    public void setHotel(String Hotel) {
        this.Hotel = Hotel;
    }

    

    

    public String getDescripcion() {
        return descripcion;
    }

    public int getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public int getCosto() {
        return costo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setNumeroHabitacion(int numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public void setCosto(int precio) {
        this.costo = precio;
    }

    public void setTipo(String tipoHabitacion) {
        this.tipo = tipoHabitacion;
    }

    
    
    
    
}
