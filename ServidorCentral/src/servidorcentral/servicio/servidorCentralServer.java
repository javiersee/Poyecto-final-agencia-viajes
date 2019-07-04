/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorcentral.servicio;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidorcentral.negocio.Cliente;
import servidorcentral.negocio.GestorCliente;
import servidorcentral.negocio.GestorPlanesTuristicos;
import servidorcentral.negocio.PlanesTuristicos;

/**
 *
 * @author Johe solis
 */
public class servidorCentralServer implements Runnable{
    
    private final GestorCliente gestorC;
    private final GestorPlanesTuristicos gestorP;
    private static ServerSocket ssock;
    private static Socket socket;

    private Scanner entradaDecorada;
    private PrintStream salidaDecorada;
    private static final int PUERTO = 5002;

    /**
     * Constructor
     */
    public servidorCentralServer() {
        gestorC = new GestorCliente();
        gestorP = new GestorPlanesTuristicos();
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
        new Thread(new servidorCentralServer()).start();
    }

    private static void abrirPuerto() {
        try {
            ssock = new ServerSocket(PUERTO);
            System.out.println("Escuchando por el puerto " + PUERTO);
        } catch (IOException ex) {
            Logger.getLogger(servidorCentralServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Espera que el cliente se conecta y le devuelve un socket
     */
    private static void esperarAlCliente() {
        try {
            socket = ssock.accept();
            System.out.println("Cliente conectado");
        } catch (IOException ex) {
            Logger.getLogger(servidorCentralServer.class.getName()).log(Level.SEVERE, null, ex);
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
        String parametros[] = new String[20];

        int i = 0;
        while (tokens.hasMoreTokens()) {
            parametros[i++] = tokens.nextToken();
        }
        String accion = parametros[0];
        procesarAccion(accion, parametros);

    }

    /**
     * Segun el protocolo decide qué accion invocar
     *
     * @param accion acción a procesar
     * @param parametros parámetros de la acción
     */
    private void procesarAccion(String accion, String parametros[]) {
        switch (accion) {
            case "ObtenerClientes":
               List<Cliente> Lcli = gestorC.clientesPotenciales();
                if (Lcli == null) {
                    salidaDecorada.println("NO_ENCONTRADO");
                } else {
                    
                    salidaDecorada.println(parseToJSONCliente(Lcli));
                    
                }
                break;
            case "ObtenerPlanes":
                
               List<PlanesTuristicos>planes = gestorP.ObtenerPlanes();
               
                if (planes == null) {
                    salidaDecorada.println("NO_ENCONTRADO");
                } else {
                    
                    salidaDecorada.println(parseToJSONPlanes(planes));
                    
                }
                break;
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
     * Convierte el objeto Ciudadano a json
     *
     * @param ciu Objeto ciudadano
     * @return cadena json
     */
    private String parseToJSONCliente(List<Cliente> listaClientes) {
            JsonArray array = new JsonArray();
            JsonObject jsonObj;
        for (Cliente cli: listaClientes){

         jsonObj = new JsonObject();
        jsonObj.addProperty("id", cli.getid());
        jsonObj.addProperty("nombres", cli.getNombres());
        jsonObj.addProperty("apellidos", cli.getApellidos());
        jsonObj.addProperty("email", cli.getEmail());
        jsonObj.addProperty("sexo", cli.getSexo());
        jsonObj.addProperty("edad", gestorC.calcularEdad(cli.getFechaNac()));
        array.add(jsonObj);
        }
        return array.toString();
    }
    private String parseToJSONPlanes(List<PlanesTuristicos> listaPlanes) {
        JsonArray array = new JsonArray();
        JsonObject jsonObj ;
         for (PlanesTuristicos plan: listaPlanes){
         jsonObj = new JsonObject();
        jsonObj.addProperty("id", plan.getId());
        jsonObj.addProperty("nombre", plan.getNombre());
        jsonObj.addProperty("descripcion", plan.getDescripcion());
        jsonObj.addProperty("rangoEdad1", plan.getRangoEdad1());
        jsonObj.addProperty("rangoEdad2", plan.getRangoEdad2());
        jsonObj.addProperty("genero", plan.getGenero());
        array.add(jsonObj);
         }
        return array.toString();
    }
}
   

