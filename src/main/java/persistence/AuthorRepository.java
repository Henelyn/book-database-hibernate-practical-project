package persistence;

import model.Author;
import util.DbUtil;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

public class AuthorRepository {

    private EntityManager entityManager;

    public AuthorRepository() {
        entityManager = DbUtil.getEntityManager();
    }


    public void saveAuthor(Author author) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(author);
            entityManager.getTransaction().commit();
            System.out.println("Author saved");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void updateAuthor(Author author) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(author); // merge updateb eelmise (kõikn väljad). based on primary key
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void deleteAuthor(Author author) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.merge(author));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public Author findAuthorById(int authorId) {
        Author author = null;
        try {
            author = entityManager.find(Author.class, authorId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return author;
    }

    public List<Author> listAllAuthors() {
        return entityManager.createQuery("FROM Author", Author.class).getResultList();
    }

    public Author findAuthorByFirstNameAndLastName(String firstName, String lastName) {
        String sql = "FROM Author WHERE firstName = :fname AND lastName = :lname";
        return entityManager.createQuery(sql, Author.class)
                .setParameter("fname", firstName)
                .setParameter("lname", lastName)
                .getSingleResult();
    }

    public Author findAuthorByPseudonym(String pseudonym) {
            String sql = "FROM Author WHERE name = :pseudonym"; //Ei saa aru, kuhu pean panema pseudonym, kuhu name, kuhu author
            return entityManager.createQuery(sql, Author.class)
                    .setParameter("name", pseudonym)
                    .getSingleResult();
        }

    public List<Author> findAuthorByNationality(String nationality) {
        return null;
    }

    public List<Author> findAuthorsByDateOfBirth(LocalDate dateOfBirth) {
        return entityManager.createQuery("FROM Author WHERE dateOfBirth = :date", Author.class)
                .setParameter("date", dateOfBirth)
                .getResultList();
    }

    public List<Author> findAuthorsByDateOfBirthRange(LocalDate fromDob, LocalDate toDob) {
        return entityManager.createQuery("FROM Author WHERE dateOfBirth between :from AND :to", Author.class)
                .setParameter("from", fromDob)
                .setParameter("to", toDob)
                .getResultList();
    }
}



