    package Controladora;

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
    private static VistaParteLogis vpl;
    private static VistaLogin vlog;
    private static VistaAlbaran va;
    private static VistaCrearParte vcp;
    private static VistaCerrarParte vclp;
    private static VistaModificarParte vmp;
    
    private static Parte p;
    private static Albaran a;
    private static Centro c;
    private static Trabajador t;
    private static Trabajador tLog;
    private static ArrayList <Integer> listaIdCentros;
    private static ArrayList <Integer> listaIdPartes;
    private static int idPart;
    
    /**
     *
     * @param args
     */
    public static void main(String[] args){
        vlog = new VistaLogin();
        vlog.setVisible(true);
    }   
    
    /**
     * Metodo que llamamos desde la vistaTrabajador para rellenar los centros
     * @return
     */
    public static ArrayList<Integer> rellenaCentros(){
        listaIdCentros = new ArrayList();
        listaIdCentros = CentroBD.getIdCentros();
        return listaIdCentros;
    }
    
    /**
     *
     */
    public static void mostrarPerfiles(){
        vt = new VistaTrabajador();
        vt.getReady();
    }
    
    /**
     * Metodo que abre los informes desde su propio controlador
     */
    public static void abrirInforme(){
        Informe.Controlador.Main.abrirInforme();
    }
    
    /**
     * Metodo que rellena los datos de la ventana trabajador
     * @param dni
     * @throws SQLException
     */
    public static void rellenaDatos(String dni) throws SQLException{
        t = TrabajadorBD.getTrabajador(dni);
        if(t!=null){
        String[] datos = TrabajadorBD.getTrabajadorExtra(t.getIdTrabajador());
            vt.rellenar(t.getNombre(), t.getApe1(), t.getApe2(), t.getCalleTrab(), t.getPortalTrab(), t.getPisoTrab(), t.getManoTrab(), t.getTlfPersonal(), t.getMovilEmp(), t.getSalario(), t.getFechaNac(), listaIdCentros.indexOf(Integer.valueOf(datos[0])), datos[1], datos[2]);
            vt.modificar();
            modificar = true;
        }else{
            vt.rellenar(null, null, null, null, null, null, null, null, null, null, null,-1,null,null);
            vt.nuevo();
            modificar = false;
        }
        if(t instanceof Administracion){
            vt.setTipo("Administracion");
        }else if(t instanceof Logistica){
            vt.setTipo("Logistica");
        }
    }
    
    /**
     * Metodo que actualiza los datos de la ventana del trabajador
     * @param nombre
     * @param ape1
     * @param ape2
     * @param calle
     * @param portal
     * @param piso
     * @param mano
     * @param tlfper
     * @param movilemp
     * @param salario
     * @param fecha
     * @param tipo
     * @throws SQLException
     */
    public static void actualizarUsuario(String nombre, String ape1, String ape2, String calle, String portal, String piso, String mano, String tlfper, String movilemp, String salario, Date fecha, String tipo, int idCentro, String user, String pass) throws SQLException{
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
        
        TrabajadorBD.modificacion(t, tipo, idCentro, user, pass);
        
        if((t instanceof Administracion && tipo.compareToIgnoreCase("Administracion")!=0) || (t instanceof Logistica && tipo.compareToIgnoreCase("Logistica")!=0)){
            TrabajadorBD.modificacion(t, tipo);
        }else
            TrabajadorBD.modificacion(t);
        
        JOptionPane.showMessageDialog(vt, "Trabajador actualizado con éxito.");
        vt.clearAll();
    }
    
    /**
     * Metodo que crea un trabajador desde la ventana trabajador
     * @param DNI
     * @param nombre
     * @param ape1
     * @param ape2
     * @param calle
     * @param portal
     * @param piso
     * @param mano
     * @param tlfper
     * @param movilemp
     * @param salario
     * @param fecha
     * @param tipo
     * @throws SQLException
     */
    public static void crearUsuario(String DNI, String nombre, String ape1, String ape2, String calle, String portal, String piso, String mano, String tlfper, String movilemp, String salario, Date fecha, String tipo, int idCentro, String user, String pass) throws SQLException{
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
        TrabajadorBD.alta(t, c.getIdCentro(), tipo, user, pass);
        JOptionPane.showMessageDialog(vt, "Trabajador creado con éxito.");
        vt.clearAll();
    }
    
    /**
     * Metodo booleano de modificar
     * @return
     */
    public static boolean modificar(){
        return modificar;
    }
    
    /**
     * Metodo que borra un trabajador
     * @throws Exception
     */
    public static void borrar() throws Exception{
        if(JOptionPane.showConfirmDialog(vt, "Borrar el usuario "+t.getNombre()+" "+t.getApe1()+"\n DNI: "+t.getDNI())==0){
            TrabajadorBD.eliminar(t);
            JOptionPane.showMessageDialog(vt, "Trabajador eliminado con éxito.");
            vt.clearAll();
        }
    }
    
    /**
     * Metodo que obtiene un centro
     * @param index
     * @return
     * @throws SQLException
     */
    public static String getCentro(int index) throws SQLException{
        c = CentroBD.getCentro(listaIdCentros.get(index));
        return c.getNombreCen();
    }
    
    /**
     * Metodo que rellena un combobox en la ventana parte
     * @param tipo
     * @return
     * @throws Exception
     */
    public static ArrayList<Integer> datosComboVistaParte(String tipo) throws Exception{
        return TrabajadorBD.getTiposCentro(tipo);
    }
    
    /**
     * Metodo que nos lleva a la ventana de crear parte
     * @param v
     */
    public static void irVCP(javax.swing.JFrame v){
        v.dispose();
        vcp = new VistaCrearParte();
        vcp.setVisible(true);
    }
    
    /**
     * Metodo que nos lleva a la ventana de crear parte sin destruir la ventana
     */
    public static void irVCP(){
        vcp = new VistaCrearParte();
        vcp.setVisible(true);
    }
    
    /**
     * Metodo que nos lleva a la ventana de modificar parte
     */
    public static void irVMP(){
        vmp = new VistaModificarParte();
        vmp.setVisible(true);
    }
        
    /**
     * Metodo que nos lleva a la ventana de trabajador
     */
    public static void irVT(){
        vt = new VistaTrabajador();
        vt.getReady();
    }
    
    /**
     * Metodo que nos lleva a la ventana de albaranes
     * @param idParte
     */
    public static void irVA(int idParte){
        idPart = idParte;
        va = new VistaAlbaran();
        va.setVisible(true);
    }
    
    /**
     * Metodo que nos lleva a la ventana de partes (administrador)
     */
    public static void irVPA(){
        vpa = new VistaParteAdmin();
        vpa.setVisible(true); 
    }    
    
    /**
     * Metodo que nos lleva a la ventana de partes (logistica)
     */
    public static void irVPL(){
        vpl = new VistaParteLogis();
        vpl.setVisible(true); 
    }    
    
    /**
     * Metodo que nos lleva a la ventana de login destruyendo la ventaba desde la que accedemos
     * @param v
     */
    public static void volverALogin(javax.swing.JFrame v){
        v.dispose();
        vlog = new VistaLogin();
        vlog.setVisible(true); 
    }
    
    /**
     * Metodo que nos lleva a la ventana del menu principal
     * @param v
     */
    public static void irVM(javax.swing.JFrame v){
        v.dispose();
        if(vm == null)
            vm = new VistaMenu();
        vm.mostrar(true);
    }
    
    /**
     * Metodo que valida todos los datos del trabajador en la ventana trabajador
     * @param DNI
     * @param nombre
     * @param ape1
     * @param ape2
     * @param calle
     * @param portal
     * @param piso
     * @param mano
     * @param tlfmovilemp
     * @param tlfper
     * @param salario
     * @return
     */
    public static boolean validar(String DNI,String nombre,String ape1,String ape2,String calle,String portal,String piso,String mano,String tlfmovilemp,String tlfper,String salario){
        boolean validado = true;
        String informeinvalidos="";
        Pattern pat;
        Matcher mat;
            if(DNI != null){
            pat = Pattern.compile("^[0-9]{8}[a-zA-Z]{1}$");
            mat = pat.matcher(DNI);
                if (!mat.find()) {validado = false; informeinvalidos += "DNI invalido";}
            }else{
                validado = false;
            }
            
            if(nombre != null){
            pat = Pattern.compile("^[A-Z]{1}[a-z]+$");
            mat = pat.matcher(nombre);
                if (!mat.find()) {validado = false; informeinvalidos += "\n Nombre invalido";}
            }else{
                validado = false;
            }
            
            if(ape1 != null){
            pat = Pattern.compile("^[A-Z]{1}[a-z]+$");
            mat = pat.matcher(ape1);
                if (!mat.find()) {validado = false; informeinvalidos += "\n 1er apellido invalido";}
            }else{
                validado = false;
            }
          
            if(ape2 != null){
            pat = Pattern.compile("^[A-Z]{1}[a-z]+$");
            mat = pat.matcher(ape2);
                if (!mat.find()) {validado = false; informeinvalidos += "\n 2º apellido invalido";}
            }else{
                validado = false;
            }
            
            if(calle != null){
            pat = Pattern.compile("^[A-Z]{1}[a-z A-Z]+$");
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
            pat = Pattern.compile("^[0-9]{9}$");
            mat = pat.matcher(tlfmovilemp);
                if (!mat.find()) {validado = false; informeinvalidos += "\n Telefono movil empresa invalido";}
            }else{
                validado = false;
            }
            
            if(tlfper != null){
            pat = Pattern.compile("^[0-9]{9}$");
            mat = pat.matcher(tlfper);
                if (!mat.find()) {
                    validado = false; informeinvalidos += "\n Telefono personal invalido";}
            }
            
            if(salario == null){
                validado = false; informeinvalidos += "\n Salario invalido";}
            
            if(!validado)
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
    
    /**
     * Metodo que busca la id de un trabajador por su usuario
     * @param usuario
     * @param pass
     */
    public static void buscarTrabajadorPorUsuario(String usuario, String pass){
        tLog = TrabajadorBD.getTrabajadorPorUsuario(usuario,pass);
        if(tLog != null){
            if(tLog instanceof Administracion)
                automatizarInforme();
            irVM(vlog);
        }else
            JOptionPane.showMessageDialog(vlog, "El usuario o contraseña no son correctos");
    }
       
    /**
     * Metodo que obtiene las IDs de los partes de un trabajador
     * @param id
     * @throws SQLException
     */
    public static void buscarPartePorID(int id) throws Exception{
        p = ParteBD.getPartePorID(id);
        
        if(p != null){
            vmp.rellenarVMP(p.getFecha(), p.getGastoDieta(), p.getGastoGasoil(), p.getGastoOtros() ,p.getGastoPeaje(), p.getKmFin(), p.getKmIni(), p.getMatricula(), p.getDescripcion());
        }
    }
    
    /**
     * Metodo que recoge la ID de un trabajador de logistica
     * @return
     * @throws Exception
     */
    public static ArrayList<Integer> recogerIdTrabajador() throws Exception{
        return TrabajadorBD.getTiposCentro("Logistica");
    }
        
    /**
     * Metodo que borra un parte seleccionado
     * @param id
     * @throws Exception
     */
    public static void borrarParte(int id) throws Exception{
        ParteBD.eliminar(id);
        JOptionPane.showMessageDialog(vpa, "Parte eliminado con éxito.");
        }   
    
    /**
     * Metodo que recoge los IDs de los partes según el ID del trabajador y la abarcación de dos fechas
     * @param idTrab
     * @param fecha1
     * @param fecha2
     * @return
     * @throws Exception
     */
    public static ArrayList<Integer> recogerPartesPorID(int idTrab, Date fecha1, Date fecha2) throws Exception{
        listaIdPartes = ParteBD.recogerPartesPorID(idTrab,fecha1,fecha2);
        return listaIdPartes;
    }
       
    /**
     * Metodo que valida un parte seleccionado
     * @param id
     * @throws SQLException
     */
    public static void validarParte (int id) throws SQLException{
        ParteBD.validarParte(id);
        JOptionPane.showMessageDialog(null, "Parte validado con éxito");
    }
    
    // Funciones de la ventana val
       
    /**
     * Metodo que guarda filas de albaranes
     * @param paneles
     * @return
     * @throws ParseException
     */
    public static ArrayList<Panel> saveRows(ArrayList<Panel> paneles) throws ParseException{
        for (int i = 0; i < paneles.size(); i++) {
            a = new Albaran();
            a.setIdAlbaran(paneles.get(i).getNumAlbaran().getText());
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
    
    
    /**
     * 
     */
    public static void cerrarModPart(){
        if(t instanceof Logistica)
            vpl.rellenarCombo();
    }
    
    /**
     * Metodo que almacena los datos de un parte en la base de datos 
     * @param fecha
     * @param matricula
     * @param kmIni
     * @param kmFin
     * @param peaje
     * @param dieta
     * @param gasoil
     * @param otros
     * @param descripcion
     * @throws Exception
     */
    public static void addParte(Calendar fecha, String matricula, float kmIni, float kmFin, float peaje, float dieta, float gasoil, float otros, String descripcion) throws Exception{
        p = new Parte();
        p.setEstado("abierto");
        p.setFecha(fecha.getTime());
        p.setMatricula(matricula);
        p.setKmIni(kmIni);
        p.setKmFin(kmFin);
        p.setGastoPeaje(peaje);
        p.setGastoDieta(dieta);
        p.setGastoGasoil(gasoil);
        p.setGastoOtros(otros);
        p.setDescripcion(descripcion);
        
        ParteBD.insert(p);
    }
    
    public static int getIdParte(){
        return idPart;
    }

   
    public static void cerrarParte() throws Exception{
        vcp.dispose();
        vclp = new VistaCerrarParte();
        vclp.setVisible(true);
    }

    /**
     * Metodo que obtiene en un textfield en que estado se encuentra un parte seleccionado
     * @param index
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public static String getEstadoParte(int index) throws SQLException, Exception{
        
        String res = ParteBD.getEstadoParte(listaIdPartes.get(index));
        return res;
    }
    
    public static ArrayList<String> getMatriculas() throws Exception{
        return CocheBD.queryAll();
    }
    
    public static ArrayList<Albaran> getAlbaranes() throws Exception{
        return AlbaranBD.queryByIdParte();
    }
        
    /**
     * Metodo que rellena un combobox con las IDs de los partes de un trabajador de logistica
     * @return
     * @throws Exception
     */
    public static ArrayList<Integer> rellenarComboPartesLogis() throws Exception{
        listaIdPartes = ParteBD.rellenarComboPartesLogis(tLog.getIdTrabajador());
        return listaIdPartes;
    }
    
    
    public static void modificarParte(Calendar fecha, String maticula, float kmIni, float kmFin, String gastoPeaje, String gastoDieta, String gastoGasoil, String otrosGastos, String descripcion) throws Exception{
        p.setFecha(fecha.getTime());
        p.setMatricula(maticula);
        p.setKmIni(kmIni);
        p.setKmFin(kmFin);
        
        if (!vmp.getGastoPeaje().getText().isEmpty()) {
            p.setGastoPeaje(Float.parseFloat(gastoPeaje));
        }
        
        if (!vmp.getGastoDieta().getText().isEmpty()) {
            p.setGastoDieta(Float.parseFloat(gastoDieta));
        }
        
        if (!vmp.getGastoGasoil().getText().isEmpty()) {
            p.setGastoGasoil(Float.parseFloat(gastoGasoil));
        }
        
        if (!vmp.getOtrosGastos().getText().isEmpty()) {
            p.setGastoOtros(Float.parseFloat(otrosGastos));
        }
        
        if (!vmp.getDescripcion().getText().isEmpty()) {
            p.setDescripcion(descripcion);
        }
        
        ParteBD.modificacion(p);
    }
    
    public static void addParte(Calendar fecha, String matricula, float kmIni) throws Exception{
        p = new Parte();
        p.setEstado("abierto");
        p.setFecha(fecha.getTime());
        p.setMatricula(matricula);
        p.setKmIni(kmIni);
        
        ParteBD.insert(p);
    }
    
    /**
     * Metodo que recoge la ID del trabajador segun el usuario con el que se logea
     * @return
     */
    public static int getIdTrabajador(){
        return tLog.getIdTrabajador();
    }
    
    // Funciones de la ventana vsp
    
    
    /**
     * Metodo que recoge la ID de parte, fecha y estado de los partes abiertos
     * @return
     * @throws Exception
     */
    
    public static ArrayList<Parte> getPartesAbiertos() throws Exception{
        return ParteBD.queryByEstado();
    }
    
    /**
     * Metodo que retorna el trabajador de administrador con el que se logea
     * @return
     */
    public static boolean isAdmin(){
        return tLog instanceof Administracion;
    }
    // Funciones de la ventana vclp
    
    public static void closeParte(float kmFin, String gastoPeaje, String gastoDieta, String gastoGasoil, String otrosGastos, String descripcion) throws Exception{
        p.setKmFin(kmFin);
        
        if (!vclp.getGastoPeaje().getText().isEmpty()) {
            p.setGastoPeaje(Float.parseFloat(gastoPeaje));
        }
        
        if (!vclp.getGastoDieta().getText().isEmpty()) {
            p.setGastoDieta(Float.parseFloat(gastoDieta));
        }
        
        if (!vclp.getGastoGasoil().getText().isEmpty()) {
            p.setGastoGasoil(Float.parseFloat(gastoGasoil));
        }
        
        if (!vclp.getOtrosGastos().getText().isEmpty()) {
            p.setGastoOtros(Float.parseFloat(otrosGastos));
        }
        
        if (!vclp.getDescripcion().getText().isEmpty()) {
            p.setDescripcion((descripcion));
        }
        
        ParteBD.cerrarParte(p);
    }
    
    public static void insert(ArrayList<Panel> paneles) throws ParseException, Exception{
        ArrayList<Albaran> albaranes = new ArrayList();
        
        for (int i = 0; i < paneles.size(); i++) {
            a = new Albaran();
            a.setIdAlbaran(paneles.get(i).getNumAlbaran().getText());
            
            String string = paneles.get(i).getHora().getSelectedItem().toString() + ":" + paneles.get(i).getMin().getSelectedIndex();
            SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
            java.util.Date d = dateFormat.parse(string);
            java.sql.Date parsedDate = new java.sql.Date(d.getTime());
            Timestamp horaSalida = new Timestamp(parsedDate.getTime());
            
            
            a.setHoraSalida(horaSalida);            
            String string1 = paneles.get(i).getHora2().getSelectedItem().toString() + ":" + paneles.get(i).getMin2().getSelectedIndex();
            SimpleDateFormat dateFormat1 = new SimpleDateFormat("hh:mm");
            java.util.Date d1 = dateFormat.parse(string1);
            java.sql.Date parsedDate1 = new java.sql.Date(d.getTime());
            Timestamp horaLlegada = new Timestamp(parsedDate1.getTime());
            
            a.setHoraLlegada(horaLlegada);
            albaranes.add(a);
        }
        
        AlbaranBD.delete();
        AlbaranBD.insert(albaranes);
    }
    
    /**
     * Metodo que cierra el programa
     */
    public static void close(){
        System.exit(0);
    } 
}
