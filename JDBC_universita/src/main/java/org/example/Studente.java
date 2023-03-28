package org.example;


import org.example.Esame;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Studente {


    public String  matr ;
    public String  nome;
    public String citta;
    public int anno;
    public ArrayList<Esame> listaEsami;
    public boolean studenteEsiste;
    public float mediaEsami;

    public int nrEsami;

    public Studente() {

        matr = "";
        nome = "";
        citta = "";
        anno = 0;
        listaEsami = null;
        studenteEsiste = false;
        nrEsami = 0;
        mediaEsami = 0;
    }

    public Studente(Connection cn, String matricola) throws Exception {

        Statement stmt = null;

        stmt = cn.createStatement();

        String sql;
        sql = "SELECT s.MATR, " +
                " s.SNOME, " +
                " s.CITTA, " +
                " s.ACORSO " +
                " FROM s  " +
                " WHERE s.MATR = '" + matricola + "'";

        ResultSet rs = stmt.executeQuery(sql);

        if(rs.next()){
            //Retrieve by column name
            this.matr  = rs.getString("MATR");
            this.nome = rs.getString("SNOME");
            this.citta = rs.getString("CITTA");
            this.anno = rs.getInt("ACORSO");
            this.studenteEsiste = true;


            sql = "SELECT COUNT(*) AS nresami, AVG(e.VOTO) AS media" +
                    " FROM e  " +
                    " WHERE e.MATR = '" + matricola + "'";

            rs.close();
            rs = stmt.executeQuery(sql);
            if(rs.next()){
                this.mediaEsami = rs.getFloat("media");
                this.nrEsami = rs.getInt("nresami");

            }
            else
                this.mediaEsami = 0;

            sql = "SELECT e.MATR, " +
                    " e.CC, " +
                    " e.DATA, " +
                    " e.VOTO, " +
                    " c.CNOME, " +
                    " d.dnome "+
                    " FROM e, c, d  " +
                    " WHERE c.CC = e.CC AND c.cd = d.cd AND " +
                    " e.MATR = '" + matricola + "'";

            rs.close();
            rs = stmt.executeQuery(sql);

            this.listaEsami = new ArrayList<Esame>();
            while(rs.next()){

                Esame e = new Esame(rs.getString("MATR"),rs.getString("CC"), rs.getString("DATA"),rs.getInt("VOTO"),rs.getString("CNOME"), rs.getString("dnome") );
                this.listaEsami.add(e);
            }

            int somma=0;
            int cont=0;

            for(Esame e : this.listaEsami){
                somma += e.voto;
                cont++;
            }
            this.mediaEsami = somma/cont;


        }
        else
        {
            this.matr = "";
            this.nome = "";
            this.citta = "";
            this.anno = 0;
            this.listaEsami = null;
            this.studenteEsiste = false;
            mediaEsami = 0;
        }

        rs.close();

        stmt.close();

    }

    public void aggiornaStudente(Connection cn) throws Exception{

        Statement stmt = cn.createStatement();
        ResultSet rs;
        String query = "SELECT * FROM s ";

        if(this.matr.length()>0) {

            query = query + " WHERE MATR = '" + this.matr + "'";

            rs = stmt.executeQuery(query);

            if(rs.next()){
                // lo studente esiste
                query = "UPDATE s SET SNOME = '" + this.nome + "', CITTA = '" + this.citta + "', ACORSO = "
                        + this.anno + "  WHERE MATR = '" + this.matr + "';";
            }
            else
            {
                // lo studente non esiste
                query = "INSERT INTO s (MATR, SNOME, CITTA, ACORSO) VALUES " +
                        "('" + this.matr + "', '" + this.nome + "', '" + this.citta + "', " + this.anno + ");";
            }
            //System.out.println(query);
            stmt.executeUpdate(query);
        }
        else
            System.out.println("Matricola non valida per l'aggiornamento...");
    }

    public void eliminaStudente(Connection cn) throws Exception {

        Statement stmt = null;
        stmt = cn.createStatement();
        String sql;
        sql = "SELECT COUNT(*) AS NumEsami " +
                " FROM e  " +
                " WHERE e.MATR = '" + this.matr + "'";

        ResultSet rs = stmt.executeQuery(sql);

        if(rs.next()){

            Integer contaEsami = rs.getInt("NumEsami");

            if (contaEsami == 0) {
                sql = "DELETE FROM s  " +
                        " WHERE s.MATR = '" + this.matr + "'";

                stmt.executeUpdate(sql);
                System.out.println("Studente eliminato con successo");
            }
            else {
                System.out.println("Lo studente ha sostenuto " + contaEsami  + " esami, non lo posso eliminare");

            }
        }

        rs.close();
        stmt.close();
    }

    public static void inserisciStudente(String matr, String nome, String citta, int anno, ArrayList<Esame> listaEsami, Connection cn) throws Exception {

        Studente s =  new Studente(cn, matr);

        if (s.studenteEsiste){
            Statement stmt = null;
            stmt = cn.createStatement();
            String query;
            query =   "Delete * " +
                    " FROM e  " +
                    " WHERE e.MATR = '" + matr + "'";

            stmt.executeUpdate(query);
        }
            String query = "INSERT INTO s (MATR, SNOME, CITTA, ACORSO) VALUES " +
                    "('" + matr + "', '" + nome + "', '" + citta + "', " + anno + ");";
            cn.createStatement().executeUpdate(query);

            for (Esame e : listaEsami ) {
                query = "INSERT INTO e (MATR, cc, data, voto) VALUES " +
                        "('" + e.matr + "', '" + e.CodiceCorso + "', '" + e.dataEsame + "', " + e.voto + ");";
                cn.createStatement().executeUpdate(query);
            }
    }

    @Override
    public String toString() {

        String out="";

        out = "Studente{ " +
                "Matricola= " + matr +
                ", Nome= " + nome+ " " +
                ", Città= " + citta + " " +
                ", Anno di corso=" + anno +
                ", Media esami=" + mediaEsami +
                ", Nr Esami=" + nrEsami +
                " }\n";

        for(Esame e : this.listaEsami){
            out += e.toString() + "\n";
        }
        return out;
    }

}
