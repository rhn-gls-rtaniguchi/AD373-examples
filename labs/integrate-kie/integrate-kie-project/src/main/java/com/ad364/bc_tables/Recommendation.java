package com.ad364.bc_tables;

public class Recommendation implements java.io.Serializable {

    static final long serialVersionUID = 1L;

    @org.kie.api.definition.type.Label(value = "Make")
    private java.lang.String make;
    @org.kie.api.definition.type.Label(value = "Model")
    private java.lang.String model;

    public Recommendation() {
    }

    public java.lang.String getMake() {
        return this.make;
    }

    public void setMake(java.lang.String make) {
        this.make = make;
    }

    public java.lang.String getModel() {
        return this.model;
    }

    public void setModel(java.lang.String model) {
        this.model = model;
    }

    public Recommendation(java.lang.String make, java.lang.String model) {
        this.make = make;
        this.model = model;
    }

    @Override
    public String toString() {
        return "Recommendation{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}