package com.test.java.services;

import com.model.Client;
import com.model.Companie;
import com.model.PersoanaFizica;
import com.model.PersoanaJuridica;
import com.service.ClientFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class ClientFactoryTest {

    @Test
    public void returneazaClient() {
        ClientFactory clientFactory = new ClientFactory();

        Client c1 = clientFactory.returneazaClient("pf;50;Mihai Badea;Pitesti;5;1;26.03.1989");
        Client c2 = clientFactory.returneazaClient("pj;51;SC Tiab;Bucuresti;5;3;SRL;10000");
        Client c3 = clientFactory.returneazaClient("pm;51;SC Tiab;Bucuresti;5;3");

        assertTrue(c1 instanceof PersoanaFizica);
        assertTrue(c2 instanceof PersoanaJuridica);
        assertTrue(c3 instanceof Client);
        PersoanaFizica persoanaFizica = (PersoanaFizica) c1;
        PersoanaJuridica persoanaJuridica = (PersoanaJuridica) c2;
        assertEquals("26.03.1989",persoanaFizica.getDataNastere());
        assertEquals(Companie.SRL,persoanaJuridica.getCompanie());

    }
}