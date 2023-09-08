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
public class TestAplicatie1 {

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
     *      -licitatiile care se vor tine se vor finaliza cu succes astfel :
     *      1002(2p) ->va fi castigata de 51.ScTiab cu suma de 205.44
     *      1003(3p) ->va fi castigata de 52.AlexPopa cu suma de 603.17
     *      1012(2p) ->va fi castigata de 57.GeorgePopescu cu suma de 780.05
     *      1017(2p) ->va fi castigata de 59.RaresStan cu suma de 646.57
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
        listaInscrieri.add(listaClienti.get(1).inscriereLicitatieClient(102, 400));

        //inscriere licitie 1003 pt prod 103
        listaInscrieri.add(listaClienti.get(2).inscriereLicitatieClient(103, 800));
        listaInscrieri.add(listaClienti.get(3).inscriereLicitatieClient(103, 600));
        listaInscrieri.add(listaClienti.get(4).inscriereLicitatieClient(103, 700));

        //inscriere licitatia 1012 pt produsul 112
        listaInscrieri.add(listaClienti.get(5).inscriereLicitatieClient(112, 2000));
        listaInscrieri.add(listaClienti.get(6).inscriereLicitatieClient(112, 6000));

        //inscriere licitatia 1017 pt produsul 117
        listaInscrieri.add(listaClienti.get(7).inscriereLicitatieClient(117, 800));
        listaInscrieri.add(listaClienti.get(8).inscriereLicitatieClient(117, 1200));

        Main.main(casaLicitatii, administrator, listaClienti, Arrays.asList(broker1, broker2),
                produsService.initializareListaDeProduseDinFisier(), licitatieService.initializareListaDeLicitatiiDinFisier(),
                listaInscrieri);

    }
}
