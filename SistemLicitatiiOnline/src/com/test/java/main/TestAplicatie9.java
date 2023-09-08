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
public class TestAplicatie9 {

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
     *      -licitatiile care se vor tine se vor finaliza dar nici un produs nu se va vinde pentru ca pretul maxim
     *      oferit de fiecare client este sub pretul de pornire:
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
        listaInscrieri.add(listaClienti.get(0).inscriereLicitatieClient(130, 10));
        listaInscrieri.add(listaClienti.get(4).inscriereLicitatieClient(130, 10));
        listaInscrieri.add(listaClienti.get(7).inscriereLicitatieClient(130, 10));
        listaInscrieri.add(listaClienti.get(1).inscriereLicitatieClient(130, 10));

        //inscriere licitie 1027 pt prod 127
        listaInscrieri.add(listaClienti.get(2).inscriereLicitatieClient(127, 10));
        listaInscrieri.add(listaClienti.get(5).inscriereLicitatieClient(127, 10));
        listaInscrieri.add(listaClienti.get(12).inscriereLicitatieClient(127, 10));

        //inscriere licitatia 1015 pt produsul 115
        listaInscrieri.add(listaClienti.get(2).inscriereLicitatieClient(115, 10));
        listaInscrieri.add(listaClienti.get(11).inscriereLicitatieClient(115, 10));
        listaInscrieri.add(listaClienti.get(8).inscriereLicitatieClient(115, 10));
        listaInscrieri.add(listaClienti.get(13).inscriereLicitatieClient(115, 10));
        listaInscrieri.add(listaClienti.get(9).inscriereLicitatieClient(115, 10));


        Main.main(casaLicitatii, administrator, listaClienti, Arrays.asList(broker1, broker2),
                produsService.initializareListaDeProduseDinFisier(), licitatieService.initializareListaDeLicitatiiDinFisier(),
                listaInscrieri);

    }
}
