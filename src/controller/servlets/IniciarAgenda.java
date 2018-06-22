package controller.servlets;

/*Servlet para iniciar a agenda*/

import controller.banco.ContatoDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "IniciarAgenda", urlPatterns = "/IniciarAgenda")
public class IniciarAgenda extends HttpServlet {

    public IniciarAgenda() {
        super();
    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            var listaContatos = new ContatoDAO().carregarContatos();

            request.setAttribute("listacontatos", listaContatos);

            request.getRequestDispatcher("/telainicial.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("erro ao carregar agenda", e);
        }
    }

}
