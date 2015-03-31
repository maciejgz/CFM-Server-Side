package pl.mg.cfm.ws.converter.test;

import org.junit.Test;
import pl.mg.cfm.ws.converter.Xml2PdfConverter;

import static org.junit.Assert.assertTrue;

public class Xml2PdfConverterTest {

    Xml2PdfConverter converter = new Xml2PdfConverter();

    @Test
    public void test() {
        converter.convert(null);
        assertTrue(true);
    }
}
