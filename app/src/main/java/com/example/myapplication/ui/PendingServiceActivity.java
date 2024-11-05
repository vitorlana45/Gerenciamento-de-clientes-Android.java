package com.example.myapplication.ui;

import android.os.Bundle;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.data.adpter.ConsumerAdapter;
import com.example.myapplication.data.model.entities.Consumer;
import com.example.myapplication.databinding.ActivityPendingServiceBinding;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;

public class PendingServiceActivity extends AppCompatActivity {

    private ActivityPendingServiceBinding binding;
    private ConsumerAdapter consumerAdapter;
    private final ArrayList<Consumer> consumerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPendingServiceBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            RecyclerView recyclerViewService = binding.recyclerView;
            recyclerViewService.setLayoutManager(new LinearLayoutManager(this));
            recyclerViewService.setHasFixedSize(true);
            consumerAdapter = new ConsumerAdapter(consumerList, this);
            recyclerViewService.setAdapter(consumerAdapter);
            getServices();

            return insets;
        });
    }

    private void getServices() {
        long dateInMillis = Instant.now().toEpochMilli();
        Consumer consumer1 = new Consumer("João", "Computador", "999999999", dateInMillis, "asd");
        Consumer consumer2 = new Consumer("Maria", "Notebook", "999999999",dateInMillis, "asdasd");
        Consumer consumer3 = new Consumer("José", "Tablet", "999999999", dateInMillis,"vbc");
        Consumer consumer4 = new Consumer("Ana", "Celular", "999999999", dateInMillis, "asdasd");
        Consumer consumer5 = new Consumer("Pedro", "Impressora", "999999999",dateInMillis, "asdasd");
        consumerList.add(consumer1);
        consumerList.add(consumer2);
        consumerList.add(consumer3);
        consumerList.add(consumer4);
        consumerList.add(consumer5);
    }

}