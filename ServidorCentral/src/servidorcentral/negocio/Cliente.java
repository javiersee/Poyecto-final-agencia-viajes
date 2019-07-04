/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorcentral.negocio;

import java.util.Date;

/**
 *
 * @author Johe solis
 */
public class Cliente {
    private String id;
    private String nom;
    private String ape;
    private String email;
    private String sexo;
    private Date fechaNac;

    public Cliente(String id, String nombres, String apellidos,  String email, String sexo, Date fecNac) {
        this.id = id;
        this.nom = nombres;
        this.ape = apellidos;
        this.email = email;
        this.sexo = sexo;
        this.fechaNac = fecNac;
    }

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

    public String getNombres() {
        return nom;
    }

    public void setNombres(String nombres) {
        this.nom = nombres;
    }

    public String getApellidos() {
        return ape;
    }

    public void setApellidos(String apellidos) {
        this.ape = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date edad) {
        this.fechaNac = edad;
    }

}
