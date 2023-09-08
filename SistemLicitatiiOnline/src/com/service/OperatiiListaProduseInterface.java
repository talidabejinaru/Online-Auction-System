package com.service;

import com.model.CasaLicitatii;
import com.model.Produs;

import java.util.Collections;
import java.util.List;

public interface OperatiiListaProduseInterface {

    default List<Produs> vizualizareListaProduse(CasaLicitatii casaLicitatii) {
        return Collections.emptyList();
    }

    default void modificaListaProduse(Produs p, CasaLicitatii casaLicitatii) {

    }

}
