package com.ad364.bc_tables;

public class TravelProfile implements java.io.Serializable {

    static final long serialVersionUID = 1L;

    @org.kie.api.definition.type.Label("Travel Percentage")
    private java.lang.Integer travelPercent;

    @org.kie.api.definition.type.Label(value = "No Travel")
    private java.lang.Boolean noTravel;

    public TravelProfile() {
    }

    public java.lang.Integer getTravelPercent() {
        return this.travelPercent;
    }

    public void setTravelPercent(java.lang.Integer travelPercent) {
        this.travelPercent = travelPercent;
    }

    public java.lang.Boolean getNoTravel() {
        return this.noTravel;
    }

    public void setNoTravel(java.lang.Boolean noTravel) {
        this.noTravel = noTravel;
    }

    public TravelProfile(java.lang.Integer travelPercent,
                         java.lang.Boolean noTravel) {
        this.travelPercent = travelPercent;
        this.noTravel = noTravel;
    }

    @Override
    public String toString() {
        return "TravelProfile{" +
                "travelPercent=" + travelPercent +
                ", noTravel=" + noTravel +
                '}';
    }
}