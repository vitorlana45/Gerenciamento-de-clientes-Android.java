package com.example.myapplication.service.ConsumerService;

import android.util.Log;

import com.example.myapplication.data.model.entities.Consumer;
import com.example.myapplication.data.repositories.ConsumerRepository;

public class ConsumerService {

    private final ConsumerRepository consumerRepository;

    public ConsumerService(ConsumerRepository consumerRepository) {
        this.consumerRepository = consumerRepository;
    }

    public void registerNewConsumer(String name, String equipment, String phone) {
        long dateMillis = System.currentTimeMillis();
        Log.d("InsertDebug", "Iniciando a inserção com dateMillis: " + dateMillis);
        long id = consumerRepository.insert(new Consumer(name, equipment, phone, dateMillis, "/storage/emulated/0/Pictures/1629780000000.jpg"));
        Log.d("InsertDebug", "ID retornado da inserção: " + id);
    }
}
