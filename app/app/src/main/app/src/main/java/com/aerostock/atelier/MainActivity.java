package com.aerostock.atelier;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

public class MainActivity extends Activity {

@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);

TextView tv = new TextView(this);
tv.setText("AeroStock Atelier fonctionne");
tv.setTextSize(26);
tv.setGravity(Gravity.CENTER);
tv.setTextColor(Color.BLACK);
tv.setBackgroundColor(Color.rgb(135,206,235));

setContentView(tv);

}

}
