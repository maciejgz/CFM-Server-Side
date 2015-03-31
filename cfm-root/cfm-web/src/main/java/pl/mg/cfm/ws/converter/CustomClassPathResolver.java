package pl.mg.cfm.ws.converter;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamSource;

/**
 * Classpath URIResolver for FOP factory
 * @author Maciej Gzik
 *
 */
public class CustomClassPathResolver implements URIResolver {

    @Override
    public Source resolve(String href, String base) throws TransformerException {
        return new StreamSource(getClass().getResourceAsStream(href));
    }

}
