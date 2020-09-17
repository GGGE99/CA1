/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import DTOs.CarDTO;
import entities.Car;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

/**
 *
 * @author baske
 */
public class CarsFacadeTest {
    private static EntityManagerFactory emf;
    private static CarFacade facade;
    private static Car c1, c2, c3, c4;

    public CarsFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = CarFacade.getCarFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        

        
        
        EntityManager em = emf.createEntityManager();
        c1 = new Car(1999, "audi", "a4", 20000.25,230000,"jan bent");
        c2 = new Car(1990, "skoda", "banjando", 23326.423, 434234, "Hans Bent");
        c3 = new Car(1979, "vw", "ax30", 43423.4, 435555, "Ranju Kazad");
        c4 = new Car(2018, "peugeot", "c20", 32232, 532423, "Justin Bieber");
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE from Car").executeUpdate();
            em.persist(c1);
            em.persist(c2);
            em.persist(c3);
            em.persist(c4);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
    @Test
    public void facadeGetAllCarsTest() {
        //assertEquals(2, facade.getRenameMeCount(), "Expects two rows in the database");
        List<CarDTO> cars = facade.getAllCars();
        assertEquals(4, cars.size());
        assertThat(cars, hasItems(
                Matchers.<CarDTO>hasProperty("model", is("a4")),
                Matchers.<CarDTO>hasProperty("model", is("banjando")),
                Matchers.<CarDTO>hasProperty("model", is("ax30")),
                Matchers.<CarDTO>hasProperty("model", is("c20"))
        ));

    }

    @Test
    public void facadePopulateCarTest() {
        //assertEquals(2, facade.getRenameMeCount(), "Expects two rows in the database");
        facade.populateDB();
        List<CarDTO> cars = facade.getAllCars();
        assertEquals(8, cars.size());
        assertThat(cars, hasItems(
                Matchers.<CarDTO>hasProperty("make", is("Honda")),
                Matchers.<CarDTO>hasProperty("make", is("Fiat")),
                Matchers.<CarDTO>hasProperty("make", is("Mercedes")),
                Matchers.<CarDTO>hasProperty("make", is("Aston Martin"))
        ));

    }


}

