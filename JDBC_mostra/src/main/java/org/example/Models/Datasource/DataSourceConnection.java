package org.example.Models.Datasource;

import org.example.Models.DaoMostraMySql;

import java.sql.*;
import java.util.Properties;

public class DataSourceConnection {

    Connection conn = null;
    DataSourceConnection ds;
    public DataSourceConnection() throws ClassNotFoundException, SQLException {

        String URL = "jdbc:mysql://localhost/ifts_2023_museo_es3";
        Properties info = new Properties( );
        info.put( "user", "daniele" );
        info.put( "password", "qwerty" );
        info.put( "autoReconnect", "true" );
        info.put( "useSSL", "false" );
        info.put( "serverTimezone", "Europe/Amsterdam" );

        Class.forName("com.mysql.cj.jdbc.Driver");

        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(URL, info);
    }

    public Connection getConnection() {
        return conn;
    }

    public void closeConneection() throws SQLException {
        conn.close();
    }
}
