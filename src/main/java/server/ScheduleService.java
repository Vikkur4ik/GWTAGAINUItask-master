package server;



import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;
@Path("/schedule")

public class ScheduleService {

    private final int PAGE_SIZE = 10;
    private XMLParser parser = new XMLParser();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{page}")
    public List<List<String>> getByPage(@PathParam("page") int page){

        List<List<String>> busList = parser.Parse();
        List<List<String>> result = getBusByPage((page - 1) * PAGE_SIZE, PAGE_SIZE, busList);

        return result;



    }

    private List<List<String>> getBusByPage(int fromIndex, int limit, List<List<String>> list) {

        if (fromIndex > list.size()) {
            return null;
        }
        if (fromIndex + limit > list.size()) {
            int lastPageSize = (fromIndex + limit) - list.size();
            return list.subList(fromIndex, fromIndex + lastPageSize);
        } else {
            return list.subList(fromIndex, fromIndex + limit);
        }
    }


}
