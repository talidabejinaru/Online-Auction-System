package com.service;

import com.model.CasaLicitatii;
import com.model.Client;
import com.model.Produs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Clasa de servicii pentru client. Foloseste singleton pattern
 */
public class ClientService {

    private static ClientService instance = new ClientService();

    private ClientService(){}

    public static ClientService getInstance(){
        return instance;
    }


    /**
     * @return lista de clienti construita din fisier
     * @throws IOException
     */
    public List<Client> initializareListaDeClientiDinFisier() throws IOException {

        String continutFisier = Util.readFromInputStream(new FileInputStream(
                new File("src/com/test/resources/Clienti.txt")));
        ClientFactory clientFactory = new ClientFactory();
        return Stream.of(continutFisier.split("\n"))
                .map(clientFactory::returneazaClient).
                        collect(Collectors.toList());
    }

}
