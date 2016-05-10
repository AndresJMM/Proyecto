package Modelo.BD;

import Modelo.UML.Administracion;
import Modelo.UML.Logistica;
import Modelo.UML.Trabajador;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

public class TrabajadorBD {
        
    public static Trabajador getTrabajador(String DNI, int idCentro) throws SQLException{
        Connection conn = GenericoBD.startConn();
        Trabajador t = null;
        try{
            CallableStatement cs = conn.prepareCall("{call PAC_TRABAJADOR.get_trabajador(?,?,?)}");
            cs.registerOutParameter(3, OracleTypes.CURSOR);
            cs.setString(1, DNI);
            cs.setInt(2, idCentro);
            cs.execute();
            ResultSet rs = (ResultSet)cs.getObject(3);
            if(rs.next()){
                if(rs.getInt("IDCENTRO")==idCentro){
                    if(rs.getString("TIPO").compareToIgnoreCase("Logistica")!=0)
                        t = new Administracion();
                    else
                        t = new Logistica();
                    t.setIdTrabajador(rs.getInt("IDTRABAJADOR"));
                    t.setDNI(rs.getString("DNI"));
                    t.setNombre(rs.getString("NOMBRE"));
                    t.setApe1(rs.getString("APE1"));
                    t.setApe2(rs.getString("APE2"));
                    t.setMovilEmp(rs.getString("MOVILEMP"));
                    t.setCalleTrab(rs.getString("CALLE"));
                    t.setPortalTrab(rs.getString("PORTAL"));
                    t.setPisoTrab(rs.getString("PISO"));
                    t.setManoTrab(rs.getString("MANO"));
                    if(rs.getDate("FECHANAC") != null)
                        t.setFechaNac(rs.getDate("FECHANAC"));
                    if(rs.getBigDecimal("SALARIO") != null)
                        t.setSalario(rs.getBigDecimal("SALARIO").floatValue());
                    if(rs.getString("TLFPERSONAL") != null)
                        t.setTlfPersonal(rs.getString("TLFPERSONAL"));   
                }
            }
        }
        catch(Exception e){
            System.out.println("Problemas al realizar query: "+e+"\n");
            System.out.print("ha sido aqui");
        }
        if(!GenericoBD.dropConn(conn)){
            System.out.println("Problemas al cerrar");
        }
        return t;
    }
    
    public static ArrayList getTrabajadoresCentro(int centro) throws SQLException{
        Connection conn = GenericoBD.startConn();
        ArrayList<String> trabajadores = new ArrayList();
        try{
            CallableStatement cs = conn.prepareCall("{call PAC_TRABAJADOR.get_trabajadores_centro(?,?)}");

            cs.registerOutParameter(2, OracleTypes.CURSOR);
            cs.setInt(1, centro);

            cs.execute();
            ResultSet rs = (ResultSet)cs.getObject(2);

            while(rs.next()){
                trabajadores.add(rs.getString("DNI"));
            }
        }
        catch(Exception e){
            System.out.println("Problemas al realizar query: "+e);
        }
        if(!GenericoBD.dropConn(conn)){
            System.out.println("Problemas al cerrar");
        }
        return trabajadores;
    }
    
