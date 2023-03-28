package org.example.Models.DataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataSourceConnection {
    Connection conn = null;
    DataSourceConnection ds;
    public DataSourceConnection() throws ClassNotFoundException, SQLException {

        String URL = "jdbc:mysql://localhost/ifts_2023_assistenza";
        Properties info = new Properties( );
        info.put( "user", "daniele" );
        info.put( "password", "dEminds17!");
        info.put( "autoReconnect", "true");
        info.put( "useSSL", "false");
        info.put( "serverTimezone", "Europe/Amsterdam");

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
