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
        
    /**
     * Metodo que llama a un procedimiento en el cual selecciona todos los datos de un trabajador seg�n su DNI y su tipo 
     * @param DNI
     * @param idCentro
     * @return
     * @throws SQLException
     */
    public static Trabajador getTrabajador(String DNI) throws SQLException{
        Connection conn = GenericoBD.startConn();
        Trabajador t = null;
        try{
            CallableStatement cs = conn.prepareCall("{call PAC_TRABAJADOR.get_trabajador(?,?)}");
            cs.registerOutParameter(2, OracleTypes.CURSOR);
            cs.setString(1, DNI);
            cs.execute();
            ResultSet rs = (ResultSet)cs.getObject(2);
            if(rs.next()){
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
            
            
        }
        if(!GenericoBD.dropConn(conn)){
            
        }
        return t;
    }
    
    /**
     * Metodo que llama a un procedimiento en el cual se selecciona los datos de un trabajador dependiendo del centro de trabajo
     * @param centro
     * @return
     * @throws SQLException
     */
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
            
        }
        if(!GenericoBD.dropConn(conn)){
            
        }
        return trabajadores;
    }
    
    /**
     * Metodo que llama a un procedimiento en el 
     * @param tipo
     * @return
     * @throws SQLException
     */
    public static ArrayList getTiposCentro(String tipo) throws SQLException{
        Connection conn = GenericoBD.startConn();
        ArrayList<String> trabajadores = new ArrayList();
        try{
            CallableStatement cs = conn.prepareCall("{call PAC_TRABAJADOR.get_tipos_centro(?,?)}");

            cs.registerOutParameter(2, OracleTypes.CURSOR);
            cs.setString(1, tipo);

            cs.execute();
            ResultSet rs = (ResultSet)cs.getObject(2);

            while(rs.next()){
                trabajadores.add(rs.getString("idTrabajador"));
            }
        }
        catch(Exception e){
            
        }
        if(!GenericoBD.dropConn(conn)){
            
        }
        return trabajadores;
    }
    
    /**
     * Metodo que llama a un procedimiento en el cual se selecciona todos los datos de un trabajador dependiendo de su usuario y contrase�a en la ventana login
     * @param usuario
     * @param pass
     * @return
     */
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
                if (rs.getInt("idTipo") == 1){
                    t = new Administracion();
                }
                else{
                    t = new Logistica();
                }
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
            
        }
        if(!GenericoBD.dropConn(conn)){
            
        }
        return t;
    }
    
    /**
     * Metodo que inserta todos los datos de un trabajador en la base de datos
     * @param t
     * @param idCentro
     * @param tipo
     * @throws SQLException
     */
    public static void alta(Trabajador t, int idCentro, String tipo, String user, String pass) throws SQLException{
        Connection conn = GenericoBD.startConn();
        
                
        String plantilla = "INSERT INTO LOGINS(usuario,pass) VALUES (?,?)";
        PreparedStatement sentenciaCon=conn.prepareStatement(plantilla);
        sentenciaCon.setString(1,user);
        sentenciaCon.setString(2,pass);
        sentenciaCon.executeUpdate();
        try{
            conn.commit();
        }catch(Exception e){
        }
        
        plantilla = "SELECT IDLOGIN FROM LOGINS WHERE USUARIO = ? AND PASS = ?";
        sentenciaCon=conn.prepareStatement(plantilla);
        sentenciaCon.setString(1,user);
        sentenciaCon.setString(2,pass);
        sentenciaCon.executeUpdate();
        ResultSet rs = sentenciaCon.getResultSet();
        rs.next();
        String idLogin = rs.getString("IDLOGIN");
        
        plantilla = "INSERT INTO TRABAJADORES (idCentro, idTipo, DNI, nombre, ape1, ape2, fechaNac, salario, movilEmp, tlfPersonal, calle, portal, piso, mano, idLogin) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        sentenciaCon = conn.prepareStatement(plantilla);
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
        sentenciaCon.setString(15,idLogin);
        sentenciaCon.executeUpdate();
        try{
            conn.commit();
        }catch(Exception e){
        }
            
        if(!GenericoBD.dropConn(conn)){
            
        }
    }
	
    /**
     * Metodo que modifica los datos de un trabajador dependiendo de su DNI
     * @param t
     * @throws SQLException
     */
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
            
        }
    }

    /**
     * Metodo que modifica el tipo de trabajador segun su DNI
     * @param t
     * @param tipo
     * @throws SQLException
     */
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
            
        }
    }

    /**
     * Metodo que modifica el ID de centro de un trabajador dependiendo de su DNI
     * @param t
     * @param idCentro
     * @throws SQLException
     */
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
            
        }
    }
    public static void modificacion (Trabajador t, String tipo, int idCentro, String user, String pass) throws SQLException{  
        Connection conn = GenericoBD.startConn();     

        String plantilla = "update TRABAJADORES set  nombre = ?, ape1 = ?, ape2 = ?, fechaNac = ?, salario = ?, movilEmp = ?, tlfPersonal = ?, calle = ?, portal = ?, piso = ?, mano = ?, idTipo = ?, idCentro = ? where DNI= ? ";
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
        ps.setInt(13,idCentro);
        ps.setString(14,t.getDNI());
        ps.executeUpdate();
        
        plantilla = "update LOGINS set usuario = ?, pass = ? where idLogin =(Select idLogin from TRABAJADORES where IDTRABAJADOR =?)";
        ps=conn.prepareStatement(plantilla);
        ps.setString(1,user);
        ps.setString(2,pass);
        ps.setInt(3,t.getIdTrabajador());
        ps.executeUpdate();
        
        if(!GenericoBD.dropConn(conn)){
            
        }
    }
    
      
    public static String[] getTrabajadorExtra(int idTrabajador) throws SQLException{
        Connection conn = GenericoBD.startConn();
        String[] datos = new String[3];
        try{
            CallableStatement cs = conn.prepareCall("{call PAC_TRABAJADOR.get_trabajador_extra(?,?,?,?)}");

            cs.setInt(1, idTrabajador);
            cs.registerOutParameter(2, java.sql.Types.NUMERIC);
            cs.registerOutParameter(3, java.sql.Types.VARCHAR);
            cs.registerOutParameter(4, java.sql.Types.VARCHAR);

            cs.execute();
            datos[0] = cs.getObject(2).toString();
            datos[1] = cs.getObject(3).toString();
            datos[2] = cs.getObject(4).toString();
        }
        catch(Exception e){
            
        }
        if(!GenericoBD.dropConn(conn)){
            
        }
        return datos;
    }
            

    /**
     * Metodo que elimina un trabajador dependiendo de su ID
     * @param t
     * @throws Exception
     */
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
            
        }
    }
}
