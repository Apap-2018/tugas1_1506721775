package com.apap.tugas1.service;

import com.apap.tugas1.model.Jabatan;
import com.apap.tugas1.repository.JabatanRepository;
import com.apap.tugas1.repository.PegawaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class JabatanServiceImpl implements JabatanService {

    private final
    JabatanRepository jabatanRepository;

    private final
    PegawaiRepository pegawaiRepository;

    @Autowired
    public JabatanServiceImpl(JabatanRepository jabatanRepository, PegawaiRepository pegawaiRepository) {
        this.jabatanRepository = jabatanRepository;
        this.pegawaiRepository = pegawaiRepository;
    }

    @Override
    public List<Jabatan> findAllJabatan() {

        return jabatanRepository.findAll();
    }

    @Override
    public Jabatan findByIdJabatan(Long id) {

        if (jabatanRepository.findById(id).isPresent()) {
            return jabatanRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public void saveOrUpdate(Jabatan jabatan) {

        jabatanRepository.save(jabatan);
    }

    @Override
    public void delete(Long id) {
        jabatanRepository.deleteById(id);
    }
}
