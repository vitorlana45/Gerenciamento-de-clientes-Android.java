package com.example.myapplication.data.model;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.myapplication.data.model.entities.Consumer;

import java.util.List;

@Dao
public interface ConsumerDao {

    @Insert
    long insert(Consumer consumer);

    @Update
    void update(Consumer consumer);

    @Delete
    void delete(Consumer consumer);

    @Query("SELECT * FROM consumer")
    List<Consumer> getAllConsumers();

    @Query("SELECT * FROM consumer WHERE id = :consumerId")
    Consumer getConsumerById(int consumerId);

    @Query("SELECT * FROM consumer WHERE name = :name LIMIT 1")
    Consumer getConsumerByName(String name);

}
