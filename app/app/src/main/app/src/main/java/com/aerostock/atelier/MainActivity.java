package com.aerostock.atelier;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.widget.TextView;
import android.view.Gravity;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView text = new TextView(this);
        text.setText("AeroStock Atelier\nApplication ouverte avec succès");
        text.setTextSize(24);
        text.setTextColor(Color.BLACK);
        text.setGravity(Gravity.CENTER);
        text.setBackgroundColor(Color.rgb(135, 206, 235));

        setContentView(text);
    }
}
