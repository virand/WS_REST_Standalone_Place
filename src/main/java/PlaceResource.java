import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by artemsolonin on 31.05.2017.
 */
@Path("/places")

@Produces({MediaType.APPLICATION_JSON + ";charset=utf-8"})
public class PlaceResource {

    private static String login="login";
    private static String password = "password";


    public boolean authenticate(@Context HttpHeaders headers){
        if(headers.getRequestHeader("login").equals(login)&&headers.getRequestHeader("password").equals(password)){
            return true;
        }
        else
        {
            return false;
        }

        //System.out.println("ALL headers -- "+ headers.getRequestHeaders().toString());
       // System.out.println("'Accept' header -- "+ headers.getHeaderString("Accept"));
        //System.out.println("'TestCookie' value -- "+ headers.getCookies().get("TestCookie").getValue());
    }

    @GET
    public List<Place> getPlaces(@QueryParam("word") String word, @Context HttpHeaders headers){

        if(authenticate(headers))
        {
            List<Place> places = new MySQLDAO().getPlaces(word);
            //ArrayList<Place> places = new ArrayList<Place>();
            return places;
        }
        else
        {
            return null;
        }

    }

    @POST
    public int createPlace(@QueryParam("name") String name, @QueryParam("decription") String description, @QueryParam("city") String city,@QueryParam("address") String address, @Context HttpHeaders headers)
    {
        if(authenticate(headers)) {
            String query = String.format("INSERT INTO places (`name`, `description`, `city`, `address`) VALUES('%s','%s','%s','%s')", name, description, city, address);
            MySQLDAO dao = new MySQLDAO();
            int id = dao.createPlace(query);
            return id;
        }
        else
        {
            return -1;
        }
    }

    @PUT
    public int changePlace(@QueryParam("id") int aId,@QueryParam("name") String name, @QueryParam("decription") String description, @QueryParam("city") String city,@QueryParam("address") String address, @Context HttpHeaders headers)
    {
        if(authenticate(headers)) {
            String query = String.format("UPDATE places SET `name`='%s',`description`='%s',`city`='%s',`address`='%s' WHERE id=%d", name, description, city, address, aId);
            MySQLDAO dao = new MySQLDAO();
            int id = dao.changePlace(query);
            return id;
        }
        else
        {
            return -1;
        }
    }

    @DELETE
    public int deletePLace(@QueryParam("id") int aId, @Context HttpHeaders headers)
    {
        if(authenticate(headers)) {
            String query = String.format("DELETE FROM `places` WHERE id=%d", aId);
            MySQLDAO dao = new MySQLDAO();
            int id = dao.deletePlace(query);
            return id;
        }
        else
        {
            return -1;
        }
    }


}
