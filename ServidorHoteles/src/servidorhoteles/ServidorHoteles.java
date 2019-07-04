/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorhoteles;

import servidorhoteles.servicio.ServidorHotelesServer;

/**
 *
 * @author Johe solis
 */
public class ServidorHoteles {

    /**
     * @param args the command line arguments para inicializar el funciamiento del servidor de hoteles
     */
    public static void main(String[] args) {
       ServidorHotelesServer serhotelSer = new ServidorHotelesServer();
        serhotelSer.iniciar();
    }
    
}
