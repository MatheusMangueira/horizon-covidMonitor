package com.example.horizoncovidmonitor.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.horizoncovidmonitor.DAO.RegisterDAO;
import com.example.horizoncovidmonitor.R;
import com.example.horizoncovidmonitor.RegisterActivity;
import com.example.horizoncovidmonitor.model.Patient;

import java.util.List;

public class PatientListAdapter extends ArrayAdapter<Patient> {
    private LayoutInflater inflater;
    private Button buttonUpdate, buttonDelete;
    private TextView nameTextView, ageTextView, temperatureTextView, coughTextView, headacheTextView, countryTextView, countryWeekTextView, status;

    public PatientListAdapter(Context context, List<Patient> patients) {
        super(context, 0, patients);
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_patient_list_item, parent, false);
        }

        Patient patient = getItem(position);
        initializeViews(convertView);

        if (patient != null) {
            bindDataToViews(patient);
            setUpdateButtonClickListener(patient);
            setDeleteButtonClickListener(patient);
        }

        return convertView;

    }

    private void initializeViews(View convertView) {
        nameTextView = convertView.findViewById(R.id.nameTextView);
        ageTextView = convertView.findViewById(R.id.ageTextView);
        temperatureTextView = convertView.findViewById(R.id.temperatureTextView);
        coughTextView = convertView.findViewById(R.id.coughTextView);
        headacheTextView = convertView.findViewById(R.id.headacheTextView);
        countryTextView = convertView.findViewById(R.id.countryTextView);
        countryWeekTextView = convertView.findViewById(R.id.countryWeekTextView);
        status = convertView.findViewById(R.id.status);
        buttonUpdate = convertView.findViewById(R.id.buttonUpdate);
        buttonDelete = convertView.findViewById(R.id.buttonDelete);
    }

    private void bindDataToViews(Patient patient) {
        nameTextView.setText("Nome: " + patient.getName());
        ageTextView.setText("Idade: " + patient.getAge());
        temperatureTextView.setText("Temperatura corporal: " + patient.getTemperature());
        coughTextView.setText("Período (em dias) com tosse: " + patient.getCoughingDays());
        headacheTextView.setText("Período (em dias) com dor de cabeça: " + patient.getHeadacheDays());
        countryTextView.setText("País visitado: " + patient.getVisitedCountry());
        countryWeekTextView.setText("Quantas semanas visitou o país: " + patient.getWeeksCountry());
        status.setText("Status: " + patient.getStatus());
    }

    private void setUpdateButtonClickListener(final Patient patient) {
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RegisterActivity.class);
                intent.putExtra("patientToUpdate", patient);
                intent.putExtra("UpMessage", "Atualizar Paciente");
                getContext().startActivity(intent);
            }
        });
    }

    private void setDeleteButtonClickListener(final Patient patient) {
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterDAO registerDAO = new RegisterDAO(getContext().getApplicationContext());
                long patientId = patient.getId(); // Obtém o ID do paciente

                try {
                    boolean deleted = registerDAO.delete(patientId);
                    if (deleted) {
                        Toast.makeText(getContext(), "Item excluído com sucesso!", Toast.LENGTH_SHORT).show();
                        remove(patient);
                        notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    Log.i("Error", "Erro ao excluir o item: " + e.getMessage());
                    Toast.makeText(getContext(), "Erro ao excluir o item", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }

}
