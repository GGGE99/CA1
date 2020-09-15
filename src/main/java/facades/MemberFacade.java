package facades;

import DTOs.MemberDTO;
import entities.Member;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MemberFacade {

    private static MemberFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private MemberFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MemberFacade getMemberFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MemberFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<MemberDTO> getAllMembers() {

        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("Members.getAll");
            List<MemberDTO> member = query.getResultList();
            return member;
        } finally {
            em.close();
        }
    }

    public void populateDB() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new Member("Marc Ekstrom", "ME-206", "Må i ikke vide xd", "Suits"));
            em.persist(new Member("Marcus Ravn Jensen","MJ-757","Går i dameundertøj","Sherlock Holmes"));
            em.persist(new Member("Mads Kristensen", "MK-613", "?XD", "House"));
            em.persist(new Member("Albert Sylvester Løhde", "al-324","Har haft en  legoklods siddende fast i endetarmen siden jeg var 4 år gammel","Gossip girl"));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
