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

        if (patient != null) {
            nameTextView.setText("Nome: " + patient.getName());
            ageTextView.setText("Idade: " + patient.getAge());
            temperatureTextView.setText("temperatura corporal: " + patient.getTemperature());
            coughTextView.setText("Período (em dias) com tosse: " + patient.getCoughingDays());
            headacheTextView.setText("Período (em dias) com dor de cabeça: " + patient.getHeadacheDays());
            countryTextView.setText("Pais visitado: " + patient.getVisitedCountry());
            countryWeekTextView.setText("Quantas semanas visitou o país: " + patient.getWeeksCountry());
            status.setText("Status: " + patient.getStatus());

            buttonUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Patient patientToUpdate = getItem(position);

                    Intent intent = new Intent(getContext(), RegisterActivity.class);
                    intent.putExtra("patientToUpdate", patientToUpdate);
                    getContext().startActivity(intent);
                }
            });

            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RegisterDAO registerDAO = new RegisterDAO(getContext().getApplicationContext());
                    Patient patientToDelete = getItem(position);
                    long patientId = patientToDelete.getId(); // Obtém o ID do paciente

                    try {
                        boolean deleted = registerDAO.delete(patientId);
                        if (deleted) {
                            Toast.makeText(PatientListAdapter.this.getContext(), "Item excluído com sucesso!", Toast.LENGTH_SHORT).show();
                            remove(patientToDelete);
                            notifyDataSetChanged();
                        }
                    } catch (Exception e) {
                        Log.i("Error", "Erro ao excluir o item: " + e.getMessage());
                        Toast.makeText(PatientListAdapter.this.getContext(), "Erro ao excluir o item", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            });

        }

        return convertView;
    }

    private String formatValue(int value) {
        return value == 0 ? "ITEM NÃO INFORMADO" : String.valueOf(value);
    }

    private static class ViewHolder {
        TextView nameTextView;
        TextView ageTextView;
        TextView temperatureTextView;
    }

}
