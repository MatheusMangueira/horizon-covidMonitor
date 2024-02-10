package com.example.horizoncovidmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.horizoncovidmonitor.DAO.RegisterDAO;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button buttonRegister, buttonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonRegister = findViewById(R.id.buttonRegister);
        buttonList = findViewById(R.id.buttonList);

        ImageSlider imageSlider = findViewById(R.id.slider);

        List<SlideModel> imageList = new ArrayList<>();


        imageList.add(new SlideModel(R.drawable.clinica, "Clinica", ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.foto_principal, "Covid 19", ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.saude, "Saúde", ScaleTypes.FIT));


        imageSlider.setImageList(imageList);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        buttonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PatientListByStatusActivity.class);
                startActivity(intent);
            }
        });

        //RegisterDAO registerDAO = new RegisterDAO(getApplicationContext());
        //registerDAO.clearData();

    }
}