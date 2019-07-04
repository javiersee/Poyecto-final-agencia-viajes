/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServidorAerolineas.negocio;
import java.util.ArrayList;
import java.util.StringTokenizer;
/**
 * gestor de  vuelos 
 * @author elcua
 */
public class GestorAerolinea {
    /**
     * creolos atributos necesatios como las listas y dos banderas 
     */
    boolean aux=false,aux2=false;
    private ArrayList<Vuelo> vuelos;

    /**
     * constructor vacio e inicializo la lista de vuelo
     */
    public GestorAerolinea() {
         vuelos = new ArrayList<Vuelo>();
      }
   /**
    *  consultae que vuelos hay  disponibles para una necesidad
    * @param fecha1 fecha de salida 
    * @param fecha2 fecha de llegada 
    * @param Origen origen de salida 
    * @param Destino origen de llegada 
    * @return  una lista con los vuelos disponibles
    */
    public ArrayList<Vuelo> ConsultarVuelo (String fecha1,String fecha2,String Origen, String Destino){
        ArrayList<Vuelo> lista = new ArrayList<Vuelo>();
        for(int i=0;i<vuelos.size();i++){
            
           if( vuelos.get(i).getOrigen().equals(Origen))  
           {
             aux=rangoFecha(fecha1, vuelos.get(i).getFecha());
            if(aux=true){
                Vuelo vuel= new Vuelo();
                vuel=vuelos.get(i);
                lista.add(vuel);
            }
           }
           if(vuelos.get(i).getDestino().equals(Destino))  
           {
             aux=rangoFecha(fecha1, vuelos.get(i).getFecha());
            if(aux=true){
                Vuelo vuel= new Vuelo();
                vuel=vuelos.get(i);
                lista.add(vuel);
            }
           }
           
           
        }
      
        return lista;
    }
    /**
     * metodo  para agregar vuelos 
     * @param vuelo 
     */
    public void AgreagarVueli(Vuelo vuelo){
        vuelos.add(vuelo);
    }
    
   
    /**
     * metodo para  comparar las fechas 
     * @param fechaSalida fecha del cliente 
     * @param fecha1 fecha del vuelo 
     * @return 
     */
    private boolean rangoFecha (String fechaSalida, String fecha1){
         String[] feS=dividirFecha(fechaSalida);
         String[] fe1=dividirFecha(fecha1);
         if(Integer.parseInt(feS[2])==Integer.parseInt(fe1[2])){
             if(Integer.parseInt(feS[1])==Integer.parseInt(fe1[1])){
                  if(Integer.parseInt(feS[0])==Integer.parseInt(fe1[0])){
                      return true;
                    }
             }
         }
         return true;
     }
    /**
     * me cre unos tokents para compararalos 
     * @param fecha la fecha en Strings 
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
    
}
