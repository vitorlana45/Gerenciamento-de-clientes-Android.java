package com.example.myapplication.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.data.adpter.ConsumerAdapter;
import com.example.myapplication.data.model.entities.Consumer;
import com.example.myapplication.data.repositories.ConsumerRepository;
import com.example.myapplication.databinding.ActivityPendingServiceBinding;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PendingServiceActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_READ_EXTERNAL_STORAGE = 101;
    private ActivityPendingServiceBinding binding;
    private final ArrayList<Consumer> consumerList = new ArrayList<>();
    private ConsumerRepository consumerRepository;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler();
    private ConsumerAdapter consumerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPendingServiceBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        applyInsets();

        consumerRepository = new ConsumerRepository(this);

        initializeRecyclerView();

        // Checando a permissão para ler o armazenamento externo antes de carregar os dados
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_READ_EXTERNAL_STORAGE);
        } else {
            loadData();
        }
    }

    private void applyInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initializeRecyclerView() {
        RecyclerView recyclerViewService = binding.recyclerView;
        recyclerViewService.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewService.setHasFixedSize(true);
        consumerAdapter = new ConsumerAdapter(consumerList, this);
        recyclerViewService.setAdapter(consumerAdapter);
    }

    private void loadData() {
        // Carrega os dados em uma thread de fundo para evitar bloqueio da interface do usuário
        executorService.execute(() -> {
            try {
                consumerList.addAll(consumerRepository.getAllConsumers());
                // Update the adapter on the main thread
                mainHandler.post(consumerAdapter::notifyDataSetChanged);
            } catch (Exception e) {
                Log.e("PendingServiceActivity", "Error getting services", e);
                mainHandler.post(() -> Toast.makeText(PendingServiceActivity.this, "Error getting services", Toast.LENGTH_SHORT).show());
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_READ_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadData();
            } else {
                Toast.makeText(this, "Permission denied. Unable to load images.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }

    @Override
    public void onBackPressed() {
        // Chama o método da superclasse para garantir que o comportamento padrão de pressionar o botão voltar seja executado.
        super.onBackPressed();

        // criando uma nova intenção para chamar a Home3Activity
        Intent intent = new Intent(PendingServiceActivity.this, Home3Activity.class);

        // Adiciona flags ao Intent:
        // FLAG_ACTIVITY_CLEAR_TOP: Se a Home3Activity já estiver na pilha de atividades,
        // limpa todas as atividades acima dela.
        // FLAG_ACTIVITY_SINGLE_TOP: Se a Home3Activity já estiver no topo da pilha,
        // não cria uma nova instância, mas chama onNewIntent() se existente.
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        // Inicia a Home3Activity com o Intent criado.
        startActivity(intent);

        // Finaliza a PendingServiceActivity, removendo-a da pilha de atividades.
        finish(); // Finaliza a atividade atual
    }


}
