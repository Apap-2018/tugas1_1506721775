package com.apap.tugas1.service;

import com.apap.tugas1.model.Provinsi;

import java.util.List;

public interface ProvinsiService {
    List<Provinsi> findAll();
    Provinsi findById(Integer id);
}
