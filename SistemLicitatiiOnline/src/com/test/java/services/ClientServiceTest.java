package com.test.java.services;

import com.model.CasaLicitatii;
import com.model.Client;
import com.model.Produs;
import com.service.ClientService;
import com.service.ProdusService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class ClientServiceTest {

    @Test
    public void initializareListaDeClientiDinFisier() throws IOException {
        ClientService clientService = ClientService.getInstance();
        List<Client> list = clientService.initializareListaDeClientiDinFisier();
        assertEquals(14, list.size());
    }

  /*  @Test
    public void vizualizareListaProduse() {
        ClientService clientService = ClientService.getInstance();
        CasaLicitatii casaLicitatii = new CasaLicitatii();
        Produs p1 = Produs.builder().withId(1).build();
        Produs p2 = Produs.builder().withId(1).build();
        casaLicitatii.setListaProduse(Arrays.asList(p1, p2));
        List<Produs> list = clientService.vizualizareListaProduse(casaLicitatii);
        assertEquals(2, list.size());
    }
    */

}