package Modelo.BD;

import java.sql.Connection;
import java.sql.DriverManager;

public class GenericoBD {
    
    private static Connection  connection;
        
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
    
    public static void connect() throws Exception{
        Class.forName("oracle.jdbc.OracleDriver");
        String user = "daw06";
        String pass = "daw06";
        String url = "jdbc:oracle:thin:@SrvOracle:1521:orcl";
        connection =   DriverManager.getConnection(url,user,pass);
    }
     
    public static void disconnect() throws Exception{
        connection.close();
    }

    public static Connection getCon(){
        return connection;
    }
}
