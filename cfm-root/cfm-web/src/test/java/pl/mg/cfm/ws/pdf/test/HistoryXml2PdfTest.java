package pl.mg.cfm.ws.pdf.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.ResourceUtils;
import org.xml.sax.SAXException;
import pl.mg.cfm.ws.converter.OpenXml2MIMEConverter;
import pl.mg.cfm.ws.converter.Xml2PdfConverter;

import javax.activation.MimeType;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import java.io.*;
import java.util.Scanner;

import static junit.framework.Assert.assertEquals;

/**
 * Created by m on 2015-03-13.
 */
public class HistoryXml2PdfTest {

    OpenXml2MIMEConverter converter = new OpenXml2MIMEConverter();

    StringBuffer xml = new StringBuffer();

    @Before
    public void before() {
        ClassLoader classLoader = getClass().getClassLoader();

        converter.setMimeType("application/pdf");
        converter.setTemplate("udrHistoryTemplatePdf");
        converter.setFopConfigurationFile("historyFopConfiguration");
        converter.setDefaultResultFileName("historia_konta.pdf");

        File xmlTestFile = null;
        try {
            xmlTestFile = ResourceUtils.getFile("classpath:xml/udrHistoryExample.xml");
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }



        try (Scanner scanner = new Scanner(xmlTestFile)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                xml.append(line).append("\n");
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(xml.toString());
    }

    @Test
    public void test() {
        byte[] resultArray = null;
        try {
            resultArray = converter.convertXml(xml.toString());
        } catch (TransformerFactoryConfigurationError e1) {
            e1.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (resultArray != null) {
            OutputStream out = null;
            try {
                out = new FileOutputStream("D:/" + "result.pdf");
                out.write(resultArray);
                out.close();
            } catch (IOException e) {
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
        assertEquals(true, (new File("D:/" + "result.pdf")).exists());
    }

}
