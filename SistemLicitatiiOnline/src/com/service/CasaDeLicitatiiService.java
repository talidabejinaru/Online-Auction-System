package com.service;

import com.model.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Clasa de servicii pentru CasaDeLicitatii
 */
public class CasaDeLicitatiiService {

    static Logger logger = Logger.getLogger(CasaDeLicitatiiService.class.getName());

    /**
     * @param broker - brokerul pentru care se adauga un client
     * @param client - clientul care va fi alocat la un broker
     */
    public void alocaBrokerPentruClient(Broker broker, Client client) {
        broker.getListaClienti().add(client);
    }


    /**
     * @param listaInscrieri - lista de inscrieri la licitatie pentru un produs
     * @param listaBrokeri   - lista de brokeri existent
     * @param licitatieMap   - lista de tip Map cu cheia produs id si valoarea Licitatie
     *                       Metoda functioneaza astfel:
     *                       - Se parcurge lista de inscrieri
     *                       - Pentru fiecare inscriere se afla broker alocat unui client
     *                       - Se adauga in lista de perechi BrokerClient din obiectul licitatie noua pereche
     */
    public void definitiveazaLicitatii(List<InscriereLicitatie> listaInscrieri, List<Broker> listaBrokeri,
                                       Map<Integer, Licitatie> licitatieMap) {

        BrokerService brokerService = new BrokerService();

        for (InscriereLicitatie inscriereLicitatie : listaInscrieri) {
            int prodId = inscriereLicitatie.getProdusId();
            Licitatie licitatie = licitatieMap.get(prodId);
            Client client = inscriereLicitatie.getClient();
            Broker broker = brokerService.returneazaBrokerulUnuiClient(listaBrokeri, inscriereLicitatie.getClient()).orElse(null);
            logger.log(Level.INFO, "Brokerul cu id {0} si nume {1} a fost asignat clientului cu id {2} si nume {3}",
                    new Object[]{broker.getId(), broker.getNume(), client.getId(), client.getNume()});
            licitatie.getListPerecheBrokerClient().add
                    (new AbstractMap.SimpleEntry<>(client, broker));
        }

    }

    /**
     * @param listaInscrieri - lista de inscrieri
     * @return - o lista de map cu
     * cheia : client id concatenat cu produs id, apoi converrtit in string
     * valoare : suma maxima oferita de fiecare client
     */
    public Map<Integer, Double> construisteListaPreturiMaximeOferite(List<InscriereLicitatie> listaInscrieri) {
        Map<Integer, Double> listaPreturiMaximeOferite = new ConcurrentHashMap<>();
        listaInscrieri
                .stream()
                .forEach(inscriereLicitatie
                        -> listaPreturiMaximeOferite.put(Integer.valueOf(String.valueOf(inscriereLicitatie.getClient().getId())
                                + inscriereLicitatie.getProdusId()),
                        inscriereLicitatie.getSumaMaximaOferita()));
        return listaPreturiMaximeOferite;

    }

    /**
     * @param licitatie            - licitatia care se efectueaza
     * @param produs               - produsul pentru care se efectueaa
     * @param preturiMaximeOferite - mapa cu preturile maxime oferite initial de clienti
     * @return Rezultatul licitatiei
     */
    public RezultatLicitatie executaLicitatie(Licitatie licitatie, Produs produs, Map<Integer, Double> preturiMaximeOferite) {

        logger.log(Level.INFO,
                "Start licitatie cu id : {0} pentru produsul cu id: {1} si numele {2}",
                new Object[]{licitatie.getId(), produs.getId(), produs.getNume()});
        //aflam lista de participanti
        List<AbstractMap.SimpleEntry<Client, Broker>> listaParticipanti = licitatie.getListPerecheBrokerClient();
        int numarPasi = licitatie.getNrPasiMaxim();
        int pas = 1;
        double pretLicitatProdus = produs.getPretMinim();
        //aflam lista de preturi
        Map<AbstractMap.SimpleEntry<Client, Broker>, Double> mapPreturi = new ConcurrentHashMap<>();

        while (pas <= numarPasi) {
            logger.log(Level.INFO, "Start pas {0}", pas);
            //obtinem preturile licitate
            mapPreturi = aflaPreturiOferite(listaParticipanti, pretLicitatProdus, preturiMaximeOferite, produs);
            //aflam pretul maxim oferit
            pretLicitatProdus = determinaMaximulDintrePreturi(mapPreturi);
            pas++;
        }

        //determinam castigatorul in cazul in care pretul oferit este mai mare decat pretul minim.Altfel produsul nu se vinde
        if (pretLicitatProdus > produs.getPretMinim()) {
            Client client = determinaCastigatorul(mapPreturi, pretLicitatProdus);
            logger.log(Level.INFO,
                    "Licitatia cu id : {0} pentru produsul cu id: {1} si numele {2} a fost castigata de clientul cu id {3} si numele {4} pentru suma de {5}",
                    new Object[]{licitatie.getId(), produs.getId(), produs.getNume(), client.getId(), client.getNume(), pretLicitatProdus});
            return new RezultatLicitatie(client, pretLicitatProdus, produs, Status.VANDUT);
        } else {
            return new RezultatLicitatie(null, 0, produs, Status.NEVANDUT);
        }
    }

