package com.test.java.services;

import com.model.*;
import com.service.CasaDeLicitatiiService;
import org.junit.Test;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.*;

public class CasaDeLicitatiiServiceTest {

    @Test
    public void alocaBrokerPentruClient() {
        CasaDeLicitatiiService casaDeLicitatiiService = new CasaDeLicitatiiService();
        Client c1 = Client.builder().withId(1).withNume("c1").build();
        Broker b1 = Broker.builder().withId(10).withListaClienti(new ArrayList<>()).build();

        assertEquals(0, b1.getListaClienti().size());
        casaDeLicitatiiService.alocaBrokerPentruClient(b1, c1);

        assertEquals(1, b1.getListaClienti().size());
    }

    @Test
    public void construisteListaPreturiMaximeOferite() {
        CasaDeLicitatiiService casaDeLicitatiiService = new CasaDeLicitatiiService();
        Client c1 = Client.builder().withId(1).withNume("c1").build();
        Client c2 = Client.builder().withId(2).withNume("c1").build();
        Client c3 = Client.builder().withId(3).withNume("c1").build();
        InscriereLicitatie inscriereLicitatie1 = new InscriereLicitatie(c1, 1, 100);
        InscriereLicitatie inscriereLicitatie2 = new InscriereLicitatie(c2, 2, 110);
        InscriereLicitatie inscriereLicitatie3 = new InscriereLicitatie(c3, 3, 120);

        Map<Integer, Double> listaPreturi = casaDeLicitatiiService.construisteListaPreturiMaximeOferite(
                Arrays.asList(inscriereLicitatie1, inscriereLicitatie2, inscriereLicitatie3));

        assertEquals(Double.valueOf(100.0),listaPreturi.get(11));
        assertEquals(Double.valueOf(110.0),listaPreturi.get(22));
        assertEquals(Double.valueOf(120.0),listaPreturi.get(33));
    }

    @Test
    public void executaLicitatie() {

        CasaDeLicitatiiService casaDeLicitatiiService = new CasaDeLicitatiiService();
        Client c1 = Client.builder().withId(1).withNume("c1").build();
        Client c2 = Client.builder().withId(2).withNume("c2").build();
        Client c3 = Client.builder().withId(3).withNume("c3").build();
        Broker b1 = Broker.builder().withId(11111).build();
        Produs p1 = Produs.builder().withId(1).withPretMinim(10).build();
        InscriereLicitatie inscriereLicitatie1 = new InscriereLicitatie(c1, 1, 100);
        InscriereLicitatie inscriereLicitatie2 = new InscriereLicitatie(c2, 1, 110);
        InscriereLicitatie inscriereLicitatie3 = new InscriereLicitatie(c3, 1, 120);

        Map<Integer, Double> listaPreturi = casaDeLicitatiiService.construisteListaPreturiMaximeOferite(
                Arrays.asList(inscriereLicitatie1, inscriereLicitatie2, inscriereLicitatie3));
        Licitatie licitatie = new Licitatie(1000,3,1,3);
        licitatie.setListaPerechiBrokerClient(new ArrayList<>());
        licitatie.getListPerecheBrokerClient().add
                (new AbstractMap.SimpleEntry<>(c1, b1));
        licitatie.getListPerecheBrokerClient().add
                (new AbstractMap.SimpleEntry<>(c2, b1));
        licitatie.getListPerecheBrokerClient().add
                (new AbstractMap.SimpleEntry<>(c3, b1));

        RezultatLicitatie rezultatLicitatie = casaDeLicitatiiService.executaLicitatie(licitatie,p1,listaPreturi);
        System.out.println(rezultatLicitatie);
        assertEquals(c3,rezultatLicitatie.getClient());
        assertEquals(p1,rezultatLicitatie.getProdus());
        assertEquals(Status.VANDUT, rezultatLicitatie.getStatus());
        assertEquals(String.valueOf(120.0),String.valueOf(rezultatLicitatie.getPretAchizitie()));

    }

