package com.example.horizoncovidmonitor.model;

public class Patient {
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
}
