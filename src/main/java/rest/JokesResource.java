package rest;

import DTOs.JokeDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Joke;
import facades.JokesFacade;
import facades.MemberFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 *
 * @author Marcus
 */
@Path("/jokes")
public class JokesResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final JokesFacade FACADE = JokesFacade.getJokesFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @Path("/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllJokes() {
        List<JokeDTO> allJokes = FACADE.getAllJokes();
        return GSON.toJson(allJokes);
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getJokeById(@PathParam("id") int id) {
        JokeDTO joke = FACADE.getJokeById(id);
        return GSON.toJson(joke);
    }

    @Path("/random")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRandomJoke() {
        JokeDTO randomJoke = FACADE.getRandomJoke();
        return GSON.toJson(randomJoke);

    }

    @Path("/populate")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String populate() {
        FACADE.populateDB();
        return GSON.toJson("msg: DB populatet");

    }
}
