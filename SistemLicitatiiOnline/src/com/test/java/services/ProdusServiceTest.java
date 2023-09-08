package com.test.java.services;

import com.model.Mobila;
import com.model.Produs;
import com.service.ProdusService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(JUnit4.class)
public class ProdusServiceTest {

    @Test
    public void vizualizareFisier() throws IOException {
        ProdusService produsService = ProdusService.getInstance();
        List<Produs> list = produsService.initializareListaDeProduseDinFisier();
        assertEquals(30,list.size());
    }

    @Test
    public void selecteazaProdusDupaId() throws IOException {
        ProdusService produsService = ProdusService.getInstance();
        List<Produs> list = produsService.initializareListaDeProduseDinFisier();
        Mobila mobila = Mobila.builder()
                .withId(102)
                .withNume("Canapea extensibila")
                .withPretVanzare(3000)
                .withPretMinim(20)
                .withAn(2020)
                .withTip("tip L")
                .withMaterial("burete")
                .build();

        Produs p = produsService.selecteazaProdusDupaId(list,102);

        assertEquals(mobila.getId(),p.getId());
        assertEquals(mobila.getNume(),p.getNume());
        assertEquals(mobila.getAn(),p.getAn());

        assertNull(produsService.selecteazaProdusDupaId(list,4454));

    }
}