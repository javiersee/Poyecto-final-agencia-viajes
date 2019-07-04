/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SevidorAerolineas.servicio;

import ServidorAerolineas.negocio.GestorAerolinea;
import ServidorAerolineas.negocio.Vuelo;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidorAerolineas.negocio.*;

public class SevidorAerolineasServer implements  Runnable{
    private  GestorAerolinea gestor;
    private static ServerSocket ssock;
    private static Socket socket;

    private Scanner entradaDecorada;
    private PrintStream salidaDecorada;
    private static final int PUERTO = 5001;

    /**
     * creo el geestor de aerolineas 
     */
    public SevidorAerolineasServer() {
        gestor= new GestorAerolinea();
    }
    public void iniciar() {
        abrirPuerto();

        while (true) {
            esperarAlCliente();
            lanzarHilo();
        }
    }
    /**
     * lanzo el hilo para la ejecucion en pararlelo del proceso 
     */
    private static void lanzarHilo() {
        new  Thread(new SevidorAerolineasServer().start());
    }
    
    /**
     * abro el puerto para la conexion 
     */
    private static void abrirPuerto() {
        try {
            ssock = new ServerSocket(PUERTO);
            System.out.println("Escuchando por el puerto " + PUERTO);
        } catch (IOException ex) {
            Logger.getLogger(SevidorAerolineasServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * espero a recibir una señal para empezar a procesar 
     */
    private static void esperarAlCliente() {
        try {
            socket = ssock.accept();
            System.out.println("Hotel conectado");
        } catch (IOException ex) {
            Logger.getLogger(SevidorAerolineasServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   /**
    * el recorrido de flujo del hilo 
    */
    public void run() {
        try {
            crearFlujos();
            leerFlujos();
            cerrarFlujos();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

        /**
         * cierro el flujo 
         * @throws IOExcepion en el cas que falle el hilo  
         */
    private void crearFlujos() throws IOException {
        salidaDecorada = new PrintStream(socket.getOutputStream());
        entradaDecorada = new Scanner(socket.getInputStream());
    }
    /**
     * le el flujo del proceso
     */
    private void leerFlujos() {
        if (entradaDecorada.hasNextLine()) {
            // Extrae el flujo que envía el cliente
            String peticion = entradaDecorada.nextLine();
            decodificarPeticion(peticion);

        } else {
            salidaDecorada.flush();
            salidaDecorada.println("NO_ENCONTRADO");
        }
    }
    /**
     * Decodifica la petición, extrayeno la acción y los parámetros
     *
     * @param peticion petición completa al estilo
     * "consultarCiudadano,983932814"
     */
    private void decodificarPeticion(String peticion) {
        StringTokenizer tokens = new StringTokenizer(peticion, ",");
        String parametros[] = new String[5];

        int i = 0;
        while (tokens.hasMoreTokens()) {
            parametros[i++] = tokens.nextToken();
        }
        procesarAccion(parametros);

    }
    private void procesarAccion(String parametros[]) {
         
               ArrayList<Vuelo> Lhab = gestor.ConsultarVuelo(parametros[0], parametros[1], parametros[2],parametros[3]);
                if (Lhab == null) {
                    salidaDecorada.println("NO_ENCONTRADO");
                } else {
                    
                    salidaDecorada.println(parseToJSONCliente(Lhab));
                    
                }
           
    }
    private void cerrarFlujos() throws IOException {
        salidaDecorada.close();
        entradaDecorada.close();
        socket.close();
    }
    
    private String parseToJSONCliente(ArrayList<Vuelo> listaHabitaciones) {
            JsonArray array = new JsonArray();
            JsonObject jsonObj;
        for (Vuelo hab: listaHabitaciones){

         jsonObj = new JsonObject();
        jsonObj.addProperty("origen ", hab.getOrigen());
        jsonObj.addProperty("destino", hab.getDestino());
        jsonObj.addProperty("fecha ",hab.getFecha());
        jsonObj.addProperty("costo", hab.getCosto());
        array.add(jsonObj);
        }
        return array.toString();
    }

    private Runnable start() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
