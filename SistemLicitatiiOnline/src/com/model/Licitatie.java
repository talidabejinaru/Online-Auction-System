package com.model;


import java.util.AbstractMap;
import java.util.List;

/**
 * Clasa model penru licitatii
 */
public class Licitatie {

    private int id;

    private int nrParticipanti;

    private int idProdus;

    private int nrPasiMaxim;

    //o lista de perechi (Broker,Client) care iau parte la licitatie
    private List<AbstractMap.SimpleEntry<Client , Broker>> listaPerecheBrokerClient;

    public Licitatie() {
    }

    public Licitatie(int id, int nrParticipanti, int idProdus, int nrPasiMaxim) {
        this.id = id;
        this.nrParticipanti = nrParticipanti;
        this.idProdus = idProdus;
        this.nrPasiMaxim = nrPasiMaxim;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNrParticipanti() {
        return nrParticipanti;
    }

    public void setNrParticipanti(int nrParticipanti) {
        this.nrParticipanti = nrParticipanti;
    }

    public int getIdProdus() {
        return idProdus;
    }

    public void setIdProdus(int idProdus) {
        this.idProdus = idProdus;
    }

    public int getNrPasiMaxim() {
        return nrPasiMaxim;
    }

    public void setNrPasiMaxim(int nrPasiMaxim) {
        this.nrPasiMaxim = nrPasiMaxim;
    }

    @Override
    public String toString() {
        return "Licitatie{" +
                "id=" + id +
                ", nrParticipanti=" + nrParticipanti +
                ", idProdus=" + idProdus +
                ", nrPasiMaxim=" + nrPasiMaxim +
                '}';
    }

    public List<AbstractMap.SimpleEntry<Client , Broker>> getListPerecheBrokerClient() {
        return listaPerecheBrokerClient;
    }

    public void setListaPerechiBrokerClient(List<AbstractMap.SimpleEntry<Client , Broker>> listaPerecheBrokerClient) {
        this.listaPerecheBrokerClient = listaPerecheBrokerClient;
    }
}
