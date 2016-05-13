package Modelo.BD;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CocheBD extends GenericoBD{
    
    public static ArrayList<String> queryAll() throws Exception{
        ArrayList<String> matriculas = new ArrayList();
        
        connect();
            Statement q = getCon().createStatement();
            ResultSet r = q.executeQuery("select matricula from coches");
            while (r.next()){
                   matriculas.add(r.getString(1)); 
            }
        disconnect();
        
        return matriculas;
    }
}
