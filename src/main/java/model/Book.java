package model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int bookId;
    @Column(length = 200, nullable = false)
    private String  title;
    @Column(name = "publish_date", length = 10, nullable = false)
    private LocalDate publishDate;
    private Integer rating;
    @Column(name = "total_pages", nullable = false)
    private Integer totalPages;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @Column(name = "date_of_register", nullable = false)
    private LocalDate dateOfRegister;

    public Book(String title, LocalDate publishDate, Integer rating, Integer totalPages, LocalDate dateOfRegister, Author author, Publisher publisher) {
        this.title = title;
        this.publishDate = publishDate;
        this.rating = rating;
        this.totalPages = totalPages;
        this.dateOfRegister = dateOfRegister;
        this.author = author;
        this.publisher = publisher;
    }

    public Book() {}

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public LocalDate getDateOfRegister() {
        return dateOfRegister;
    }

    public void setDateOfRegister(LocalDate dateOfRegister) {
        this.dateOfRegister = dateOfRegister;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", rating=" + rating +
                ", totalPages=" + totalPages +
                ", author=" + author +
                ", genre=" + genre +
                ", publisher=" + publisher +
                ", dateOfRegister=" + dateOfRegister +
                '}';
    }
}
