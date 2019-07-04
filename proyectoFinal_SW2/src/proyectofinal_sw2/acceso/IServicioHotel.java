package proyectofinal_sw2.acceso;

import java.util.ArrayList;
import servidorhoteles.negocio.Habitacion;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */





/**
 *
 * @author libardo
 */
public interface IServicioHotel {
    public ArrayList<Habitacion> consultarHotel(String fecha1,String fecha2,String ciudad);
}


