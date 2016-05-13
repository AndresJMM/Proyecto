package Modelo.UML;

import java.sql.Timestamp;

public class Albaran {
    private String idAlbaran;
    private Timestamp horaSalida;
    private Timestamp horaLlegada;
    
    private Parte parte;

    public String getIdAlbaran() {
        return idAlbaran;
    }

    public void setIdAlbaran(String idAlbaran) {
        this.idAlbaran = idAlbaran;
    }

    public Timestamp getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Timestamp horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Timestamp getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(Timestamp horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public Parte getParte() {
        return parte;
    }

    public void setParte(Parte parte) {
        this.parte = parte;
    }

    public Albaran() {
    }

    public Albaran(String idAlbaran, Timestamp horaSalida, Timestamp horaLlegada, Parte parte) {
        this.idAlbaran = idAlbaran;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.parte = parte;
    }
}
