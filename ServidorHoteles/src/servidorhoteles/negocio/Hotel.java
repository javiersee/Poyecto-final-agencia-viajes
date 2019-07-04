/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorhoteles.negocio;

import java.util.ArrayList;

/**
 *
 * @author Johe solis
 */
/**
 * 
 */
public class Hotel {
                                      //creacion de cada uno de los atributos  
    private String nombre;
    private ArrayList<Habitacion> habitaciones;
    private  String ciudad;
    private int estrellas;
    private ArrayList<InfoHabitacion> reservas;
    private ArrayList<InfoHabitacion> registroHabitacion;

    public Hotel() {
    }
    /**
     * metodo para la creacion de un Hoteles
     * @param nombre nombre del hotel
     * @param habitaciones  cantidad de  habitaciones 
     * @param ciudad ciudad del hotel
     * @param estrellas calificacion del hotel
     * @param reservas  habitacion reservadas
     * @param registroHabitacion   registrar habitaciones
     */
    public Hotel(String nombre, ArrayList<Habitacion> habitaciones, String ciudad, int estrellas, ArrayList<InfoHabitacion> reservas, ArrayList<InfoHabitacion> registroHabitacion) {
        this.nombre = nombre;
        this.habitaciones = habitaciones;
        this.ciudad = ciudad;
        this.estrellas = estrellas;
        this.reservas = reservas;
        this.registroHabitacion = registroHabitacion;
    }
 /**
  * creacion de getters y setters 
  * @return 
  */
    public ArrayList<InfoHabitacion> getRegistroHabitacion() {
        return registroHabitacion;
    }

    public ArrayList<InfoHabitacion> getReservas() {
        return reservas;
    }

    public void setRegistroHabitacion(ArrayList<InfoHabitacion> registroHabitacion) {
        this.registroHabitacion = registroHabitacion;
    }

    public void setReservas(ArrayList<InfoHabitacion> reservas) {
        this.reservas = reservas;
    }

   
 
    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }

    public int getEstrellas() {
        return estrellas;
    }

    

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCiudad() {
        return ciudad;
    }

  

    public ArrayList<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setHabitaciones(ArrayList<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
