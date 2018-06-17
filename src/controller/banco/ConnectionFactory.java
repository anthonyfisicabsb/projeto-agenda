package controller.banco;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.sql.DataSource;
import java.sql.Connection;

public class ConnectionFactory {

    public Connection getConnection() throws ServletException {
        try {

            Context initContext = new InitialContext();
            Context envContext = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("jdbc/AgendaDB");
            Connection conexao = ds.getConnection();

            return conexao;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("erro de conexao com o banco", e);
        }
    }
}