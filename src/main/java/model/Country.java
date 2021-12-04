package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private int countryId;
    @Column(length = 45, nullable = false)
    private String country;

    public Country(String country) {
        this.country = country;
    }

    public Country(){}

    @OneToMany(mappedBy = "country")
    private List<Publisher> publishers;

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryId=" + countryId +
                ", country='" + country + '\'' +
                '}';
    }
}