    public static ArrayList getTiposCentro(int centro, String tipo) throws SQLException{
        Connection conn = GenericoBD.startConn();
        ArrayList<String> trabajadores = new ArrayList();
        try{
            CallableStatement cs = conn.prepareCall("{call PAC_TRABAJADOR.get_tipos_centro(?,?,?)}");

            cs.registerOutParameter(3, OracleTypes.CURSOR);
            cs.setInt(1, centro);
            cs.setString(2, tipo);

            cs.execute();
            ResultSet rs = (ResultSet)cs.getObject(3);

            while(rs.next()){
                trabajadores.add(rs.getString("DNI"));
            }
        }
        catch(Exception e){
            System.out.println("Problemas al realizar query: "+e);
        }
        if(!GenericoBD.dropConn(conn)){
            System.out.println("Problemas al cerrar");
        }
        return trabajadores;
    }
    
    
    public static Trabajador getTrabajadorPorUsuario(String usuario,String pass){
        Connection conn = GenericoBD.startConn();
        Trabajador t = null;
        try{
            CallableStatement cs = conn.prepareCall("{call PAC_TRABAJADOR.get_trabajador_usuario(?,?,?)}");
            cs.registerOutParameter(3, OracleTypes.CURSOR);
            cs.setString(1, usuario);
            cs.setString(2, pass);
            cs.execute();
            ResultSet rs = (ResultSet)cs.getObject(3);
            if(rs.next()==true){
                if(rs.getString("TIPO").compareToIgnoreCase("Logistica")!=0)
                    t = new Administracion();
                else
                    t = new Logistica();
                t.setIdTrabajador(rs.getInt("IDTRABAJADOR"));
                t.setDNI(rs.getString("DNI"));
                t.setNombre(rs.getString("NOMBRE"));
                t.setApe1(rs.getString("APE1"));
                t.setApe2(rs.getString("APE2"));
                t.setMovilEmp(rs.getString("MOVILEMP"));
                t.setCalleTrab(rs.getString("CALLE"));
                t.setPortalTrab(rs.getString("PORTAL"));
                t.setPisoTrab(rs.getString("PISO"));
                t.setManoTrab(rs.getString("MANO"));
                if(rs.getDate("FECHANAC") != null)
                    t.setFechaNac(rs.getDate("FECHANAC"));
                if(rs.getBigDecimal("SALARIO") != null)
                    t.setSalario(rs.getBigDecimal("SALARIO").floatValue());
                if(rs.getString("TLFPERSONAL") != null)
                    t.setTlfPersonal(rs.getString("TLFPERSONAL"));   
            }
        }
        catch(Exception e){
            System.out.println("Problemas al buscar usuario: "+e);
        }
        if(!GenericoBD.dropConn(conn)){
            System.out.println("Problemas al cerrar");
        }
        return t;
    }
    
    public static void alta(Trabajador t, int idCentro, String tipo) throws SQLException{
        Connection conn = GenericoBD.startConn();
        
        String plantilla = "INSERT INTO TRABAJADORES (idCentro, idTipo, DNI, nombre, ape1, ape2, fechaNac, salario, movilEmp, tlfPersonal, calle, portal, piso, mano) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement sentenciaCon = conn.prepareStatement(plantilla);
        sentenciaCon.setInt(1,idCentro);
        if(tipo.compareToIgnoreCase("Logistica")==0)
            sentenciaCon.setInt(2,2);
        else
            sentenciaCon.setInt(2,1);
        sentenciaCon.setString(3,t.getDNI());
        sentenciaCon.setString(4,t.getNombre());
        sentenciaCon.setString(5,t.getApe1());
        sentenciaCon.setString(6,t.getApe2());
        if(t.getFechaNac()!=null)
            sentenciaCon.setDate(7,new java.sql.Date(t.getFechaNac().getTime()));
        else   
            sentenciaCon.setDate(7,null);
        if(t.getSalario()!=null)
            sentenciaCon.setFloat(8,t.getSalario());
        else   
            sentenciaCon.setString(8,null);
            sentenciaCon.setString(9,t.getMovilEmp());
            sentenciaCon.setString(10,t.getTlfPersonal());
            sentenciaCon.setString(11,t.getCalleTrab());
            sentenciaCon.setString(12,t.getPortalTrab());
            sentenciaCon.setString(13,t.getPisoTrab());
            sentenciaCon.setString(14,t.getManoTrab());
            sentenciaCon.executeUpdate();
        try{
            conn.commit();
            System.out.print("Llego");
        }catch(Exception e){
        }
            
        if(!GenericoBD.dropConn(conn)){
            System.out.println("Problemas al cerrar");
        }
    }
	
