package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private int genreId;
    @Column(length = 45, nullable = false)
    private String genre;

    @OneToMany(mappedBy = "genre")
    private List<Book> books;

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "genreId=" + genreId +
                ", genre='" + genre + '\'' +
                '}';
    }
}
