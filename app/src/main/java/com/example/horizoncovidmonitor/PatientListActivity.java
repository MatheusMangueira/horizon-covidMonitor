package com.example.horizoncovidmonitor;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.horizoncovidmonitor.DAO.RegisterDAO;
import com.example.horizoncovidmonitor.model.Patient;
import com.example.horizoncovidmonitor.utils.PatientListAdapter;

import java.util.List;

public class PatientListActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);

        listView = findViewById(R.id.listView);

        RegisterDAO registerDAO = new RegisterDAO(this);

        List<Patient> patients = registerDAO.list();

        PatientListAdapter adapter = new PatientListAdapter(this, patients);

        listView.setAdapter(adapter);
    }
}