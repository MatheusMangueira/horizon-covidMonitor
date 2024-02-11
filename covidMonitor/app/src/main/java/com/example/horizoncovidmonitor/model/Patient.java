package com.example.horizoncovidmonitor.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Patient implements Parcelable {
    private Long id;
    private String name;
    private int age;
    private int temperature;
    private int coughingDays;
    private int headacheDays;
    private String visitedCountry;
    private int weeksCountry;
    private String status;

    public Patient() {
    }

    public Patient(Long id, String name, int age, int temperature, int coughingDays, int headacheDays, String visitedCountry, int weeksCountry, String status) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.temperature = temperature;
        this.coughingDays = coughingDays;
        this.headacheDays = headacheDays;
        this.visitedCountry = visitedCountry;
        this.weeksCountry = weeksCountry;
        this.status = status;
    }

    protected Patient(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        name = in.readString();
        age = in.readInt();
        temperature = in.readInt();
        coughingDays = in.readInt();
        headacheDays = in.readInt();
        visitedCountry = in.readString();
        weeksCountry = in.readInt();
        status = in.readString();
    }

    public static final Creator<Patient> CREATOR = new Creator<Patient>() {
        @Override
        public Patient createFromParcel(Parcel in) {
            return new Patient(in);
        }

        @Override
        public Patient[] newArray(int size) {
            return new Patient[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getCoughingDays() {
        return coughingDays;
    }

    public void setCoughingDays(int coughingDays) {
        this.coughingDays = coughingDays;
    }

    public int getHeadacheDays() {
        return headacheDays;
    }

    public void setHeadacheDays(int headacheDays) {
        this.headacheDays = headacheDays;
    }

    public String getVisitedCountry() {
        return visitedCountry;
    }

    public void setVisitedCountry(String visitedCountry) {
        this.visitedCountry = visitedCountry;
    }

    public int getWeeksCountry() {
        return weeksCountry;
    }

    public void setWeeksCountry(int weeksCountry) {
        this.weeksCountry = weeksCountry;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(name);
        dest.writeInt(age);
        dest.writeInt(temperature);
        dest.writeInt(coughingDays);
        dest.writeInt(headacheDays);
        dest.writeString(visitedCountry);
        dest.writeInt(weeksCountry);
        dest.writeString(status);
    }
}
