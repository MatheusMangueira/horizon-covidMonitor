package com.example.horizoncovidmonitor.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.horizoncovidmonitor.interfaces.IRegisterDAO;
import com.example.horizoncovidmonitor.model.Patient;
import com.example.horizoncovidmonitor.utils.Database;

import java.util.ArrayList;
import java.util.List;

public class RegisterDAO implements IRegisterDAO {

    private SQLiteDatabase putData;
    private SQLiteDatabase getData;

    public RegisterDAO(Context context) {
        Database db = new Database(context);
        putData = db.getWritableDatabase();
        getData = db.getReadableDatabase();
    }

    @Override
    public boolean save(Patient patient) {
        ContentValues cv = new ContentValues();
        cv.put(Database.NAME, patient.getName());
        cv.put(Database.AGE, patient.getAge());
        cv.put(Database.TEMPERATURE, patient.getTemperature());
        cv.put(Database.COUGH, patient.getCoughingDays());
        cv.put(Database.HEADACHE, patient.getHeadacheDays());
        cv.put(Database.STATUS, patient.getStatus());
        cv.put(Database.VISITEDCOUNTRY, patient.getVisitedCountry());
        cv.put(Database.WEEKSCOUNTRY, patient.getWeeksCountry());

        try {
            putData.insert(Database.TABLE_NAME, null, cv);
            Log.e("INFO", "Cadastro feito com sucesso!");

        } catch (Exception e) {
            Log.e("INFO", "Erro ao salvaor o cadastro " + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean update(Patient patient) {
        return false;
    }

    @Override
    public boolean delete(Patient patient) {
        return false;
    }

    @Override
    public List<Patient> list() {
        List<Patient> patients = new ArrayList<>();

        String sql = "SELECT * FROM " + Database.TABLE_NAME + " ;";
        Cursor c = getData.rawQuery(sql, null);


        while (c.moveToNext()){
            Patient patient = new Patient();

            Long id = c.getLong(c.getColumnIndexOrThrow(Database.ID));
            String name = c.getString(c.getColumnIndexOrThrow(Database.NAME));
            int age = c.getInt(c.getColumnIndexOrThrow(Database.AGE));
            int temperature = c.getInt(c.getColumnIndexOrThrow(Database.TEMPERATURE));
            int cough = c.getInt(c.getColumnIndexOrThrow(Database.COUGH));
            int headache = c.getInt(c.getColumnIndexOrThrow(Database.HEADACHE));
            String status = c.getString(c.getColumnIndexOrThrow(Database.STATUS));
            String visitedCountry =  c.getString(c.getColumnIndexOrThrow(Database.VISITEDCOUNTRY));
            int weekCountry = c.getInt(c.getColumnIndexOrThrow(Database.WEEKSCOUNTRY));

            patient.setId(id);
            patient.setName(name);
            patient.setAge(age);
            patient.setTemperature(temperature);
            patient.setCoughingDays(cough);
            patient.setHeadacheDays(headache);
            patient.setStatus(status);
            patient.setVisitedCountry(visitedCountry);
            patient.setWeeksCountry(weekCountry);

            patients.add(patient);

        }

        return patients;
    }
}
