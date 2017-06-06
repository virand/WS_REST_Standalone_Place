import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.ClassNamesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;

import java.io.IOException;
import java.net.URI;

/**
 * Created by artemsolonin on 31.05.2017.
 */
public class App {
    private static final URI BASE_URI = URI.create("http://localhost:8081/rest/");
    public static void main(String[] args)
    {
        org.glassfish.grizzly.http.server.HttpServer server = null;
        try{
            ResourceConfig resourceConfig = new ClassNamesResourceConfig(PlaceResource.class);
            server = GrizzlyServerFactory.createHttpServer(BASE_URI, resourceConfig);
            server.start();
            System.in.read();
            stopServer(server);
        }
        catch(IOException e)
        {
            e.printStackTrace();
            stopServer(server);
        }
    }

    private static void stopServer(org.glassfish.grizzly.http.server.HttpServer server)
    {
        if(server != null)
            server.stop();
    }
}
