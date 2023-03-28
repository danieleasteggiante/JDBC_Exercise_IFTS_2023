package org.example.Models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DaoMostra {
    ResultSet getMostra(String cm, Connection connection) throws SQLException;
    ResultSet getQuadriMostra(String cm, Connection connection) throws SQLException;

}
