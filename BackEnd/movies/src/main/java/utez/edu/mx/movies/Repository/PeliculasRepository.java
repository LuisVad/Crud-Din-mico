package utez.edu.mx.movies.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utez.edu.mx.movies.entities.Peliculas;

@Repository
public interface PeliculasRepository extends JpaRepository<Peliculas,Long> {
    boolean existsByName(String name);
}
