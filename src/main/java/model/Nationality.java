package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Nationality")
public class Nationality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nationality_id")
    private int nationalityId;
    @Column(length = 45, nullable = false)
    private String nationality;

    @OneToMany(mappedBy = "nationality")
    private List<Author> authors;

    public int getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(int nationalityId) {
        this.nationalityId = nationalityId;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "Nationality{" +
                "nationalityId=" + nationalityId +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}
