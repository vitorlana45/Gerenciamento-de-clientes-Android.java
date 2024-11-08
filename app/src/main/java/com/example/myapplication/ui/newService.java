package com.example.myapplication.ui;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
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
import com.example.myapplication.data.model.enums.Status;
import com.example.myapplication.data.repositories.ConsumerRepository;
import com.example.myapplication.service.ConsumerService.ConsumerService;
import com.example.myapplication.ui.Utils.ButtonAnimationUtil;
import com.example.myapplication.ui.Utils.UiUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class newService extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private String selectedImagePath;

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
        equipment = findViewById(R.id.textInputEditText3);
        telephone = findViewById(R.id.textInputEditText2);
        Button registerButton = findViewById(R.id.buttonCadastrar);
        ConsumerRepository consumerRepository = new ConsumerRepository(this);
        consumerService = new ConsumerService(consumerRepository);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button cancelButton = findViewById(R.id.buttonCancelar);

        ButtonAnimationUtil.setAnimation(
                cancelButton,
                R.anim.button,
                R.color.warning, // cor ao pressionar
                R.color.error  // cor normal
        );
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(newService.this, Home3Activity.class);
                startActivity(intent);
                finish();
            }
        });

        ButtonAnimationUtil.setAnimation(
                registerButton,
                R.anim.button,
                R.color.success, // cor ao pressionar
                R.color.background_color_btn  // cor normal
        );
        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                executor.execute(() -> {
                    try {
                        String strClientName = clientName.getText().toString();
                        String strEquipment = equipment.getText().toString();
                        String strTelephone = telephone.getText().toString();

                        boolean anyFieldIsEmpty = strClientName.isEmpty() || strEquipment.isEmpty() || strTelephone.isEmpty();
                        showErrorInField(anyFieldIsEmpty);


                       if (!strTelephone.matches("\\d+")) {
                            mainHandler.post(() -> {
                                setMessageErrorOnField(telephone,"Por favor, preencha o campo de telefone apenas com números");
                            });
                        }
                        else if (strTelephone.length() < 11) {
                            mainHandler.post(() -> {
                               setMessageErrorOnField(telephone, "Por favor, preencha o campo de telefone com 11 dígitos");
                            });
                        }
                        else {
                            consumerService.registerNewConsumer(strClientName, strEquipment, strTelephone, selectedImagePath);
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

        Button selectImageButton = findViewById(R.id.buttonSelectImage);
        ButtonAnimationUtil.setAnimation(
                selectImageButton,
                R.anim.button,
                R.color.success,
                R.color.background_color_btn  // cor normal
        );
        selectImageButton.setOnClickListener(v -> openImageSelector());
    }

    private void openImageSelector() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            selectedImagePath = getPathFromUri(selectedImageUri);
        }
    }

    private String getPathFromUri(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(projection[0]);
            String path = cursor.getString(columnIndex);
            cursor.close();
            return path;
        }
        return uri.getPath();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, Home3Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }

    protected void setMessageErrorOnField(EditText field, String message) {
        field.setError(message);
    }

    private void showErrorInField(boolean haveEmptyFields) {

        if(haveEmptyFields) {
            if (clientName.getText().toString().isEmpty()) {
                mainHandler.post(() -> {
                    setMessageErrorOnField(clientName, "Por favor, preencha o campo de nome");
                });
            }

            if (equipment.getText().toString().isEmpty()) {
                mainHandler.post(() -> {
                    setMessageErrorOnField(equipment, "Por favor, preencha o campo de equipamento");
                });
            }

            if (telephone.getText().toString().isEmpty()) {
                mainHandler.post(() -> {
                    setMessageErrorOnField(telephone, "Por favor, preencha o campo de telefone");
                });
            }
        }
    }
}