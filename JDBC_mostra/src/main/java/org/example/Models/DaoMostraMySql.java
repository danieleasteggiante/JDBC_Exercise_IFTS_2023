package org.example.Models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoMostraMySql implements DaoMostra{
    public DaoMostraMySql() {
    }
    @Override
    public ResultSet getMostra(String cm, Connection connection) throws SQLException {
        Statement stmt = null;
        String query = "SELECT * FROM mostra WHERE mostra.cm = " + cm + " ";
        stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        return rs;
    }

    @Override
    public ResultSet getQuadriMostra(String cm, Connection connection) throws SQLException {
        Statement statement = null;
        String query = "SELECT quadro.* FROM quadro, espone " +
                "WHERE espone.cm = " + cm + " " +
                "AND espone.cq = quadro.cq ";
        statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        return rs;
    }
}
