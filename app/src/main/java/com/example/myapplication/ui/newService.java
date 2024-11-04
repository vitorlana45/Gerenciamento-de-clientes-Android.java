package com.example.myapplication.ui;

import android.content.Intent;
import android.net.wifi.hotspot2.pps.HomeSp;
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
import com.example.myapplication.data.repositories.ConsumerRepository;
import com.example.myapplication.service.ConsumerService.ConsumerService;
import com.example.myapplication.ui.Utils.ButtonAnimationUtil;
import com.example.myapplication.ui.Utils.UiUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class newService extends AppCompatActivity {

    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    private EditText clientName;
    private EditText equipment;
    private EditText telephone;

    private ConsumerService consumerService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_service);

        clientName = findViewById(R.id.textInputEditText);
        equipment = findViewById(R.id.textInputEditText2);
        telephone = findViewById(R.id.textInputEditText3);
        Button registerButton = findViewById(R.id.buttonCadastrar);
        ConsumerRepository consumerRepository = new ConsumerRepository(this);
        consumerService = new ConsumerService(consumerRepository);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ButtonAnimationUtil.setAnimation(
                registerButton,
                R.anim.button,
                R.color.success, // cor ao pressionar
                R.color.background_color_btn  // cor normal
        );


        Button cancelButton = findViewById(R.id.buttonCancelar);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(newService.this, Home3Activity.class);
                startActivity(intent);
                finish();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executor.execute(() -> {
                    try {
                        String strClientName = clientName.getText().toString();
                        String strEquipment = equipment.getText().toString();
                        String strTelephone = telephone.getText().toString();

                        boolean anyFieldIsEmpty = strClientName.isEmpty() || strEquipment.isEmpty() || strTelephone.isEmpty();

                        if (anyFieldIsEmpty) {
                            mainHandler.post(() -> {
                                Toast.makeText(newService.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
                            });
                        } else {
                            consumerService.registerNewConsumer(strClientName, strEquipment, strTelephone);
                            mainHandler.post(() -> {
                                UiUtils.clearFields(clientName, equipment, telephone);
                                Toast.makeText(newService.this, "Serviço cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
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