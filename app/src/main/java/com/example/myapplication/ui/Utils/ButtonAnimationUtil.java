package com.example.myapplication.ui.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.core.content.ContextCompat;

public class ButtonAnimationUtil {

    // Método para aplicar animação e mudar a cor do botão
    @SuppressLint("ClickableViewAccessibility")
    public static void setAnimation(final Button button, final int animationResource, int colorPressed, int colorNormal) {
        final Animation scaleAnimation = AnimationUtils.loadAnimation(button.getContext(), animationResource);

        // Cria o ColorStateList com as cores definidas
        ColorStateList colorStateList = createColorStateList(button.getContext(), colorPressed, colorNormal);
        button.setBackgroundTintList(colorStateList); // Aplica o ColorStateList ao botão

        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.startAnimation(scaleAnimation);
                        button.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(button.getContext(), colorPressed))); // Altera a cor ao pressionar
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        v.clearAnimation();
                        button.setBackgroundTintList(colorStateList); // Restaura a cor normal
                        break;
                }
                return false;
            }
        });
    }

    // Método para criar ColorStateList para animações de botão
    public static ColorStateList createColorStateList(Context context, int colorPressed, int colorNormal) {
        return new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_pressed}, // estado pressionado
                        new int[]{}, // estado normal
                },
                new int[]{
                        ContextCompat.getColor(context, colorPressed), // cor ao pressionar
                        ContextCompat.getColor(context, colorNormal), // cor normal
                }
        );
    }
}