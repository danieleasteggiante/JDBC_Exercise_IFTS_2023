package org.example.Domain;

import java.sql.Connection;
import java.util.List;

public class Cliente {

    String codCliente;
    String nomeCliente;
    String cittaCliente;
    List<Errore> listaErrori;

    public Cliente(String codCliente, String nomeCliente, String cittaCliente) {
        this.codCliente = codCliente;
        this.nomeCliente = nomeCliente;
        this.cittaCliente = cittaCliente;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "codCliente='" + codCliente + '\'' +
                ", nomeCliente='" + nomeCliente + '\'' +
                ", cittaCliente='" + cittaCliente + '\'' +
                '}';
    }
}
