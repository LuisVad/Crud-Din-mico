package utez.edu.mx.movies.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.movies.entities.Peliculas;
import utez.edu.mx.movies.services.PeliculasServices;
import utez.edu.mx.movies.utils.CustomResponse;


@RestController
@RequestMapping("/api/peliculas")
@CrossOrigin(origins = "*")
public class PeliculasController {

    @Autowired
    PeliculasServices service;

    @GetMapping("/getAll")
    public CustomResponse<List<Peliculas>> getAllPeliculas() throws SQLException {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public CustomResponse<Peliculas> getById(@PathVariable Long id) throws SQLException {
        return service.getById(id);
    }

    @PostMapping("/registrar")
    public CustomResponse<Peliculas> save(@RequestBody Peliculas pelicula) throws SQLException {
        return service.save(pelicula);
    }

    @PutMapping("/actualizar")
    public CustomResponse<Peliculas> update(@RequestBody Peliculas pelicula) throws SQLException {
        return service.update(pelicula);
    }

    @DeleteMapping("/{id}")
    public CustomResponse<Peliculas> delete(@PathVariable Long id) throws SQLException {
        return service.delete(id);
    }
}
