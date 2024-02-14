<template>
  <div>
    <h1>Peliculas</h1>
    <div>
      <b-button v-b-modal.modal-p>Agregar Pelicula</b-button>

      <b-modal id="modal-1" title="BootstrapVue">
        <p class="my-4">Hello from modal!</p>
      </b-modal>
      <b-modal id="modal-m" title="Modificar Película">
        <div>
          <b-form @submit="onSubmit2" @reset="onReset" v-if="show">
            <!-- Campo oculto para almacenar el ID de la película -->
            <input type="hidden" v-model="form.id_movie" value="peliculasel"/>

            <b-form-group
              id="input-group-2"
              label="Nombre:"
              label-for="input-2"
            >
              <b-form-input
                id="input-2"
                v-model="form.name"
                placeholder="Ingrese el nombre de la película"
                required
              ></b-form-input>
            </b-form-group>

            <b-form-group
              id="input-group-3"
              label="Director:"
              label-for="input-3"
            >
              <b-form-input
                id="input-3"
                v-model="form.director_movie"
                placeholder="Ingrese el nombre del director"
                required
              ></b-form-input>
            </b-form-group>

            <b-form-group
              id="input-group-3"
              label="Duración:"
              label-for="input-3"
            >
              <b-form-input
                id="input-3"
                v-model="form.duration"
                placeholder="Ingrese la duración de la película"
                required
              ></b-form-input>
            </b-form-group>

            <b-form-group
              id="input-group-4"
              label="Género:"
              label-for="input-4"
              class="mb-3"
            >
              <b-form-select
                id="input-4"
                v-model="form.genero"
                :options="generos"
                required
              ></b-form-select>
            </b-form-group>

            <b-button type="submit" variant="primary">Modificar</b-button>
            <b-button type="reset" variant="danger">Reset</b-button>
          </b-form>
        </div>
      </b-modal>

      <b-modal id="modal-p" title="Agregar Película">
        <div>
          <b-form @submit="onSubmit" @reset="onReset" v-if="show">
            <b-form-group
              id="input-group-2"
              label="Nombre:"
              label-for="input-2"
            >
              <b-form-input
                id="input-2"
                v-model="form.name"
                placeholder="Ingrese el nombre de la película"
                required
              ></b-form-input>
            </b-form-group>

            <b-form-group
              id="input-group-3"
              label="Director:"
              label-for="input-3"
            >
              <b-form-input
                id="input-3"
                v-model="form.director_movie"
                placeholder="Ingrese el nombre del director"
                required
              ></b-form-input>
            </b-form-group>

            <b-form-group
              id="input-group-3"
              label="Duración:"
              label-for="input-3"
            >
              <b-form-input
                id="input-3"
                v-model="form.duration"
                placeholder="Ingrese la duración de la película"
                required
              ></b-form-input>
            </b-form-group>

            <b-form-group
              id="input-group-4"
              label="Género:"
              label-for="input-4"
              class="mb-3"
            >
              <b-form-select
                id="input-4"
                v-model="form.genero"
                :options="generos"
                required
              ></b-form-select>
            </b-form-group>

            <b-button type="submit" variant="primary">Agregar</b-button>
            <b-button type="reset" variant="danger">Reset</b-button>
          </b-form>
        </div>
      </b-modal>
    </div>
    <b-card-group id="mainPeliculas" deck>
      <b-card
        v-for="(pelicula, index) in peliculas"
        :key="index"
        :title="pelicula.name"
        header-tag="header"
        footer-tag="footer"
        @click="setPelicula(pelicula.id_movie)"
      >
        <b-card-text>Director: {{ pelicula.director_movie }}</b-card-text>
        <b-card-text>Duración: {{ pelicula.duration }}</b-card-text>
        <b-card-text>Género: {{ pelicula.genero.name }}</b-card-text>
        <template #header>
          <b-container align-h="center">
            <b-row>
              <b-col>
                <b-button href="#" variant="warning" v-b-modal.modal-m
                  >Editar</b-button
                >
                <b-button
                  href="#"
                  variant="danger"
                  @click="eliminarPelicula(pelicula.id_movie)"
                >
                  Eliminar
                </b-button>
              </b-col>
            </b-row>
          </b-container>
        </template>
      </b-card>
    </b-card-group>
  </div>
</template>

<script>
import servicioPeliculas from "../services/Pelicula";

export default {
  data() {
    return {
      form: {
        name: "",
        director_movie: "",
        duration: "",
        genero: null,
      },
      peliculas: [],
      generos: [],
      generos_id: [],
      peliculasel:null,
      show: true,
    };
  },
  methods: {
    setPelicula(ide) {
      this.peliculasel = ide;
    },
    async obtenerPeliculas() {
      try {
        this.peliculas = await servicioPeliculas.obtenerPeliculas();
        console.log(this.peliculas);
      } catch (error) {
        console.error("Error al obtener películas:", error);
      }
    },
    async obtenerGeneros() {
      try {
        const generos = await servicioPeliculas.obtenerGeneros();
        this.generos = generos.map((item) => item.name);
        this.generos_id = generos;
        console.log(this.generos);
      } catch (error) {
        console.error("Error al obtener géneros:", error);
      }
    },
    async agregarPelicula() {
      try {
        const fidegenero = this.generos_id.find(
          (genero) => genero.name === this.form.genero
        );
        const id_gen = fidegenero.id_gen;
        await servicioPeliculas.agregarPelicula({
          ...this.form,
          genero: { id_gen },
        });
        alert("Película agregada exitosamente");
        // Puedes recargar la lista de películas después de agregar una nueva si lo deseas
        this.obtenerPeliculas();
      } catch (error) {
        console.error("Error al agregar película:", error);
      }
    },
    async eliminarPelicula(idPelicula) {
      try {
        await servicioPeliculas.eliminarPelicula(idPelicula);
        alert("Película eliminada exitosamente");
        // Actualizar la lista de películas después de eliminar una
        this.obtenerPeliculas();
      } catch (error) {
        console.error("Error al eliminar película:", error);
      }
    },
    async actualizarPelicula(idPelicula) {
      try {
        const id_movie = idPelicula;
        const fidegenero = this.generos_id.find(
          (genero) => genero.name === this.form.genero
        );
        const id_gen = fidegenero.id_gen;
        console.log({
          ...this.form,
          genero: { id_gen },
        });
        await servicioPeliculas.actualizarPelicula({
          ...this.form,
          genero: { id_gen },
          id_movie
        });
        alert("Película actualizada exitosamente");
        // Actualizar la lista de películas después de actualizar una
        this.obtenerPeliculas();
      } catch (error) {
        console.error("Error al actualizar película:", error);
      }
    },
    onSubmit(event) {
      event.preventDefault();
      this.agregarPelicula();
    },
    onSubmit2(event) {
      event.preventDefault();
      this.actualizarPelicula(this.peliculasel);
    },
    onReset(event) {
      event.preventDefault();
      // Resetear los valores del formulario
      this.form.director = "";
      this.form.titulo = "";
      this.form.duracion = "";
      this.form.genero = null;
      // Truco para resetear/limpiar el estado de validación del formulario del navegador
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    },
  },
  created() {
    // Llamar a las funciones de obtener películas y obtener géneros al crear el componente
    this.obtenerPeliculas();
    this.obtenerGeneros();
  },
};
</script>

<style>
</style>