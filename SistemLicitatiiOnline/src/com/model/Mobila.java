package com.model;

/**
 * Clasa de model pentru Bijuterie. Extinde clasa produs
 */
public class Mobila extends Produs {

    private String tip;

    private String material;

    public Mobila(Builder builder) {
        super(builder);
        this.tip = builder.tip;
        this.material = builder.material;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getTip() {
        return tip;
    }

    public String getMaterial() {
        return material;
    }

    @Override
    public String toString() {
        return "Mobila{" +
                super.toString() +
                "tip='" + tip + '\'' +
                ", material='" + material + '\'' +
                '}';
    }

    public static class Builder extends Produs.Builder<Builder> {

        private String tip;

        private String material;

        @Override
        public Builder getThis() {
            return this;
        }

        public Builder withMaterial(String material) {
            this.material = material;
            return this;
        }

        public Builder withTip(String tip) {
            this.tip = tip;
            return this;
        }


        public Mobila build() {
            return new Mobila(this);
        }

    }
}
