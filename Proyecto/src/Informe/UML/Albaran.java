package Informe.UML;

import java.sql.Timestamp;

public class Albaran {
    private String idAlbaran;
    private Timestamp horaSalida;
    private Timestamp horaLLegada;
    
    public Albaran(){
    }
    
    public Albaran(String idAlbaran, Timestamp horaSalida, Timestamp horaLLegada) {
        this.idAlbaran = idAlbaran;
        this.horaSalida = horaSalida;
        this.horaLLegada = horaLLegada;
    }

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

    public Timestamp getHoraLLegada() {
        return horaLLegada;
    }

    public void setHoraLLegada(Timestamp horaLLegada) {
        this.horaLLegada = horaLLegada;
    }
    
    
}
