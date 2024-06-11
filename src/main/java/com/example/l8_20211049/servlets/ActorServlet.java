package com.example.l8_20211049.servlets;

import com.example.l8_20211049.beans.actor;
import com.example.l8_20211049.beans.pelicula;
import com.example.l8_20211049.daos.actorDao;
import com.example.l8_20211049.daos.peliculaDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "actor-servlet", value = "/listaActores")
public class ActorServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        int idPelicula = Integer.parseInt(request.getParameter("idPelicula"));
        actorDao actorDao = new actorDao();
        ArrayList<actor> listaActores = actorDao.listarActores(idPelicula);
        request.setAttribute("listaActores", listaActores);

        RequestDispatcher view = request.getRequestDispatcher("actores.jsp");
        view.forward(request,response);
    }
}
