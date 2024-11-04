package com.example.myapplication.data.model.entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "consumer")
public class Consumer {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String equipment;
    private String contactNumber;
    private long dateInMillis;

    public Consumer() { }

    @Ignore
    public Consumer(String name, String equipment, String contactNumber, long dateInMillis) {
        this.name = name;
        this.equipment = equipment;
        this.contactNumber = contactNumber;
        this.dateInMillis = dateInMillis;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEquipment() { return equipment; }
    public void setEquipment(String equipment) { this.equipment = equipment; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    public long getDateInMillis() { return dateInMillis; }
    public void setDateInMillis(long dateInMillis) { this.dateInMillis = dateInMillis; }
}
