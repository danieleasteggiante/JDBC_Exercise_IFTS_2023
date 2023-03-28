package org.example;

import org.example.Domain.Mostra;
import org.example.Models.Datasource.DataSourceConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        DataSourceConnection ds = new DataSourceConnection();
        Connection connection = ds.getConnection();
        Mostra mostra = new Mostra("1", connection);
        System.out.println(mostra);

        ds.closeConneection();
    }
}