package com.example.horizoncovidmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText registerName, age, registerTemperature, registerDay, registerHeadache, registerWeek;
    private RadioGroup registerRadioCoughing, registerRadioHeadache, registerRadioCountry;
    private RadioButton yesCoughing, noCoughing, yesHeadache, noHeadache, italiaRadio, indonesiaRadio, portugalRadio, euaRadio, noVisited;
    private TextView testando;
    private boolean validateData;
    private Button registerButton;
    private LinearLayout componentCoughing, componentHeadache, componentWeek;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        //EditText
        registerName = findViewById(R.id.registerName);
        age = findViewById(R.id.age);
        registerTemperature = findViewById(R.id.registerTemperature);
        registerDay = findViewById(R.id.registerDay);
        registerHeadache = findViewById(R.id.registerHeadache);
        registerWeek = findViewById(R.id.registerWeek);

        //button
        registerButton = findViewById(R.id.registerButton);

        //radio button
        yesCoughing = findViewById(R.id.yesCoughing);
        yesHeadache = findViewById(R.id.yesHeadache);
        noVisited = findViewById(R.id.noVisited);

        //Layout
        LinearLayout componentCoughing = findViewById(R.id.componentCoughing);
        LinearLayout componentHeadache = findViewById(R.id.componentHeadache);
        LinearLayout componentWeek = findViewById(R.id.componentWeek);

        // RadioGroup
        registerRadioCoughing = findViewById(R.id.registerRadioCoughing);
        registerRadioHeadache = findViewById(R.id.registerRadioHeadache);
        registerRadioCountry = findViewById(R.id.registerRadioCountry);

        registerRadioHeadache.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.yesHeadache) {
                    componentHeadache.setVisibility(View.VISIBLE);
                } else {
                    componentHeadache.setVisibility(View.GONE);
                    registerHeadache.setText("");
                }
            }
        });

        registerRadioCoughing.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.yesCoughing) {
                    componentCoughing.setVisibility(View.VISIBLE);
                } else {
                    componentCoughing.setVisibility(View.GONE);
                    registerDay.setText("");
                }
            }
        });


        registerRadioCountry.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.noVisited) {
                    componentWeek.setVisibility(View.GONE);
                    registerWeek.setText("");
                } else {
                    componentWeek.setVisibility(View.VISIBLE);

                }
            }
        });


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData = formValidate();

                if (validateData) {
                   //codigo aqui.
                }
            }
        });
    }

    public boolean formValidate() {
        boolean isNull = false;

        if (!TextUtils.isEmpty(registerName.getText().toString())) {
            isNull = true;
        } else {
            registerName.setError("Campo Obrigatorio!");
            registerName.requestFocus();
        }

        if (!TextUtils.isEmpty(age.getText().toString())) {
            isNull = true;
        } else {
            age.setError("Campo Obrigatorio!");
            age.requestFocus();
        }

        if (!TextUtils.isEmpty(registerTemperature.getText().toString())) {
            isNull = true;
        } else {
            registerTemperature.setError("Campo Obrigatorio!");
            registerTemperature.requestFocus();
        }

        return isNull;
    }

}



