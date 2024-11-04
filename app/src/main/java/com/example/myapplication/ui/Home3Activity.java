package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.example.myapplication.ui.Utils.ButtonAnimationUtil; // Importe a classe de animação

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Home3Activity extends AppCompatActivity {

    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home3);

        Button createNewServiceButton = findViewById(R.id.btn_cadastrar_servico);

        ButtonAnimationUtil.setAnimation(
                createNewServiceButton,
                R.anim.button,
                R.color.success,
                R.color.background_color_btn
        );

        createNewServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Home3Activity.this, newService.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    Log.e("Home3Activity", "Error starting newService", e);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Fecha o executor quando a Activity for destruída para evitar vazamento de memória
        executor.shutdown();
    }
}
