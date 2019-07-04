package proyectofinal_sw2.negocio;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Johe solis
 */
public class Cliente {
    private String identificacion;
    private String nombres;
    private String apellidos;
    private String fechaNacimiento;
    private String email;
    private String genero;
    private String direccion;
    private String ciudadReside;
    private String celular;
    /**
     * constructor del cliente 
     * @param id ide del cliente 
     * @param nombres nombre cliente 
     * @param apellidos apellido del cliente 
     * @param email email del cliente 
     * @param sexo  sexo del cliente 
     */
    public Cliente(String id, String nombres, String apellidos,  String email, String sexo) {
        this.identificacion = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.genero = sexo;
    }
    /**
     * construcctor del cliente 
     */
    public Cliente() {

    }
    /**
     * getters and setters del cliente 
     * @return 
     */
    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String id) {
        this.identificacion = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String sexo) {
        this.genero = sexo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCiudadReside() {
        return ciudadReside;
    }

    public void setCiudadReside(String ciudadReside) {
        this.ciudadReside = ciudadReside;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String Direccion) {
        this.direccion = Direccion;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    

    
     
}
