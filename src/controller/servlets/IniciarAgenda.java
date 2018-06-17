package controller.servlets;

/*Servlet para iniciar a agenda*/

import controller.banco.ContatoDAO;
import model.Contato;

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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try{
            var listaContatos = new ContatoDAO().carregarContatos();

            for(Contato c:listaContatos){
                System.out.println(c.getListaEmail());
            }

            request.setAttribute("listacontatos", listaContatos);

            request.getRequestDispatcher("/telainicial.jsp").forward(request, response);

        }catch (Exception e){
            e.printStackTrace();
            throw new ServletException("erro ao carregar agenda", e);
        }
    }

}
