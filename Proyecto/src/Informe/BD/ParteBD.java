package Informe.BD;

import Modelo.BD.GenericoBD;
import Informe.UML.Albaran;
import Informe.UML.Parte;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;


public class ParteBD {
    
    public static ArrayList getPartesMes(String mes){
        Connection conn = GenericoBD.startConn();
        ArrayList<Parte> partes = new ArrayList();
        Parte p;
        Albaran a;
        try {
            CallableStatement cs = conn.prepareCall("{call PAC_PARTE.get_partes_mes(?,?)}");

            cs.setString(1, mes);
            cs.registerOutParameter(2, OracleTypes.CURSOR);

            cs.execute();
            ResultSet rs = (ResultSet)cs.getObject(2);
            
            while(rs.next()){
                p = new Parte(rs.getInt("idParte"),rs.getInt("idTrabajador"),rs.getString("estado"),rs.getDate("fecha"),rs.getFloat("kmIni"),rs.getFloat("kmFin"), rs.getString("matricula"));
                if(rs.getFloat("GASTOPEAJE")!=0){
                    p.setGastoPeaje(rs.getFloat("GASTOPEAJE"));
                }
                if(rs.getFloat("GASTODIETA")!=0){
                    p.setGastoDieta(rs.getFloat("GASTODIETA"));
                }
                if(rs.getFloat("GASTOGASOIL")!=0){
                    p.setGastoGasolina(rs.getFloat("GASTOGASOIL"));
                }
                if(rs.getFloat("GASTOOTROS")!=0){
                    p.setGastoOtros(rs.getFloat("GASTOOTROS"));
                }
                
                cs = conn.prepareCall("{call PAC_PARTE.get_albaranes_parte(?,?)}");
                
                cs.registerOutParameter(2, OracleTypes.CURSOR);
                cs.setInt(1, p.getIdParte());

                cs.execute();
                ResultSet rsSec = (ResultSet)cs.getObject(2);
                while(rsSec.next()){
                    a = new Albaran(rsSec.getString("idAlbaran"),rsSec.getTimestamp("horaSalida"),rsSec.getTimestamp("horaLlegada"));
                    p.addAlbaran(a);
                }
                
                partes.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParteBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(!GenericoBD.dropConn(conn)){
            
        }
        return partes;
    }
    
}

