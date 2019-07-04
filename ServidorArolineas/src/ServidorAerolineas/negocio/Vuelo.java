/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServidorAerolineas.negocio;

/**
 *clase   de vuelos 
 * @author elcua
 */
public class Vuelo {
    private String Fecha;
    private String Origen;
    private String Destino;
    private String Aerolinea;
    private  int costo;

    /**
     * costructor vacio 
     */
    public Vuelo() {
    }

    /**
     * costructor parametrizado 
     * @param Fecha  fecha del vuelo
     * @param Origen oigen del vuelo 
     * @param Destino destino del vuelo 
     * @param Aerolinea aeolinea que brinda el servicio
     * @param costo  costo del vuelo
     */
    public Vuelo(String Fecha, String Origen, String Destino, String Aerolinea, int costo) {
        this.Fecha = Fecha;
        this.Origen = Origen;
        this.Destino = Destino;
        this.Aerolinea = Aerolinea;
        this.costo = costo;
    }

    /**
     *  creacion de los getters and setters 
     * @return 
     */
    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getOrigen() {
        return Origen;
    }

    public void setOrigen(String Origen) {
        this.Origen = Origen;
    }

    public String getDestino() {
        return Destino;
    }

    public void setDestino(String Destino) {
        this.Destino = Destino;
    }

    public String getAerolinea() {
        return Aerolinea;
    }

    public void setAerolinea(String Aerolinea) {
        this.Aerolinea = Aerolinea;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    @Override
    public String toString() {
        return "Vuelo{" + "Fecha=" + Fecha + ", Origen=" + Origen + ", Destino=" + Destino + ", Aerolinea=" + Aerolinea + ", costo=" + costo + '}';
    }

   
    
    
}
