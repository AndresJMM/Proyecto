package Modelo.BD;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import oracle.jdbc.OracleTypes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import Modelo.UML.Parte;
import Controladora.Main;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;


public class ParteBD extends GenericoBD{
    
public static ArrayList<Integer> rellenarTrabLog(int centro){
    ArrayList<Integer> nombresTrab = new ArrayList();   
    try{
        Connection conn = GenericoBD.startConn();
        CallableStatement cs = conn.prepareCall("{call PAC_TRABAJADOR.get_tipos_centro(?,?,?)}");
        cs.setInt(1, centro);
        cs.setString(2, "Logistica");
        cs.registerOutParameter(3, OracleTypes.CURSOR);
        cs.execute();
        ResultSet rs = (ResultSet) cs.getObject(3);
            while(rs.next()){
                nombresTrab.add(rs.getInt("idTrabajador"));
            }
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null,"Problemas al realizar query: "+e);
    }
    return nombresTrab;
}

public static String getEstadoParte(int idParte) throws Exception{
    Connection conn = GenericoBD.startConn();
    String resultado = null;
    try{
        CallableStatement cs = conn.prepareCall("{call PAC_PARTE.get_estado_parte(?,?)}");
        cs.setInt(1, idParte);
        cs.registerOutParameter(2, java.sql.Types.VARCHAR);
        cs.execute();
        resultado = cs.getString(2);
        
    }
    catch(Exception e){
        
    }
    if(!GenericoBD.dropConn(conn)){
        
    }
    return resultado;
}

    public static void insert(Parte parte) throws Exception{
        connect();
        
        String q = "insert into partes(idTrabajador, estado, fecha, kmIni, matricula) values(?, ?, ?, ?, ?)";
        PreparedStatement ps = getCon().prepareStatement(q);
        
        ps.setInt(1, Main.getIdTrabajador());
        ps.setString(2,parte.getEstado());
        ps.setDate(3, new java.sql.Date(parte.getFecha().getTime()));
        ps.setFloat(4, parte.getKmIni());
        ps.setString(5, parte.getMatricula());
        
        ps.executeUpdate();
        
        disconnect();
    }
public static Parte getPartePorID(int id) throws Exception{
    Parte p = new Parte();
    
    connect();
    String q = "select * from partes where idParte = ?";
    PreparedStatement r = getCon().prepareStatement(q);
    r.setInt(1, id);
    ResultSet rset = r.executeQuery();
    
    if (rset.next())
    {
        p.setIdParte(rset.getInt("idParte"));
        p.setFecha(rset.getDate("fecha"));
        p.setEstado(rset.getString("estado"));
        p.setMatricula(rset.getString("matricula"));
        p.setKmIni(rset.getInt("kmini"));
        p.setKmFin(rset.getInt("kmfin"));
        p.setGastoPeaje(rset.getFloat("gastopeaje"));
        p.setGastoGasoil(rset.getFloat("gastogasoil"));
        p.setGastoDieta(rset.getFloat("gastodieta"));
        p.setGastoOtros(rset.getFloat("gastootros"));
        p.setDescripcion(rset.getString("descripcion"));   
    }
    
    disconnect();

    return p;
}

public static void modificacion(Parte p) throws Exception{
    connect();     

    String q = "update PARTES set fecha = ?, gastodieta = ?, gastogasoil = ?, gastootros = ?, gastopeaje = ?, kmfin = ?, kmini = ?, descripcion = ?, matricula = ? where idParte = ? ";
    PreparedStatement ps=getCon().prepareStatement(q);
    ps.setDate(1,new java.sql.Date(p.getFecha().getTime()));
    ps.setFloat(2,p.getGastoDieta());
    ps.setFloat(3,p.getGastoGasoil());
    ps.setFloat(4,p.getGastoOtros());
    ps.setFloat(5,p.getGastoPeaje());
    ps.setFloat(6,p.getKmFin());
    ps.setFloat(7,p.getKmIni());
    ps.setString(8,p.getDescripcion());
    ps.setString(9,p.getMatricula());
    ps.setInt(10,Main.getIdParte());
    
    ps.executeUpdate();
    
    disconnect();
}

