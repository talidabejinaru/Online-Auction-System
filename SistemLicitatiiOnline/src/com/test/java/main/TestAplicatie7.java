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
public class TestAplicatie7 {

    /**
     * @throws IOException
     *
     * Produsele, Clientii si Licitatiile se citesc din fisierele Produse.txt,Clienti.txt,Licitatii.txt
     * Construim 2 brokeri
     * Construim o casa de licitatii
     * Scenariul este urmatorul:
     *      -30 de produse
     *      -30 licitatii posibile
     *      -4 din licitatii vor porni, pentru restul nu vor fi inscrieri
     *      -9 clienti vor fi inscrisi in total
     *      -Clientul 57.GeorgePopescu se inscrie la fiecare licitati
     *      -licitatiile care se vor tine se vor finaliza cu succes astfel :
     *      1002(2p) ->va fi castigata de 57.GeorgePopescu cu suma de 709.27
     *      1003(3p) ->va fi castigata de 57.GeorgePopescu cu suma de 1328.59
     *      1012(2p) ->va fi castigata de 57.GeorgePopescu cu suma de 780.05
     *      1017(2p) ->va fi castigata de 57.GeorgePopescu cu suma de 1413.52
     *
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
        //inscriere licitatia 1002 pt produsul 102
        listaInscrieri.add(listaClienti.get(0).inscriereLicitatieClient(102, 200));
        listaInscrieri.add(listaClienti.get(6).inscriereLicitatieClient(102, 6000));

        //inscriere licitie 1003 pt prod 103
        listaInscrieri.add(listaClienti.get(2).inscriereLicitatieClient(103, 800));
        listaInscrieri.add(listaClienti.get(3).inscriereLicitatieClient(103, 600));
        listaInscrieri.add(listaClienti.get(6).inscriereLicitatieClient(103, 6000));

        //inscriere licitatia 1012 pt produsul 112
        listaInscrieri.add(listaClienti.get(5).inscriereLicitatieClient(112, 2000));
        listaInscrieri.add(listaClienti.get(6).inscriereLicitatieClient(112, 6000));

        //inscriere licitatia 1017 pt produsul 117
        listaInscrieri.add(listaClienti.get(7).inscriereLicitatieClient(117, 800));
        listaInscrieri.add(listaClienti.get(6).inscriereLicitatieClient(117, 6000));

        Main.main(casaLicitatii, administrator, listaClienti, Arrays.asList(broker1, broker2),
                produsService.initializareListaDeProduseDinFisier(), licitatieService.initializareListaDeLicitatiiDinFisier(),
                listaInscrieri);

    }
}
