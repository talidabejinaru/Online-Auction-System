package com.model;

/**
 * Clasa de model pentru Tablou. Extinde clasa produs
 */
public class Tablou extends Produs {

    private String numePictor;

    private Culori culori;

    public Tablou(Builder builder) {
        super(builder);
        this.numePictor = builder.numePictor;
        this.culori = builder.culori;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getNumePictor() {
        return numePictor;
    }

    public Culori getCulori() {
        return culori;
    }

    @Override
    public String toString() {
        return "Tablou{" +
                super.toString() +
                "numePictor='" + numePictor + '\'' +
                ", culori=" + culori +
                '}';
    }

    public static class Builder extends Produs.Builder<Builder> {

        private String numePictor;

        private Culori culori;

        @Override
        public Builder getThis() {
            return this;
        }

        public Builder withNumePictor(String numePictor) {
            this.numePictor = numePictor;
            return this;
        }

        public Builder withCulori(Culori culori) {
            this.culori = culori;
            return this;
        }


        public Tablou build() {
            return new Tablou(this);
        }

    }
}
