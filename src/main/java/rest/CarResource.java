package rest;

import DTOs.CarDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.CarFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;


@Path("/cars")
public class CarResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final CarFacade FACADE = CarFacade.getCarFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @Path("/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllMembers() {
        List<CarDTO> allMembers = FACADE.getAllCars();
        return GSON.toJson(allMembers);
    }
    
    
    @Path("/populate")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String populate() {
        FACADE.populateDB();
        return "4 Members has been added";
    }
}

