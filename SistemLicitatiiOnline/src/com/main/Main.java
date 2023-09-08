package com.main;

import com.model.*;
import com.service.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(CasaLicitatii casaLicitatii, Administrator administrator,
                            List<Client> listaClienti, List<Broker> listaBrokeri,
                            List<Produs> listaProduse, List<Licitatie> listaLicitatii,
                            List<InscriereLicitatie> listaInscrieriLicitatii) {


        //initializare obiecte etc
        ProdusService produsService = ProdusService.getInstance();
        BrokerService brokerService = new BrokerService();

        //setam campurile pe casa de licitatii
        casaLicitatii.setListaProduse(listaProduse);
        casaLicitatii.setListaClienti(listaClienti);
        casaLicitatii.setListaLicitatii(listaLicitatii);

        //parcugem lista de clienti care s-au inscris, si alocam cate un broker pentru fiecare
        listaInscrieriLicitatii
                .stream().
                forEach(inscriereLicitatie -> casaLicitatii.alocaBrokerPentruClient(brokerService.returneazaUnBrokerAleator(listaBrokeri),
                        inscriereLicitatie.getClient()));
        //adaugam pe fiecare licitatie lista de participanti la ea (Client,Broker)
        casaLicitatii.definitiveazaLicitatii(listaInscrieriLicitatii, listaBrokeri, casaLicitatii.getListaLicitatii());

        //construim un Map cu preturile maxime oferite.
        Map<Integer, Double> listaPreturiMaxime = casaLicitatii.construisteListaPreturiMaximeOferite(listaInscrieriLicitatii);

        //parcurgem licitatiile, iar pentru cele la care s-au inscris suficienti participanti le pornim
        //licitatiile se executa in parallel in multithreading
        Map<Integer, RezultatLicitatie> rezultateLicitatii = new ConcurrentHashMap<>();
        casaLicitatii.getListaLicitatii()
                .entrySet()
                .stream()
                .filter(integerLicitatieEntry ->
                        integerLicitatieEntry.getValue().getListPerecheBrokerClient().size() >=
                                integerLicitatieEntry.getValue().getNrParticipanti())
                .parallel()
                .forEach(integerLicitatieEntry ->
                        rezultateLicitatii.put(integerLicitatieEntry.getValue().getId(),
                                casaLicitatii.executaLicitatie(integerLicitatieEntry.getValue(),
                                        produsService.selecteazaProdusDupaId(casaLicitatii.getListaProduse(),
                                                integerLicitatieEntry.getValue().getIdProdus()), listaPreturiMaxime)));

        //afisam licitatiile pentru care nu s-au inscris suficienti clienti
        System.out.println("==============Licitatii care nu se vor tine pentru ca nu sunt suficienti participanti=========");
        casaLicitatii.getListaLicitatii()
                .entrySet()
                .stream()
                .filter(integerLicitatieEntry ->
                        integerLicitatieEntry.getValue().getListPerecheBrokerClient().size() <
                                integerLicitatieEntry.getValue().getNrParticipanti())
                .forEach(integerLicitatieEntry ->
                        System.out.println("Licitatia " + String.valueOf(integerLicitatieEntry.getValue().getId()) + " nu va porni"));


        //afisam licitatiile efectuate si rezultatele lor.
        System.out.println("=============================Rezultate=======================");
        rezultateLicitatii.
                forEach((integer, rezultatLicitatie) ->
                        System.out.println("Licitatia cu id-ul " + integer + ":\n" + rezultatLicitatie));

    }

}
