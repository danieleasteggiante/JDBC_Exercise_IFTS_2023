package org.example.Domain;

import org.example.Models.DaoMostraMySql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Mostra {
    String cm;
    List<Quadro> quadri;
    String nome;
    String anno;
    String organizzazione;
    DaoMostraMySql daoMostra = new DaoMostraMySql();

    public Mostra(String cm, Connection connessione) throws SQLException {
        this.cm = cm;

        ResultSet rsMostra = daoMostra.getMostra(cm , connessione);

        if (rsMostra.next()){ //se uno solo altrimenti whilr
            this.nome = rsMostra.getString("nome");
            this.anno = rsMostra.getString("anno");
            this.organizzazione = rsMostra.getString("organizzazione");
        }

        ResultSet rsQuadri = daoMostra.getQuadriMostra(cm, connessione);

        this.quadri = new ArrayList<>();
        while (rsQuadri.next()){
            quadri.add(new Quadro(rsQuadri.getString("cq"),rsQuadri.getString("autore"),rsQuadri.getString("periodo")));
        }

    }


    @Override
    public String toString() {
        return "Mostra{" +
                "cm='" + cm + '\'' +
                ", quadri=" + quadri +
                ", nome='" + nome + '\'' +
                ", anno='" + anno + '\'' +
                ", organizzazione='" + organizzazione + '\'' +
                ", daoMostra=" + daoMostra +
                '}';
    }
}