public static void validarParte(int id) throws SQLException {
    Connection conn = GenericoBD.startConn();
    String plantilla = "update PARTES set estado = 'VALIDADO' where idParte = ? and UPPER(ESTADO) like 'CERRADO'";
    PreparedStatement ps = conn.prepareStatement(plantilla);
    ps.setInt(1, id); 
    ps.executeUpdate();
    dropConn(conn);
}

public static void cerrarParte(Parte parte) throws Exception{
    connect();
    
    String q = "update PARTES set estado = 'CERRADO', kmFin=?, gastoPeaje=?, gastoDieta=?, gastoGasoil=?, gastoOtros=?, descripcion=? where idParte = ?";
    PreparedStatement r = getCon().prepareStatement(q);
    r.setFloat(1, parte.getKmFin());
    r.setFloat(2, parte.getGastoPeaje());
    r.setFloat(3, parte.getGastoDieta());
    r.setFloat(4, parte.getGastoGasoil());
    r.setFloat(5, parte.getGastoOtros());
    r.setString(6, parte.getDescripcion());
    r.setInt(7, Main.getIdParte());
    
    r.executeUpdate();
    
    disconnect();
}

public static void eliminar(int id) throws Exception {
    Connection conn = GenericoBD.startConn();
    String plantilla = "delete from PARTES where IDPARTE = ?";
    PreparedStatement sentenciaCon = conn.prepareStatement(plantilla);
    sentenciaCon.setInt(1, id);
    sentenciaCon.executeUpdate();
    try{
        conn.commit();
    }catch(Exception e){
    }
    if(!GenericoBD.dropConn(conn)){
        
    }}

public static ArrayList<Integer> rellenarComboPartesLogis(int id) throws Exception{
    ArrayList<Integer> listaIds = new ArrayList();
        Connection conn = GenericoBD.startConn();
        PreparedStatement sentenciaCon = conn.prepareStatement("select idParte from partes where idTrabajador = ? and Upper(estado) like 'ABIERTO'");
        sentenciaCon.setInt(1, id);
        ResultSet rset = sentenciaCon.executeQuery();
        while (rset.next()){
               listaIds.add(rset.getInt(1)); 
        }
        if(!GenericoBD.dropConn(conn)){
            
        }    
    return listaIds;
}    
    
public static ArrayList<Integer> recogerPartesPorID (int id, java.util.Date fecha1, java.util.Date fecha2) throws SQLException{
        Connection conn = GenericoBD.startConn();
        java.sql.Date sqlDate1 = new java.sql.Date(fecha1.getTime());
        java.sql.Date sqlDate2 = new java.sql.Date(fecha2.getTime());
        ArrayList<Integer> idspartes = new ArrayList();
        try{
            CallableStatement cs = conn.prepareCall("{call PAC_PARTE.get_ids_partes(?,?,?,?)}");

            cs.registerOutParameter(4, OracleTypes.CURSOR);
            cs.setInt(1, id);
            cs.setDate(2, sqlDate1);
            cs.setDate(3, sqlDate2);
            cs.execute();
            ResultSet rs = (ResultSet)cs.getObject(4);

            while(rs.next()){
                idspartes.add(rs.getInt("idParte"));
            }
        }
        catch(Exception e){
            
        }
        if(!GenericoBD.dropConn(conn)){
            
        }
        return idspartes;
    }

    
    public static ArrayList<Parte> queryByEstado() throws Exception{
        ArrayList<Parte> partes = new ArrayList();
        Connection conn = GenericoBD.startConn();
        
        Statement q = conn.createStatement();
        ResultSet r = q.executeQuery("select idParte, fecha, estado from partes where UPPER(estado) like 'ABIERTO'");
        
        while (r.next()){
                Parte parte = new Parte();
                parte.setIdParte(r.getInt(1));
                parte.setFecha(new java.util.Date(r.getDate(2).getTime()));
                parte.setEstado(r.getNString(3));
                
                partes.add(parte);
        }
        
        if(!GenericoBD.dropConn(conn)){
            
        }
        return partes;
    }    
}