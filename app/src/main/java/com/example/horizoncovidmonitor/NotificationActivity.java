package com.example.horizoncovidmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class NotificationActivity extends AppCompatActivity {

    private Button buttonNewRegister, buttonInitialView;
    private TextView diagnosis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        buttonNewRegister = findViewById(R.id.buttonNewRegister);
        buttonInitialView = findViewById(R.id.buttonInitialView);

        diagnosis = findViewById(R.id.diagnosis);

        Intent intent = getIntent();
        String valorRecebido = intent.getStringExtra("result");
        diagnosis.setText(valorRecebido);

        buttonNewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotificationActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });

        buttonInitialView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotificationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
