package com.model;

import com.service.OperatiiListaProduseInterface;

import java.util.List;

/**
 * Clasa de model pentru Broker. Extinde clasa angajat
 */
public class Broker extends Angajat implements OperatiiListaProduseInterface {

    private List<Client> listaClienti;

    public List<Client> getListaClienti() {
        return listaClienti;
    }

    public Broker(Builder builder) {
        super(builder);
        this.listaClienti = builder.listaClienti;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * @param p = produsul pe care dorim sa il stergem din lista
     * @param casaLicitatii  - care mentine lista
     */
    @Override
    public void modificaListaProduse(Produs p, CasaLicitatii casaLicitatii) {
        casaLicitatii.getListaProduse().remove(p);
    }

    /**
     * @param client - clientul pentru care brokerul ii va calcula comisionul
     * @param valoareTranzactionata - valoarea
     * @return - un comision de tip double
     */
    public double calculeazaComision(Client client, double valoareTranzactionata) {
        if (client instanceof PersoanaFizica) {
            if (client.getNrParticipari() > 5) {
                return (15 * valoareTranzactionata) / 100;
            } else {
                return (20 * valoareTranzactionata) / 100;
            }
        } else if (client instanceof PersoanaJuridica) {
            if (client.getNrParticipari() > 25) {
                return (10 * valoareTranzactionata) / 100;
            } else {
                return (25 * valoareTranzactionata) / 100;
            }
        }

        return 0;
    }

    /**
     * @param client - clientul caruia brokerul ii cere suma de licitat
     * @param sumaLicitataActual - pretul actual al licitatiei
     * @param pretMaximStabilit - cat a stabilit clientul ca poate oferi maxim
     * @return
     */
    public double cerereSumaDeLicitatDeLaClient(Client client, double sumaLicitataActual, double pretMaximStabilit) {
        return client.calculeazaPretOferit(sumaLicitataActual, pretMaximStabilit);
    }

    public static class Builder extends Angajat.Builder<Builder> {

        private List<Client> listaClienti;

        @Override
        public Builder getThis() {
            return this;
        }

        public Builder withListaClienti(List<Client> listaClienti) {
            this.listaClienti = listaClienti;
            return this;
        }

        public Broker build() {
            return new Broker(this);
        }

    }

    @Override
    public String toString() {
        return "Broker{" +
                "listaClienti=" + listaClienti +
                '}';
    }
}
