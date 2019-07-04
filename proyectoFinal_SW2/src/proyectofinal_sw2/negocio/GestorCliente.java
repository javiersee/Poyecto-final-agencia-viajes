/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal_sw2.negocio;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mvcf.AModel;

/**
 *
 * @author Johe solis
 */
public class GestorCliente extends AModel {
    
    //private final  IServidorCentral servidorCentral;
    private final ConectorJdbc  conector;

    public GestorCliente() {
      //  servidorCentral=new ServicioServidorCentralSocket();
        this.conector= new ConectorJdbc();
        
    }
    /**
     * Trae de la base de datos todos los clientes
     * @return
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
      public ArrayList<Cliente> consultarClientes() throws ClassNotFoundException, SQLException {

        conector.conectarse();
        
          conector.crearConsulta("SELECT * FROM clientes ");
        
        ArrayList<Cliente> clientes = new ArrayList();

        while (conector.getResultado().next()) {
            Cliente cli = new Cliente(conector.getResultado().getString("identificacion"), conector.getResultado().getString("nombres"), conector.getResultado().getString("apellidos"), conector.getResultado().getString("email"), conector.getResultado().getString("genero"));
            if(conector.getResultado().getString("fechaNacido")!=null){
                cli.setFechaNacimiento(conector.getResultado().getString("fechaNacido"));
            }
            if(conector.getResultado().getString("direccion")!=null){
                cli.setDireccion(conector.getResultado().getString("direccion"));
            }
            if(conector.getResultado().getString("ciudadReside")!=null){
                cli.setCiudadReside(conector.getResultado().getString("ciudadReside"));
            }
            if(conector.getResultado().getString("celular")!=null){
                cli.setCelular(conector.getResultado().getString("celular"));
            }
            
            clientes.add(cli);
        }
        conector.desconectarse();
        return clientes;

    }
   
   
       
     
        
    /**
     * Deserializa el objeto json y lo convierte en un objeto Cliente
     *
     * @param cliente Objeto tipo Cliente
     * @param json objeto cliente en formato json
     */
    private ArrayList<Cliente> parseToClientes( String arrayJson) {
        ArrayList<Cliente> clientes1 = new ArrayList();
        Gson gson = new Gson();
        
        final java.lang.reflect.Type tipoListaCliente = new TypeToken<List<Cliente>>(){}.getType();
	final List<Cliente> clientes = gson.fromJson(arrayJson, tipoListaCliente);
        
        for (Cliente cli :clientes) {
        Cliente cli2 = new Cliente();
        cli2=cli;
         clientes1.add(cli2);
        }
        
        return  clientes1;
    }
    
   
    /**
     * Busca un cliente en la bd de la agencia de viajes
     *
     * @param id identificador del cliente
     * @return Objeto tipo cliente, null si no lo encuentra
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public Cliente buscarCliente(String id) throws ClassNotFoundException, SQLException {
        conector.conectarse();
        conector.crearConsulta("SELECT * FROM clientes Where identificacion='" + id + "'");

        Cliente cliente = null;
        if (conector.getResultado().next()) {
           Cliente cli = new Cliente(conector.getResultado().getString("identificacion"), conector.getResultado().getString("nombres"), conector.getResultado().getString("apellidos"), conector.getResultado().getString("email"), conector.getResultado().getString("genero"));
            if(conector.getResultado().getString("fechaNacido")!=null){
                cli.setFechaNacimiento(conector.getResultado().getString("fechaNacido"));
            }
            if(conector.getResultado().getString("direccion")!=null){
                cli.setDireccion(conector.getResultado().getString("direccion"));
            }
            if(conector.getResultado().getString("ciudadReside")!=null){
                cli.setCiudadReside(conector.getResultado().getString("ciudadReside"));
            }
            if(conector.getResultado().getString("celular")!=null){
                cli.setCelular(conector.getResultado().getString("celular"));
            }
            
        }
        conector.desconectarse();
        return cliente;
    }
   

public int contarClientes() throws ClassNotFoundException, SQLException {
        conector.conectarse();
        conector.crearConsulta("SELECT count(*)as alias FROM clientes");

            int conClientes=0;
        conector.getResultado().next();
            conClientes =Integer.parseInt(conector.getResultado().getString("alias"));
      
        conector.desconectarse();
        return conClientes;
    }
   
    
  

        /**
     * agrega un cliente a la base de datos
     * @param cli
     * @throws ClassNotFoundException
     * @throws SQLException
*/
      public void agregarCliente(Cliente cli) throws ClassNotFoundException, SQLException {
        conector.conectarse();
        conector.actualizar("INSERT INTO Clientes (identificacion, nombres, apellidos, email, genero)"
                + " VALUES ("
                + "'" + cli.getIdentificacion() + "',"
                + "'" +cli.getNombres() + "',"
                + "'" + cli.getApellidos() + "',"
                + "'" + cli.getEmail() + "',"
                + "'" + cli.getGenero() + "'"
                + ")");
        conector.desconectarse();
          editarClienteOpc(cli);
        this.notificar();
    }

