package Modelo.BD;

import java.sql.Connection;
import java.sql.DriverManager;

public class GenericoBD {
        
    public static Connection startConn(){
        try
        {
            Connection conn;
            DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
            conn = DriverManager.getConnection("jdbc:oracle:thin:@SrvOracle:1521:orcl","daw06","daw06");
            return conn;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public static boolean dropConn(Connection conn){
        try
        {
          conn.close();
          return true;
        }
        catch(Exception e){
            return false;
        }
    }

    static Object getCon() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
