package com.example.myapplication.data.model.entities;

public class Service {
    private String clientName;
    private String equipment;
    private String telephone;

    public Service() {
    }

    public Service(String clientName, String equipment, String telephone) {
        this.clientName = clientName;
        this.equipment = equipment;
        this.telephone = telephone;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
