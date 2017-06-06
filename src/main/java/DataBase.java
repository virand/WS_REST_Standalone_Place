import java.sql.*;


/**
 * Created by artemsolonin on 18.05.2017.
 */
public class DataBase {
    private static String driver="com.mysql.jdbc.Driver";
    private static String jdbcUrl="jdbc:mysql://nebbel.beget.tech:3306/nebbel_ws";
    private static String user="nebbel_ws";
    private static String password="111111";

    public static ResultSet runQuery(String query)
    {
        ResultSet rs=null;
        try{
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(jdbcUrl, user, password);
            Statement st = conn.createStatement();
            System.out.print(query);
            rs = st.executeQuery(query);
        }
        catch (Exception se)
        {
            se.printStackTrace();
        }

        return rs;
    }

    public static int runInsert(String query)
    {
        int id=-1;

        try{
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(jdbcUrl, user, password);
            Statement st = conn.createStatement();
            System.out.println(query);

            PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.executeUpdate();
            ResultSet keys = pstmt.getGeneratedKeys();
            keys.next();
            id = keys.getInt(1);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return id;
    }


    public static int runUpdate(String query)
    {
        int status=-1;

        try{
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(jdbcUrl, user, password);
            Statement st = conn.createStatement();
            System.out.println(query);

            status=st.executeUpdate(query);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return status;
    }
}
