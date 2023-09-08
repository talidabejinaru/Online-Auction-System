package com.test.java.main;

import com.main.Main;
import com.model.*;
import com.service.ClientService;
import com.service.LicitatieService;
import com.service.ProdusService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(JUnit4.class)
public class TestAplicatie4 {

    /**
     * @throws IOException
     *
     * Produsele, Clientii si Licitatiile se citesc din fisierele Produse.txt,Clienti.txt,Licitatii.txt
     * Construim 2 brokeri
     * Construim o casa de licitatii
     * Scenariul este urmatorul:
     *      -30 de produse
     *      -30 licitatii posibile
     *      -1 din licitatii vor porni, pentru restul nu vor fi inscrieri
     *      -10 clienti vor fi inscrisi in total la aceeasi licitatie
     *      -licitatiile care se vor tine se vor finaliza cu succes astfel :
     *      1019(10p) ->va fi castigata de 55.Adrian Alexa cu suma de 25202.15

     */
    @Test
    public void runTest() throws IOException {
        Administrator administrator = Administrator.builder().withId(1).withNume("Administrator").build();
        Broker broker1 = Broker.builder().withId(100).withNume("Broker1").withListaClienti(new ArrayList<>()).build();
        Broker broker2 = Broker.builder().withId(101).withNume("Broker2").withListaClienti(new ArrayList<>()).build();
        ProdusService produsService = ProdusService.getInstance();
        ClientService clientService = ClientService.getInstance();
        LicitatieService licitatieService = new LicitatieService();
        CasaLicitatii casaLicitatii = new CasaLicitatii();
        List<InscriereLicitatie> listaInscrieri = new ArrayList<>();
        List<Client> listaClienti = clientService.initializareListaDeClientiDinFisier();
        //inscriere licitatia 1010 pt produsul 110
        listaInscrieri.add(listaClienti.get(0).inscriereLicitatieClient(110, 20000));
        listaInscrieri.add(listaClienti.get(1).inscriereLicitatieClient(110, 4000));
        listaInscrieri.add(listaClienti.get(2).inscriereLicitatieClient(110, 8000));
        listaInscrieri.add(listaClienti.get(3).inscriereLicitatieClient(110, 6000));
        listaInscrieri.add(listaClienti.get(4).inscriereLicitatieClient(110, 70000));
        listaInscrieri.add(listaClienti.get(5).inscriereLicitatieClient(110, 20000));
        listaInscrieri.add(listaClienti.get(6).inscriereLicitatieClient(110, 60000));
        listaInscrieri.add(listaClienti.get(7).inscriereLicitatieClient(110, 8000));
        listaInscrieri.add(listaClienti.get(8).inscriereLicitatieClient(110, 12000));
        listaInscrieri.add(listaClienti.get(9).inscriereLicitatieClient(110, 10000));

        Main.main(casaLicitatii, administrator, listaClienti, Arrays.asList(broker1, broker2),
                produsService.initializareListaDeProduseDinFisier(), licitatieService.initializareListaDeLicitatiiDinFisier(),
                listaInscrieri);

    }
}
