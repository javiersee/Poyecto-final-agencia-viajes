/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorcentral.negocio;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Johe solis
 */
public class GestorCliente {
   
    private List<Cliente> listado;

    public GestorCliente() {
        listado = new ArrayList();
        inicializar();
    }

    public void inicializar() {
        try {
          
            listado.add(new Cliente("98000001", "Antonio Jose","Vega Soto", "libardo@gmail.com", "M", new SimpleDateFormat("dd/MM/yyyy").parse("01/02/2000")));
            listado.add(new Cliente("98000002", "Carlos", "Pantoja",  "carlos@gmail.com", "M", new SimpleDateFormat("dd/MM/yyyy").parse("01/02/2001")));
            listado.add(new Cliente("98000003", "Andrea Elizabeth","Sanchez", "andreaeli@hotmail.com", "F", new SimpleDateFormat("dd/MM/yyyy").parse("01/02/1994")));
            listado.add(new Cliente("98000004", "Fernanda", "Arevalo", "fercha@hotmail.com", "F", new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1993")));
            listado.add(new Cliente("98000005", "Ana Julia","Torres Gallardo", "anita@hotmail.com", "F", new SimpleDateFormat("dd/MM/yyyy").parse("01/02/1994")));
            listado.add(new Cliente("98000006", "Manuel", "Perez", "manuel@hotmail.com", "M", new SimpleDateFormat("dd/MM/yyyy").parse("01/05/1980")));
            listado.add(new Cliente("98000007", "Alejandro", "Mosquera", "ajemos@hotmail.com", "M", new SimpleDateFormat("dd/MM/yyyy").parse("01/05/1981")));
            listado.add(new Cliente("98000008", "Cesar", "Gutierres Sanchez", "cesar@hotmail.com", "M", new SimpleDateFormat("dd/MM/yyyy").parse("01/05/1982")));
            listado.add(new Cliente("98000009", "Julio", "Bravo Bravo","julio@hotmail.com", "M", new SimpleDateFormat("dd/MM/yyyy").parse("01/05/1982")));
            listado.add(new Cliente("98000010", "Alberto", "Mendez Bravo", "alberto@hotmail.com", "M", new SimpleDateFormat("dd/MM/yyyy").parse("01/05/1985")));
            listado.add(new Cliente("98000011", "Alexandra", "Ponce Yepes", "aleja@hotmail.com", "F", new SimpleDateFormat("dd/MM/yyyy").parse("01/05/1979")));
        } catch (ParseException ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    } public List<Cliente> clientesPotenciales() {
        return listado;
    }
    public List<Cliente> clientesPotenciales2(String rangoEdad1,String rangoEdad2,String genero) {
        List<Cliente> Cpotenciales=new ArrayList();
        for (Cliente cli : listado) {
            if(genero.equalsIgnoreCase("T")){
                if (Integer.parseInt(rangoEdad1)<=calcularEdad(cli.getFechaNac()) && Integer.parseInt(rangoEdad2)>=calcularEdad(cli.getFechaNac())) {
                Cpotenciales.add(cli);
            }
            }
            if (Integer.parseInt(rangoEdad1)<=calcularEdad(cli.getFechaNac()) && Integer.parseInt(rangoEdad2)>=calcularEdad(cli.getFechaNac())&& cli.getSexo().equals(genero)) {
                Cpotenciales.add(cli);
            }
        }
        return Cpotenciales;
    }
    public int calcularEdad(Date fecha ){
         DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
LocalDate fechaNac = LocalDate.parse(formatoFecha.format(fecha), fmt);
LocalDate ahora = LocalDate.now();
Period periodo = Period.between(fechaNac, ahora);

        return periodo.getYears();
    }
}
