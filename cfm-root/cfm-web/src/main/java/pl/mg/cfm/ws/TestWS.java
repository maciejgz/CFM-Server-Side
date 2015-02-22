package pl.mg.cfm.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("")
public class TestWS {
    @GET
    @Path("/test")
    public Response test() {
        String value = "test";
        return Response.status(200).entity(value).build();
    }
}
