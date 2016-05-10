package Informe.BD;

import Modelo.BD.GenericoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InformeBD {
    
    public static boolean informeRealizado(String d){
        Connection conn = GenericoBD.startConn();
        boolean realizado = false;
        try {
            Calendar c = Calendar.getInstance();
            c.set(Integer.parseInt(d.split("-")[1]), Integer.parseInt(d.split("-")[0]), 0, 0, 0);  
            java.sql.Date sqlDate = new java.sql.Date(c.getTime().getTime());
            PreparedStatement cs = conn.prepareCall("SELECT CONTENIDO FROM INFORME WHERE to_char(FECHA,'MM-YYYY') = ?");
            cs.setString(1, d);
            ResultSet rs = (ResultSet)cs.executeQuery();
            if(rs.next()){
                realizado = true;
            }else{
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParteBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return realizado;
    }
    
    public static void almacenarInforme(String d, String informe){
        Connection conn = GenericoBD.startConn();
        try {
            Calendar c = Calendar.getInstance();
            c.set(Integer.parseInt(d.split("-")[1]), Integer.parseInt(d.split("-")[0]), 0, 0, 0);  
            java.sql.Date sqlDate = new java.sql.Date(c.getTime().getTime());
            if(informeRealizado(d)){
                PreparedStatement cs = conn.prepareCall("UPDATE INFORME SET FECHA=?, CONTENIDO=? WHERE to_char(FECHA,'MM-YYYY') = ?");
                cs.setDate(1, sqlDate);
                cs.setString(2, informe);
                cs.setString(3, d);
                cs.executeUpdate();
                
            }else{
                PreparedStatement cs = conn.prepareCall("INSERT INTO INFORME VALUES(?,?)");
                cs.setDate(1, sqlDate);
                cs.setString(2, informe);
                cs.executeUpdate();
                try{
                    conn.commit();
                }catch(Exception e){
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParteBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
