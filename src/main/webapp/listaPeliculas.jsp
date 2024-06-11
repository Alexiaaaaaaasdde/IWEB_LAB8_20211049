

<%@page import="java.util.ArrayList"%>
<%@page import="com.example.l8_20211049.beans.pelicula"%>
<%@page import="com.example.l8_20211049.beans.genero"%>
<%@page import="com.example.l8_20211049.beans.streaming"%>
<%@page import="java.text.NumberFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    //Job job = (Job) request.getAttribute("job");
    ArrayList<pelicula> lista = (ArrayList) request.getAttribute("listaPeliculas");
    //ArrayList<genero> listaGeneros = (ArrayList) request.getAttribute("listarGeneros");
    //ArrayList<streaming> listaStreaming = (ArrayList) request.getAttribute("listarStraming");
    String searchTerm = request.getParameter("searchTerm");
    NumberFormat formatter = NumberFormat.getInstance();
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Películas</title>
</head>
<body>

<h1>Lista de Películas</h1>




<table border="1">
    <tr>

        <th>Titulo</th>
        <th>Director</th>
        <th>Ano Publicacion</th>
        <th>Rating</th>
        <th>BoxOffice</th>
        <th>Genero</th>
        <th>Duracion</th>
        <th>Streaming</th>
        <th>Premios Oscar</th>
        <th>Actores</th>
        <th>Borrar</th>

    </tr>
    <%
        for (pelicula movie : lista) {
    %>
    <tr>

        <td><a href="viewPelicula?idPelicula=<%= movie.getIdPelicula() %>"><%=movie.getTitulo()%></a></td>
        <td><%=movie.getDirector()%></td>
        <td><%=movie.getAnoPublicacion()%></td>
        <td><%=movie.getRating()%>/10</td>
        <td>$<%=formatter.format(movie.getBoxOffice())%></td>
        <td><%=movie.getGenero()%></td>
        <td><%=movie.getDuracion()%></td>
        <td><%=movie.getStreaming()%></td>
        <td><%=movie.isPremioOscar()%></td>
        <td><a href="listaActores?idPelicula=<%= movie.getIdPelicula() %>">Ver Actores</a></td>
        <td>
            <a class="btn btn-danger"
               href="<%=request.getContextPath()%>/PeliculaServlet?action=borrar&id=<%=movie.getIdPelicula()%>">
                <i class="bi bi-trash3"></i>
            </a>
        </td>
    </tr>

    <%
        }
    %>

</table>

<a href="listaPeliculas?action=crear" style="display: inline-block; padding: 10px 20px; background-color: #007bff; color: #fff; text-decoration: none; border: none; border-radius: 5px;">Crear Pelicula</a>

</body>
</html>
