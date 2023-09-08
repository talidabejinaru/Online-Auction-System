package com.service;

import com.model.*;

import java.util.stream.Stream;

/**
 * Clasa de factory pentru crearea de obiecte de tip Produs
 */
public class ProdusFactory {

    /**
     * @param linieFisier - linie din fisier din care formam un vector
     * @return obiecte de tip Produs(mobila,tablou,bijuterie) pe baza primului element din vector
     */
    public Produs returneazaProdus(String linieFisier) {

        String[] detaliiProdus = linieFisier.split(";");
        if ("mobila".equalsIgnoreCase(detaliiProdus[0])) {
            return construiesteProdusMobila(detaliiProdus);
        } else if ("tablou".equalsIgnoreCase(detaliiProdus[0])) {
            return construiesteProdusTablou(detaliiProdus);
        } else if ("bijuterie".equalsIgnoreCase(detaliiProdus[0])) {
            return construiesteProdusBijuterie(detaliiProdus);
        } else {
            return construiesteProdus(detaliiProdus);
        }

    }

    /**
     * @param detaliiProdus - vector cu valori
     * @return - un obiect mobila
     */
    private Mobila construiesteProdusMobila(String[] detaliiProdus) {
        return Mobila.builder().withId(Integer.parseInt(detaliiProdus[1]))
                .withNume(detaliiProdus[2])
                .withPretVanzare(Double.parseDouble(detaliiProdus[3]))
                .withPretMinim(Double.parseDouble(detaliiProdus[4]))
                .withAn(Integer.parseInt(detaliiProdus[5]))
                .withTip(detaliiProdus[6])
                .withMaterial(detaliiProdus[7])
                .build();

    }

    /**
     * @param detaliiProdus - vector cu valori
     * @return - un obiect tablou
     */
    private Tablou construiesteProdusTablou(String[] detaliiProdus) {
        return Tablou.builder().withId(Integer.parseInt(detaliiProdus[1]))
                .withNume(detaliiProdus[2])
                .withPretVanzare(Double.parseDouble(detaliiProdus[3]))
                .withPretMinim(Double.parseDouble(detaliiProdus[4]))
                .withAn(Integer.parseInt(detaliiProdus[5]))
                .withNumePictor(detaliiProdus[6])
                .withCulori(Culori.valueOf(detaliiProdus[7].toUpperCase()))
                .build();
    }

    /**
     * @param detaliiProdus - vector cu valori
     * @return - un obiect bijuteri
     */
    private Bijuterie construiesteProdusBijuterie(String[] detaliiProdus) {
        return Bijuterie.builder().withId(Integer.parseInt(detaliiProdus[1]))
                .withNume(detaliiProdus[2])
                .withPretVanzare(Double.parseDouble(detaliiProdus[3]))
                .withPretMinim(Double.parseDouble(detaliiProdus[4]))
                .withAn(Integer.parseInt(detaliiProdus[5]))
                .withMaterial(detaliiProdus[6])
                .withPiatraPretioasa(convertToBoolean(detaliiProdus[7]))
                .build();
    }

    /**
     * @param detaliiProdus - vector cu valori
     * @return - un obiect produs
     */
    private Produs construiesteProdus(String[] detaliiProdus) {
        return Produs.builder().withId(Integer.parseInt(detaliiProdus[1]))
                .withNume(detaliiProdus[2])
                .withPretVanzare(Double.parseDouble(detaliiProdus[3]))
                .withPretMinim(Double.parseDouble(detaliiProdus[4]))
                .withAn(Integer.parseInt(detaliiProdus[5]))
                .build();
    }

    private boolean convertToBoolean(String value) {
        if ("Da".equalsIgnoreCase(value)) {
            return true;
        } else if ("Nu".equalsIgnoreCase(value)) {
            return false;
        }
        return false;
    }
}
