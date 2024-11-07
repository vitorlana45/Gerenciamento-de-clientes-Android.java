package com.example.myapplication.service.ConsumerService;

import android.util.Log;

import com.example.myapplication.data.model.entities.Consumer;
import com.example.myapplication.data.model.enums.Status;
import com.example.myapplication.data.repositories.ConsumerRepository;

import java.util.List;

public class ConsumerService {

    private final ConsumerRepository consumerRepository;

    public ConsumerService(ConsumerRepository consumerRepository) {
        this.consumerRepository = consumerRepository;
    }

    public void registerNewConsumer(String name, String equipment, String phone, String photoPath) {
        long dateMillis = System.currentTimeMillis();
        Log.d("InsertDebug", "Iniciando a inserção com dateMillis: " + dateMillis);
        long id = consumerRepository.insert(new Consumer(name, equipment, phone, dateMillis, photoPath, Status.Pendente));
        Log.d("InsertDebug", "ID retornado da inserção: " + id);
    }
}
