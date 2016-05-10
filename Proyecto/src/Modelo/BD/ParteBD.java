/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.BD;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import oracle.jdbc.OracleTypes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import Modelo.UML.Parte;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 *
 * @author andrés
 */
public class ParteBD {
    
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
public static Parte getPartePorID(int id) throws SQLException{
    Parte p = new Parte();
    try{
        Connection conn = GenericoBD.startConn();
        String plantilla = "select * from partes where idParte = ?";
        PreparedStatement sentenciaCon = GenericoBD.startConn().prepareStatement(plantilla);
        sentenciaCon.setInt(1, id);
        ResultSet rset = sentenciaCon.executeQuery();
        if (rset.next())
        {
            p.setFecha(rset.getDate("fecha"));
            p.setMatricula(rset.getString("matricula"));
            p.setKmIni(rset.getInt("kmini"));
            p.setKmFin(rset.getInt("kmfin"));
            p.setGastoPeaje(rset.getInt("gastopeaje"));
            p.setGastoGasolina(rset.getInt("gastogasolina"));
            p.setGastoDieta(rset.getInt("gastodieta"));
            p.setGastoOtros(rset.getInt("gastootros"));
            p.setDescripcion(rset.getString("descripcion"));
            GenericoBD.dropConn(conn);
        }
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null,"Problemas al realizar query: "+e);
    }
    return p;
}

public static void modificacion(Parte p) throws SQLException{
    Connection conn = GenericoBD.startConn();     

    String plantilla = "update PARTES set fecha = ?, gastodieta = ?, gastogasolina = ?, gastootros = ?, gastopeaje = ?, kmfin = ?, kmini = ?, descripcion = ?, matricula = ? where idParte = ? ";
    PreparedStatement ps=conn.prepareStatement(plantilla);
    ps.setDate(1,new java.sql.Date(p.getFecha().getTime()));
    ps.setInt(2,p.getGastoDieta());
    ps.setInt(3,p.getGastoGasolina());
    ps.setInt(4,p.getGastoOtros());
    ps.setInt(5,p.getGastoPeaje());
    ps.setInt(6,p.getKmFin());
    ps.setInt(7,p.getKmIni());
    ps.setString(8,p.getDescripcion());
    ps.setString(9,p.getMatricula());
    ps.setInt(10,p.getIdParte());
    ps.executeUpdate();
    if(!GenericoBD.dropConn(conn)){
        System.out.println("Problemas al cerrar");
    }
}

public static void validarParte(int id) throws SQLException {
    Connection conn = GenericoBD.startConn();
    String plantilla = "update PARTES set estado = 'VALIDADO' where idParte = ?";
    PreparedStatement ps = conn.prepareStatement(plantilla);
    ps.setInt(1, id); 
    ps.executeUpdate();
    if(!GenericoBD.dropConn(conn)){
        System.out.println("Problemas al cerrar");
}}

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
        System.out.println("Problemas al cerrar");
    }}
    
public static ArrayList<Integer> recogerPartesPorID (int id, Date fecha1, Date fecha2) throws SQLException{
        Connection conn = GenericoBD.startConn();
        java.sql.Date sqlDate1 = new java.sql.Date(fecha1.getTime());
        java.sql.Date sqlDate2 = new java.sql.Date(fecha2.getTime());
        ArrayList<Integer> idspartes = new ArrayList();
        try{
            CallableStatement cs = conn.prepareCall("{call PAC_TRABAJADOR.get_ids_parte(?,?,?,?)}");

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
            System.out.println("Problemas al realizar query: "+e);
        }
        if(!GenericoBD.dropConn(conn)){
            System.out.println("Problemas al cerrar");
        }
        return idspartes;
    }

    
    public static ArrayList<Parte> queryByEstado() throws Exception{
        ArrayList<Parte> partes = new ArrayList();
        Connection conn = GenericoBD.startConn();
        
        Statement q = conn.createStatement();
        ResultSet r = q.executeQuery("select idParte, fecha, estado from partes where estado = 'abierto'");
        
        while (r.next()){
                Parte parte = new Parte();
                parte.setIdParte(r.getInt(1));
                parte.setFecha(new java.util.Date(r.getDate(2).getTime()));
                parte.setEstado(r.getNString(3));
                
                partes.add(parte);
        }
        
        if(!GenericoBD.dropConn(conn)){
            System.out.println("Problemas al cerrar");
        }
        return partes;
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