      public void editarClienteOpc(Cliente cli) throws ClassNotFoundException, SQLException {
          conector.conectarse();
          if(cli.getFechaNacimiento()!=null){
           conector.actualizar("UPDATE Clientes SET  fechaNacido = '"+cli.getFechaNacimiento()+"'"
           + " WHERE identificacion ='" + cli.getIdentificacion()
                + "'");
        }
        if(cli.getDireccion()!=null){
           conector.actualizar("UPDATE Clientes SET  direccion = '"+cli.getDireccion()+"'"
           + " WHERE identificacion ='" + cli.getIdentificacion()
                + "'");
        }
        if(cli.getCelular()!=null){
           conector.actualizar("UPDATE Clientes SET  celular = '"+cli.getCelular()+"'"
           + " WHERE identificacion ='" + cli.getIdentificacion()
                + "'");
        }
        if(cli.getCiudadReside()!=null){
           conector.actualizar("UPDATE Clientes SET  ciudadReside = '"+cli.getCiudadReside()+"'"
           + " WHERE identificacion ='" + cli.getIdentificacion()
                + "'");
        }
        
        conector.desconectarse();
        
        
      }
    /**
     * Edita un cliente en la base de datos
     *
     * @param cli
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void editarCliente(Cliente cli) throws ClassNotFoundException, SQLException {
        conector.conectarse();
            conector.actualizar("UPDATE Clientes SET "
                + "nombres = '" + cli.getNombres() + "',"
                + "apellidos ='" + cli.getApellidos() + "',"
                + "email ='" + cli.getEmail() + "',"
                + "genero ='" + cli.getGenero() + "',"
                + " WHERE identificacion ='" + cli.getIdentificacion()
                + "'");
        
        if(cli.getFechaNacimiento()!=null){
           conector.actualizar("UPDATE Clientes SET  fechaNacido = '"+cli.getFechaNacimiento()+"'"
           + " WHERE identificacion ='" + cli.getIdentificacion()
                + "'");
        }
        if(cli.getDireccion()!=null){
           conector.actualizar("UPDATE Clientes SET  direccion = '"+cli.getDireccion()+"'"
           + " WHERE identificacion ='" + cli.getIdentificacion()
                + "'");
        }
        if(cli.getCelular()!=null){
           conector.actualizar("UPDATE Clientes SET  celular = '"+cli.getCelular()+"'"
           + " WHERE identificacion ='" + cli.getIdentificacion()
                + "'");
        }
        if(cli.getCiudadReside()!=null){
           conector.actualizar("UPDATE Clientes SET  ciudadReside = '"+cli.getCiudadReside()+"'"
           + " WHERE identificacion ='" + cli.getIdentificacion()
                + "'");
        }
        
        conector.desconectarse();
        
        this.notificar();

    }
    
    

    /**
     * Elimina un cliente de la base de datos
     *
     * @param identificacion
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void eliminarCliente(String identificacion) throws ClassNotFoundException, SQLException {
        conector.conectarse();
        conector.actualizar("DELETE FROM Clientes  "
                + " WHERE identificacion ='" + identificacion
                + "'");
        conector.desconectarse();
        this.notificar();
    }
    
    
    
    
    
    
    
}
