package persistence;

import model.Genre;
import model.Nationality;
import util.DbUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class NationalRepository {

    private EntityManager entityManager;

    public NationalRepository() {
        entityManager = DbUtil.getEntityManager();
    }
    public void saveNationality(Nationality nationality) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(nationality);
            entityManager.getTransaction().commit();
            System.out.println("Nationality saved");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void updateNationality(Nationality nationality) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(nationality);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void deleteNationality(Nationality nationality) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.merge(nationality));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public List<Nationality> listAllNationalities() {
        return entityManager.createQuery("FROM Nationality", Nationality.class).getResultList();
    }
}
