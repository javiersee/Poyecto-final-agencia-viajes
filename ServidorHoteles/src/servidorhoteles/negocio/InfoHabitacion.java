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

public class InfoHabitacion {
    private String fechaLlegada;
    private String fechaSalida;
    private int habitacion;
 
    /**
     * un constructor de habitacion
     */
    public InfoHabitacion() {
    }
    /**
     * constructor parametrizado
     * @param fechaLlegada fecha  de llegada para la ocupacion  de la habitacion
     * @param fechaSalida  fecha  de salida  para la desocupar  de la habitacion
     * @param habitacion  la habitacion 
     */
    public InfoHabitacion(String fechaLlegada, String fechaSalida, int habitacion) {
        this.fechaLlegada = fechaLlegada;
        this.fechaSalida = fechaSalida;
        this.habitacion = habitacion;
    }

    /**
     * creacion de  getters y setters
     * @return 
     */
    public String getFechaLlegada() {
        return fechaLlegada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public int getHabitacion() {
        return habitacion;
    }

    public void setFechaLlegada(String fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public void setHabitacion(int habitacion) {
        this.habitacion = habitacion;
    }
    
    
     
}
