package com.service;

import com.model.Licitatie;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Clasa de servicii pentru Licitatii
 */
public class LicitatieService {

    /**
     * @return o lista de licitatii citita din fisier
     * @throws IOException
     */
    public List<Licitatie> initializareListaDeLicitatiiDinFisier() throws IOException {

        String continutFisier = Util.readFromInputStream(new FileInputStream(
                new File("src/com/test/resources/Licitatii.txt")));
        return Stream.of(continutFisier.split("\n"))
                .map(this::construiesteLicitatie).
                        collect(Collectors.toList());
    }

    /**
     * @param linieFisier - o linie din fisier
     * @return un obiect de tip licitatie construit dintr-o linie din fisier.
     * Campurile din fisier se separa prin ";"
     */
    private Licitatie construiesteLicitatie(String linieFisier) {
        String[] continutFisier = linieFisier.split(";");
        Licitatie licitatie = new Licitatie();
        licitatie.setId(Integer.parseInt(continutFisier[0]));
        licitatie.setNrParticipanti(Integer.parseInt(continutFisier[1]));
        licitatie.setIdProdus(Integer.parseInt(continutFisier[2]));
        licitatie.setNrPasiMaxim(Integer.parseInt(continutFisier[3]));
        licitatie.setListaPerechiBrokerClient(new ArrayList<>());
        return licitatie;
    }
}