    @Test
    public void executaLicitatieClientiCuAcelatiPret() {

        CasaDeLicitatiiService casaDeLicitatiiService = new CasaDeLicitatiiService();
        Client c1 = Client.builder().withId(1).withNume("c1").withNrLicitatiiCastigate(1).build();
        Client c2 = Client.builder().withId(2).withNume("c2").withNrLicitatiiCastigate(10).build();
        Client c3 = Client.builder().withId(3).withNume("c3").withNrLicitatiiCastigate(20).build();
        Broker b1 = Broker.builder().withId(11111).build();
        Produs p1 = Produs.builder().withId(1).withPretMinim(10).build();
        InscriereLicitatie inscriereLicitatie1 = new InscriereLicitatie(c1, 1, 100);
        InscriereLicitatie inscriereLicitatie2 = new InscriereLicitatie(c2, 1, 100);
        InscriereLicitatie inscriereLicitatie3 = new InscriereLicitatie(c3, 1, 100);

        Map<Integer, Double> listaPreturi = casaDeLicitatiiService.construisteListaPreturiMaximeOferite(
                Arrays.asList(inscriereLicitatie1, inscriereLicitatie2, inscriereLicitatie3));
        Licitatie licitatie = new Licitatie(1000,3,1,3);
        licitatie.setListaPerechiBrokerClient(new ArrayList<>());
        licitatie.getListPerecheBrokerClient().add
                (new AbstractMap.SimpleEntry<>(c1, b1));
        licitatie.getListPerecheBrokerClient().add
                (new AbstractMap.SimpleEntry<>(c2, b1));
        licitatie.getListPerecheBrokerClient().add
                (new AbstractMap.SimpleEntry<>(c3, b1));

        RezultatLicitatie rezultatLicitatie = casaDeLicitatiiService.executaLicitatie(licitatie,p1,listaPreturi);
        System.out.println(rezultatLicitatie);
        assertEquals(c3,rezultatLicitatie.getClient());
        assertEquals(p1,rezultatLicitatie.getProdus());
        assertEquals(Status.VANDUT, rezultatLicitatie.getStatus());
        assertEquals(String.valueOf(100.0),String.valueOf(rezultatLicitatie.getPretAchizitie()));

    }

    @Test
    public void executaLicitatieClientiNuOferaPretulMinim() {

        CasaDeLicitatiiService casaDeLicitatiiService = new CasaDeLicitatiiService();
        Client c1 = Client.builder().withId(1).withNume("c1").withNrLicitatiiCastigate(1).build();
        Client c2 = Client.builder().withId(2).withNume("c2").withNrLicitatiiCastigate(10).build();
        Client c3 = Client.builder().withId(3).withNume("c3").withNrLicitatiiCastigate(20).build();
        Broker b1 = Broker.builder().withId(11111).build();
        Produs p1 = Produs.builder().withId(1).withPretMinim(1000).build();
        InscriereLicitatie inscriereLicitatie1 = new InscriereLicitatie(c1, 1, 100);
        InscriereLicitatie inscriereLicitatie2 = new InscriereLicitatie(c2, 1, 100);
        InscriereLicitatie inscriereLicitatie3 = new InscriereLicitatie(c3, 1, 100);

        Map<Integer, Double> listaPreturi = casaDeLicitatiiService.construisteListaPreturiMaximeOferite(
                Arrays.asList(inscriereLicitatie1, inscriereLicitatie2, inscriereLicitatie3));
        Licitatie licitatie = new Licitatie(1000,3,1,3);
        licitatie.setListaPerechiBrokerClient(new ArrayList<>());
        licitatie.getListPerecheBrokerClient().add
                (new AbstractMap.SimpleEntry<>(c1, b1));
        licitatie.getListPerecheBrokerClient().add
                (new AbstractMap.SimpleEntry<>(c2, b1));
        licitatie.getListPerecheBrokerClient().add
                (new AbstractMap.SimpleEntry<>(c3, b1));

        RezultatLicitatie rezultatLicitatie = casaDeLicitatiiService.executaLicitatie(licitatie,p1,listaPreturi);
        System.out.println(rezultatLicitatie);
        assertEquals(null,rezultatLicitatie.getClient());
        assertEquals(p1,rezultatLicitatie.getProdus());
        assertEquals(Status.NEVANDUT, rezultatLicitatie.getStatus());
        assertEquals(String.valueOf(0.0),String.valueOf(rezultatLicitatie.getPretAchizitie()));

    }
}