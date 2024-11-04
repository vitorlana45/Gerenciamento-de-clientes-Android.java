package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.service.authService.userAuthService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity {

    private EditText fieldName;
    private EditText fieldPassword;
    private userAuthService authService;

    // Executor para executar tarefas em segundo plano
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        authService = new userAuthService();

        // Inicializa os campos de entrada
        fieldName = findViewById(R.id.fieldName);
        fieldPassword = findViewById(R.id.fieldPassword);
        Button registerButton = findViewById(R.id.button);

        // Define o listener para o botão
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = fieldName.getText().toString().trim();
                String password = fieldPassword.getText().toString().trim();

                if (name.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
                } else {
                    // Inicia a verificação das credenciais em segundo plano
                    verifyCredentials(name, password);
                }
            }
        });
    }

    private void verifyCredentials(String name, String password) {
        executor.execute(() -> {
            // Executa a verificação das credenciais em segundo plano
            boolean isAuthenticated = authService.verifyUserCredentials(name, password);

            // Atualiza a UI na thread principal
            mainHandler.post(() -> {
                if (isAuthenticated) {
                    // Redireciona para a próxima Activity
                    System.out.println("Usuário autenticado!");
                    Intent intent = new Intent(LoginActivity.this, Home3Activity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Usuário ou senha incorretos!", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Fecha o executor quando a Activity for destruída para evitar vazamento de memória
        executor.shutdown();
    }
}
