package controller.servlets;

import controller.banco.ContatoDAO;
import model.Contato;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ContatoParaAlterar", urlPatterns = "/ContatoParaAlterar")
public class ContatoParaAlterar extends HttpServlet {

    public ContatoParaAlterar() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            var listaContato = new ContatoDAO().carregarContatos();
            var contato = new Contato();
            var id = Integer.parseInt(request.getParameter("idcontato"));

            for (Contato cont : listaContato) {
                if (id == cont.getIdBanco())
                    contato = cont;
            }

            request.setAttribute("contato", contato);
            request.getRequestDispatcher("/alterarcontato.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("erro ao carregar contato para alterar", e);
        }
    }

}