    /**
     * @param listaParticipanti    - lista de perechi Client Broker
     * @param pretLicitat          - cat s-a licitat maxim
     * @param preturiMaximeOferite - preturile oferite initial
     * @param produs               - produsul pentru care se liciteaza
     * @return o mapa cu
     * cheie: pereche Client-Broker
     * valoare: pretul nou oferit
     * Lista se parcurge in multithreading cu .parallel
     */
    private Map<AbstractMap.SimpleEntry<Client, Broker>, Double> aflaPreturiOferite(List<AbstractMap.SimpleEntry<Client, Broker>> listaParticipanti,
                                                                                    double pretLicitat, Map<Integer, Double> preturiMaximeOferite, Produs produs) {

        Map<AbstractMap.SimpleEntry<Client, Broker>, Double> mapPreturi = new ConcurrentHashMap<>();
        listaParticipanti.stream().parallel().forEach(
                pereche -> comunicarePreturiBrokerClient(mapPreturi, pereche, pretLicitat, preturiMaximeOferite, produs));

        return mapPreturi;
    }

    /**
     * @param mapPreturi           - preturi
     * @param perecheBrokerClient  - participanti
     * @param pretLicitat          - pretul licitat
     * @param preturiMaximeOferite - preturi maxime
     * @param produs               - produsul care se vinde
     *                             Se updateaza lista de preturi oferite cu noile oferte de la clienti.
     */
    private void comunicarePreturiBrokerClient(Map<AbstractMap.SimpleEntry<Client, Broker>, Double> mapPreturi,
                                               AbstractMap.SimpleEntry<Client, Broker> perecheBrokerClient, double pretLicitat,
                                               Map<Integer, Double> preturiMaximeOferite, Produs produs) {
        Client client = perecheBrokerClient.getKey();
        Broker broker = perecheBrokerClient.getValue();
        logger.log(Level.INFO, " Brokerul cu id {0} si nume {1} informeaza Clientul cu id {2} si nume {3}" +
                        " ca pretul stabilit este {4}",
                new Object[]{broker.getId(), broker.getNume(), client.getId(), client.getNume(), pretLicitat});
        Double pretNouOferit = broker.cerereSumaDeLicitatDeLaClient(client, pretLicitat,
                preturiMaximeOferite.get(Integer.valueOf(String.valueOf(client.getId()) + produs.getId())));
        logger.log(Level.INFO, " Clientul cu id {0} si nume {1} informeaza Brokerul cu id {2} si nume {3}" +
                        " ca pretul oferit este {4}",
                new Object[]{client.getId(), client.getNume(), broker.getId(), broker.getNume(), pretNouOferit});
        mapPreturi.put(perecheBrokerClient, pretNouOferit);
    }


    /**
     * @param map
     * @param <K>
     * @param <V>
     * @return maximul dintr-o mapa. Se alege perechea cu pretul cel mai mare
     */
    private <K, V extends Comparable<V>> V determinaMaximulDintrePreturi(Map<K, V> map) {
        Optional<Map.Entry<K, V>> maxEntry = map.entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue)
                );

        return maxEntry.get().getValue();
    }

    /**
     * @param mapPreturi - lista de preturi finale oferite
     * @param pretLicitatProdus - pretul maxim determinat
     * @return Clientul care a castigat.
     *
     * Aflam daca sunt mai multi clienti cu acelasi pret oferit.Daca da, castiga cel cu cele mai multe licitatii
     */
    private Client determinaCastigatorul(Map<AbstractMap.SimpleEntry<Client, Broker>, Double> mapPreturi, double pretLicitatProdus) {
        List<Client> listaClientiCuPretMaxim = mapPreturi
                .entrySet()
                .stream()
                .filter(simpleEntryDoubleEntry -> simpleEntryDoubleEntry.getValue() == pretLicitatProdus)
                .map(simpleEntryDoubleEntry -> simpleEntryDoubleEntry.getKey().getKey())
                .collect(Collectors.toList());

        listaClientiCuPretMaxim.
                stream().
                forEach(client -> logger.log(Level.INFO, "In urma licitatiei clientul cu id {0}," +
                                " numele {1} si numar de licitatii castigate {2} a oferit pretul maxim",
                        new Object[]{client.getId(), client.getNume(), client.getNrLicitatiiCastigate()}));

        return listaClientiCuPretMaxim
                .stream()
                .max(Comparator.comparing(Client::getNrLicitatiiCastigate))
                .orElse(null);
    }
}
