package proyectofinal_sw2.acceso;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidorhoteles.negocio.Habitacion;


public class ServidorHotelSocket implements IServicioHotel {

    private Socket socket = null;
    private Scanner entradaDecorada;
    private PrintStream salidaDecorada;
    private final String IP_SERVIDOR = "localhost";
    private final int PUERTO = 5000;

    private String consultarServicio(String servicio) {
        
         String respuesta = null;
        try {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(servicio);
            cerrarFlujos();
            desconectar();

        } catch (IOException ex) {
            Logger.getLogger(ServidorHotelSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;

        
    }
    
    


public ArrayList<Habitacion> consultarHotel(String fecha1, String fecha2, String ciudad) {
    String json = consultarServicio("consultarHoteles");
        if (!json.equals("NO_ENCONTRADO")) {
            //Lo encontró
                    System.out.println();
            ArrayList<Habitacion> habitaciones =parseToHabitaciones(json);
           
            return   habitaciones;
        }
        return null;
    }
    
    private ArrayList<Habitacion> parseToHabitaciones( String arrayJson) {
        
        Gson gson = new Gson();
        
        final java.lang.reflect.Type tipoListaCliente = new TypeToken<List<Habitacion>>(){}.getType();
	final List<Habitacion> hab = gson.fromJson(arrayJson, tipoListaCliente);
        ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();
        for (int i=0;i<hab.size();i++) {
        Habitacion h = new Habitacion();
        h=hab.get(i);
         habitaciones.add(h);
        }
        
        return  habitaciones;
    }
        private String leerFlujoEntradaSalida(String servicio) throws IOException {
        String respuesta = "";
        entradaDecorada = new Scanner(socket.getInputStream());
        salidaDecorada = new PrintStream(socket.getOutputStream());
        salidaDecorada.flush();
        // Usando el protocolo de comunicación
        salidaDecorada.println(servicio);
        if (entradaDecorada.hasNextLine()) {
            respuesta = entradaDecorada.nextLine();
        }
        return respuesta;
    }
        
        private void desconectar() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServidorHotelSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void conectar(String address, int port) throws IOException {
        socket = new Socket(address, port);
        System.out.println("Conectado");
    }
        private void cerrarFlujos() {
        salidaDecorada.close();
        entradaDecorada.close();
    }

   

}
