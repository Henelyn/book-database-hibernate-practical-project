package model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity                 //Loob uue tabeli klassi nimega
@Table(name = "Author") //Kui soovid teise nimega tabelit. Optional. Sql's ilmselt väikese tähega kõik.
public class Author {
    @Id                 //Id = primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment
    @Column(name = "author_id") // optional, if you'd like to change something
    private int authorId;
    @Column(name = "first_name", length = 45, nullable = false)
    private String firstName;
    @Column(name = "last_name", length = 45, nullable = false)
    private String lastName;
    @Column(length = 45)
    private String pseudonym;
    @Column(name = "date_of_birth", length = 10, nullable = false)
    private LocalDate dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "nationality_id")
    private Nationality nationality;

    @OneToMany(mappedBy = "author")
    private List<Book> books;

    public Author(String firstName, String lastName, String pseudonym, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pseudonym = pseudonym;
        this.dateOfBirth = dateOfBirth;
    }

    public Author() {}

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPseudonym() {
        return pseudonym;
    }

    public void setPseudonym(String pseudonym) {
        this.pseudonym = pseudonym;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", pseudonym='" + pseudonym + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", nationality=" + nationality +
                '}';
    }
}
