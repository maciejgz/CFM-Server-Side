package pl.mg.cfm.ws.converter;

import java.io.File;

/**
 * Created by Maciej Gzik on 2015-03-20.
 */
public class Xml2TxtConverter implements Converter {


    private String stylesheet;



    @Override
    public byte[] convert(String xml) {


        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(stylesheet).getFile());

        return new byte[0];
    }

    public String getStylesheet() {
        return stylesheet;
    }

    public void setStylesheet(String stylesheet) {
        this.stylesheet = stylesheet;
    }
}
