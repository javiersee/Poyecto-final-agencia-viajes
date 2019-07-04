/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorcentral.negocio;



import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Johe solis
 */
public class GestorPlanesTuristicos {
    private List<PlanesTuristicos> listado;

    public GestorPlanesTuristicos() {
        listado = new ArrayList();
        inicializar();
    }

    public void inicializar() {
        try {
            listado.add(new PlanesTuristicos("001","Plan Popayán - Puracé","Conocer el avistamiento de cóndores, cascadas de agua, naturaleza. Viernes a domingo.","18","20","M"));
            listado.add(new PlanesTuristicos("002","Plan Popayán - Silvia","Conocer Silvia, comunidades indígenas. Viernes a domingo","25","30","F"));
            listado.add(new PlanesTuristicos("003","Plan Popayán - Caloto","Conocer Caloto y sus sitios naurales. Viernes a domingo","35","40","T"));
        } catch (Exception e) {
            Logger.getLogger(GestorPlanesTuristicos.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public List<PlanesTuristicos> ObtenerPlanes() {
         return listado;
    }
        
}
