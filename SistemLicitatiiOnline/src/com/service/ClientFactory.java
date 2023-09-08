package com.service;

import com.model.*;

/**
 * Clasa de factory pentru creare de obiecte de tip client. Se foloseste design pattern-ul factory
 */
public class ClientFactory {

    /**
     * @param linieFisier - linia din fisier din care construim un obiect Client
     * @return - un client
     * Din linia din fisier se formeaza un vector. In functie de valoarea primului element(pf sau pj) determinam
     * daca clientul este de tip persoana fizica sau juridica
     */
    public Client returneazaClient(String linieFisier) {

        String[] detaliiClient = linieFisier.split(";");
        if ("pf".equalsIgnoreCase(detaliiClient[0])) {
            return construiestePersoanaFizica(detaliiClient);
        } else if ("pj".equalsIgnoreCase(detaliiClient[0])) {
            return construiestePersoanaJuridica(detaliiClient);
        } else {
            return construiesteClient(detaliiClient);
        }

    }


    /**
     * @param detaliiClient - linia din fisier
     * @return - persoana fizica
     */
    private PersoanaFizica construiestePersoanaFizica(String[] detaliiClient) {
        return PersoanaFizica.builder().withId(Integer.parseInt(detaliiClient[1]))
                .withNume(detaliiClient[2])
                .withAdresa(detaliiClient[3])
                .withNrParticipari(Integer.parseInt(detaliiClient[4]))
                .withNrLicitatiiCastigate(Integer.parseInt(detaliiClient[5]))
                .withDataNastere(detaliiClient[6])
                .build();
    }

    /**
     * @param detaliiClient - linia din fisier
     * @return - persoana juridica
     */
    private PersoanaJuridica construiestePersoanaJuridica(String[] detaliiClient) {
        return PersoanaJuridica.builder().withId(Integer.parseInt(detaliiClient[1]))
                .withNume(detaliiClient[2])
                .withAdresa(detaliiClient[3])
                .withNrParticipari(Integer.parseInt(detaliiClient[4]))
                .withNrLicitatiiCastigate(Integer.parseInt(detaliiClient[5]))
                .withCompanie(Companie.valueOf(detaliiClient[6].toUpperCase()))
                .withCapitalSocial(Double.parseDouble(detaliiClient[7]))
                .build();
    }

    /**
     * @param detaliiClient - linia din fisier
     * @return - client
     */
    private Client construiesteClient(String[] detaliiClient) {
        return Client.builder().withId(Integer.parseInt(detaliiClient[1]))
                .withNume(detaliiClient[2])
                .withAdresa(detaliiClient[3])
                .withNrParticipari(Integer.parseInt(detaliiClient[4]))
                .withNrLicitatiiCastigate(Integer.parseInt(detaliiClient[5]))
                .build();
    }
}
