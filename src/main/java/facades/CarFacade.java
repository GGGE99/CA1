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
import javax.persistence.Query;

/**
 *
 * @author marcg
 */
public class CarFacade {

    private static CarFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private CarFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CarFacade getCarFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CarFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<CarDTO> getAllCars() {

        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("select c from Car c");
            List<CarDTO> member = query.getResultList();
            return member;
        } finally {
            em.close();
        }
    }

    public void populateDB() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new Car(2004, "Fiat", "Multipla", 20.0, 10.0, "Albert"));
            em.persist(new Car(2012, "Honda", "Civic", 2000.0, 5.5, "Marc"));
            em.persist(new Car(2018, "Mercedes", "S-class", 200000000.0, 3.0, "Mads"));
            em.persist(new Car(2019, "Aston Martin", "DB9", 20000.0, 14.0, "Marcus"));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
