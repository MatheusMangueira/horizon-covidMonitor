package com.example.horizoncovidmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.horizoncovidmonitor.service.RegisterService;

public class RegisterActivity extends AppCompatActivity {

    private EditText registerName, age, registerTemperature, registerDay, registerHeadache, registerWeek;
    private RadioGroup registerRadioCoughing, registerRadioHeadache, registerRadioCountry;
    private RadioButton yesCoughing, noCoughing, yesHeadache, noHeadache, italiaRadio, indonesiaRadio, portugalRadio, euaRadio, noVisited;
    private boolean validateData;
    private Button registerButton, goBackInitial;
    private LinearLayout componentCoughing, componentHeadache, componentWeek;

    private RegisterService registerService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Find views
        registerName = findViewById(R.id.registerName);
        age = findViewById(R.id.age);
        registerTemperature = findViewById(R.id.registerTemperature);
        registerDay = findViewById(R.id.registerDay);
        registerHeadache = findViewById(R.id.registerHeadache);
        registerWeek = findViewById(R.id.registerWeek);
        registerButton = findViewById(R.id.registerButton);
        yesCoughing = findViewById(R.id.yesCoughing);
        yesHeadache = findViewById(R.id.yesHeadache);
        noVisited = findViewById(R.id.noVisited);
        componentCoughing = findViewById(R.id.componentCoughing);
        componentHeadache = findViewById(R.id.componentHeadache);
        componentWeek = findViewById(R.id.componentWeek);
        registerRadioCoughing = findViewById(R.id.registerRadioCoughing);
        registerRadioHeadache = findViewById(R.id.registerRadioHeadache);
        registerRadioCountry = findViewById(R.id.registerRadioCountry);
        goBackInitial = findViewById(R.id.goBackInitial);

        // Set listeners
        setRadioListeners();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData = formValidate();

                if (validateData) {
                    int newAge = parseEditTextToInt(age);
                    int newTemperature = parseEditTextToInt(registerTemperature);
                    int newRegisterHeadache = parseEditTextToInt(registerHeadache);
                    int newRegisterDay = parseEditTextToInt(registerDay);
                    int newPaisVisitado = parseEditTextToInt(registerWeek);

                    registerService = new RegisterService(newRegisterDay, newPaisVisitado, newRegisterHeadache, newTemperature, newAge);

                    String hospitalized = registerService.inpatient();

                    Intent intent = new Intent(RegisterActivity.this, NotificationActivity.class);
                    intent.putExtra("result", hospitalized);
                    startActivity(intent);
                }
            }
        });

        goBackInitial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setRadioListeners() {
        registerRadioHeadache.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.yesHeadache) {
                componentHeadache.setVisibility(View.VISIBLE);
            } else {
                componentHeadache.setVisibility(View.GONE);
                registerHeadache.setText("");
            }
        });

        registerRadioCoughing.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.yesCoughing) {
                componentCoughing.setVisibility(View.VISIBLE);
            } else {
                componentCoughing.setVisibility(View.GONE);
                registerDay.setText("");
            }
        });

        registerRadioCountry.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.noVisited) {
                componentWeek.setVisibility(View.GONE);
                registerWeek.setText("");
            } else {
                componentWeek.setVisibility(View.VISIBLE);
            }
        });
    }

    private int parseEditTextToInt(EditText editText) {
        return TextUtils.isEmpty(editText.getText().toString()) ? 0 : Integer.parseInt(editText.getText().toString());
    }

    private boolean formValidate() {
        boolean isValid = true;

        if (TextUtils.isEmpty(registerName.getText().toString())) {
            registerName.setError("Campo Obrigatorio!");
            registerName.requestFocus();
            isValid = false;
        }

        if (TextUtils.isEmpty(age.getText().toString())) {
            age.setError("Campo Obrigatorio!");
            age.requestFocus();
            isValid = false;
        }

        if (TextUtils.isEmpty(registerTemperature.getText().toString())) {
            registerTemperature.setError("Campo Obrigatorio!");
            registerTemperature.requestFocus();
            isValid = false;
        }

        return isValid;
    }

}



