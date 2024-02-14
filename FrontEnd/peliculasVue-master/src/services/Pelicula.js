import axios from 'axios';

let GET_PELICULA = 'http://localhost:8080/api/peliculas/getAll';
let GET_GENERO = 'http://localhost:8080/api/generos/getAll';
let ADD_PELICULA = 'http://localhost:8080/api/peliculas/registrar';
let DELETE_PELICULA = 'http://localhost:8080/api/peliculas/';
let UPDATE_PELICULA = 'http://localhost:8080/api/peliculas/actualizar';


const obtenerPeliculas = async () => {
    try {
       const response = await axios.get(GET_PELICULA);
       console.log(response);
       return response.data.data;
    } catch (error) {
       throw error;
    }
 };
 
 const obtenerGeneros = async () => {
    try {
       const response = await axios.get(GET_GENERO);
       console.log(response);
       return response.data.data;
    } catch (error) {
       throw error;
    }
 };
 
 const agregarPelicula = async (datosPelicula) => {
     try {
        const response = await axios.post(ADD_PELICULA, datosPelicula);
        console.log(response);
        return response.data.data;
     } catch (error) {
        throw error;
     }
 };
 
 const eliminarPelicula = async (idPelicula) => {
     try {
        const response = await axios.delete(`${DELETE_PELICULA}${idPelicula}`);
        console.log(response);
        return response.data.data;
     } catch (error) {
        throw error;
     }
 };
 
 const actualizarPelicula = async (datosPelicula) => {
     try {
        const response = await axios.put(UPDATE_PELICULA, datosPelicula);
        console.log(response);
        return response.data.data;
     } catch (error) {
        throw error;
     }
 };
 
 export default {
    obtenerPeliculas,
    obtenerGeneros,
    agregarPelicula,
    eliminarPelicula,
    actualizarPelicula
 };