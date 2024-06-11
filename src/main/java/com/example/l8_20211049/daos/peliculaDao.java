package com.example.l8_20211049.daos;

import com.example.l8_20211049.beans.pelicula;

import java.sql.*;
import java.util.ArrayList;
import java.sql.Connection;

public class peliculaDao extends baseDao{

    public ArrayList<pelicula> listarPeliculas() {

        ArrayList<pelicula> listaPeliculas = new ArrayList<>();
        String sql = "SELECT A.*, B.NOMBRE, C.NOMBRESERVICIO FROM  \n" +
                "(SELECT * FROM Pelicula ) AS A \n" +
                "INNER JOIN \n" +
                "(SELECT * FROM GENERO) AS B\n" +
                "ON A.IDGENERO = B.IDGENERO\n" +
                "INNER JOIN \n" +
                "(SELECT * FROM STREAMING) AS C\n" +
                "ON A.IDSTREAMING = C.IDSTREAMING\n" +
                "ORDER BY RATING DESC , BOXOFFICE DESC";

        try(Connection conn = this.getConection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                pelicula movie = new pelicula();
                int idPelicula = rs.getInt(1);
                movie.setIdPelicula(idPelicula);
                String titulo = rs.getString("titulo");
                movie.setTitulo(titulo);
                String director = rs.getString("director");
                movie.setDirector(director);
                int anoPublicacion = rs.getInt("anoPublicacion");
                movie.setAnoPublicacion(anoPublicacion);
                double rating = rs.getDouble("rating");
                movie.setRating(rating);
                double boxOffice = rs.getDouble("boxOffice");
                movie.setBoxOffice(boxOffice);
                String nombregenero = rs.getString("nombre");
                movie.setGenero(nombregenero);
                String duracion = rs.getString("duracion");
                movie.setDuracion(duracion);
                String streaming = rs.getString("nombreServicio");
                movie.setStreaming(streaming);
                boolean oscar = rs.getBoolean("premioOscar");
                movie.setPremioOscar(oscar);

                listaPeliculas.add(movie);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaPeliculas;
    }

    public ArrayList<pelicula> listarPeliculasFiltradas(int idGenero) {

        ArrayList<pelicula> listaPeliculasFiltradas= new ArrayList<>();

        return listaPeliculasFiltradas;
    }

        public void crearPelicula(String titulo, String director, int anoPublicacion,double boxOffice, double rating, String duracion, int idGenero, int idStreaming, boolean premioOscar){
        String query = "INSERT INTO pelicula (titulo, director, anoPublicacion, rating, boxOffice, idGenero, duracion, premioOscar, idStreaming) VALUES (?,?,?,?,?,?,?,?,?)";
        try (Connection connection = this.getConection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, titulo);
            pstmt.setString(2, director);
            pstmt.setInt(3, anoPublicacion);
            pstmt.setDouble(4, rating);
            pstmt.setDouble(5, boxOffice);
            pstmt.setString(6, duracion);
            pstmt.setInt(7, idGenero);
            pstmt.setBoolean(8, premioOscar);
            pstmt.setInt(9, idStreaming);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void borrarPelicula(int idPelicula) {
        String query = "DELETE FROM pelicula WHERE idPelicula = ?";
        if (deleteValidation(pelicula)) {
            try (Connection connection = getConnection();
                 PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setInt(1, idPelicula);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No se puede borrar la película.");
        }


    }

    @Override
    protected boolean deleteValidation(Object obj) {
        pelicula pelicula = (pelicula) obj;
        return pelicula.getDuracion().contains(">180") && !pelicula.isPremioOscar();
    }


}
