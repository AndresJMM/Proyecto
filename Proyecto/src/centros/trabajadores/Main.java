    package centros.trabajadores;

import Vista.*;
import Modelo.UML.*;
import Modelo.BD.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.xml.transform.TransformerException;


public class Main {
    
    private static boolean modificar;
    private static VistaTrabajador vt;
    private static VistaMenu vm;
    private static VistaParteAdmin vpa;
    private static VistaCrearParte vcp;
    private static VistaLogin vlog;
    private static VenAlbara val;
    private static VenParte vpl;
    private static VenShowPartes vsp;
    
    private static Parte p;
    private static Albaran a;
    private static Centro c;
    private static Trabajador t;
    private static ArrayList <Integer> listaIdCentros;
    
    
    public static void main(String[] args){
        /*vlog = new VistaLogin();
        vlog.setVisible(true);*/
        
        vm = new VistaMenu();
        vm.setVisible(true);
    }   
    
    public static ArrayList<Integer> rellenaCentros(){
        listaIdCentros = new ArrayList();
        listaIdCentros = CentroBD.getIdCentros();
        return listaIdCentros;
    }
    
    public static void mostrarPerfiles(){
        vt = new VistaTrabajador();
        vt.getReady();
    }
    
    public static void abrirInforme(){
        Informe.Controlador.Main.abrirInforme();
    }
    
    public static void rellenaDatos(String dni) throws SQLException{
        t = TrabajadorBD.getTrabajador(dni, c.getIdCentro());
        if(t!=null){
            vt.rellenar(t.getNombre(), t.getApe1(), t.getApe2(), t.getCalleTrab(), t.getPortalTrab(), t.getPisoTrab(), t.getManoTrab(), t.getTlfPersonal(), t.getMovilEmp(), t.getSalario(), t.getFechaNac());
            vt.modificar();
            modificar = true;
        }else{
            vt.rellenar(null, null, null, null, null, null, null, null, null, null, null);
            vt.nuevo();
            modificar = false;
        }
        if(t instanceof Administracion){
            vt.setTipo("Administracion");
        }else if(t instanceof Logistica){
            vt.setTipo("Logistica");
        }
    }
    
    public static void actualizarUsuario(String nombre, String ape1, String ape2, String calle, String portal, String piso, String mano, String tlfper, String movilemp, String salario, Date fecha, String tipo) throws SQLException{
        t.setNombre(nombre);
        t.setApe1(ape1);
        t.setApe2(ape2);
        t.setCalleTrab(calle);
        t.setPortalTrab(portal);
        t.setPisoTrab(piso);
        t.setManoTrab(mano);
        t.setTlfPersonal(tlfper);
        t.setMovilEmp(movilemp);
        if(salario!=null && salario.length()>0)
            t.setSalario(Float.parseFloat(salario));
        t.setFechaNac(fecha);
        
        if((t instanceof Administracion && tipo.compareToIgnoreCase("Administracion")!=0) || (t instanceof Logistica && tipo.compareToIgnoreCase("Logistica")!=0)){
            TrabajadorBD.modificacion(t, tipo);
        }else
            TrabajadorBD.modificacion(t);
        
        JOptionPane.showMessageDialog(vt, "Trabajador actualizado con éxito.");
        vt.clearAll();
    }
    
    public static void crearUsuario(String DNI, String nombre, String ape1, String ape2, String calle, String portal, String piso, String mano, String tlfper, String movilemp, String salario, Date fecha, String tipo) throws SQLException{
        if(tipo.compareToIgnoreCase("Administracion")==0)
            t = new Administracion();
        else
            t = new Logistica();
        t.setDNI(DNI);
        t.setNombre(nombre);
        t.setApe1(ape1);
        t.setApe2(ape2);
        t.setCalleTrab(calle);
        t.setPortalTrab(portal);
        t.setPisoTrab(piso);
        t.setManoTrab(mano);
        if(tlfper!=null)
            t.setTlfPersonal(tlfper);
        t.setMovilEmp(movilemp);
        if(salario!=null && salario.length()>0)
            t.setSalario(Float.parseFloat(salario));
        t.setFechaNac(fecha);
        TrabajadorBD.alta(t, c.getIdCentro(), tipo);
        JOptionPane.showMessageDialog(vt, "Trabajador creado con éxito.");
        vt.clearAll();
    }
    
    public static boolean modificar(){
        return modificar;
    }
    
