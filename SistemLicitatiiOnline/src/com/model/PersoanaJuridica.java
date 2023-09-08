package com.model;

/**
 * Clasa model pentru Persoana Juridica
 */
public class PersoanaJuridica extends Client {

    private double capitalSocial;

    private Companie companie;

    public PersoanaJuridica(Builder builder) {
        super(builder);
        this.capitalSocial = builder.capitalSocial;
        this.companie = builder.companie;
    }

    public static Builder builder() {
        return new Builder();
    }

    public double getCapitalSocial() {
        return capitalSocial;
    }

    @Override
    public String toString() {
        return "PersoanaJuridica{" +
                super.toString() +
                "capitalSocial=" + capitalSocial +
                ", companie=" + companie +
                '}';
    }

    public Companie getCompanie() {
        return companie;
    }

    public static class Builder extends Client.Builder<Builder> {

        private double capitalSocial;

        private Companie companie;

        @Override
        public Builder getThis() {
            return this;
        }

        public Builder withCapitalSocial(double capitalSocial) {
            this.capitalSocial = capitalSocial;
            return this;
        }

        public Builder withCompanie(Companie companie) {
            this.companie = companie;
            return this;
        }


        public PersoanaJuridica build() {
            return new PersoanaJuridica(this);
        }

    }
}
