/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal_sw2;

import proyectofinal_sw2.negocio.Encriptacion;

/**
 *
 * @author Johe solis
 */
public class ProyectoFinal_SW2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        String palaba="mundo12332*";
        String palaba2=Encriptacion.Encriptar(palaba);
        System.out.println("contraseña: "+palaba2);
        System.out.println("contraseña: "+Encriptacion.Desencriptar(palaba2));
    }
    
}
