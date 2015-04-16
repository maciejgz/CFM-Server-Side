package pl.mg.cfm.ws.converter;

import org.apache.fop.apps.*;
import org.springframework.core.io.ClassPathResource;
import org.xml.sax.SAXException;

import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

/**
 * Created by Maciej Gzik on 2015-03-22.
 */
public class OpenXml2MIMEConverter {

    private String defaultResultFileName = "defaultFileName";


    private String template;
    private static String templateDirectory = "xsl";
    private String defaultTemplate = "defaultTemplate.xsl";
    private static String configurationDirectory = "configuration";
    private final FopFactory fopFactory = FopFactory.newInstance();


    private String fopConfigurationFile = "defaultConfiguration";

    private String mimeType;

    public byte[] convertXml(String openApiXmlResult) throws IOException, SAXException, TransformerConfigurationException, TransformerFactoryConfigurationError, TransformerException {
        byte[] result = null;
        File templateFile = createTemplateFile(template);

        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
        ByteArrayOutputStream out = null;

        try {

            out = new ByteArrayOutputStream();

            File configurationFile = new ClassPathResource(configurationDirectory + "/" + fopConfigurationFile + ".xml")
                    .getFile();

            System.out.println("template= " + template);
            System.out.println("template file= " + templateFile.exists());
            System.out.println("config file exists= " + configurationFile.exists());
            System.out.println("config directory = " + configurationDirectory);
            System.out.println("fop configuration file = " + fopConfigurationFile);

            FOURIResolver uriResolver = (FOURIResolver) fopFactory.getURIResolver();
            uriResolver.setCustomURIResolver(new CustomClassPathResolver());

            fopFactory.setUserConfig(configurationFile);
            Fop fop = fopFactory.newFop(mimeType, foUserAgent, out);

            StringReader reader = new StringReader(openApiXmlResult);
            TransformerFactory factory = TransformerFactory.newInstance();

            Transformer transformer = factory.newTransformer(new StreamSource(templateFile));

            Source src = new StreamSource(reader);
            Result res = new SAXResult(fop.getDefaultHandler());

            transformer.transform(src, res);

            result = out.toByteArray();
        } catch (FOPException e) {
            e.printStackTrace();
            throw new IOException(e.getLocalizedMessage());
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }

        return result;
    }


    private File createTemplateFile(String template) {
        File templateFile = null;
        try {
            templateFile = new ClassPathResource(templateDirectory + "/" + template + ".xsl").getFile();
            System.out.println(templateFile.exists());
            System.out.println("template directory: " + templateDirectory);
            System.out.println("template: " + template);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (templateFile != null && templateFile.exists()) {
            return templateFile;
        } else {
            try {
                return new ClassPathResource(templateDirectory + "/" + defaultTemplate).getFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public String getDefaultResultFileName() {
        return defaultResultFileName;
    }

    public void setDefaultResultFileName(String defaultResultFileName) {
        this.defaultResultFileName = defaultResultFileName;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getFopConfigurationFile() {
        return fopConfigurationFile;
    }

    public void setFopConfigurationFile(String fopConfigurationFile) {
        this.fopConfigurationFile = fopConfigurationFile;
    }
}
