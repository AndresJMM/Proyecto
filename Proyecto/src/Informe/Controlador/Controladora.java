package Informe.Controlador;

import Informe.UML.Albaran;
import Informe.UML.Parte;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
    
/*  
    Este parser recoge información de las tablas PARTES y ALBARANES, almacena
    los datos en un archivo XML en la carpeta informes, que se encuentra en la
    raíz del proyecto, y parsea este archivo para almacenarlo en la tabla INFORMES
*/

public class Controladora extends DefaultHandler{
    
    private Document dom;
    private ArrayList<Parte> informeAr;
    private String mes;
    private Element rootEle;

    public Controladora(String mes) {
        this.mes = mes;
        informeAr = new ArrayList();
        loadData();
        createDocument();
    }

    public void runExample(){
        createDOMTree();
        printToFile();
        try {
            saveToDB();
        } catch (TransformerException | SAXException | IOException | ParserConfigurationException ex) {
            Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(!informeAr.isEmpty()){
            Main.acabado();
        }
    }

    private void loadData() {
        informeAr = Informe.BD.ParteBD.getPartesMes(mes);
        if(informeAr.isEmpty()){
            Main.partesVacio();
        }
    }

    private void createDocument() {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();

            dom = db.newDocument();

        } catch (ParserConfigurationException pce) {
            
            System.exit(1);
        }

    }

    private void createDOMTree() {

        rootEle = dom.createElement("Partes");
        dom.appendChild(rootEle);

        Iterator it = informeAr.iterator();
        
        for(Parte p : informeAr){
            Element parteEle = crearEleParte(p);
            rootEle.appendChild(parteEle);
        }

    }
    
    private Element crearEleParte(Parte p) {

        Element parteEl = dom.createElement("parte");
        Element tempEl;
        Text tempText;
        
        
        tempEl = dom.createElement("idParte");
        tempText = dom.createTextNode(String.valueOf(p.getIdParte()));
        tempEl.appendChild(tempText);
        parteEl.appendChild(tempEl);
        
        tempEl = dom.createElement("idTrabajador");
        tempText = dom.createTextNode(String.valueOf(p.getIdTrabajador()));
        tempEl.appendChild(tempText);
        parteEl.appendChild(tempEl);
        
        tempEl = dom.createElement("estado");
        tempText = dom.createTextNode(p.getEstado());
        tempEl.appendChild(tempText);
        parteEl.appendChild(tempEl);
        
        tempEl = dom.createElement("fecha");
        tempText = dom.createTextNode(p.getFecha().toString());
        tempEl.appendChild(tempText);
        parteEl.appendChild(tempEl);
        
        tempEl = dom.createElement("kmIni");
        tempText = dom.createTextNode(String.valueOf(p.getKmIni()));
        tempEl.appendChild(tempText);
        parteEl.appendChild(tempEl);
        
        tempEl = dom.createElement("kmFin");
        tempText = dom.createTextNode(String.valueOf(p.getKmFin()));
        tempEl.appendChild(tempText);
        parteEl.appendChild(tempEl);
        
        if(p.getDescripcion()!=null){
            tempEl = dom.createElement("descripcion");
            tempText = dom.createTextNode(p.getDescripcion());
            tempEl.appendChild(tempText);
            parteEl.appendChild(tempEl);
        }
        
        if(p.getGastoDieta()!=null || p.getGastoGasolina()!=null || p.getGastoPeaje()!=null || p.getGastoOtros()!=null){
            Element gastEl = dom.createElement("gastos");
            if(p.getGastoPeaje()!=null){
                tempEl = dom.createElement("peaje");
                tempText = dom.createTextNode(String.valueOf(p.getGastoPeaje()));
                tempEl.appendChild(tempText);
                gastEl.appendChild(tempEl);
            }
            if(p.getGastoDieta()!=null){
                tempEl = dom.createElement("dieta");
                tempText = dom.createTextNode(String.valueOf(p.getGastoDieta()));
                tempEl.appendChild(tempText);
                gastEl.appendChild(tempEl);
            }
            if(p.getGastoGasolina()!=null){
                tempEl = dom.createElement("gasoil");
                tempText = dom.createTextNode(String.valueOf(p.getGastoGasolina()));
                tempEl.appendChild(tempText);
                gastEl.appendChild(tempEl);
            }
            if(p.getGastoOtros()!=null){
                tempEl = dom.createElement("otro");
                tempText = dom.createTextNode(String.valueOf(p.getGastoOtros()));
                tempEl.appendChild(tempText);
                gastEl.appendChild(tempEl);
            }
            parteEl.appendChild(gastEl);
        }
        
        if(p.getAlbaranes().size()>0){
            Element listEl = dom.createElement("albaranes");
            for(Albaran a : p.getAlbaranes()){
                Element albEl = dom.createElement("albaran");
                
                tempEl = dom.createElement("idAlbaran");
                tempText = dom.createTextNode(String.valueOf(a.getIdAlbaran()));
                tempEl.appendChild(tempText);
                albEl.appendChild(tempEl);
                
                tempEl = dom.createElement("horaSalida");
                tempText = dom.createTextNode(convertirTimestamp(a.getHoraSalida()));
                tempEl.appendChild(tempText);
                albEl.appendChild(tempEl);
                
                tempEl = dom.createElement("horaLlegada");
                tempText = dom.createTextNode(convertirTimestamp(a.getHoraLLegada()));
                tempEl.appendChild(tempText);
                albEl.appendChild(tempEl);
                
                listEl.appendChild(albEl);
            }
            parteEl.appendChild(listEl);
        }
        
        tempEl = dom.createElement("matricula");
        tempText = dom.createTextNode(p.getMatricula());
        tempEl.appendChild(tempText);
        parteEl.appendChild(tempEl);

        return parteEl;

    }
    private void printToFile() {
        if(!informeAr.isEmpty()){
            try {
                OutputFormat format = new OutputFormat(dom);
                format.setIndenting(true);

                XMLSerializer serializer = new XMLSerializer(new FileOutputStream(new File("./informes/informe-"+mes+".xml")), format);

                serializer.serialize(dom);

            } catch (IOException ie) {
                ie.printStackTrace();
            }
        }
    }
    
    private String convertirTimestamp(Timestamp t){
        String result;
        Date date = new Date(t.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        result = sdf.format(date);
        return result;
    }
    
    public void saveToDB() throws TransformerConfigurationException, TransformerException, SAXException, IOException, ParserConfigurationException{ 
        if(!informeAr.isEmpty()){
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
            Document xmlDom = docBuilder.parse(new File("./informes/informe-"+mes+".xml"));
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(xmlDom), new StreamResult(writer));
            String output = writer.getBuffer().toString();
            
            Informe.BD.InformeBD.almacenarInforme(mes, output);
        }
    }


}
    

