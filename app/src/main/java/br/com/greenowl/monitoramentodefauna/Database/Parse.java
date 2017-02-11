package br.com.greenowl.monitoramentodefauna.Database;
import br.com.greenowl.monitoramentodefauna.Dominio.Entidade.RegistrosSpp;
import br.com.greenowl.monitoramentodefauna.Util.DateUtil;
import android.content.Context;
import android.content.res.Resources;
import android.os.Environment;
import android.widget.ArrayAdapter;


import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import br.com.greenowl.monitoramentodefauna.Dominio.Entidade.Registros;

/**
 * This class helps open, create, and upgrade the database file.
 */
public  class Parse {
    public ArrayList<RegistrosSpp> Importardados(Context fContext) {
        ArrayList<RegistrosSpp> Lista = new ArrayList<RegistrosSpp>();
//-----------------------------------------------------------------------------
        // initialize our input source variable
        InputSource inputSource = null;
        File xmlFile = new File(Environment.getExternalStorageDirectory()+"/especies.xml");
        FileInputStream xmlFileInputStream = null;
        try {
            xmlFileInputStream = new FileInputStream(
                    xmlFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        inputSource = new InputSource(xmlFileInputStream);
//---------------------------------------------------------------------------
// Get xml resource file
        Resources res = fContext.getResources();
        //Open xml file
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser _xml = factory.newPullParser();
            _xml.setInput(xmlFileInputStream,null);
            //Check for end of document
            int eventType = _xml.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                //Search for record tags
                if ((eventType == XmlPullParser.START_TAG) && (_xml.getName().equals("record"))) {
                    //Record tag found, now get values and insert record
                    //String _parcela = _xml.getAttributeValue(null, Registros.PARCELA);
                    // Date _data= DateUtil.getDate(2016,01,01);
                    //String _plato = _xml.getAttributeValue(null, Registros.PLATO);
                    //String _ambiente = _xml.getAttributeValue(null, Registros.AMBIENTE);
                    // String _periodo = _xml.getAttributeValue(null, Registros.PERIODO);
                    // String _metodo = _xml.getAttributeValue(null, Registros.METODO);
                    // String _especie = _xml.getAttributeValue(null, Registros.ESPECIE);
                    String _especie = _xml.getAttributeValue(null, RegistrosSpp.SPP);
                    String _nomecientifico = _xml.getAttributeValue(null, RegistrosSpp.NOMECIENTIFICO);

                    String _genero = _xml.getAttributeValue(null, RegistrosSpp.GENERO);
                    String _familia = _xml.getAttributeValue(null, RegistrosSpp.FAMILIA);
                    String _ordem= _xml.getAttributeValue(null, RegistrosSpp.ORDEM);
                    String _classe= _xml.getAttributeValue(null, RegistrosSpp.CLASSE);



                    RegistrosSpp registrospp = new RegistrosSpp();

                    registrospp.setSPP(_especie);
                    registrospp.setNOMEC(_nomecientifico);
                    registrospp.setGENERO(_genero);
                    registrospp.setFAMILIA(_familia);
                    registrospp.setORDEM(_ordem);
                    registrospp.setCLASSE(_classe);


                    Lista.add(registrospp);
                }
                eventType = _xml.next();
            }
        }
        //Catch errors
        catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return Lista;
            //Close the xml file
        }
    }
    public ArrayList<Registros> AdaptertoList (ArrayAdapter<Registros> contatos){
        ArrayList<Registros> Aresultado =  new ArrayList<Registros>();
        for (int i= 0 ; i<contatos.getCount();i++){
            Registros C = new Registros();

            C.setDATA(contatos.getItem(i).getDATA());
            C.setPLATO(contatos.getItem(i).getPLATO());
            C.setAMBIENTE(contatos.getItem(i).getAMBIENTE());
            C.setPERIODO(contatos.getItem(i).getPERIODO());
            C.setMETODO(contatos.getItem(i).getMETODO());
            C.setESPECIE(contatos.getItem(i).getESPECIE());
            C.setCONDICOESCLIMATICAS(contatos.getItem(i).getCONDICOESCLIMATICAS());
            C.setOBSERVACAO(contatos.getItem(i).getOBSERVACAO());
            C.setTRANSECTO(contatos.getItem(i).getTRANSECTO());
            C.setRESPONSAVEL(contatos.getItem(i).getRESPONSAVEL());







            Aresultado.add(C);
        }
        return Aresultado;
    }
    public void Exportarxml(ArrayList<Registros> contatos) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("dados");
            doc.appendChild(rootElement);
            for (int i =0; i<contatos.size();i++) {
                // staff elements
                Element staff = doc.createElement("record");
                rootElement.appendChild(staff);
                // set attribute to staff element
                Attr attr = doc.createAttribute("RESPONSAVEL");
                attr.setValue(contatos.get(i).getRESPONSAVEL());
                staff.setAttributeNode(attr);

                attr  = doc.createAttribute("OBSERVACAO");
                attr.setValue(contatos.get(i).getOBSERVACAO());
                staff.setAttributeNode(attr);

                attr  = doc.createAttribute("CONDICOESCLIMATICAS");
                attr.setValue(contatos.get(i).getCONDICOESCLIMATICAS());
                staff.setAttributeNode(attr);

                attr  = doc.createAttribute("TRANSECTO");
                attr.setValue(contatos.get(i).getTRANSECTO());
                staff.setAttributeNode(attr);

                attr = doc.createAttribute("PLATO");
                attr.setValue(contatos.get(i).getPLATO());
                staff.setAttributeNode(attr);

                attr = doc.createAttribute("AMBIENTE");
                attr.setValue(contatos.get(i).getAMBIENTE());
                staff.setAttributeNode(attr);
                attr = doc.createAttribute("PERIODO");
                attr.setValue(contatos.get(i).getPERIODO());
                staff.setAttributeNode(attr);
                attr = doc.createAttribute("DATA");
                attr.setValue(contatos.get(i).getDATA().toString());
                staff.setAttributeNode(attr);
                attr = doc.createAttribute("METODO");
                attr.setValue(contatos.get(i).getMETODO());
                staff.setAttributeNode(attr);
                attr = doc.createAttribute("ESPECIE");
                attr.setValue(contatos.get(i).getESPECIE());
                staff.setAttributeNode(attr);

            }
            // shorten way
            // staff.setAttribute("id", "1");
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(Environment.getExternalStorageDirectory()+"/formulario.xml"));
            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);
            System.out.println("File saved!");
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
/*
    public double Calculaxml(ArrayList<Contato> contatos) {

        double areatotal = 0;
        double sumprod= 0;
        double prod = 0;
        for(int i = 0; i < contatos.size(); i++)
        {
         String valors = contatos.get(i).getSOBREVIVENCIA();
         String area = contatos.get(i).getAREA();
            double valued = Double.parseDouble(valors);
            double aread = Double.parseDouble(area);


            prod = valued*aread;
            sumprod += prod;
            areatotal+= aread;
        }
      return sumprod/areatotal;



        }*/
}