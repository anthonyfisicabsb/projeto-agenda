package controller.servlets;

import controller.banco.ContatoDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ExcluirContato", urlPatterns = "/ExcluirContato")
public class ExcluirContato extends HttpServlet {

    public ExcluirContato() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        var id = Integer.parseInt(request.getParameter("idcontato"));
        var contatodao = new ContatoDAO();

        contatodao.excluirContato(id);

        request.getRequestDispatcher("IniciarAgenda").forward(request, response);
    }
}
