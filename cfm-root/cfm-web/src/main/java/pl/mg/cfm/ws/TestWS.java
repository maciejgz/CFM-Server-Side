package pl.mg.cfm.ws;

import pl.mg.cfm.ws.converter.Xml2PdfConverter;
import pl.mg.cfm.ws.spring.SpringApplicationContext;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.File;


@Path("")
public class TestWS {

    private Xml2PdfConverter converter;

    @GET
    @Path("/test")
    public Response test() {
        String value = "test";
        return Response.status(200).entity(value).build();
    }
    @GET
    @Path("/pdf")
    @Produces("application/pdf")
    public Response getPdf() {

        converter = (Xml2PdfConverter) SpringApplicationContext.getBean("xml2PdfConverter");

        converter.convert(null);

        File examplePdf = new File("D:/1.pdf");

        Response.ResponseBuilder response = Response.ok(examplePdf);
        response.header("Content-Disposition", "attachment; filename=\"1.pdf\"");
        return response.build();
    }

    public Xml2PdfConverter getConverter() {
        return converter;
    }

    public void setConverter(Xml2PdfConverter converter) {
        this.converter = converter;
    }
}
