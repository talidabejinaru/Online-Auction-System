package com.service;

import com.model.Produs;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Clasa de servicii legate de produs. Foloseste patternul singleton pentru a crea o singura instanta
 */
public class ProdusService {

    private static ProdusService instance = new ProdusService();

    private ProdusService(){}

    public static ProdusService getInstance(){
        return instance;
    }

    /**
     * @return o lista de produse citite din fisier
     * @throws IOException
     */
    public List<Produs> initializareListaDeProduseDinFisier() throws IOException {

        String continutFisier = Util.readFromInputStream(new FileInputStream(
                new File("src/com/test/resources/Produse.txt")));
        ProdusFactory produsFactory = new ProdusFactory();
        return Stream.of(continutFisier.split("\n"))
                .map(produsFactory::returneazaProdus).
                        collect(Collectors.toList());
    }

    /**
     * @param listaProduse - lista de produse in care se cauta
     * @param produsId - produs id pentru produsul cautat
     * @return un produs
     */
    public Produs selecteazaProdusDupaId(List<Produs> listaProduse, int produsId) {
        return listaProduse.stream().filter(produs -> produs.getId() == produsId).findFirst().orElse(null);
    }


}
