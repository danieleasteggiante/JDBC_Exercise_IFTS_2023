package org.example.Models.DataSource.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AssistenzaDaoMySql implements AssistenzaDaoInterface {

    public AssistenzaDaoMySql() {
    }
    @Override
    public ResultSet getCliente(String cc, Connection connection) throws SQLException {
        Statement stmt = null;
        String query = "SELECT * FROM cliente WHERE cliente.codc = " + cc + " ";
        stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        return rs;
    }

    @Override
    public ResultSet getListaErroriCliente(String cc, Connection connection) throws SQLException {
        Statement statement = null;
        String query = "SELECT errore.* FROM cliente, errore " +
                "WHERE cliente.codc = " + cc + " " +
                "AND errore.codc = cliente.codc ";
        statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        return rs;
    }

    @Override
    public ResultSet getErroreCliente(String ce, Connection connection) throws SQLException {
        Statement statement = null;
        String query = "SELECT cliente.* FROM cliente, errore " +
                "WHERE errore.code = '" + ce + "' " +
                "AND errore.codc = cliente.codc ";
        statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        return rs;
    }

    public ResultSet getClienteErroreDiversoDa(String ce, Connection connection) throws SQLException {
        Statement statement = null;
        String query = "select distinct cliente.*" +
                " from cliente, errore" +
                " where cliente.codc = errore.codc" +
                " and errore.codc != '" + ce + "' ";
        statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        return rs;
    }

    @Override
    public ResultSet getClienteSenzaErrore(String ce, Connection connection) throws SQLException {
        Statement statement = null;
        String query = "SELECT cliente.* " +
                "FROM cliente " +
                "where cliente.codc not in " +
                                    "(select errore.codc " +
                                    "from errore " +
                                    "where errore.code = '"+ce+"')";
        statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        return rs;
    }

    @Override
    public ResultSet getCittaSenzaErrore(String ce, Connection connection) throws SQLException {
        Statement statement = null;
        String query = "SELECT cliente.citta " +
                "FROM cliente " +
                "where cliente.citta not in " +
                "(select cliente.citta from errore, cliente " +
                "where errore.codc = cliente.codc and errore.code = '"+ ce +"')";
        statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        return rs;
    }

    @Override
    public ResultSet getInterventiPerErrore(Connection connection) throws SQLException {
        Statement statement = null;
        String query = "SELECT errore.code, count(*) as count" +
                "    FROM errore" +
                "    group by errore.code";
        statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        return rs;
    }

    @Override
    public ResultSet getCostiPerCitta(Connection connection) throws SQLException {
        Statement statement = null;
        String query = "SELECT cliente.citta, sum(errore.costo) as costo" +
                "    FROM cliente, errore" +
                "    where cliente.codc = errore.codc" +
                "    group by cliente.citta";
        statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        return rs;
    }

      @Override
    public ResultSet getWorstCliente(Connection connection) throws SQLException {
        Statement statement = null;
        String query = "SELECT cliente.*, sum(errore.costo)" +
                "    FROM cliente, errore" +
                "    where cliente.codc = errore.codc" +
                "    group by cliente.codc" +
                "    having sum(errore.costo) >= all (SELECT sum(errore.costo)" +
                                            "    FROM errore errore" +
                                            "    group by errore.codc)";
        statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        return rs;
    }

    public ResultSet getnWorstsClienti(Connection connection, int nr) throws SQLException {
        Statement statement = null;
        String query = "select cliente.*, sum(errore.costo) as s" +
                "    from cliente, errore" +
                "    where cliente.codc = errore.codc" +
                "    group by cliente.codc" +
                "    order by s desc" +
                "    LIMIT " + nr + ";";
        //esiste un modo piu bello??
        statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        return rs;
    }
}
