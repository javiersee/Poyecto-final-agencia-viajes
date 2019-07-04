/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorhoteles.servicio;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidorhoteles.negocio.GestorHotel;
import servidorhoteles.negocio.Habitacion;

/**
 *
 * @author Johe solis
 */
public class ServidorHotelesServer  implements Runnable{
    
    private final GestorHotel gestor;
    private static ServerSocket ssock;
    private static Socket socket;

    private Scanner entradaDecorada;
    private PrintStream salidaDecorada;
    private static final int PUERTO = 5000;

    /**
     * Constructor
     */
    public ServidorHotelesServer() {
        gestor = new GestorHotel();
    }
    /**
     * Logica completa del servidor
     */
    public void iniciar() {
        abrirPuerto();

        while (true) {
            esperarAlCliente();
            lanzarHilo();
        }
    }

    /**
     * Lanza el hilo
     */
    private static void lanzarHilo() {
        new Thread(new ServidorHotelesServer()).start();
    }

    private static void abrirPuerto() {
        try {
            ssock = new ServerSocket(PUERTO);
            System.out.println("Escuchando por el puerto " + PUERTO);
        } catch (IOException ex) {
            Logger.getLogger(ServidorHotelesServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Espera que el cliente se conecta y le devuelve un socket
     */
    private static void esperarAlCliente() {
        try {
            socket = ssock.accept();
            System.out.println("Hotel conectado");
        } catch (IOException ex) {
            Logger.getLogger(ServidorHotelesServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Cuerpo del hilo
     */
    @Override
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
     * Crea los flujos con el socket
     *
     * @throws IOException
     */
    private void crearFlujos() throws IOException {
        salidaDecorada = new PrintStream(socket.getOutputStream());
        entradaDecorada = new Scanner(socket.getInputStream());
    }

    /**
     * Lee los flujos del socket
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

    /**
     * Segun el protocolo decide qué accion invocar
     * @param parametros parámetros de la acción
     */
    private void procesarAccion(String parametros[]) {
         
               ArrayList<Habitacion> Lhab = gestor.buscarHotel(parametros[0], parametros[1], parametros[2]);
                if (Lhab == null) {
                    salidaDecorada.println("NO_ENCONTRADO");
                } else {
                    
                    salidaDecorada.println(parseToJSONCliente(Lhab));
                    
                }
           
    }

    /**
     * Cierra los flujos de entrada y salida
     *
     * @throws IOException
     */
    private void cerrarFlujos() throws IOException {
        salidaDecorada.close();
        entradaDecorada.close();
        socket.close();
    }

    /**
     * Convierte el objeto Habitacion a json
     *
     * @param listaHabitaciones Objeto habitacion
     * @return cadena json
     */
    private String parseToJSONCliente(ArrayList<Habitacion> listaHabitaciones) {
            JsonArray array = new JsonArray();
            JsonObject jsonObj;
        for (Habitacion hab: listaHabitaciones){

         jsonObj = new JsonObject();
        jsonObj.addProperty("hotel", hab.getHotel());
        jsonObj.addProperty("tipo", hab.getTipo());
        jsonObj.addProperty("descripcion",hab.getDescripcion());
        jsonObj.addProperty("costo", hab.getCosto());
        array.add(jsonObj);
        }
        return array.toString();
    }
    
}
