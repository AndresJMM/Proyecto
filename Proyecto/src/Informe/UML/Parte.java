package Informe.UML;

import java.util.ArrayList;
import java.util.Date;

public class Parte {
    private int idParte;
    private int idTrabajador;
    private String estado;
    private Date fecha;
    private Float kmIni;
    private Float kmFin;
    private String matricula;
    private Float gastoPeaje;
    private Float gastoGasolina;
    private Float gastoDieta;
    private Float gastoOtros;
    private String descripcion;
    private ArrayList <Albaran> albaranes;
    
    public Parte() {
    }
    
    public Parte(int idParte, int idTrabajador, String estado, Date fecha, Float kmIni, Float kmFin, String matricula) {
        this.idParte = idParte;
        this.idTrabajador = idTrabajador;
        this.estado = estado;
        this.fecha = fecha;
        this.kmIni = kmIni;
        this.kmFin = kmFin;
        this.matricula = matricula;
        this.albaranes = new ArrayList();
    }

    public int getIdParte() {
        return idParte;
    }

    public void setIdParte(int idParte) {
        this.idParte = idParte;
    }
    
    public int getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }
    
    public String getEstado() {
        return estado;
    }

    public void setIdTipoParte(String estado) {
        this.estado = estado;
    }


    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Float getKmIni() {
        return kmIni;
    }

    public void setKmIni(Float kmIni) {
        this.kmIni = kmIni;
    }

    public Float getKmFin() {
        return kmFin;
    }

    public void setKmFin(Float kmFin) {
        this.kmFin = kmFin;
    }

    public Float getGastoPeaje() {
        return gastoPeaje;
    }

    public void setGastoPeaje(Float gastoPeaje) {
        this.gastoPeaje = gastoPeaje;
    }

    public Float getGastoGasolina() {
        return gastoGasolina;
    }

    public void setGastoGasolina(Float gastoGasolina) {
        this.gastoGasolina = gastoGasolina;
    }

    public Float getGastoDieta() {
        return gastoDieta;
    }

    public void setGastoDieta(Float gastoDieta) {
        this.gastoDieta = gastoDieta;
    }

    public Float getGastoOtros() {
        return gastoOtros;
    }

    public void setGastoOtros(Float gastoOtros) {
        this.gastoOtros = gastoOtros;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public ArrayList<Albaran> getAlbaranes() {
        return albaranes;
    }

    public void setAlbaranes(ArrayList<Albaran> albaranes) {
        this.albaranes = albaranes;
    }
    
    public void addAlbaran(Albaran a){
        this.albaranes.add(a);
    }
    
    
}
