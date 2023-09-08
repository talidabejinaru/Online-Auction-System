package com.model;

/**
 * Clasa model pentru inscrieri
 */
public class InscriereLicitatie {

    private Client client;

    private int produsId;

    private double sumaMaximaOferita;

    public InscriereLicitatie(Client client, int produsId, double sumaMaximaOferita) {
        this.client = client;
        this.produsId = produsId;
        this.sumaMaximaOferita = sumaMaximaOferita;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getProdusId() {
        return produsId;
    }

    public void setProdusId(int produsId) {
        this.produsId = produsId;
    }

    public double getSumaMaximaOferita() {
        return sumaMaximaOferita;
    }

    public void setSumaMaximaOferita(double sumaMaximaOferita) {
        this.sumaMaximaOferita = sumaMaximaOferita;
    }
}
