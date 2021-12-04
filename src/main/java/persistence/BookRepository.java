package persistence;

import model.Author;
import model.Book;
import model.Publisher;
import util.DbUtil;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

public class BookRepository {
    private EntityManager entityManager;

    public BookRepository() {
        entityManager = DbUtil.getEntityManager();
    }

    public void saveBook(Book book) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(book);
            entityManager.getTransaction().commit();
            System.out.println("Book saved");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void updateBook(Book book) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(book);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void deleteBook(Book book) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.merge(book));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public Book findBookById(int bookId) {
        Book book = null;
        try {
            book = entityManager.find(Book.class, bookId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }

    public Book updateRatingByBookId(int bookId, int rating) {
        Book book = findBookById(bookId);
        if (book != null) {
            try {
                entityManager.getTransaction().begin();
                book.setRating(rating);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
                entityManager.getTransaction().rollback();
            }
        } else {
            System.out.println("Book with id = " + bookId + " is not found.");
        }
        return book;
    }

    public List<Book> listAllBooks() {
        return entityManager.createQuery("FROM Book", Book.class).getResultList();
    }

    public List<Book> findAllBooksByDateOfRegisterLastMonth() {
        return entityManager.createQuery("FROM Book WHERE dateOfRegister BETWEEN :dateFrom AND :dateTo", Book.class)
                .setParameter("dateFrom", LocalDate.now().minusMonths(1))
                .setParameter("dateTo", LocalDate.now())
                .getResultList();
    }

    public List<Book> findAllBooksByDateOfRegisterYesterday() {
        return entityManager.createQuery("FROM Book WHERE dateOfRegister = :yesterday", Book.class)
                .setParameter("yesterday", LocalDate.now().minusDays(1))
                .getResultList();
    }

    public List<Book> findBookByTitle(String title) {
        return null;
    }

    public List<Book> findBookByRating(int rating) {
        return null;
    }

    public List<Book> findBookByAuthorFirstNameAndLastName() {
        return null;
    }

    public List<Book> findBookByGenre(String genre) {
        return null;
    }

    public List<Book> findBooksByPublisher(Publisher publisher) {
        return entityManager.createQuery("FROM Book WHERE publisher = :publisher", Book.class)
                .setParameter("publisher", publisher)
                .getResultList();
    }

    public List<Book> findBookByAuthor(Author author) {
        return entityManager.createQuery("FROM Book WHERE author = :author", Book.class)
                .setParameter("author", author)
                .getResultList();
    }

    public Long findBookTotalCount() {
        return (Long) entityManager.createQuery("SELECT count(*) from Book").getSingleResult();
    }
}

