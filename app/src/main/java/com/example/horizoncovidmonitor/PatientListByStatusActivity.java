package com.example.horizoncovidmonitor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.horizoncovidmonitor.DAO.RegisterDAO;
import com.example.horizoncovidmonitor.model.Patient;

import java.util.List;

public class PatientListByStatusActivity extends AppCompatActivity {

    private Button buttonCovid, buttonQuarantine, buttonReleased;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list_by_status);

        Button buttonCovid = findViewById(R.id.buttonCovid);
        Button buttonQuarantine = findViewById(R.id.buttonQuarantine);
        Button buttonReleased = findViewById(R.id.buttonReleased);

        buttonCovid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPatientListActivity("Covid");
            }
        });
        buttonQuarantine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPatientListActivity("Quarentena");
            }
        });
        buttonReleased.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPatientListActivity("Liberado");
            }
        });
    }

    private void openPatientListActivity(String status) {
        RegisterDAO registerDAO = new RegisterDAO(this);
        List<Patient> patients = registerDAO.listByStatus(status);

        if (patients == null || patients.isEmpty()) {
            Toast.makeText(this, "Nenhum item na lista", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(PatientListByStatusActivity.this, PatientListActivity.class);
            intent.putExtra("status", status);
            startActivity(intent);
        }
    }
}