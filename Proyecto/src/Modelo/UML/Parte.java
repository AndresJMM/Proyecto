/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.UML;

import java.util.ArrayList;
import java.util.Date;

public class Parte {
    private int idParte;
    private Date fecha;
    private String estado;
    private String matricula;
    private int kmIni;
    private int kmFin;
    private int gastoPeaje;
    private int gastoGasolina;
    private int gastoDieta;
    private int gastoOtros;
    private String descripcion;
    private ArrayList <Albaran> albaranes;
    
    public Parte() {
    }
    
    public Parte(int idParte, Date fecha, String estado, int kmIni) {
        this.idParte = idParte;
        this.fecha = fecha;
        this.estado = estado;
        this.kmIni = kmIni;
    }

    public int getIdParte() {
        return idParte;
    }

    public void setIdParte(int idParte) {
        this.idParte = idParte;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getKmIni() {
        return kmIni;
    }

    public void setKmIni(int kmIni) {
        this.kmIni = kmIni;
    }

    public int getKmFin() {
        return kmFin;
    }

    public void setKmFin(int kmFin) {
        this.kmFin = kmFin;
    }

    public int getGastoPeaje() {
        return gastoPeaje;
    }

    public void setGastoPeaje(int gastoPeaje) {
        this.gastoPeaje = gastoPeaje;
    }

    public int getGastoGasolina() {
        return gastoGasolina;
    }

    public void setGastoGasolina(int gastoGasolina) {
        this.gastoGasolina = gastoGasolina;
    }

    public int getGastoDieta() {
        return gastoDieta;
    }

    public void setGastoDieta(int gastoDieta) {
        this.gastoDieta = gastoDieta;
    }

    public int getGastoOtros() {
        return gastoOtros;
    }

    public void setGastoOtros(int gastoOtros) {
        this.gastoOtros = gastoOtros;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList<Albaran> getAlbaranes() {
        return albaranes;
    }

    public void setAlbaranes(ArrayList<Albaran> albaranes) {
        this.albaranes = albaranes;
    }
    
    public String getEstado(){
        return estado;
    }
    
    public void setEstado(String estado){
        this.estado = estado;
    }
    
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
