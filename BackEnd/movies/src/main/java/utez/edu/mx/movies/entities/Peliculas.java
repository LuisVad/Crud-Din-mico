package utez.edu.mx.movies.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "peliculas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Peliculas{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_movie;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String director_movie;

    @Column(nullable = false)
    private long duration;

    @ManyToOne
    @JoinColumn(name = "fk_genero")
    private Generos genero;

}
