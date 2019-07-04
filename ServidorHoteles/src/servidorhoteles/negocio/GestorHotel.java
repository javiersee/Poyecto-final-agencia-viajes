/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorhoteles.negocio;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * clase para la gestion de hoteles 
 * @author Johe solis
 */
public class GestorHotel {
    /**
     * creo una lista de hoteles 
     */
    private ArrayList<Hotel> hoteles;
    /**
     * inicializo la lista de hoteles
     */
    public GestorHotel() {
        hoteles = new ArrayList<Hotel>();
        
    }
    /**
     *  busca un hotel en determinada  ciudad 
     * @param fecha1 fecha de llegada 
     * @param fecha2 fecha de salidaq 
     * @param ciudad ciudad del hotel
     * @return 
     */
    public ArrayList<Habitacion> buscarHotel(String fecha1,String fecha2,String ciudad){
        ArrayList<Habitacion> lista = new ArrayList<Habitacion>();
        for(int i=0;i<hoteles.size();i++){
            if(hoteles.get(i).getCiudad().equalsIgnoreCase(ciudad)){
                lista=validarHabitacion(hoteles.get(i), fecha1, fecha2, lista);
            }
        }
      
        return lista;
    }
    /**
     *  valida si una  habitacion esta libre en determinada fecha 
     * @param hotel ingresa el hotel 
     * @param fecha1 ingresa  fecha de llegada 
     * @param fecha2 ingresa fecha de salida 
     * @param habitaciones  estado de la  habitacion si esta disponible
     * @return 
     */
     private ArrayList<Habitacion> validarHabitacion(Hotel hotel,String fecha1, String fecha2,ArrayList<Habitacion> habitaciones){
         boolean bandera=false;
         for(int i=0; i<hotel.getHabitaciones().size();i++){
              bandera=false;
             for(int e=0;e<hotel.getRegistroHabitacion().size();e++){
                 if(hotel.getHabitaciones().get(i).getNumeroHabitacion()==hotel.getRegistroHabitacion().get(e).getHabitacion()){
                    
                         if((rangoFecha(hotel.getRegistroHabitacion().get(e).getFechaSalida(),fecha1))){
                            bandera=true; 
                         }
                      
                 }
             }
             if(!bandera){
                 for(int k=0;k<hotel.getReservas().size();k++){
                 if(hotel.getHabitaciones().get(i).getNumeroHabitacion()==hotel.getReservas().get(k).getHabitacion()){
                     
                         if(!(rangoFecha(hotel.getReservas().get(k).getFechaSalida(),fecha1))){
                            habitaciones.add(hotel.getHabitaciones().get(i));
                         }
                     
                     
                 }
             }
                 
             }
         }
         return habitaciones;
     }
     /**
      * me permite dividir la fecha para poderla comparar
      * @param fecha
      * @return 
      */
     private String[] dividirFecha(String fecha){
         StringTokenizer tokens = new StringTokenizer(fecha, "/");
         String parametros[] = new String[4];

        int i = 0;
        while (tokens.hasMoreTokens()) {
            parametros[i++] = tokens.nextToken();
        }
        return parametros;
     }
     /**
      * habitaciones que esten libres en ese rango de fecha 
      * @param fechaSalida fecha de salida 
      * @param fecha1 fecha  de habitacion 
      * @return 
      */
     private boolean rangoFecha (String fechaSalida, String fecha1){
         String[] feS=dividirFecha(fechaSalida);
         String[] fe1=dividirFecha(fecha1);
         if(Integer.parseInt(feS[2])<Integer.parseInt(fe1[2])){
             return false; 
         }else {
             if(Integer.parseInt(feS[1])<Integer.parseInt(fe1[1])){
                 return false;
             }else if(Integer.parseInt(feS[1])==Integer.parseInt(fe1[1])){
                 if(Integer.parseInt(feS[0])<=Integer.parseInt(fe1[0])){
                     return false;
                 }
             }
         }
         return true;
     }
    
}
