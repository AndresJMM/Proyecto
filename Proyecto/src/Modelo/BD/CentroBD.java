package Modelo.BD;

import Modelo.UML.Centro;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

public class CentroBD {
    public static ArrayList getIdCentros(){
        Connection conn = GenericoBD.startConn();
        ArrayList<Integer> tCentros = new ArrayList();
        try{
            try (Statement stmt = conn.createStatement()) {
                ResultSet rset = stmt.executeQuery("select idCentro from CENTRO");
                if(rset != null){
                    while (rset.next()){
                        tCentros.add(rset.getInt(1));
                    }
                }
            }
        }
        catch(Exception e){
            System.out.println("Problemas al realizar query: "+e);
        }
        if(!GenericoBD.dropConn(conn)){
            System.out.println("Problemas al cerrar");
        }
        return tCentros;
    }
    
    public static Centro getCentro(int idCentro) throws SQLException{
        Connection conn = GenericoBD.startConn();
        Centro c = null;
        try{
            CallableStatement cs = conn.prepareCall("{call PAC_CENTRO.visualizar_datos_centro(?,?)}");
            cs.registerOutParameter(2, OracleTypes.CURSOR);
            cs.setInt(1, idCentro);
            cs.execute();
            ResultSet rs = (ResultSet)cs.getObject(2);
            if(rs.next()){
                c = new Centro(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));
            }
        }
        catch(Exception e){
            System.out.println("Problemas al realizar query: "+e);
            c = null;
        }
        if(!GenericoBD.dropConn(conn)){
            System.out.println("Problemas al cerrar");
        }
        return c;
    }
    public static void alta(Centro c) throws SQLException{
        Connection conn = GenericoBD.startConn();
        
        String plantilla = "INSERT INTO CENTRO (IDCENTRO, NOMBRE, TELEFONO, CALLE, NUMERO, CP, CIUDAD, PROVINCIA) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(plantilla);
        ps.setInt(1,c.getIdCentro());
        ps.setString(2,c.getNombreCen());
        ps.setString(3,c.getTelefonoCen());
        ps.setString(4,c.getCalleCen());
        ps.setString(5,c.getNumeroCen());
        ps.setString(6,c.getCpCen());
        ps.setString(7,c.getCiudadCen());
        ps.setString(8,c.getProvinciaCen());
        ps.executeUpdate();
        conn.commit();
            
        if(!GenericoBD.dropConn(conn)){
            System.out.println("Problemas al cerrar");
        }
    }
    public static void modificar(Centro c) throws SQLException{
        Connection conn = GenericoBD.startConn();
        String plantilla = "UPDATE CENTRO SET(NOMBRE, TELEFONO, CALLE, NUMERO, CP, CIUDAD, PROVINCIA) VALUES (?,?,?,?,?,?,?) WHERE IDCENTRO = ?";
        PreparedStatement ps=conn.prepareStatement(plantilla);
        ps.setString(1,c.getNombreCen());
        ps.setString(2,c.getTelefonoCen());
        ps.setString(3,c.getCalleCen());
        ps.setString(4,c.getNumeroCen());
        ps.setString(5,c.getCpCen());
        ps.setString(6,c.getCiudadCen());
        ps.setString(7,c.getProvinciaCen());
        ps.setInt(8,c.getIdCentro());
        ps.executeUpdate();
        conn.commit();
        if(!GenericoBD.dropConn(conn)){
            System.out.println("Problemas al cerrar");
        }
    }

    public static void eliminar(int idFuente, int idDestino) throws Exception {
        Connection conn = GenericoBD.startConn();
        CallableStatement cs = conn.prepareCall("{call PAC_CENTRO.borrar_centro(?,?)}");
            cs.setInt(1, idFuente);
            cs.setInt(2, idDestino);
            cs.execute();
        if(!GenericoBD.dropConn(conn)){
            System.out.println("Problemas al cerrar");
        }
    }
    
}
