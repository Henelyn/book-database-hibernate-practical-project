package persistence;

import model.Country;
import model.Genre;
import util.DbUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class GenreRepository {
    private EntityManager entityManager;

    public GenreRepository() {
        entityManager = DbUtil.getEntityManager();
    }

    public void saveGenre(Genre genre) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(genre);
            entityManager.getTransaction().commit();
            System.out.println("Genre saved");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void updateGenre(Genre genre) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(genre);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void deleteGenre(Genre genre) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.merge(genre));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public Genre findGenreByGenre(String genre) {
        String sql = "FROM Genre WHERE genre = :name";
        return entityManager.createQuery(sql, Genre.class)
                .setParameter("name", genre)
                .getSingleResult();
    }
    public List<Genre> listAllGenres() {
        return entityManager.createQuery("FROM Genre", Genre.class).getResultList();
    }
}
