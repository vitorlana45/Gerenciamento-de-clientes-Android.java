package com.example.myapplication.data.repositories;

import android.content.Context;

import com.example.myapplication.data.AppDatabase;
import com.example.myapplication.data.model.ConsumerDao;
import com.example.myapplication.data.model.entities.Consumer;

import java.util.List;

public class ConsumerRepository {

    private final ConsumerDao consumerDao;

    public ConsumerRepository(Context context) {
        AppDatabase database = AppDatabase.getInstance(context);
        consumerDao = database.consumerDao();
    }

    public long insert(Consumer consumer) {
        return consumerDao.insert(consumer);
    }

    public void update(Consumer consumer) {
        consumerDao.update(consumer);
    }

    public void delete(Consumer consumer) {
        consumerDao.delete(consumer);
    }

    public List<Consumer> getAllConsumers() {
        return consumerDao.getAllConsumers();
    }

    public Consumer getConsumerById(int consumerId) {
        return consumerDao.getConsumerById(consumerId);
    }

    public Consumer getConsumerByName(String name) {
        return consumerDao.getConsumerByName(name);
    }
}