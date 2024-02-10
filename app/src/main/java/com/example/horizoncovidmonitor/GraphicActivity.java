package com.example.horizoncovidmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.horizoncovidmonitor.DAO.RegisterDAO;
import com.example.horizoncovidmonitor.model.Patient;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GraphicActivity extends AppCompatActivity {

    private PieChart pieChart;
    private TextView messageTextView;
    private Button graphicBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic);

        pieChart = findViewById(R.id.pieChart);
        messageTextView = findViewById(R.id.messageTextView);
        graphicBackButton = findViewById(R.id.graphicBackButton);

        graphicBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GraphicActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        try {
            int[] numPatients = getNumberOfPatientsByStatus();
            boolean hasSufficientData = checkSufficientData(numPatients);

            if (!hasSufficientData) {
                pieChart.setVisibility(View.GONE);
                messageTextView.setVisibility(View.VISIBLE);
            } else {
                messageTextView.setVisibility(View.GONE);
                pieChart.setVisibility(View.VISIBLE);
                displayPieChart(numPatients);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(GraphicActivity.this, "Erro ao acessar o banco de dados", Toast.LENGTH_SHORT).show();
        }

    }

    private int[] getNumberOfPatientsByStatus() throws SQLException {
        RegisterDAO registerDAO = new RegisterDAO(getApplicationContext());
        int[] numPatients = new int[3];
        String[] status = {"Covid", "Quarentena", "Liberado"};

        for (int i = 0; i < status.length; i++) {
            List<Patient> patients = registerDAO.listByStatus(status[i]);
            numPatients[i] = patients.size();
        }
        return numPatients;
    }

    private boolean checkSufficientData(int[] numPatients) {
        for (int numPatient : numPatients) {
            if (numPatient > 0) {
                return true;
            }
        }
        return false;
    }

    private void displayPieChart(int[] numPatients) {
        ArrayList<PieEntry> status = new ArrayList<>();
        status.add(new PieEntry(numPatients[0], "Covid"));
        status.add(new PieEntry(numPatients[1], "Quarentena"));
        status.add(new PieEntry(numPatients[2], "Liberado"));

        PieDataSet pieDataSet = new PieDataSet(status, "Status");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.animate();
    }

}