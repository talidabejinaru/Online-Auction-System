package com.test.java.model;

import com.model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BrokerTest {

    @Test
    public void modificaListaProduse() {
        Broker broker = Broker.builder().withId(1).withNume("Broker").build();
        Produs p1 = Produs.builder().withId(100).build();
        Produs p2 = Produs.builder().withId(101).build();
        List<Produs> listaProduse = new ArrayList<>();
        listaProduse.add(p1);
        listaProduse.add(p2);
        CasaLicitatii casaLicitatii = new CasaLicitatii(listaProduse, new ArrayList<>(), null);


        assertEquals(2, casaLicitatii.getListaProduse().size());
        broker.modificaListaProduse(p1, casaLicitatii);

        assertEquals(1, casaLicitatii.getListaProduse().size());
    }

    @Test
    public void calculeazaComision() {
        Broker broker = Broker.builder().withId(1).withNume("Broker").build();

        PersoanaFizica client1 = PersoanaFizica.builder().withNrParticipari(5).build();

        double comision = broker.calculeazaComision(client1, 100);
        assertEquals(String.valueOf(20.0), String.valueOf(comision));

        PersoanaFizica client2 = PersoanaFizica.builder().withNrParticipari(6).build();

        comision = broker.calculeazaComision(client2, 100);
        assertEquals(String.valueOf(15.0), String.valueOf(comision));

        PersoanaJuridica client3 = PersoanaJuridica.builder().withNrParticipari(25).build();

        comision = broker.calculeazaComision(client3, 100);
        assertEquals(String.valueOf(25.0), String.valueOf(comision));

        PersoanaJuridica client4 = PersoanaJuridica.builder().withNrParticipari(26).build();

        comision = broker.calculeazaComision(client4, 100);
        assertEquals(String.valueOf(10.0), String.valueOf(comision));
    }

    @Test
    public void cerereSumaDeLicitatDeLaClient() {
        Broker broker = Broker.builder().withId(1).withNume("Broker").build();
        PersoanaFizica client1 = PersoanaFizica.builder().withNrParticipari(5).withNrLicitatiiCastigate(2).build();

        double pret = broker.cerereSumaDeLicitatDeLaClient(client1,100,1000);

        assertEquals(String.valueOf(280.0), String.valueOf(pret));

    }
}