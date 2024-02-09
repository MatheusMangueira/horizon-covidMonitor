package com.example.horizoncovidmonitor.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.horizoncovidmonitor.R;
import com.example.horizoncovidmonitor.model.Patient;

import java.util.List;

public class PatientListAdapter extends ArrayAdapter<Patient> {
    private LayoutInflater inflater;

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

        if (patient != null) {
            ((TextView) convertView.findViewById(R.id.nameTextView)).setText("Nome: " + patient.getName());
            ((TextView) convertView.findViewById(R.id.ageTextView)).setText("Idade: " + patient.getAge());
            ((TextView) convertView.findViewById(R.id.temperatureTextView)).setText("temperatura corporal: " + patient.getTemperature());
            ((TextView) convertView.findViewById(R.id.coughTextView)).setText("período (em dias) com tosse: " + patient.getCoughingDays());
            ((TextView) convertView.findViewById(R.id.headacheTextView)).setText("período (em dias) com dor de cabeça: " + patient.getHeadacheDays());
            ((TextView) convertView.findViewById(R.id.countryTextView)).setText("Pais visitado: " + patient.getVisitedCountry());
            ((TextView) convertView.findViewById(R.id.countryWeekTextView)).setText("quantas semanas visitou o Pais: " + patient.getWeeksCountry());
        }

        return convertView;
    }

    private static class ViewHolder {
        TextView nameTextView;
        TextView ageTextView;
        TextView temperatureTextView;
    }

}
