package com.example.horizoncovidmonitor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.horizoncovidmonitor.DAO.RegisterDAO;
import com.example.horizoncovidmonitor.model.Patient;
import com.example.horizoncovidmonitor.utils.PatientListAdapter;

import java.util.List;

public class PatientListActivity extends AppCompatActivity {
    private ListView listView;
    private Button backButton;
    private TextView titleStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);

        listView = findViewById(R.id.listView);
        backButton = findViewById(R.id.backButton);
        titleStatus = findViewById(R.id.titleStatus);

        String status = getIntent().getStringExtra("status");

        titleStatus.setText("Lista de: " + status);

        RegisterDAO registerDAO = new RegisterDAO(this);

        List<Patient> patients = registerDAO.listByStatus(status);

        PatientListAdapter adapter = new PatientListAdapter(this, patients);

        listView.setAdapter(adapter);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientListActivity.this, PatientListByStatusActivity.class);
                startActivity(intent);
            }
        });

    }

}