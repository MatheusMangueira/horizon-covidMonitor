package com.example.horizoncovidmonitor.interfaces;

import com.example.horizoncovidmonitor.model.Patient;

import java.util.List;

public interface IRegisterDAO {
    public boolean save(Patient patient);

    public boolean update(Patient patient);

    boolean delete(Long patientId);

    public List<Patient> list();

    public boolean clearData();
}
