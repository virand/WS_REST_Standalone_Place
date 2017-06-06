import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by artemsolonin on 31.05.2017.
 */
@Path("/places")

@Produces({MediaType.APPLICATION_JSON + ";charset=utf-8"})
public class PlaceResource {
    @GET
    public List<Place> getPlaces(@QueryParam("word") String word){

        List<Place> places = new MySQLDAO().getPlaces(word);
        //ArrayList<Place> places = new ArrayList<Place>();
        return places;
    }

    @POST
    public int createPlace(@QueryParam("name") String name, @QueryParam("decription") String description, @QueryParam("city") String city,@QueryParam("address") String address)
    {
        String query = String.format("INSERT INTO places (`name`, `description`, `city`, `address`) VALUES('%s','%s','%s','%s')",name,description,city,address);
        MySQLDAO dao = new MySQLDAO();
        int id=dao.createPlace(query);
        return id;
    }

    @PUT
    public int changePlace(@QueryParam("id") int aId,@QueryParam("name") String name, @QueryParam("decription") String description, @QueryParam("city") String city,@QueryParam("address") String address)
    {
        String query = String.format("UPDATE places SET `name`='%s',`description`='%s',`city`='%s',`address`='%s' WHERE id=%d",name,description,city, address, aId);
        MySQLDAO dao = new MySQLDAO();
        int id = dao.changePlace(query);
        return id;
    }

    @DELETE
    public int deletePLace(@QueryParam("id") int aId)
    {
        String query = String.format("DELETE FROM `places` WHERE id=%d", aId);
        MySQLDAO dao = new MySQLDAO();
        int id = dao.deletePlace(query);
        return id;
    }


}
