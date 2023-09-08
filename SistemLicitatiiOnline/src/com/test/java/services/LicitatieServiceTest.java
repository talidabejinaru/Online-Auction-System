package com.test.java.services;

import com.model.Licitatie;
import com.service.LicitatieService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class LicitatieServiceTest {

    @Test
    public void vizualizareFisier() throws IOException {
        LicitatieService licitatieService = new LicitatieService();
        List<Licitatie> list = licitatieService.initializareListaDeLicitatiiDinFisier();
        assertEquals(30,list.size());
    }

    @Test
    public void construireLicitatie() throws IOException {
        LicitatieService licitatieService = new LicitatieService();
        List<Licitatie> list = licitatieService.initializareListaDeLicitatiiDinFisier();
        assertEquals(1001,list.get(0).getId());
    }

}