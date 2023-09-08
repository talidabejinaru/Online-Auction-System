package com.model;

/**
 * Clasa de model pentru Angajat
 */
public class Angajat {

    private int id;

    private String nume;

    protected Angajat(Builder<?> builder) {
        this.id = builder.id;
        this.nume = builder.nume;
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

    public abstract static class Builder<T extends Builder<T>> {
        private int id;

        private String nume;

        public abstract T getThis();

        public T withId(int id) {
            this.id = id;
            return this.getThis();
        }

        public T withNume(String nume) {
            this.nume = nume;
            return this.getThis();
        }

        public Angajat build() {
            return new Angajat(this);
        }

    }

}
