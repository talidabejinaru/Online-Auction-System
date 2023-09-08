package com.model;

import com.service.CasaDeLicitatiiService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Clasa model pentru CasaLicitatii. Extinde clasa de services
 */
public class CasaLicitatii extends CasaDeLicitatiiService {

    private List<Produs> listaProduse;

    private List<Client> listaClienti;

    private Map<Integer, Licitatie> listaLicitatii = new ConcurrentHashMap<>();

    public CasaLicitatii() {
    }

    public CasaLicitatii(List<Produs> listaProduse, List<Client> listaClienti, Map<Integer, Licitatie> listaLicitatii) {
        this.listaProduse = listaProduse;
        this.listaClienti = listaClienti;
        this.listaLicitatii = listaLicitatii;
    }

    public List<Produs> getListaProduse() {
        return listaProduse;
    }

    public void setListaProduse(List<Produs> listaProduse) {
        this.listaProduse = listaProduse;
    }

    public List<Client> getListaClienti() {
        return listaClienti;
    }

    public void setListaClienti(List<Client> listaClienti) {
        this.listaClienti = listaClienti;
    }

    public Map<Integer, Licitatie> getListaLicitatii() {
        return listaLicitatii;
    }

    public void setListaLicitatii(List<Licitatie> listaLicitatii) {
        listaLicitatii.forEach(licitatie -> this.listaLicitatii.put(licitatie.getIdProdus(), licitatie));
    }
}
