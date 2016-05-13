package Modelo.UML;

import java.util.ArrayList;
import java.util.Date;

public class Parte {
    private int idParte;
    private String estado;
    private Date fecha;
    private float kmIni;
    private float kmFin;
    private Coche coche;
    private float gastoPeaje;
    private float gastoDieta;
    private float gastoGasoil;
    private float gastoOtros;
    private String descripcion;
    
    private ArrayList<Albaran> albaranes;

    public int getIdParte() {
        return idParte;
    }

    public void setIdParte(int idParte) {
        this.idParte = idParte;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getKmIni() {
        return kmIni;
    }

    public void setKmIni(float kmIni) {
        this.kmIni = kmIni;
    }

    public float getKmFin() {
        return kmFin;
    }

    public void setKmFin(float kmFin) {
        this.kmFin = kmFin;
    }

    public String getMatricula() {
        return this.coche.getMatricula();
    }

    public void setMatricula(String matricula) {
        this.coche = new Coche();
        this.coche.setMatricula(matricula);
    }

    public float getGastoPeaje() {
        return gastoPeaje;
    }

    public void setGastoPeaje(float gastoPeaje) {
        this.gastoPeaje = gastoPeaje;
    }

    public float getGastoDieta() {
        return gastoDieta;
    }

    public void setGastoDieta(float gastoDieta) {
        this.gastoDieta = gastoDieta;
    }

    public float getGastoGasoil() {
        return gastoGasoil;
    }

    public void setGastoGasoil(float gastoGasoil) {
        this.gastoGasoil = gastoGasoil;
    }

    public float getGastoOtros() {
        return gastoOtros;
    }

    public void setGastoOtros(float gastoOtros) {
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

    public Parte() {
        albaranes = new ArrayList();
    }

    public Parte(int idParte, String estado, Date fecha, float kmIni, float kmFin, String matricula, float gastoPeaje, float gastoDieta, float gastoGasoil, float gastoAutopista, float gastoOtros, String descripcion) {
        this.idParte = idParte;
        this.estado = estado;
        this.fecha = fecha;
        this.kmIni = kmIni;
        this.kmFin = kmFin;
        this.coche.setMatricula(matricula);
        this.gastoPeaje = gastoPeaje;
        this.gastoDieta = gastoDieta;
        this.gastoGasoil = gastoGasoil;
        this.gastoOtros = gastoOtros;
        this.descripcion = descripcion;
        albaranes = new ArrayList();
    }
}
