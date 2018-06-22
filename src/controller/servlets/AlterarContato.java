package controller.servlets;

import controller.banco.ContatoDAO;
import model.Contato;
import model.Endereco;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@WebServlet(name = "AlterarContato", urlPatterns = "/AlterarContato")
public class AlterarContato extends HttpServlet {
    public AlterarContato() {
        super();
    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var dao = new ContatoDAO();
        var contato = new Contato();
        var sfmt = new SimpleDateFormat("dd/MM/yyyy");

        try {
            contato.setIdBanco(Integer.parseInt(request.getParameter("id")));
            contato.setNome(request.getParameter("nome"));
            contato.setSobrenome(request.getParameter("sobrenome"));
            contato.setSobrenomeFonetico(request.getParameter("sobrenomefonetico"));
            contato.setNomeFonetico("nomefonetico");
            contato.setEmpresa(request.getParameter("empresa"));
            contato.setEmpresaFonetico(request.getParameter("empresafonetico"));
            contato.setListaTelefone(new ArrayList<>());
            contato.setDataAniversario(sfmt.parse(request.getParameter("datanascimento")));

            for (String telefone : request.getParameterValues("telefones"))
                contato.getListaTelefone().add(telefone);

            for (String email : request.getParameterValues("email"))
                contato.getListaEmail().add(email);

            for (String url : request.getParameterValues("url"))
                contato.getListaURL().add(url);

            for (String redesocial : request.getParameterValues("redesocial"))
                contato.getListaRedeSocial().add(redesocial);

            var ruas = request.getParameterValues("rua");
            var bairros = request.getParameterValues("bairro");
            var cidades = request.getParameterValues("cidade");
            var estados = request.getParameterValues("estado");
            var complementos = request.getParameterValues("complemento");
            var ceps = request.getParameterValues("cep");
            var paises = request.getParameterValues("pais");

            contato.setListaEndereco(new ArrayList<>());

            for (int i = 0; i < ruas.length; i++) {
                var endereco = new Endereco();

                endereco.setRua(ruas[i]);
                endereco.setBairro(bairros[i]);
                endereco.setCidade(cidades[i]);
                endereco.setEstado(estados[i]);
                endereco.setComplemento(complementos[i]);
                endereco.setCEP(Integer.parseInt(ceps[i]));
                endereco.setPais(paises[i]);

                contato.getListaEndereco().add(endereco);
            }


            dao.alterarContato(contato);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("erro ao alterar contato", e);
        }

        request.getRequestDispatcher("/startagenda.html").forward(request, response);
    }

}
