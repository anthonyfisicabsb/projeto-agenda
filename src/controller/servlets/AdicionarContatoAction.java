package controller.servlets;

import controller.banco.ContatoDAO;
import model.Contato;
import model.Endereco;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "AdicionarContatoAction", urlPatterns = "/AdicionarContatoAction")
public class AdicionarContatoAction extends HttpServlet {
    public AdicionarContatoAction() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException {

        var listaTelefone = new ArrayList<String>();
        var listaEmail = new ArrayList<String>();
        var listaURL = new ArrayList<String>();
        var listaEndereco = new ArrayList<Endereco>();
        var listaRedeSocial = new ArrayList<String>();
        var f = new SimpleDateFormat("dd/MM/yyyy");

        Date dataAniversario = null;
        var nome = request.getParameter("nome");
        var nomeFonetico = request.getParameter("nomefonetico");
        var sobrenome = request.getParameter("sobrenome");
        var sobrenomeFonetico = request.getParameter("sobrenomefonetico");
        var empresa = request.getParameter("empresa");
        var empresaFonetico = request.getParameter("empresafonetico");

        try {
            dataAniversario = f.parse(request.getParameter("datanascimento"));
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }

        this.criarLista(listaTelefone, "telefones", request);
        this.criarLista(listaEmail, "email", request);
        this.criarLista(listaURL, "url", request);
        this.criarLista(listaRedeSocial, "redesocial", request);

        this.criarListaEndereco(listaEndereco, request);

        var contato = new Contato();

        contato.setDataAniversario(dataAniversario);
        contato.setEmpresaFonetico(empresaFonetico);
        contato.setEmpresa(empresa);
        contato.setNome(nome);
        contato.setSobrenome(sobrenome);
        contato.setNomeFonetico(nomeFonetico);
        contato.setSobrenomeFonetico(sobrenomeFonetico);

        contato.setListaEmail(listaEmail);
        contato.setListaEndereco(listaEndereco);
        contato.setListaEndereco(listaEndereco);
        contato.setListaURL(listaURL);
        contato.setListaTelefone(listaTelefone);
        contato.setListaRedeSocial(listaRedeSocial);

        var contatodao = new ContatoDAO();

        try {

            contatodao.inserirContato(contato);
            request.getRequestDispatcher("/IniciarAgenda").forward(request, response);

        } catch (Exception e4) {
            throw new ServletException("erro ao inserir contato", e4);
        }
    }

    public void criarLista(ArrayList<String> lista, String parametro, HttpServletRequest request) {
        for (String componentelista : request.getParameterValues(parametro)) {
            lista.add(componentelista);
        }
    }

    public void criarListaEndereco(ArrayList<Endereco> lista, HttpServletRequest request) {
        String[] ruas = request.getParameterValues("rua");
        String[] bairros = request.getParameterValues("bairro");
        String[] cidades = request.getParameterValues("cidade");
        String[] estados = request.getParameterValues("estado");
        String[] complementos = request.getParameterValues("complemento");
        String[] ceps = request.getParameterValues("cep");
        String[] pais = request.getParameterValues("pais");

        for (int i = 0; i < ruas.length; i++) {
            var endereco = new Endereco();

            endereco.setBairro(bairros[i]);
            endereco.setCEP(Integer.parseInt(ceps[i]));
            endereco.setCidade(cidades[i]);
            endereco.setComplemento(complementos[i]);
            endereco.setPais(pais[i]);
            endereco.setRua(ruas[i]);
            endereco.setEstado(estados[i]);

            lista.add(endereco);
        }
    }

}
