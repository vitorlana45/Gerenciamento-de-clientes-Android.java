package com.example.myapplication.ui.Utils;

import android.widget.EditText;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class UiUtils {

    public static void clearFields(EditText... fields) {
        for (EditText field : fields) {
            field.setText("");
        }
    }

    public static String convertDate(Long dateInMillis) {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.getDefault());
        Date date = new Date(dateInMillis);
        return dateFormat.format(date);
    }
}
