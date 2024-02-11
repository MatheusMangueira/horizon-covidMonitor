package com.example.horizoncovidmonitor.service;

public class RegisterService {
    private String visitedCountry;
    private int week = 0;
    private int headache = 0;
    private int coughing = 0;
    private int temperature;
    private int age;

    public RegisterService(int coughing, int week, int headache, int temperature, int age) {
        // this.visitedCountry = visitedCountry;
        this.week = week;
        this.headache = headache;
        this.temperature = temperature;
        this.age = age;
        this.coughing = coughing;
    }

    public String inpatient() {
        String admit;

        if (week > 0 && week <= 6 && coughing > 5 && headache > 5 && temperature > 37) {
            admit = "Covid";
        } else if (week <= 6 && week > 0) {
            admit = "Quarentena";
        } else if ((age > 60 || age < 10) && (temperature > 37 || headache > 3 || coughing > 5)) {
            admit = "Quarentena";
        } else if (age >= 10 && age <= 60 && temperature > 37 && headache > 5 && coughing > 5 && week == 0) {
            admit = "Quarentena";
        } else {
            admit = "Liberado";
        }

        return admit;
    }

}
