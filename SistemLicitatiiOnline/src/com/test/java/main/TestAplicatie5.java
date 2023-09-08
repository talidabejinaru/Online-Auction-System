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
public class TestAplicatie5 {

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
     *      -5 clienti vor fi inscrisi in total la aceeasi licitatie
     *      -licitatiile care se vor tine se vor finaliza cu succes astfel :
     *      -2 clienti vor oferi aceeasi suma. Va fi ales cel cu cele mai multe licitatii
     *      1000(5p) ->va fi castigata de 51.Sc Tiab cu suma de 259872

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
        //inscriere licitatia 1001 pt produsul 101
        listaInscrieri.add(listaClienti.get(0).inscriereLicitatieClient(101, 600000));
        listaInscrieri.add(listaClienti.get(1).inscriereLicitatieClient(101, 600000));
        listaInscrieri.add(listaClienti.get(2).inscriereLicitatieClient(101, 5000));
        listaInscrieri.add(listaClienti.get(3).inscriereLicitatieClient(101, 5000));
        listaInscrieri.add(listaClienti.get(4).inscriereLicitatieClient(101, 5000));

        Main.main(casaLicitatii, administrator, listaClienti, Arrays.asList(broker1, broker2),
                produsService.initializareListaDeProduseDinFisier(), licitatieService.initializareListaDeLicitatiiDinFisier(),
                listaInscrieri);

    }
}
