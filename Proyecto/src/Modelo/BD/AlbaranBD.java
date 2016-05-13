package Modelo.BD;

import Modelo.UML.Albaran;
import Modelo.UML.Parte;
import Controladora.Main;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Alejandra Oteiza
 */
public class AlbaranBD extends GenericoBD{
    
    private static ArrayList<Albaran> albaranes;
    
    public static void insert(ArrayList<Albaran> albaranes) throws Exception{
        connect();
        
        for (int i = 0; i < albaranes.size(); i++) {
            String q = "insert into albaranes values(?, ?, ?, ?)";
            PreparedStatement ps = getCon().prepareStatement(q/*, Statement.RETURN_GENERATED_KEYS*/);
            
            ps.setString(1,albaranes.get(i).getIdAlbaran());
            ps.setInt(2,Main.getIdParte());
            ps.setTimestamp(3,albaranes.get(i).getHoraSalida());
            ps.setTimestamp(4,albaranes.get(i).getHoraLlegada());
            
            ps.executeUpdate();
            
            /*ResultSet rs = ps.getGeneratedKeys();
            int nuevoId = -1;
            if(rs.next())
                nuevoId = (int) rs.getLong(1);
              */  
        }
        
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
    
    public static void delete() throws Exception{
        connect();
        
        String q = "delete from albaranes where idParte = ?";
        PreparedStatement ps = getCon().prepareStatement(q);

        ps.setInt(1,Main.getIdParte());

        ps.executeUpdate();
        
        disconnect();
        
    }
    
    public static ArrayList<Albaran> queryByIdParte() throws Exception{
        connect();
        
        String q = "select idAlbaran, horaSalida, horaLlegada from albaranes where idParte = ?";
        PreparedStatement ps = getCon().prepareStatement(q);
        ps.setInt(1, Main.getIdParte());
        ResultSet r = ps.executeQuery();
        
        albaranes = new ArrayList();
        
        Albaran albaran;
        while(r.next()){
            albaran = new Albaran();
            albaran.setIdAlbaran(r.getString("idAlbaran"));
            albaran.setHoraSalida(r.getTimestamp("horaSalida"));
            albaran.setHoraLlegada(r.getTimestamp("horaSalida"));
            albaranes.add(albaran);
        }
        
        disconnect();
        return albaranes;
    }
}
