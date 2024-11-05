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
    private String photoPath;
    public Consumer() { }

    @Ignore
    public Consumer(String name, String equipment, String contactNumber, long dateInMillis, String photoPath) {
        this.name = name;
        this.equipment = equipment;
        this.contactNumber = contactNumber;
        this.dateInMillis = dateInMillis;
        this.photoPath = photoPath;
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

    public String getPhotoPath() { return photoPath; }

    public void setPhotoPath(String photoPath) {this.photoPath = photoPath;}
}
