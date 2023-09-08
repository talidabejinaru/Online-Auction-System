package com.test.java.model;

import com.model.Administrator;
import com.model.CasaLicitatii;
import com.model.Produs;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AdministratorTest {

    @Test
    public void modificaListaProduse() {
        Administrator administrator = Administrator.builder().withId(1).withNume("Admin").build();
        CasaLicitatii casaLicitatii = new CasaLicitatii(new ArrayList<>(),new ArrayList<>(),null);
        Produs p = Produs.builder().withId(100).build();

        assertEquals(0,casaLicitatii.getListaProduse().size());
        administrator.modificaListaProduse(p,casaLicitatii);

        assertEquals(1,casaLicitatii.getListaProduse().size());
    }
}