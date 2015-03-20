package pl.mg.cfm.ws.pdf.test;

import org.junit.Test;
import pl.mg.cfm.ws.pdf.Xml2PdfConverter;

import static org.junit.Assert.assertTrue;

public class Xml2PdfConverterTest {

    Xml2PdfConverter converter = new Xml2PdfConverter();

    @Test
    public void test() {

        converter.convertXmlToPdf();
        assertTrue(true);
    }
}
