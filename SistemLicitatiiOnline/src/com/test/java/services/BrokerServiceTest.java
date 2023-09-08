package com.test.java.services;

import com.model.Broker;
import com.model.Client;
import com.service.BrokerService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BrokerServiceTest {

    @Test
    public void returneazaUnBrokerAleator() {
        Broker broker1 = Broker.builder().withId(1).withNume("Broker 1").withListaClienti(Collections.emptyList()).build();
        Broker broker2 = Broker.builder().withId(2).withNume("Broker 2").withListaClienti(Collections.emptyList()).build();
        List<Broker> listaBrokeri = new ArrayList<>();
        listaBrokeri.add(broker1);
        listaBrokeri.add(broker2);

        BrokerService brokerService = new BrokerService();
        Broker brokerRezultat = brokerService.returneazaUnBrokerAleator(listaBrokeri);
        System.out.println(brokerRezultat);
    }

    @Test
    public void returneazaBrokerulUnuiClient() {

        Client client1 = Client.builder().withId(1).withNume("Client1").build();
        Client client2 = Client.builder().withId(2).withNume("Client2").build();
        Client client3 = Client.builder().withId(3).withNume("Client3").build();
        Client client4 = Client.builder().withId(4).withNume("Client4").build();


        Broker broker1 = Broker.builder().withId(1).withNume("Broker 1").withListaClienti(Arrays.asList(client1, client2)).build();
        Broker broker2 = Broker.builder().withId(2).withNume("Broker 2").withListaClienti(Arrays.asList(client3, client4)).build();
        List<Broker> listaBrokeri = new ArrayList<>();
        listaBrokeri.add(broker1);
        listaBrokeri.add(broker2);

        BrokerService brokerService = new BrokerService();
        Broker brokerRezultat = brokerService.returneazaBrokerulUnuiClient(listaBrokeri, client1).get();

        assertEquals(broker1, brokerRezultat);

        brokerRezultat = brokerService.returneazaBrokerulUnuiClient(listaBrokeri, client2).get();

        assertEquals(broker1, brokerRezultat);

        brokerRezultat = brokerService.returneazaBrokerulUnuiClient(listaBrokeri, client3).get();

        assertEquals(broker2, brokerRezultat);

        brokerRezultat = brokerService.returneazaBrokerulUnuiClient(listaBrokeri, client4).get();

        assertEquals(broker2, brokerRezultat);

    }
}