package com.model;

/**
 * Clasa model pentru Persoana Fizica
 */
public class PersoanaFizica extends Client {

    String dataNastere;

    public PersoanaFizica(Builder builder) {
        super(builder);
        this.dataNastere = builder.dataNastere;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getDataNastere() {
        return dataNastere;
    }

    @Override
    public String toString() {
        return "PersoanaFizica{" +
                super.toString() +
                "dataNastere='" + dataNastere + '\'' +
                '}';
    }

    public static class Builder extends Client.Builder<Builder> {

        private String dataNastere;

        @Override
        public Builder getThis() {
            return this;
        }

        public Builder withDataNastere(String dataNastere) {
            this.dataNastere = dataNastere;
            return this;
        }


        public PersoanaFizica build() {
            return new PersoanaFizica(this);
        }

    }
}
