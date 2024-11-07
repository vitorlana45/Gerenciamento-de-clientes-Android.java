package com.example.myapplication.data.model.entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.myapplication.data.model.enums.Status;

@Entity(tableName = "consumer")
public class Consumer {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String equipment;
    private String contactNumber;
    private long createdAt;
    private long exitAt;
    private String photoPath;
    private Status status;

    public Consumer() { }

    @Ignore
    public Consumer(String name, String equipment, String contactNumber, long createdAt, String photoPath, Status status) {
        this.name = name;
        this.equipment = equipment;
        this.contactNumber = contactNumber;
        this.createdAt = createdAt;
        this.photoPath = photoPath;
        this.status = status;
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

    public long getCreatedAt() { return createdAt; }
    public void setCreatedAt(long dateInMillis) { this.createdAt = dateInMillis; }

    public long getExitAt() { return exitAt; }

    public void setExitAt(long exitAt) { this.exitAt = exitAt;}

    public String getPhotoPath() { return photoPath; }

    public void setPhotoPath(String photoPath) { this.photoPath = photoPath;}

    public Status getStatus() { return status; }

    public void setStatus(Status status) { this.status = status; }
}
