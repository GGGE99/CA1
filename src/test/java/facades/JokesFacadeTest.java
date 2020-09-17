/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import DTOs.JokeDTO;
import DTOs.MemberDTO;
import entities.Joke;
import entities.Member;
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
public class JokesFacadeTest {

    private static EntityManagerFactory emf;
    private static JokesFacade facade;
    private static Joke j1, j2, j3, j4;

    public JokesFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = JokesFacade.getJokesFacade(emf);
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
        j1 = new Joke("Marc Ekstrom", "ME-206", "Må i ikke vide xd");
        j2 = new Joke("Marcus Ravn Jensen", "MJ-757", "Går i dameundertøj");
        j3 = new Joke("Mads Kristensen", "MK-613", "?XD");
        j4 = new Joke("Albert Sylvester Løhde", "al-324", "Har haft en  legoklods siddende fast i endetarmen siden jeg var 4 år gammel");
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE from Joke").executeUpdate();
            em.persist(j1);
            em.persist(j2);
            em.persist(j3);
            em.persist(j4);
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
    public void facadeGetAllJokesTest() {
        //assertEquals(2, facade.getRenameMeCount(), "Expects two rows in the database");
        List<JokeDTO> joke = facade.getAllJokes();
        assertEquals(4, joke.size());
        assertThat(joke, hasItems(
                Matchers.<JokeDTO>hasProperty("joke", is("Marc Ekstrom")),
                Matchers.<JokeDTO>hasProperty("joke", is("Marcus Ravn Jensen")),
                Matchers.<JokeDTO>hasProperty("joke", is("Albert Sylvester Løhde")),
                Matchers.<JokeDTO>hasProperty("joke", is("Mads Kristensen"))
        ));

    }

    @Test
    public void facadePopulateJokesTest() {
        //assertEquals(2, facade.getRenameMeCount(), "Expects two rows in the database");
        facade.populateDB();
        List<JokeDTO> joke = facade.getAllJokes();
        assertEquals(18, joke.size());
        assertThat(joke, hasItems(
                Matchers.<JokeDTO>hasProperty("joke", is("Marc Ekstrom")),
                Matchers.<JokeDTO>hasProperty("joke", is("Din mor er så fed at man kan se hendes telefon nummer når hun stiller sig på vægten")),
                Matchers.<JokeDTO>hasProperty("joke", is("hvorfor begraver man altid en Aarhusianer med røven i vejret? Så han kan bruges som cykelstativ")),
                Matchers.<JokeDTO>hasProperty("joke", is("Hvad sagde præsten da han satte sig på biblen? \"Kors i røven!\""))
        ));

    }

    @Test
    public void testgetJokeById() {
        JokeDTO jDTO = facade.getJokeById(j1.getId());
        JokeDTO jDTO2 = new JokeDTO(j1);
        assertThat(jDTO, samePropertyValuesAs(jDTO2));
    }
}
