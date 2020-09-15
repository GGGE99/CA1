package facades;

import DTOs.MemberDTO;
import utils.EMF_Creator;
import entities.Member;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.hamcrest.MatcherAssert;
import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class MemberFacadeTest {

    private static EntityManagerFactory emf;
    private static MemberFacade facade;

    public MemberFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = MemberFacade.getMemberFacade(emf);
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
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE from Member").executeUpdate();
            em.persist(new Member("Marc Ekstrom", "ME-206", "Må i ikke vide xd", "Suits"));
            em.persist(new Member("Marcus Ravn Jensen", "MJ-757", "Går i dameundertøj", "Sherlock Holmes"));
            em.persist(new Member("Mads Kristensen", "MK-613", "?XD", "House"));
            em.persist(new Member("Albert Sylvester Løhde", "al-324", "Har haft en  legoklods siddende fast i endetarmen siden jeg var 4 år gammel", "Gossip girl"));
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
    public void facadeGetAllMembersTest() {
        //assertEquals(2, facade.getRenameMeCount(), "Expects two rows in the database");
        List<MemberDTO> members = facade.getAllMembers();
        assertEquals(4, members.size());
        assertThat(members, hasItems(
                Matchers.<MemberDTO>hasProperty("name", is("Marc Ekstrom")),
                Matchers.<MemberDTO>hasProperty("name", is("Marcus Ravn Jensen")),
                Matchers.<MemberDTO>hasProperty("name", is("Albert Sylvester Løhde")),
                Matchers.<MemberDTO>hasProperty("name", is("Mads Kristensen"))
        ));

    }

}
