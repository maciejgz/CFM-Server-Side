package pl.mg.cfm.ws.converter.test;

import org.junit.Before;
import org.junit.Test;
import pl.mg.cfm.ws.converter.Converter;
import pl.mg.cfm.ws.converter.Xml2TxtConverter;

/**
 * Created by Maciej Gzik on 2015-03-20.
 */
public class Xml2TxtConverterTest {

    String exmaplePath;
    String resultFilePath;

    Converter converter;

    @Before
    public void setup() {
        this.converter = new Xml2TxtConverter();
    }

    @Test
    public void test() {
        String xmlToParse = loadXmlToParse();
        byte[] result = converter.convert(xmlToParse);

        saveTxt(result, resultFilePath);
    }

    private String loadXmlToParse() {
        return null;
    }


    private void saveTxt(byte[] result, String resultFilePath) {

    }


}
