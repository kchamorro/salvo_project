package com.webcode.kc.salvo.model;

public class ShipLocation {
    private Ship shipId;
    private String cell;

    //Empty Constructor
    public ShipLocation() {
    }

    //Constructor with parameters
    public ShipLocation(Ship shipId, String cell) {
        this.shipId = shipId;
        this.cell = cell;
    }

    //Getters and Setters
    public Ship getShipId() {
        return shipId;
    }

    public void setShipId(Ship shipId) {
        this.shipId = shipId;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    //toString Method
    @Override
    public String toString() {
        return "ShipLocations{" +
                "shipId=" + shipId +
                ", cell='" + cell + '\'' +
                '}';
    }
}

