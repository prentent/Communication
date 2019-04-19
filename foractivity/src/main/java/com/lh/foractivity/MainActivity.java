package com.lh.foractivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String stringExtra = getIntent().getStringExtra("name");
        TextView text = findViewById(R.id.text);
        text.setText(stringExtra == null ? "" : stringExtra);
    }
}
