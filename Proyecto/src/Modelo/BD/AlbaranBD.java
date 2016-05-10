package Modelo.BD;

import Modelo.UML.Albaran;
import Modelo.UML.Parte;
import java.sql.PreparedStatement;

/**
 *
 * @author Alejandra Oteiza
 */
public class AlbaranBD extends GenericoBD{
    public static void insert(Albaran albaran) throws Exception{
        connect();
        
        String q = "insert into albaranes values(?, ?, ?, ?)";
        PreparedStatement ps = getCon().prepareStatement(q);
        
        // SIN AUTOINCREMENT
        ps.setInt(1,1);
        ps.setInt(2,1);
        ps.setTimestamp(3,albaran.getHoraSalida());
        ps.setTimestamp(4,albaran.getHoraLlegada());
        
        ps.executeUpdate();
        
        disconnect();
    }
    
    public static void update(Albaran albaran, Parte parte) throws Exception{
        connect();
        
        String q = "update albaranes set idAlbaran=?,idParte=?,horaSalida=?,horaLlegada=? where idAlbaran=?";
        PreparedStatement ps = getCon().prepareStatement(q);
        ps.setString(1,albaran.getIdAlbaran());
        ps.setInt(2,parte.getIdParte());
        ps.setTimestamp(3,albaran.getHoraSalida());
        ps.setTimestamp(4,albaran.getHoraLlegada());
        ps.setString(5,albaran.getIdAlbaran());
        
        ps.executeUpdate();
        
        disconnect();
    }
}
