package com.test.java.services;

import com.model.*;
import com.service.ProdusFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class ProdusFactoryTest {

    @Test
    public void returneazaProdus() {
        ProdusFactory produsFactory = new ProdusFactory();

        Produs p1 = produsFactory.returneazaProdus("tablou;101;Car cu boi;10000;6000;1899;Nicolae Grigorescu;tempera");
        Produs p2 = produsFactory.returneazaProdus("mobila;102;Canapea extensibila;3000;20;2020;tip L;burete");
        Produs p3 = produsFactory.returneazaProdus("bijuterie;103;Colier;5000;500;2019;argint;nu");

        assertTrue(p1 instanceof Tablou);
        assertTrue(p2 instanceof Mobila);
        assertTrue(p3 instanceof Bijuterie);
        Tablou tablou = (Tablou) p1;
        Mobila mobila = (Mobila) p2;
        Bijuterie bijuterie = (Bijuterie) p3;


        assertEquals(Culori.TEMPERA, tablou.getCulori());
        assertEquals("burete", mobila.getMaterial());
        assertEquals("argint", bijuterie.getMaterial());

    }

}