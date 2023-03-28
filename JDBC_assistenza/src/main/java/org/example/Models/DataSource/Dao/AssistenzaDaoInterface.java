//Interfaccia che ora ha solo una implementazione MysqL. #to do implementazione sqlite

package org.example.Models.DataSource.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface AssistenzaDaoInterface {
    ResultSet getCliente(String cc, Connection connection) throws SQLException;
    ResultSet getListaErroriCliente(String cc, Connection connection) throws SQLException;
    ResultSet getErroreCliente(String ce, Connection connection) throws SQLException;
    ResultSet getClienteSenzaErrore(String ce, Connection connection) throws SQLException;
    ResultSet getCittaSenzaErrore(String ce, Connection connection) throws SQLException;
    ResultSet getInterventiPerErrore(Connection connection) throws SQLException;
    ResultSet getCostiPerCitta(Connection connection) throws SQLException;
    ResultSet getWorstCliente(Connection connection) throws SQLException;
}
