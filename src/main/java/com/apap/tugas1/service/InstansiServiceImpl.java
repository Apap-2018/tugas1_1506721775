package com.apap.tugas1.service;

import com.apap.tugas1.model.Instansi;
import com.apap.tugas1.repository.InstansiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InstansiServiceImpl implements InstansiService {

    private final
    InstansiRepository instansiRepository;

    @Autowired
    public InstansiServiceImpl(InstansiRepository instansiRepository) {
        this.instansiRepository = instansiRepository;
    }

    @Override
    public List<Instansi> findAll() {
        return instansiRepository.findAll();
    }

}
