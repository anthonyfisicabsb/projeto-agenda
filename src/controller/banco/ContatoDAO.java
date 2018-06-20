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

    /**
     * @param id
     * @throws ServletException
     */
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


    /**
     * @param contato
     * @throws ServletException
     * @throws SQLException
     */
    public void alterarContato(Contato contato) throws ServletException, SQLException {
        PreparedStatement st = null;
        var idContato = contato.getIdBanco();

        try (var con = new ConnectionFactory().getConnection();) {
            var sql = "UPDATE Nome SET Nome=?, `Nome Fonético`=?, Sobrenome=?, `Sobrenome Fonético`=?," +
                    "`Data Nascimento`=?, Empresa=?, `Empresa Fonético`=? WHERE id=?";

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

    /**
     * @param contato
     * @throws ServletException
     */
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

    /**
     * @return
     * @throws ServletException
     */
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

    /**
     * @param pesquisa
     * @return
     */
    public List<String> pesquisarContatos(String pesquisa) throws ServletException {
        var lista = new ArrayList<String>();

        var sql1 = "SELECT Nome as nome, Sobrenome as sobrenome, `Nome Fonético` as nomefonetico, `Sobrenome Fonético` as sobrenomefonetico," +
                "`Data Nascimento` as datanascimento FROM Nome WHERE Nome LIKE '%" + pesquisa + "%' OR Sobrenome LIKE '%" + pesquisa + "%'" +
                "OR `Nome Fonético` LIKE '%" + pesquisa + "%' OR `Sobrenome Fonético` LIKE '%" + pesquisa + "%'" +
                "ORDER BY Nome ASC";

        var sql2 = "SELECT Empresa as empresa, `Empresa Fonético` as empresafonetico FROM Nome WHERE " +
                "Empresa LIKE '%" + pesquisa + "%' OR `Empresa Fonético` LIKE '%" + pesquisa + "%'" +
                "ORDER BY Empresa ASC";

        var sql3 = "SELECT E.`E-mail` as email, N.Nome as nome FROM Email E INNER JOIN Nome N " +
                "ON E.id = N.id WHERE `E-mail` LIKE '%" + pesquisa + "%' ORDER BY `E-mail` ASC";

        var sql4 = "SELECT R.`Rede Social` as redesocial, N.Nome nome FROM `Rede Social` R INNER JOIN " +
                "Nome N ON R.id = N.id WHERE `Rede Social` LIKE '%" + pesquisa + "%' ORDER BY `Rede Social` ASC";

        var sql5 = "SELECT U.Url as url, N.Nome as nome FROM Url U INNER JOIN Nome N " +
                "ON N.id=U.id WHERE Url LIKE '%" + pesquisa + "%' ORDER BY Url ASC";

        var sql6 = "SELECT C.Telefone as telefone, N.Nome as nome FROM Contato C INNER JOIN Nome N " +
                "ON N.id=C.id WHERE Telefone LIKE '%" + pesquisa + "%' ORDER BY Telefone ASC";

        var sql7 = "SELECT * FROM Endereco WHERE Rua LIKE '%" + pesquisa + "%' OR Bairro LIKE '%" + pesquisa + "%' OR " +
                "Estado LIKE '%" + pesquisa + "%' OR Cidade LIKE '%" + pesquisa + "%' OR CEP LIKE '%" + pesquisa + "%' OR " +
                "Pais LIKE '%" + pesquisa + "%' OR '%" + pesquisa + "%' ORDER BY Rua ASC";

        try (var con = new ConnectionFactory().getConnection(); var st1 = con.prepareStatement(sql1);
             var st2 = con.prepareStatement(sql2); var st3 = con.prepareStatement(sql3); var st4 = con.prepareStatement(sql4);
             var st5 = con.prepareStatement(sql5); var st6 = con.prepareStatement(sql6); var st7 = con.prepareStatement(sql7);
             var rs1 = st1.executeQuery(); var rs2 = st2.executeQuery(); var rs3 = st3.executeQuery();
             var rs4 = st4.executeQuery(); var rs5 = st5.executeQuery(); var rs6 = st6.executeQuery();
             var rs7 = st7.executeQuery()) {


            while (rs1.next()) {
                var str = rs1.getString("nome") + " " + rs1.getString("sobrenome") + " (" +
                        rs1.getString("nomefonetico") + " " + rs1.getString("sobrenomefonetico") + ") - " +
                        rs1.getDate("datanascimento").toString();

                lista.add(str);
            }

            while (rs2.next()) {
                var str = rs2.getString("empresa") + " (" + rs2.getString("empresafonetico") + ")";

                lista.add(str);
            }

            while (rs3.next()) {
                var str = rs3.getString("email") + " (" + rs3.getString("nome") + ")";

                lista.add(str);
            }

            while (rs4.next()) {
                var str = rs4.getString("redesocial") + " (" + rs4.getString("nome") + ")";

                lista.add(str);
            }

            while (rs5.next()) {
                var str = rs5.getString("url") + " (" + rs5.getString("nome") + ")";

                lista.add(str);
            }

            while (rs6.next()) {
                var str = rs6.getString("telefone") + " (" + rs6.getString("nome") + ")";

                lista.add(str);
            }

            while (rs7.next()) {
                var str = rs7.getString("Complemento") + " " + rs7.getString("Estado") + "-" +
                        rs7.getString("CEP") + " " + rs7.getString("Pais");

                lista.add(str);
            }

        } catch (Exception e) {
            throw new ServletException("erro ao consultar contato", e);
        }

        return lista;
    }

    /**
     * @param lista
     * @param stm
     * @param id
     * @throws SQLException
     */
    private void inserirListas(ArrayList<String> lista, PreparedStatement stm, int id) throws SQLException {
        for (String componenteLista : lista) {
            stm.setString(1, componenteLista);
            stm.setInt(2, id);
            stm.executeUpdate();
        }
    }

    /**
     * @param stm
     * @param lista
     * @param id
     * @param label
     * @throws SQLException
     */
    private void carregarListas(PreparedStatement stm, ArrayList<String> lista, int id, String label) throws SQLException {
        stm.setInt(1, id);
        var rs = stm.executeQuery();

        while (rs.next()) {
            lista.add(rs.getString(label));
        }
    }

    /**
     * @param stm
     * @param lista
     * @param id
     * @throws SQLException
     */
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