package com.apap.tugas1.service;

import com.apap.tugas1.model.Jabatan;

import java.util.List;

public interface JabatanService {
    List<Jabatan> findAllJabatan();

    Jabatan findByIdJabatan(Long id);

    void saveOrUpdate(Jabatan jabatan);

    void delete(Long Id);
}
