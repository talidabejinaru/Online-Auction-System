package com.test.java.model;

import com.model.CasaLicitatii;
import com.model.Client;
import com.model.InscriereLicitatie;
import com.model.Produs;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ClientTest {

    @Test
    public void vizualizareListaProduse() {
        Client client = Client.builder().withId(1).withNume("client").withNrParticipari(10).build();
        Produs p1 = Produs.builder().withId(100).build();
        Produs p2 = Produs.builder().withId(101).build();
        List<Produs> listaProduse = new ArrayList<>();
        listaProduse.add(p1);
        listaProduse.add(p2);
        CasaLicitatii casaLicitatii = new CasaLicitatii(listaProduse, new ArrayList<>(), null);

        List<Produs> produsList = client.vizualizareListaProduse(casaLicitatii);

        assertEquals(2,produsList.size());

    }

    @Test
    public void inscriereLicitatieClient() {
        Client client = Client.builder().withId(1).withNume("client").withNrParticipari(10).build();
        InscriereLicitatie inscriereLicitatie = client.inscriereLicitatieClient(100,10);

        assertEquals(client,inscriereLicitatie.getClient());
        assertEquals(100, inscriereLicitatie.getProdusId());
        assertEquals(String.valueOf(10.0), String.valueOf(inscriereLicitatie.getSumaMaximaOferita()));
    }

    @Test
    public void calculeazaPretOferit() {
        Client client = Client.builder().withId(1).withNume("client").withNrParticipari(10).build();
        double pret = client.calculeazaPretOferit(10,100);
        assertEquals(String.valueOf(19.0),String.valueOf(pret));

        Client client2 = Client.builder().withId(1).withNume("client").withNrParticipari(10).build();
        pret = client2.calculeazaPretOferit(10,10);
        assertEquals(String.valueOf(10.0),String.valueOf(pret));
    }
}