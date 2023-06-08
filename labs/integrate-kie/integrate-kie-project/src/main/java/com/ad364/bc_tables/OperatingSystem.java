package com.ad364.bc_tables;

public class OperatingSystem implements java.io.Serializable {

    static final long serialVersionUID = 1L;

    @org.kie.api.definition.type.Label(value = "Operating System")
    private java.lang.String osName;

    public OperatingSystem() {
    }

    public java.lang.String getOsName() {
        return this.osName;
    }

    public void setOsName(java.lang.String osName) {
        this.osName = osName;
    }

    public OperatingSystem(java.lang.String osName) {
        this.osName = osName;
    }

    @Override
    public String toString() {
        return "OperatingSystem{" +
                "osName='" + osName + '\'' +
                '}';
    }
}