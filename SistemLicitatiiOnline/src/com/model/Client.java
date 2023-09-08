package com.model;

import com.service.CasaDeLicitatiiService;
import com.service.OperatiiListaProduseInterface;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clasa model pentru Client
 */
public class Client implements OperatiiListaProduseInterface {

    static Logger logger = Logger.getLogger(Client.class.getName());

    private int id;

    private String nume;

    private String adresa;

    private int nrParticipari;

    private int nrLicitatiiCastigate;

    protected Client(Builder<?> builder) {
        this.id = builder.id;
        this.nume = builder.nume;
        this.nrParticipari = builder.nrParticipari;
        this.nrLicitatiiCastigate = builder.nrLicitatiiCastigate;
        this.adresa = builder.adresa;
    }

    public static Builder builder() {
        return new Builder() {
            @Override
            public Builder getThis() {
                return this;
            }
        };
    }

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public int getNrParticipari() {
        return nrParticipari;
    }

    public String getAdresa() {
        return adresa;
    }

    public int getNrLicitatiiCastigate() {
        return nrLicitatiiCastigate;
    }

    /**
     * @param casaLicitatii - care mentine lista de produse
     * @return o lista de produse
     */
    @Override
    public List<Produs> vizualizareListaProduse(CasaLicitatii casaLicitatii) {
        return casaLicitatii.getListaProduse();
    }

    /**
     * @param produsId          - produsul pentru care clientul vrea sa liciteze
     * @param sumaMaximaOferita - suma maxima pe care doreste sa o plateasca
     * @return un Obiect de tipul Inscriere Licitatie
     */
    public InscriereLicitatie inscriereLicitatieClient(int produsId, double sumaMaximaOferita) {
        logger.log(Level.INFO, "Clientul cu id {0} si nume {1} s-a inscris la licitatia pentru produsul cu id {2}. " +
                "Pretul maxim oferit de el este : {3} ", new Object[]{this.getId(), this.getNume(), produsId, sumaMaximaOferita});

        return new InscriereLicitatie(this, produsId, sumaMaximaOferita);
    }

    /**
     * @param sumaLicitataActual - la ce pret s-a ajuns in licitatie
     * @param pretMaximStabilit  - cat poate oferi
     * @return noul pret pe care il poate plati
     * Algoritmul este:
     * la pretul actual al licitatie se adauga diferenta dintre pretul maxim si cel actual impartita la numarul de participari.
     * Daca acest pret depaseste pe cel maxim, va fi oferit cel maxim
     */
    public double calculeazaPretOferit(double sumaLicitataActual, double pretMaximStabilit) {
        double pretOferit = sumaLicitataActual + (pretMaximStabilit - sumaLicitataActual) / (this.getNrParticipari());
        if (pretOferit < pretMaximStabilit) {
            return pretOferit;
        } else {
            return pretMaximStabilit;
        }
    }

    public abstract static class Builder<T extends Builder<T>> {
        private int id;

        private String nume;

        private String adresa;

        private int nrParticipari;

        private int nrLicitatiiCastigate;

        public abstract T getThis();

        public T withId(int id) {
            this.id = id;
            return this.getThis();
        }

        public T withNume(String nume) {
            this.nume = nume;
            return this.getThis();
        }

        public T withAdresa(String adresa) {
            this.adresa = adresa;
            return this.getThis();
        }

        public T withNrLicitatiiCastigate(int nrLicitatiiCastigate) {
            this.nrLicitatiiCastigate = nrLicitatiiCastigate;
            return this.getThis();
        }


        public T withNrParticipari(int nrParticipari) {
            this.nrParticipari = nrParticipari;
            return this.getThis();
        }

        public Client build() {
            return new Client(this);
        }

    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", adresa='" + adresa + '\'' +
                ", nrParticipari=" + nrParticipari +
                ", nrLicitatiiCastigate=" + nrLicitatiiCastigate +
                '}';
    }
}
