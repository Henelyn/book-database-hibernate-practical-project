package persistence;

import model.Publisher;
import util.DbUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class PublisherRepository {

    private EntityManager entityManager;

    public PublisherRepository() {
        entityManager = DbUtil.getEntityManager();
    }

    public void savePublisher(Publisher publisher) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(publisher);
            entityManager.getTransaction().commit();
            System.out.println("Publisher saved");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void updatePublisher(Publisher publisher) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(publisher);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void deletePublisher(Publisher publisher) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.merge(publisher));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public Publisher findPublisherById(int publisherId) {
        Publisher publisher = null;
        try {
            publisher = entityManager.find(Publisher.class, publisherId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return publisher;
    }

    public List<Publisher> listOfAllPublishers() {
        return entityManager.createQuery("FROM Publisher", Publisher.class).getResultList();
    }

    public Publisher findPublisherByName(String publisher) {
        String sql = "FROM Publisher WHERE name = :publisher";
        return entityManager.createQuery(sql, Publisher.class)
                .setParameter("publisher", publisher)
                .getSingleResult();
    }
}
