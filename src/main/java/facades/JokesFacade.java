package facades;

import DTOs.JokeDTO;
import DTOs.MemberDTO;
import entities.Joke;
import entities.Member;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class JokesFacade {

    private static JokesFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private JokesFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static JokesFacade getJokesFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new JokesFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<JokeDTO> getAllJokes() {

        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT m FROM Joke m");
            List<JokeDTO> joke = query.getResultList();
            return joke;
        } finally {
            em.close();
        }
    }
    public JokeDTO getRandomJoke(){
              EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("SELECT m FROM Joke m");
            List<JokeDTO> jokes = query.getResultList();
            
            int tal = (int) (Math.random()*jokes.size());
            
            JokeDTO joke = jokes.get(tal);
            
            return joke;
        } finally {
            em.close();
        }  
    }
    
        public JokeDTO getJokeById(long id){
        EntityManager em = emf.createEntityManager();
        try {
              Query query = em.createNamedQuery("SELECT m FROM Joke m WHERE m.id = :id");
              query.setParameter("id", id);
              JokeDTO joke = (JokeDTO) query.getSingleResult();
              return joke;
        }         
        finally {
            em.close();
        }
    }

    public void populateDB() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            em.persist(new Joke("Din mor er så fed at man kan se hendes telefon nummer når hun stiller sig på vægten", "Albert", "Din mor"));
            em.persist(new Joke("hvorfor begraver man altid en Aarhusianer med røven i vejret? Så han kan bruges som cykelstativ", "Københavner", "Aarhusianer joke"));
            em.persist(new Joke("Hvad sagde præsten da han satte sig på biblen? \"Kors i røven!\"", "Marcus", "Præste joke"));
            em.persist(new Joke("Du er så fattig, at når du går ned ad gaden i en sko og folk spørger om du har tabt en, svarer du \"Nej jeg har fundet en!\" ", "Albert", "Fattig joke"));
            em.persist(new Joke("Hvorfor var blondinen glad for, at samle et puzzlespil på 6 måneder?\n" +
"– fordi der stod 2-4 år", "Mads", "Blondine joke"));
            em.persist(new Joke("Hvorfor tager århusianeren 2 bildøre med ud i ørkenen?\n" +
"– For at lave gennemtræk", "Marcus", "Aarhusianer joke"));
            em.persist(new Joke("Du har ringet til ishockey hallen\n" +
"– du taler med Puk","Marcus","Ishockey joke"));
            em.persist(new Joke("Hvad er forskellen mellem en bog og en kvinde?\n" +
"– En bog kan finde ud af at klappe i.","Mads","Sexistisk joke"));
            em.persist(new Joke("Er Google hankøn eller hunkøn? Hunkøn – for det lader dig ikke færdiggøre en sætning.","Mads","Sexistisk joke"));
            em.persist(new Joke("Hvad kalder man en person, som både køber og så også sælger katte?\n" +
"Han er en mishandler.","Mads","Katte Joke"));
            em.persist(new Joke("Hvordan ved du at din hun kat er færdig med at slikke sig selv?\n" +
"Den ryger en cigaret!","Albert","Katte Joke"));
            em.persist(new Joke("Hvad var det, at den ene røvballe sagde til den anden?\n" +
"“Jeg synes altså, der har været så meget lort mellem os”","Mads","Far humor"));
            em.persist(new Joke("hvor mange blondiner skal til for at skrue en pære i\n" +
"5, 1 til at holde pæren og 4 til at rotere stigen","Marcus","Blondine joke"));
            em.persist(new Joke("Hvad koster en hjort?\n" +
"– Jeg ved det ikke præcist, men den er rådyr","Mads","Far humor"));
            
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}