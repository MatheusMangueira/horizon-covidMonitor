package com.example.horizoncovidmonitor.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String DB_NAME = "DB_PATIENTS";
    public static String TABLE_NAME = "patient";
    public static String ID = "_id";
    public static String NAME = "name";
    public static String TEMPERATURE = "temperature";
    public static String AGE = "age";
    public static String COUGH = "cough";
    public static String HEADACHE = "headache";
    public static String STATUS = "status";
    public static String VISITEDCOUNTRY = "visitedCountry";
    public static String WEEKSCOUNTRY = "weeksCountry";

    public Database(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " TEXT NOT NULL, " +
                TEMPERATURE + " INTEGER NOT NULL, " +
                AGE + " INTEGER NOT NULL, " +
                COUGH + " INTEGER, " +
                HEADACHE + " INTEGER, " +
                STATUS + " TEXT NOT NULL, " +
                VISITEDCOUNTRY + " TEXT, " +
                WEEKSCOUNTRY + " INTEGER" +
                ");";

        try {
            db.execSQL(sql);
            Log.i("INFO DB", "Sucesso ao criar a tabela ");
        } catch (Exception e) {
            Log.i("INFO DB", "Erro ao criar a tabela " + e.getMessage());
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql =("DROP TABLE IF EXISTS " + TABLE_NAME + ";");

        try {
            db.execSQL(sql);
            onCreate(db);
            Log.i("INFO DB", "Sucesso ao criar a tabela ");
        } catch (Exception e) {
            Log.i("INFO DB", "Erro ao criar a tabela " + e.getMessage());
        }
    }
}
