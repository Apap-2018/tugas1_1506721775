package com.apap.tugas1.service;

import com.apap.tugas1.model.Provinsi;
import com.apap.tugas1.repository.ProvinsiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProvinsiServiceImpl implements ProvinsiService {

    private final ProvinsiRepository provinsiRepository;

    @Autowired
    public ProvinsiServiceImpl(ProvinsiRepository provinsiRepository) {
        this.provinsiRepository = provinsiRepository;
    }

    @Override
    public List<Provinsi> findAll() {
        return provinsiRepository.findAll();
    }

    @Override
    public Provinsi findById(Integer id) {
        return provinsiRepository.findById(id);
    }
}