    public static void borrar() throws Exception{
        if(JOptionPane.showConfirmDialog(vt, "Borrar el usuario "+t.getNombre()+" "+t.getApe1()+"\n DNI: "+t.getDNI())==0){
            TrabajadorBD.eliminar(t);
            JOptionPane.showMessageDialog(vt, "Trabajador eliminado con éxito.");
            vt.clearAll();
        }
    }
    
    public static String getCentro(int index) throws SQLException{
        c = CentroBD.getCentro(listaIdCentros.get(index));
        return c.getNombreCen();
    }
    
    public static ArrayList<Integer> datosComboVistaParte(String tipo) throws Exception{
        return TrabajadorBD.getTiposCentro(c.getIdCentro(), tipo);
    }
    
    public static void irVCP(javax.swing.JFrame v){
        v.dispose();
        vcp = new VistaCrearParte();
        vcp.setVisible(true);
    }
        
    public static void irVT(){
        vt = new VistaTrabajador();
        vt.setVisible(true);
    }
    
    public static void irVPA(){
        vpa = new VistaParteAdmin();
        vpa.setVisible(true); 
    }    
    
    public static void volverALogin(javax.swing.JFrame v){
        v.dispose();
        vlog = new VistaLogin();
        vlog.setVisible(true); 
    }
    
    public static void irVM(javax.swing.JFrame v){
        v.dispose();
        if(vm == null)
            vm = new VistaMenu();
        vm.setVisible(true);
    }
    
    public static boolean validar(String DNI,String nombre,String ape1,String ape2,String calle,String portal,String piso,String mano,String tlfmovilemp,String tlfper,String salario){
        boolean validado = true;
        String informeinvalidos=null;
        Pattern pat;
        Matcher mat;
            if(DNI != null){
            pat = Pattern.compile("^[0-9]{8}-[a-zA-Z]{1}$");
            mat = pat.matcher(DNI);
                if (!mat.find()) {validado = false; informeinvalidos += "DNI invalido";}
            }else{
                validado = false;
            }
            
            if(nombre != null){
            pat = Pattern.compile("^[A-Z]{1}[a-z]$");
            mat = pat.matcher(nombre);
                if (!mat.find()) {validado = false; informeinvalidos += "\n Nombre invalido";}
            }else{
                validado = false;
            }
            
            if(ape1 != null){
            pat = Pattern.compile("^[A-Z]{1}[a-z]$");
            mat = pat.matcher(ape1);
                if (!mat.find()) {validado = false; informeinvalidos += "\n 1er apellido invalido";}
            }else{
                validado = false;
            }
          
            if(ape2 != null){
            pat = Pattern.compile("^[A-Z]{1}[a-z]$");
            mat = pat.matcher(ape2);
                if (!mat.find()) {validado = false; informeinvalidos += "\n 2º apellido invalido";}
            }else{
                validado = false;
            }
            
            if(calle != null){
            pat = Pattern.compile("^(C/)[A-Z]{1}[a-z]$");
            mat = pat.matcher(calle);
                if (!mat.find()) {validado = false; informeinvalidos += "\n Calle invalida";}
            }
            
            if(portal != null){
            pat = Pattern.compile("^[1-100]$");
            mat = pat.matcher(portal);
                if (!mat.find()) {validado = false; informeinvalidos += "\n Portal invalido";}
            }
            
            if(piso != null){
            pat = Pattern.compile("^[1-10]$");
            mat = pat.matcher(piso);
                if (!mat.find()) {validado = false; informeinvalidos += "\n Piso invalido";}
            }
            
            if(mano != null){
            pat = Pattern.compile("^[ABCD]{1}$");
            mat = pat.matcher(mano);
                if (!mat.find()) {validado = false; informeinvalidos += "\n Mano invalida";}
            }
            
            if(tlfmovilemp != null){
            pat = Pattern.compile("^[6]{1}[0-9]{8}$");
            mat = pat.matcher(tlfmovilemp);
                if (!mat.find()) {validado = false; informeinvalidos += "\n Telefono movil empresa invalido";}
            }else{
                validado = false;
            }
            
            if(tlfper != null){
            pat = Pattern.compile("^[6]{1}[0-9]{8}$");
            mat = pat.matcher(tlfper);
                if (!mat.find()) {
                    validado = false; informeinvalidos += "\n Telefono personal invalido";}
            }
            
            if(salario == null){
                validado = false; informeinvalidos += "\n Salario invalido";}
            
            JOptionPane.showMessageDialog(null,"ERRORES:\n" + informeinvalidos);    
            
            return validado;
    }
    
