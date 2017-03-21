package client;


import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Path("/api/schedule")

public interface Service extends RestService {

    @GET
    @Path("/{page}")
    void getBusByPage(@PathParam("page") int page, MethodCallback<List<List<String>>> callback);


}
