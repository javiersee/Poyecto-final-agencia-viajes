/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServidorAerolinea;
import SevidorAerolineas.servicio.SevidorAerolineasServer;
/**
 *
 * @author elcua
 */
public class ServidorAerolineas {
    /**
     * creo e inicializo el servidor de aerolineas 
     * @param args 
     */
    public static void main(String[] args) {
       SevidorAerolineasServer serhotelSer = new SevidorAerolineasServer();
        serhotelSer.iniciar();
    }
    
}