    private static void automatizarInforme(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Date result = cal.getTime();
        DateFormat df = new SimpleDateFormat("MM-yyyy");
        String prueba = df.format(result);
        if(!Informe.BD.InformeBD.informeRealizado(prueba)){
            JOptionPane.showMessageDialog(vt, "Informe del mes pasado no detectado, \n iniciando proceso automáticamente.");
            try {
                Informe.Controlador.Main.crearInforme(prueba);
            } catch (TransformerException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void buscarTrabajadorPorUsuario(String usuario, String pass){
        t = TrabajadorBD.getTrabajadorPorUsuario(usuario,pass);
        if(t != null)
            irVM(vlog);
        else
            JOptionPane.showMessageDialog(vlog, "El usuario o contraseña no son correctos");
    }
       
    
    public static void buscarPartePorID(int id) throws SQLException{
        p = ParteBD.getPartePorID(id);
        if(p!=null){
            //vcp.rellenarVCP(p.getFecha(), p.getGastoDieta(), p.getGastoGasolina(), p.getGastoOtros() ,p.getGastoPeaje(), p.getKmFin(), p.getKmIni(), p.getMatricula(), p.getDescripcion());
            ParteBD.modificacion(p);
    }}
    
    public static ArrayList<Integer> recogerIdTrabajador(String tipo) throws Exception{
        return TrabajadorBD.getTiposCentro(c.getIdCentro(), tipo);
    }
        
    public static void borrarParte(int id) throws Exception{
        ParteBD.eliminar(id);
        JOptionPane.showMessageDialog(vpa, "Parte eliminado con éxito.");
        }   
    
    public static ArrayList<Integer> recogerPartesPorID(int idTrab, Date fecha1, Date fecha2) throws Exception{
        return ParteBD.recogerPartesPorID(idTrab,fecha1,fecha2);
    }
    
    public static void validarParte (int id) throws SQLException{
        ParteBD.validarParte(id);
        JOptionPane.showMessageDialog(null, "Parte validado con éxito");
    }
    
    // Funciones de la ventana val
    
    public static void ejecutar_val(){
        val = new VenAlbara();
        val.setVisible(true);
    }
    
    public static ArrayList<Panel> saveRows(ArrayList<Panel> paneles) throws ParseException{
        for (int i = 0; i < paneles.size(); i++) {
            a = new Albaran();
            a.setIdAlbaran(Integer.parseInt(paneles.get(i).getNumAlbaran().getText()));
            //albaran.setParte(parte);
            
            String string = paneles.get(i).getHora().getSelectedItem().toString() + ":" + paneles.get(i).getMin().getSelectedIndex();
            SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
            java.util.Date d = dateFormat.parse(string);
            java.sql.Date parsedDate = new java.sql.Date(d.getTime());
            Timestamp horaSalida = new Timestamp(parsedDate.getTime());
            
            a.setHoraSalida(horaSalida);
            paneles.get(i).getHora().getSelectedItem().toString();
            paneles.get(i).getMin().getSelectedIndex();
            
            paneles.get(i).getMin().getSelectedItem();
            paneles.get(i).getHora2().getSelectedItem();
            paneles.get(i).getMin2().getSelectedItem();
        }
        
        return paneles;
    }
    
    // Funciones de la ventana vpl
    
    public static void executeVenParte(){
        vpl = new VenParte();
        vpl.setVisible(true);
    }
    
    public static void addParte(Calendar fecha, String matricula, float kmIni, float kmFin, float peaje, float dieta, float gasolina, float autopista, float otros, String descripcion) throws Exception{
        parte = new Parte();
        parte.setEstado("abierto");
        parte.setFecha(fecha.getTime());
        parte.setMatricula(matricula);
        parte.setKmIni(kmIni);
        parte.setKmFin(kmFin);
        parte.setGastoPeaje(peaje);
        parte.setGastoDieta(dieta);
        parte.setGastoGasoil(gasolina);
        parte.setGastoAutopista(autopista);
        parte.setGastoOtros(otros);
        parte.setDescripcion(descripcion);
        
        ParteBD.insert(parte);
    }
    
    public static int getIdTrabajador(){
        return TrabajadorBD.getTrabajador().getIdTrabajador();
    }
    
    // Funciones de la ventana vsp
    
    public static void executeVenShowPartes(){
        vsp = new VenShowPartes();
        vsp.setVisible(true);
    }
    
    public static ArrayList<Parte> getPartesAbiertos() throws Exception{
        return ParteBD.queryByEstado();
    }
    
    public static void close(){
        System.exit(0);
    }
}
