package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.data.model.entities.Consumer;
import com.example.myapplication.data.repositories.ConsumerRepository;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class newService extends AppCompatActivity {

    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    private EditText textInputEditText;
    private EditText textInputEditText2;
    private EditText textInputEditText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_service);

        textInputEditText = findViewById(R.id.textInputEditText);
        textInputEditText2 = findViewById(R.id.textInputEditText2);
        textInputEditText3 = findViewById(R.id.textInputEditText3);
        Button buttonCadastrar = findViewById(R.id.buttonCadastrar);

        ConsumerRepository consumerRepository = new ConsumerRepository(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button buttonCancelar = findViewById(R.id.buttonCancelar);
        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(newService.this, Home3Activity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executor.execute(() -> {
                    try {
                        String nomeCliente = textInputEditText.getText().toString();
                        String equipamento = textInputEditText3.getText().toString();
                        String telefoneCliente = textInputEditText2.getText().toString();

                        boolean anyFieldIsEmpty = nomeCliente.isEmpty() || equipamento.isEmpty() || telefoneCliente.isEmpty();

                        if (anyFieldIsEmpty) {
                            mainHandler.post(() -> {
                                Toast.makeText(newService.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
                            });
                        } else {
                            long dateMillis = System.currentTimeMillis(); // Obtenha a data atual em milissegundos
                            Log.d("InsertDebug", "Iniciando a inserção com dateMillis: " + dateMillis);
                            long id = consumerRepository.insert(new Consumer(nomeCliente, equipamento, telefoneCliente, dateMillis));
                            Log.d("InsertDebug", "ID retornado da inserção: " + id);
                            mainHandler.post(() -> {
                                Toast.makeText(newService.this, "Serviço cadastrado com sucesso! ID: " + id, Toast.LENGTH_SHORT).show();
                            });
                        }
                    } catch (Exception e) {
                        Log.e("InsertDebug", "Erro na inserção", e);
                        mainHandler.post(() -> {
                            Toast.makeText(newService.this, "Erro na inserção", Toast.LENGTH_SHORT).show();
                        });
                    }
                });
            }
        });
    }
}