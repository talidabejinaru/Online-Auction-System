package com.model;

/**
 * Clasa model pentru Rezultat Licitatie
 */
public class RezultatLicitatie {

    private Client client;

    private double pretAchizitie;

    private Produs produs;

    private Status status;

    public RezultatLicitatie(Client client, double pretAchizitie, Produs produs, Status status) {
        this.client = client;
        this.pretAchizitie = pretAchizitie;
        this.produs = produs;
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public double getPretAchizitie() {
        return pretAchizitie;
    }

    public void setPretAchizitie(double pretAchizitie) {
        this.pretAchizitie = pretAchizitie;
    }

    public Produs getProdus() {
        return produs;
    }

    public void setProdus(Produs produs) {
        this.produs = produs;
    }

    @Override
    public String toString() {
        return "RezultatLicitatie: " +
                "\n Clientul care a castigat licitatia = " + client +
                "\n Pretul de vanzare al produsului = " + pretAchizitie +
                "\n Produsul vandut = " + produs +
                "\n Statusul produsului =" + status +
                "\n --------------------------------------------------------------------";
    }

    public Status getStatus() {
        return status;
    }
}