    public static void modificacion (Trabajador t) throws SQLException{  
        Connection conn = GenericoBD.startConn();     

        String plantilla = "update TRABAJADORES set  nombre = ?, ape1 = ?, ape2 = ?, fechaNac = ?, salario = ?, movilEmp = ?, tlfPersonal = ?, calle = ?, portal = ?, piso = ?, mano = ? where DNI= ? ";
        PreparedStatement ps=conn.prepareStatement(plantilla);
        ps.setString(1,t.getNombre());
        ps.setString(2,t.getApe1());
        ps.setString(3,t.getApe2());
        if(t.getFechaNac()!=null)
            ps.setDate(4,new java.sql.Date(t.getFechaNac().getTime()));
        else   
            ps.setDate(4,null);
        if(t.getSalario()!=null)
            ps.setFloat(5,t.getSalario());
        else   
            ps.setString(5,null);
        ps.setString(6,t.getMovilEmp());
        ps.setString(7,t.getTlfPersonal());
        ps.setString(8,t.getCalleTrab());
        ps.setString(9,t.getPortalTrab());
        ps.setString(10,t.getPisoTrab());
        ps.setString(11,t.getManoTrab());
        ps.setString(12,t.getDNI());
        ps.executeUpdate();
        if(!GenericoBD.dropConn(conn)){
            System.out.println("Problemas al cerrar");
        }
    }
    public static void modificacion (Trabajador t, String tipo) throws SQLException{  
        Connection conn = GenericoBD.startConn();     

        String plantilla = "update TRABAJADORES set  nombre = ?, ape1 = ?, ape2 = ?, fechaNac = ?, salario = ?, movilEmp = ?, tlfPersonal = ?, calle = ?, portal = ?, piso = ?, mano = ? , idTipo = ? where DNI= ? ";
        PreparedStatement ps=conn.prepareStatement(plantilla);
        ps.setString(1,t.getNombre());
        ps.setString(2,t.getApe1());
        ps.setString(3,t.getApe2());
        if(t.getFechaNac()!=null)
            ps.setDate(4,new java.sql.Date(t.getFechaNac().getTime()));
        else   
            ps.setDate(4,null);
        if(t.getSalario()!=null)
            ps.setFloat(5,t.getSalario());
        else   
            ps.setString(5,null);
        ps.setString(6,t.getMovilEmp());
        ps.setString(7,t.getTlfPersonal());
        ps.setString(8,t.getCalleTrab());
        ps.setString(9,t.getPortalTrab());
        ps.setString(10,t.getPisoTrab());
        ps.setString(11,t.getManoTrab());
        if(tipo.compareToIgnoreCase("Logistica")==0)
            ps.setInt(12,2);
        else
            ps.setInt(12,1);
        ps.setString(13,t.getDNI());
        ps.executeUpdate();
        if(!GenericoBD.dropConn(conn)){
            System.out.println("Problemas al cerrar");
        }
    }
    public static void modificacion (Trabajador t, int idCentro) throws SQLException{  
        Connection conn = GenericoBD.startConn();     

        String plantilla = "update TRABAJADORES set  nombre = ?, ape1 = ?, ape2 = ?, fechaNac = ?, salario = ?, movilEmp = ?, tlfPersonal = ?, calle = ?, portal = ?, piso = ?, mano = ?, idCentro = ?,  where DNI= ? ";
        PreparedStatement ps=conn.prepareStatement(plantilla);
        ps.setString(1,t.getNombre());
        ps.setString(2,t.getApe1());
        ps.setString(3,t.getApe2());
        if(t.getFechaNac()!=null)
            ps.setDate(4,new java.sql.Date(t.getFechaNac().getTime()));
        else   
            ps.setDate(4,null);
        if(t.getSalario()!=null)
            ps.setFloat(5,t.getSalario());
        else   
            ps.setString(5,null);
        ps.setString(6,t.getMovilEmp());
        ps.setString(7,t.getTlfPersonal());
        ps.setString(8,t.getCalleTrab());
        ps.setString(9,t.getPortalTrab());
        ps.setString(10,t.getPisoTrab());
        ps.setString(11,t.getManoTrab());
        ps.setInt(12,idCentro);
        ps.setString(13,t.getDNI());
        ps.executeUpdate();
        if(!GenericoBD.dropConn(conn)){
            System.out.println("Problemas al cerrar");
        }
    }

    public static void eliminar(Trabajador t) throws Exception {
        Connection conn = GenericoBD.startConn();
        String plantilla = "delete from TRABAJADORES where IDTRABAJADOR= ?";
        PreparedStatement sentenciaCon = conn.prepareStatement(plantilla);
        sentenciaCon.setInt(1,t.getIdTrabajador());
        sentenciaCon.executeUpdate();
        try{
            conn.commit();
        }catch(Exception e){
        }
        if(!GenericoBD.dropConn(conn)){
            System.out.println("Problemas al cerrar");
        }
    }
}
