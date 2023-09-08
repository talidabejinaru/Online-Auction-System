package com.service;

import com.model.Broker;
import com.model.Client;

import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Clasa responsabile pentru niste operatiunui legate de broker
 */
public class BrokerService {

    /**
     * @param listaBrokeri
     * @return - Broker
     * Returneaza aleator un broker dintr-o lista de brokeri folosind un random
     */
    public Broker returneazaUnBrokerAleator(List<Broker> listaBrokeri) {
        Random rand = new Random();
        int randomIndex = rand.nextInt(listaBrokeri.size());
        return listaBrokeri.get(randomIndex);
    }

    /**
     * @param listaBrokeri - lista de brokeri existenti
     * @param client - clientul pentru care vrem sa aflam brokerul
     * @return
     * Primeste ca parametru un client si lista de brokeri existenti, si ii returneaza brokerul asignat
     */
    public Optional<Broker> returneazaBrokerulUnuiClient(List<Broker> listaBrokeri, Client client) {

        return listaBrokeri.stream()
                .filter(broker -> broker.getListaClienti()
                        .stream()
                        .filter(client1 -> client1.equals(client)).anyMatch(client1 -> client1.equals(client)))
                .findAny();
    }
}
