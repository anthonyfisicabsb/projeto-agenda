package controller.banco;

import model.Contato;
import model.Endereco;

import javax.servlet.ServletException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {
    public void excluirContato(int id) throws ServletException {

        var removerNome = "DELETE FROM Nome WHERE id = ?;";
        var removerContato = "DELETE FROM Contato WHERE id = ?;";
        var removerEmail = "DELETE FROM Email WHERE id = ?;";
        var removerUrl = "DELETE FROM Url WHERE id = ?;";
        var removerRedeSocial = "DELETE FROM `Rede Social` WHERE id = ?;";
        var removerEndereco = "DELETE FROM `Endereco` WHERE id = ?;";

        try (var con = new ConnectionFactory().getConnection(); var removeNome = con.prepareStatement(removerNome);
             var removeContato = con.prepareStatement(removerContato); var removeEndereco = con.prepareStatement(removerEndereco);
             var removeEmail = con.prepareStatement(removerEmail); var removeUrl = con.prepareStatement(removerUrl);
             var removeRedeSocial = con.prepareStatement(removerRedeSocial)) {

            removeNome.setInt(1, id);
            removeEndereco.setInt(1, id);
            removeContato.setInt(1, id);
            removeEmail.setInt(1, id);
            removeRedeSocial.setInt(1, id);
            removeUrl.setInt(1, id);
            removeEndereco.executeUpdate();
            removeContato.executeUpdate();
            removeEmail.executeUpdate();
            removeRedeSocial.executeUpdate();
            removeUrl.executeUpdate();
            removeNome.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }


    public void alterarContato(Contato contato) throws ServletException, SQLException {
        PreparedStatement st = null;
        var idContato = contato.getIdBanco();

        try (var con = new ConnectionFactory().getConnection();) {
            var sql = String.format("UPDATE TABLE Nome SET Nome=?, `Nome Fonético`=?, Sobrenome=?, `Sobrenome Fonético`=?," +
                    "`Data Nascimento`=?, Empresa=?, `Empresa Fonético`=?WHERE id=?");

            var sqlContatoDelete = "DELETE FROM Contato WHERE id=?";
            var sqlContatoCreate = "INSERT INTO Contato (Telefone, id)" +
                    "VALUES (?,?)";

            var sqlEmailCreate = "INSERT INTO Email (`E-mail`,id) VALUES (?, ?)";
            var sqlEmailDelete = "DELETE FROM Email WHERE id=?";

            var sqlRedeSocialDelete = "DELETE FROM `Rede Social` WHERE id=?";

            var sqlRedeSocialCreate = "INSERT INTO `Rede Social` (`Rede Social`, id) VALUES (?,?)";

            var sqlUrlDelete = "DELETE FROM Url WHERE id=?";

            var sqlUrlCreate = "INSERT INTO Url (Url, id) VALUES (?,?)";

            var sqlEnderecoCreate = String.format("INSERT INTO Endereco(Rua, Bairro, Estado, Cidade, CEP, Pais, Complemento," +
                    " id) VALUES (?,?,?,?,?,?,?,?)");

            var sqlEnderecoDelete = "DELETE FROM Endereco where id=?";
            st = con.prepareStatement(sql);

            st.setString(1, contato.getNome());
            st.setString(2, contato.getNomeFonetico());
            st.setString(3, contato.getSobrenome());
            st.setString(4, contato.getSobrenomeFonetico());
            st.setDate(5, new java.sql.Date(contato.getDataAniversario().getTime()));
            st.setString(6, contato.getEmpresa());
            st.setString(7, contato.getEmpresaFonetico());
            st.setInt(8, idContato);

            st.execute();

            st = con.prepareStatement(sqlContatoDelete);
            st.setInt(1, idContato);

            st.execute();

            st = con.prepareStatement(sqlContatoCreate);
            inserirListas(contato.getListaTelefone(), st, idContato);

            st = con.prepareStatement(sqlEmailDelete);
            st.setInt(1, idContato);

            st.execute();

            st = con.prepareStatement(sqlEmailCreate);
            inserirListas(contato.getListaEmail(), st, idContato);

            st = con.prepareStatement(sqlRedeSocialDelete);
            st.setInt(1, idContato);

            st.execute();

            st = con.prepareStatement(sqlRedeSocialCreate);
            inserirListas(contato.getListaRedeSocial(), st, idContato);

            st = con.prepareStatement(sqlUrlDelete);
            st.setInt(1, idContato);

            st.execute();

            st = con.prepareStatement(sqlUrlCreate);
            inserirListas(contato.getListaURL(), st, idContato);

            st = con.prepareStatement(sqlEnderecoDelete);
            st.setInt(1, idContato);

            st.execute();

            st = con.prepareStatement(sqlEnderecoCreate);

            var listaEndereco = contato.getListaEndereco();
            for (int i = 0, listaEnderecoSize = listaEndereco.size(); i < listaEnderecoSize; i++) {
                Endereco endereco = listaEndereco.get(i);
                st.setString(1, endereco.getRua());
                st.setString(2, endereco.getBairro());
                st.setString(3, endereco.getEstado());
                st.setString(4, endereco.getCidade());
                st.setInt(5, endereco.getCEP());
                st.setString(6, endereco.getPais());
                st.setString(7, endereco.getComplemento());
                st.setInt(8, idContato);
                st.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("erro ao alterar", e);
        } finally {
            if (st != null)
                st.close();
        }
    }

    public void inserirContato(Contato contato) throws ServletException {

        int id = 0;

        var inserirNome = "INSERT INTO Nome (Nome, `Nome Fonético`, Sobrenome, `Sobrenome Fonético`, `Data Nascimento`,"
                + "Empresa, `Empresa Fonético`) VALUES (?,?,?,?,?,?,?);";

        var inserirContato = "INSERT INTO Contato (Telefone, id) VALUES (?,?);";

        var inserirEmail = "INSERT INTO Email (`E-mail`, id) VALUES (?,?);";

        var inserirUrl = "INSERT INTO Url (Url, id) VALUES (?,?);";

        var inserirRedeSocial = "INSERT INTO `Rede Social` (`Rede Social`, id) VALUES (?,?)";

        var inserirEndereco = "INSERT INTO Endereco(Rua, Bairro, Estado, Cidade, CEP, Pais, Complemento, id) " +
                "VALUES (?,?,?,?,?,?,?,?);";

        try (var con = new ConnectionFactory().getConnection(); var insertNome = con.prepareStatement(inserirNome);
             var insertContato = con.prepareStatement(inserirContato); var insertEndereco = con.prepareStatement(inserirEndereco);
             var insertEmail = con.prepareStatement(inserirEmail); var insertUrl = con.prepareStatement(inserirUrl);
             var insertRedeSocial = con.prepareStatement(inserirRedeSocial)) {

            insertNome.setString(1, contato.getNome());
            insertNome.setString(2, contato.getNomeFonetico());
            insertNome.setString(3, contato.getSobrenome());
            insertNome.setString(4, contato.getSobrenomeFonetico());
            insertNome.setDate(5, new Date(contato.getDataAniversario().getTime()));
            insertNome.setString(6, contato.getEmpresa());
            insertNome.setString(7, contato.getEmpresaFonetico());
            insertNome.executeUpdate();

            var stm = con.createStatement();
            var rs = stm.executeQuery("SELECT LAST_INSERT_ID();");

            while (rs.next()) {
                id = rs.getInt("LAST_INSERT_ID()");
            }

            inserirListas(contato.getListaTelefone(), insertContato, id);

            inserirListas(contato.getListaEmail(), insertEmail, id);

            inserirListas(contato.getListaURL(), insertUrl, id);

            inserirListas(contato.getListaRedeSocial(), insertRedeSocial, id);

            var listaEndereco = contato.getListaEndereco();
            for (int i = 0, listaEnderecoSize = listaEndereco.size(); i < listaEnderecoSize; i++) {
                Endereco endereco = listaEndereco.get(i);
                insertEndereco.setString(1, endereco.getRua());
                insertEndereco.setString(2, endereco.getBairro());
                insertEndereco.setString(3, endereco.getEstado());
                insertEndereco.setString(4, endereco.getCidade());
                insertEndereco.setInt(5, endereco.getCEP());
                insertEndereco.setString(6, endereco.getPais());
                insertEndereco.setString(7, endereco.getComplemento());
                insertEndereco.setInt(8, id);
                insertEndereco.executeUpdate();

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("erro inserir contato", e);
        }

    }

    public List<Contato> carregarContatos() throws ServletException {
        var lista = new ArrayList<Contato>();

        Contato contat;

        var nome = "SELECT * FROM Nome ORDER BY Nome";
        var contato = "SELECT * FROM Contato WHERE id=?";
        var email = "SELECT * FROM Email WHERE id=?";
        var url = "SELECT * FROM Url WHERE id=?";
        var redeSocial = "SELECT * FROM `Rede Social` WHERE id=?";
        var endereco = "SELECT * FROM Endereco WHERE id=?";

        try (var con = new ConnectionFactory().getConnection(); var pegarNome = con.prepareStatement(nome);
             var pegarContato = con.prepareStatement(contato); var pegarEmail = con.prepareStatement(email);
             var pegarUrl = con.prepareStatement(url); var pegarRedeSocial = con.prepareStatement(redeSocial);
             var pegarEndereco = con.prepareStatement(endereco); var rs = pegarNome.executeQuery()) {

            while (rs.next()) {
                contat = new Contato();

                int id = rs.getInt("id");

                contat.setNome(rs.getString("Nome"));
                contat.setIdBanco(id);
                contat.setNomeFonetico(rs.getString("Nome Fonético"));
                contat.setSobrenome(rs.getString("Sobrenome"));
                contat.setSobrenomeFonetico(rs.getString("Sobrenome Fonético"));
                contat.setEmpresa(rs.getString("Empresa"));
                contat.setEmpresaFonetico(rs.getString("Empresa Fonético"));
                contat.setDataAniversario(rs.getDate("Data Nascimento"));

                carregarListas(pegarContato, contat.getListaTelefone(), id, "Telefone");
                carregarListas(pegarEmail, contat.getListaEmail(), id, "E-mail");
                carregarListas(pegarUrl, contat.getListaURL(), id, "Url");
                carregarListas(pegarRedeSocial, contat.getListaRedeSocial(), id, "Rede Social");
                carregarListaEndereco(pegarEndereco, contat.getListaEndereco(), id);

                lista.add(contat);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("erro ao carregar a lista de contatos", e);
        }


        return lista;
    }

    private void inserirListas(ArrayList<String> lista, PreparedStatement stm, int id) throws SQLException {
        for (String componenteLista : lista) {
            stm.setString(1, componenteLista);
            stm.setInt(2, id);
            stm.executeUpdate();
        }
    }

    private void carregarListas(PreparedStatement stm, ArrayList<String> lista, int id, String label) throws SQLException {
        stm.setInt(1, id);
        var rs = stm.executeQuery();

        while (rs.next()) {
            lista.add(rs.getString(label));
        }
    }

    private void carregarListaEndereco(PreparedStatement stm, ArrayList<Endereco> lista, int id) throws SQLException {
        stm.setInt(1, id);
        var rs = stm.executeQuery();

        while (rs.next()) {
            var endereco = new Endereco();

            endereco.setBairro(rs.getString("Bairro"));
            endereco.setCEP(rs.getInt("CEP"));
            endereco.setCidade(rs.getString("Cidade"));
            endereco.setPais(rs.getString("Pais"));
            endereco.setEstado(rs.getString("Estado"));
            endereco.setComplemento(rs.getString("Complemento"));
            endereco.setRua(rs.getString("Rua"));

            lista.add(endereco);
        }
    }
}