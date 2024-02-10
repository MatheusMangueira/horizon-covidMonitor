package com.example.horizoncovidmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.horizoncovidmonitor.DAO.RegisterDAO;
import com.example.horizoncovidmonitor.model.Patient;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

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

        RegisterDAO registerDAO = new RegisterDAO(getApplicationContext());

        int[] numPatients = new int[3];
        String[] status = {"Covid", "Quarentena", "Liberado"};

        for (int i = 0; i < status.length; i++) {
            List<Patient> patients = registerDAO.listByStatus(status[i]);
            numPatients[i] = patients.size();
        }

        boolean hasSufficientData = false;
        for (int i = 0; i < numPatients.length; i++) {
            if (numPatients[i] > 0) {
                hasSufficientData = true;
                break;
            }
        }

        if (!hasSufficientData) {
            pieChart.setVisibility(View.GONE);
            messageTextView.setVisibility(View.VISIBLE);
        } else {
            messageTextView.setVisibility(View.GONE);
            pieChart.setVisibility(View.VISIBLE);
        }

        Log.i("teste", "teste " + numPatients[0] + " " + numPatients[1] + " " + numPatients[2]);

        ArrayList<PieEntry> visitors = new ArrayList<>();
        visitors.add(new PieEntry(numPatients[0], "Covid"));
        visitors.add(new PieEntry(numPatients[1], "Quarentena"));
        visitors.add(new PieEntry(numPatients[2], "Liberado"));

        PieDataSet pieDataSet = new PieDataSet(visitors, "Visitors");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.animate();

    }


}