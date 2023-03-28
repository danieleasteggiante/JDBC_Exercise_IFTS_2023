package org.example;

import org.example.Domain.Cliente;
import org.example.Models.DataSource.Dao.AssistenzaDaoMySql;
import org.example.Models.DataSource.DataSourceConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DataSourceConnection ds = new DataSourceConnection();
        Connection connection = ds.getConnection();

        //////////////////////////////////////////////////////////////////////////////////////

        AssistenzaDaoMySql assistenza = new AssistenzaDaoMySql();

        //Clienti con codice errore e1
        ResultSet rs = assistenza.getErroreCliente("e1", connection);
        System.out.println("\nClienti con errore e1");
        while(rs.next()){
            System.out.println(new Cliente(rs.getString("codc"), rs.getString("nomecliente"), rs.getString("citta")));
        }

        //Clienti senza codice errore e1 #strictmode like
        rs = assistenza.getClienteSenzaErrore("e1", connection);
        System.out.println("\nClienti senza errore e1");
        while(rs.next()){
            System.out.println(new Cliente(rs.getString("codc"), rs.getString("nomecliente"), rs.getString("citta")));
        }

        //Clienti con codice errore diverso da e1
        rs = assistenza.getClienteErroreDiversoDa("e1", connection);
        System.out.println("\nClienti con errore diverso da e1");
        while(rs.next()){
            System.out.println(new Cliente(rs.getString("codc"), rs.getString("nomecliente"), rs.getString("citta")));
        }

        //Citta senza errore e1
        rs = assistenza.getCittaSenzaErrore("e1", connection);
        System.out.println("\nCittà senza errore e1");
        while(rs.next()){
            System.out.println(rs.getString("citta"));
        }

        //Nr interventi per errore
        rs = assistenza.getInterventiPerErrore(connection);
        System.out.println("\nNumero di interventi per errore");
        while(rs.next()){
            System.out.println(rs.getString("code")+ " - Eseguito: " + rs.getString("count") + " volte");
        }

        //Costo riparazioni per città
        rs = assistenza.getCostiPerCitta(connection);
        System.out.println("\nCosto riparazioni per città");
        while(rs.next()){
            System.out.println(rs.getString("citta")+ " - Costo: " + rs.getString("costo"));
        }

        //Peggior cliente
        rs = assistenza.getWorstCliente(connection);
        System.out.println("\nPeggior cliente");
        while(rs.next()){
            System.out.println(new Cliente(rs.getString("codc"), rs.getString("nomecliente"),rs.getString("citta") ));
        }

        //Peggiori n clienti
        int n = 2;
        rs = assistenza.getnWorstsClienti(connection,2);
        System.out.println("\nPeggiori "+ n + " clienti");
        while(rs.next()){
            System.out.println(new Cliente(rs.getString("codc"), rs.getString("nomecliente"),rs.getString("citta") ));
        }

        //////////////////////////////////////////////////////////////////////////////////////

        ds.closeConneection();

    }
}