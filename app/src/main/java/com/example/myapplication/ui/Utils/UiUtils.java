package com.example.myapplication.ui.Utils;

import android.widget.EditText;

public class UiUtils {

    public static void clearFields(EditText... fields) {
        for (EditText field : fields) {
            field.setText("");
        }
    }

}
