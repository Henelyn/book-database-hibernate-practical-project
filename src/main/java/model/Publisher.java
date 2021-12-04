package model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Publisher")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id")
    private int publisherId;
    @Column(length = 45, nullable = false)
    private String name;
    @Column(name = "year_of_establishment", length = 10)
    private LocalDate yearOfEstablishment;

    public Publisher(String name, LocalDate yearOfEstablishment, Country country) {
        this.name = name;
        this.yearOfEstablishment = yearOfEstablishment;
        this.country = country;
    }

    public Publisher(){}

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(mappedBy = "publisher")
    private List<Book> books;

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getYearOfEstablishment() {
        return yearOfEstablishment;
    }

    public void setYearOfEstablishment(LocalDate yearOfEstablishment) {
        this.yearOfEstablishment = yearOfEstablishment;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "publisherId=" + publisherId +
                ", name='" + name + '\'' +
                ", yearOfEstablishment='" + yearOfEstablishment + '\'' +
                ", country=" + country +
                '}';
    }
}
