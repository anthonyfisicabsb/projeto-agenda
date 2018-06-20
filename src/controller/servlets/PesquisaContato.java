package controller.servlets;

import controller.banco.ContatoDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "PesquisaContato", urlPatterns = "/PesquisaContato")
public class PesquisaContato extends HttpServlet {

    public PesquisaContato() {
        super();
    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var pesquisa = request.getParameter("barrapesquisa");

        var lista = new ContatoDAO().pesquisarContatos(pesquisa);

        request.setAttribute("resultado", lista);

        request.getRequestDispatcher("telaresultado.jsp").forward(request, response);
    }
}
