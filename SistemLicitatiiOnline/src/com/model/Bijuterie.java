package com.model;

/**
 * Clasa de model pentru Bijuterie. Extinde clasa produs
 */
public class Bijuterie extends Produs {

    private String material;

    private boolean piatraPretioasa;

    public Bijuterie(Builder builder) {
        super(builder);
        this.material = builder.material;
        this.piatraPretioasa = builder.piatraPretioasa;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getMaterial() {
        return material;
    }

    public boolean isPiatraPretioasa() {
        return piatraPretioasa;
    }

    @Override
    public String toString() {
        return "Bijuterie{" +
                super.toString() +
                "material='" + material + '\'' +
                ", piatraPretioasa=" + piatraPretioasa +
                '}';
    }

    public static class Builder extends Produs.Builder<Builder> {

        private String material;

        private boolean piatraPretioasa;

        @Override
        public Builder getThis() {
            return this;
        }

        public Builder withMaterial(String material) {
            this.material = material;
            return this;
        }

        public Builder withPiatraPretioasa(boolean piatraPretioasa) {
            this.piatraPretioasa = piatraPretioasa;
            return this;
        }


        public Bijuterie build() {
            return new Bijuterie(this);
        }

    }
}
