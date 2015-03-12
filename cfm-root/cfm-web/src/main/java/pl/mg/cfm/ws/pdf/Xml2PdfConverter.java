package pl.mg.cfm.ws.pdf;

import org.apache.fop.apps.*;

import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by m on 2015-03-11.
 */
public class Xml2PdfConverter {


    private String resultPdfFilePath = "D:/result.pdf";
    private String xslFilePath = "D:/formatter.xsl";
    private String xmlFilePath = "D:/projectteam.xml";


    private final FopFactory fopFactory = FopFactory.newInstance();
    private File pdffile = new File(resultPdfFilePath);
    private File xsltfile = new File(xslFilePath);
    private File xmlFile = new File(xmlFilePath);

    public void convertXmlToPdf() {
        OutputStream out = null;

        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
        try {
            out = new java.io.FileOutputStream(pdffile);
            out = new java.io.BufferedOutputStream(out);


            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsltfile));

            //przykłąd ustalenia paramtru w stylesheecie
            transformer.setParameter("versionParam", "2.0");

            Source src = new StreamSource(xmlFile);
            Result res = new SAXResult(fop.getDefaultHandler());

            transformer.transform(src, res);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (FOPException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public String getResultPdfFilePath() {
        return resultPdfFilePath;
    }

    public void setResultPdfFilePath(String resultPdfFilePath) {
        this.resultPdfFilePath = resultPdfFilePath;
    }

    public String getXslFilePath() {
        return xslFilePath;
    }

    public void setXslFilePath(String xslFilePath) {
        this.xslFilePath = xslFilePath;
    }

    public String getXmlFilePath() {
        return xmlFilePath;
    }

    public void setXmlFilePath(String xmlFilePath) {
        this.xmlFilePath = xmlFilePath;
    }
}
