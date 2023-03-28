package org.example.Domain;

public class Quadro {
    String cq;
    String autore;
    String peiodo;

    public Quadro(String cq, String autore, String peiodo) {
        this.cq = cq;
        this.autore = autore;
        this.peiodo = peiodo;
    }

    public String getCq() {
        return cq;
    }

    public void setCq(String cq) {
        this.cq = cq;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getPeiodo() {
        return peiodo;
    }

    public void setPeiodo(String peiodo) {
        this.peiodo = peiodo;
    }

    @Override
    public String toString() {
        return "Quadro{" +
                "cq='" + cq + '\'' +
                ", autore='" + autore + '\'' +
                ", peiodo='" + peiodo + '\'' +
                '}';
    }
}
