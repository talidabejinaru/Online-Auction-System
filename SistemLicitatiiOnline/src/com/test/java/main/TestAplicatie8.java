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
public class TestAplicatie8 {

    /**
     * @throws IOException
     *
     * Produsele, Clientii si Licitatiile se citesc din fisierele Produse.txt,Clienti.txt,Licitatii.txt
     * Construim 2 brokeri
     * Construim o casa de licitatii
     * Scenariul este urmatorul:
     *      -30 de produse
     *      -30 licitatii posibile
     *      -3 din licitatii vor porni, pentru restul nu vor fi inscrieri
     *      -11 clienti vor fi inscrisi in total
     *      -licitatiile care se vor tine se vor finaliza cu succes astfel :
     *      1027(3p) ->va fi castigata de 52.AlexPopa cu suma de 30792.5
     *      1030(3p) ->va fi castigata de 50.MihaiBadea cu suma de 13627.2
     *      1015(5p) ->va fi castigata de 59.RaresStatn cu suma de 1000
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
        //inscriere licitatia 1030 pt produsul 130
        listaInscrieri.add(listaClienti.get(0).inscriereLicitatieClient(130, 21000));
        listaInscrieri.add(listaClienti.get(4).inscriereLicitatieClient(130, 6000));
        listaInscrieri.add(listaClienti.get(7).inscriereLicitatieClient(130, 8000));
        listaInscrieri.add(listaClienti.get(1).inscriereLicitatieClient(130, 8000));

        //inscriere licitie 1027 pt prod 127
        listaInscrieri.add(listaClienti.get(2).inscriereLicitatieClient(127, 80000));
        listaInscrieri.add(listaClienti.get(5).inscriereLicitatieClient(127, 66666));
        listaInscrieri.add(listaClienti.get(12).inscriereLicitatieClient(127, 1000));

        //inscriere licitatia 1015 pt produsul 115
        listaInscrieri.add(listaClienti.get(2).inscriereLicitatieClient(115, 1000));
        listaInscrieri.add(listaClienti.get(11).inscriereLicitatieClient(115, 1000));
        listaInscrieri.add(listaClienti.get(8).inscriereLicitatieClient(115, 1000));
        listaInscrieri.add(listaClienti.get(13).inscriereLicitatieClient(115, 1000));
        listaInscrieri.add(listaClienti.get(9).inscriereLicitatieClient(115, 1000));


        Main.main(casaLicitatii, administrator, listaClienti, Arrays.asList(broker1, broker2),
                produsService.initializareListaDeProduseDinFisier(), licitatieService.initializareListaDeLicitatiiDinFisier(),
                listaInscrieri);

    }
}
