package Modelo.UML;

import java.util.ArrayList;

public class Centro {
    
    private int idCentro;
    private String nombre;
    private String telefono;
    private String calle;
    private String numero;
    private String cp;
    private String ciudad;
    private String provincia;
    private ArrayList <Trabajador> listaTrabajadores;

    public Centro() {
    }
    public Centro(int idCentro, String nombre, String telefono, String calle, String numero, String cp, String ciudad, String provincia){
        this.idCentro = idCentro;
        this.nombre = nombre;
        this.telefono = telefono;
        this.calle = calle;
        this.numero = numero;
        this.cp = cp;
        this.ciudad = ciudad;
        this.provincia = provincia;
    }
    public Centro(int idCentro, String nombre, String telefono, String calle, String numero, String cp, String ciudad, String provincia, ArrayList<Trabajador> listaTrabajadores) {
        this.idCentro = idCentro;
        this.nombre = nombre;
        this.telefono = telefono;
        this.calle = calle;
        this.numero = numero;
        this.cp = cp;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.listaTrabajadores = listaTrabajadores;
    }
    public void setIdCentro(int id) {
        this.idCentro = id;
    }
    public int getIdCentro() {
        return idCentro;
    }
    public String getNombreCen() {
        return nombre;
    }
    public void setNombreCen(String nombre) {
        this.nombre = nombre;
    }
    public String getTelefonoCen() {
        return telefono;
    }
    public void setTelefonoCen(String telefono) {
        this.telefono = telefono;
    }
    public ArrayList<Trabajador> getListaTrabajadores() {
        return listaTrabajadores;
    }
    public void setListaTrabajadores(ArrayList<Trabajador> listaTrabajadores) {
        this.listaTrabajadores = listaTrabajadores;
    }
    public String getCalleCen() {
        return calle;
    }
    public void setCalleCen(String calle) {
        this.calle = calle;
    }
    public String getNumeroCen() {
        return numero;
    }
    public void setNumeroCen(String numero) {
        this.numero = numero;
    }
    public String getCpCen() {
        return cp;
    }
    public void setCpCen(String cp) {
        this.cp = cp;
    }
    public String getCiudadCen() {
        return ciudad;
    }
    public void setCiudadCen(String ciudad) {
        this.ciudad = ciudad;
    }
    public String getProvinciaCen() {
        return provincia;
    }
    public void setProvinciaCen(String provincia) {
        this.provincia = provincia;
    }
}