package utez.edu.mx.movies.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.movies.Repository.PeliculasRepository;
import utez.edu.mx.movies.entities.Peliculas;
import utez.edu.mx.movies.utils.CustomResponse;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PeliculasServices {
    @Autowired
    private PeliculasRepository repository;

    @Transactional(readOnly = true)
    public CustomResponse<List<Peliculas>> getAll() throws SQLException {
        return new CustomResponse<>(this.repository.findAll(), false, 200, "Ok");
    }

    @Transactional(readOnly = true)
    public CustomResponse<Peliculas> getById(Long id) throws SQLException {
        Optional<Peliculas> peliOptional = this.repository.findById(id);
        if (peliOptional.isPresent()) {
            return new CustomResponse<>(
                    peliOptional.get(),
                    false,
                    200,
                    "Ok"
            );
        } else {
            return new CustomResponse<>(
                    null,
                    true,
                    404,
                    "Pelicula no encontrada"
            );
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Peliculas> save(Peliculas pelicula) throws SQLException {
        if (this.repository.existsByNombre(pelicula.getNombre())) {
            return new CustomResponse<>(
                    null,
                    false,
                    404,
                    "¡Está Película ya existe!"
            );
        } else {
            return new CustomResponse<>(
                    this.repository.saveAndFlush(pelicula),
                    true,
                    200,
                    "Pelicula registrada correctamente"
            );
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Peliculas> update(Peliculas pelicula) throws SQLException {
        if (!this.repository.existsById(pelicula.getId_movie())) {
            return new CustomResponse<>(
                    null,
                    true,
                    404,
                    "¡La película que quieres buscar no existe!"
            );
        } else {
            return new CustomResponse<>(
                    this.repository.saveAndFlush(pelicula),
                    false,
                    200,
                    "Película actualizada correctamente"
            );
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Peliculas> delete(Long id) throws SQLException {
        if (!repository.existsById(id)) {
            return new CustomResponse<>(
                    null,
                    true,
                    404,
                    "¡La película que quieres eliminar no existe!"
            );
        } else {
            repository.deleteById(id);
            return new CustomResponse<>(
                    null,
                    false,
                    200,
                    "Película eliminada correctamente"
            );
        }
    }

    public CustomResponse<List<Peliculas>> getPeliculasByNombre(String nombre) {
        List<Peliculas> peliculas = repository.findByNombreContaining(nombre);
        return new CustomResponse<>(peliculas, false, 200, "Ok");
    }

    public CustomResponse<List<Peliculas>> getPeliculasByDirector(String director) {
        List<Peliculas> peliculas = repository.findByDirectorMovieContaining(director);
        return new CustomResponse<>(peliculas, false, 200, "Ok");
    }

    public CustomResponse<List<Peliculas>> getPeliculasByFechaPublicacion(Date fechaInicio, Date fechaFin) {
        List<Peliculas> peliculas = repository.findByFechaPublicacionBetween(fechaInicio, fechaFin);
        return new CustomResponse<>(peliculas, false, 200, "Ok");
    }

    public CustomResponse<List<Peliculas>> getPeliculasByGenero(String genero) {
        List<Peliculas> peliculas = repository.findByGeneroName(genero);
        return new CustomResponse<>(peliculas, false, 200, "Ok");
    }

    public CustomResponse<List<Peliculas>> getPeliculasOrderByFechaPublicacionDesc() {
        List<Peliculas> peliculas = repository.findByOrderByFechaPublicacionDesc();
        return new CustomResponse<>(peliculas, false, 200, "Ok");
    }

}
