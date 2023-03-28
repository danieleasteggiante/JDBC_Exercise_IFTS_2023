package org.example;

import java.sql.Date;



public class Esame {

    public String  matr ;
    public String  CodiceCorso;
    public String  NomeCorso;
    public String dataEsame;
    public int voto;

    public String nomeDocente;

    public Esame() {
        matr = "";
        CodiceCorso = "";
        NomeCorso = "";
        dataEsame = null;
        voto = 0;
    }


    public Esame(String matricola, String cc, String dataEsame, int voto, String NomeCorso, String nomeDocente) throws Exception {
        this.matr  = matricola;
        this.CodiceCorso= cc;
        this.dataEsame = dataEsame;
        this.voto = voto;
        this.NomeCorso = NomeCorso;
        this.nomeDocente = nomeDocente;
    }


    @Override
    public String toString() {

        String out="";

        out = "Esame [NomeCorso=" + NomeCorso + ", dataEsame=" + dataEsame
                + ", voto=" + voto + ", docente: " + this.nomeDocente+"]";
        return out;
    }

}
