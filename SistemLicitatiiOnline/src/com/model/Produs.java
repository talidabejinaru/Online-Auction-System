package com.model;

/**
 * Clasa model pentru Produs
 */
public class Produs {

    private int id;

    private String nume;

    private double pretVanzare;

    private double pretMinim;

    private int an;

    protected Produs(Builder<?> builder) {
        this.id = builder.id;
        this.nume = builder.nume;
        this.pretVanzare = builder.pretVanzare;
        this.pretMinim = builder.pretMinim;
        this.an = builder.an;
    }

    public static Builder builder() {
        return new Builder() {
            @Override
            public Builder getThis() {
                return this;
            }
        };
    }

    public abstract static class Builder<T extends Builder<T>> {
        private int id;

        private String nume;

        private double pretVanzare;

        private double pretMinim;

        private int an;

        public abstract T getThis();

        public T withId(int id) {
            this.id = id;
            return this.getThis();
        }

        public T withNume(String nume) {
            this.nume = nume;
            return this.getThis();
        }

        public T withPretVanzare(double pretVanzare) {
            this.pretVanzare = pretVanzare;
            return this.getThis();
        }

        public T withPretMinim(double pretMinim) {
            this.pretMinim = pretMinim;
            return this.getThis();
        }

        public T withAn(int an) {
            this.an = an;
            return this.getThis();
        }

        public Produs build() {
            return new Produs(this);
        }

    }


    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public double getPretVanzare() {
        return pretVanzare;
    }

    public double getPretMinim() {
        return pretMinim;
    }

    public int getAn() {
        return an;
    }

    @Override
    public String toString() {
        return "Produs{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", pretVanzare=" + pretVanzare +
                ", pretMinim=" + pretMinim +
                ", an=" + an +
                '}';
    }

}
