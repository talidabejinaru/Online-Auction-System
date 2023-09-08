package com.model;

import com.service.OperatiiListaProduseInterface;

/**
 * Clasa de model pentru Administrator.
 */
public class Administrator extends Angajat implements OperatiiListaProduseInterface {


    public Administrator(Builder builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * @param p - produsul pe care vrem sa il adaugam
     * @param casaLicitatii - care mentine lista de produse
     */
    @Override
    public void modificaListaProduse(Produs p, CasaLicitatii casaLicitatii) {
        casaLicitatii.getListaProduse().add(p);
    }

    public static class Builder extends Angajat.Builder<Builder> {

        @Override
        public Builder getThis() {
            return this;
        }

        public Administrator build() {
            return new Administrator(this);
        }

    }
}
