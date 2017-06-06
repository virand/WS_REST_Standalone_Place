import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by artemsolonin on 31.05.2017.
 */
public class MySQLDAO {
    public List<Place> getPlaces(String word)
    {
        String query= String.format("SELECT * FROM places WHERE name='%s' OR description='%s' OR city='%s' OR address='%s'", word, word, word, word);
        System.out.println(query);
        List<Place> places = new ArrayList<>();
        ResultSet rs = DataBase.runQuery(query);
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String city = rs.getString("city");
                String address  = rs.getString("address");
                Place place = new Place(id, name,description,city,address);
                places.add(place);
            }
        }
        catch (SQLException se)
        {
            se.printStackTrace();
        }

        return places;
    }


    public int createPlace(String query)
    {
        System.out.println(query);
        int id = DataBase.runInsert(query);
        return id;
    }

    public int changePlace(String query)
    {
        System.out.println(query);
        int id = DataBase.runUpdate(query);

        return id;
    }

    public int deletePlace(String query)
    {
        System.out.println(query);
        int id = DataBase.runUpdate(query);

        return id;
    }
}
