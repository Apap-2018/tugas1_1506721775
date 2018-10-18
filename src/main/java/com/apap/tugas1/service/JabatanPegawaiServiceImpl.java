package com.apap.tugas1.service;

import com.apap.tugas1.model.JabatanPegawai;
import com.apap.tugas1.model.Pegawai;
import com.apap.tugas1.repository.JabatanPegawaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JabatanPegawaiServiceImpl implements JabatanPegawaiService {

    private final JabatanPegawaiRepository jabatanPegawaiRepository;

    @Autowired
    public JabatanPegawaiServiceImpl(JabatanPegawaiRepository jabatanPegawaiRepository) {
        this.jabatanPegawaiRepository = jabatanPegawaiRepository;
    }

    @Override
    public void addJabatanPegawai(JabatanPegawai jabatanPegawai) {
        jabatanPegawaiRepository.save(jabatanPegawai);
    }

    @Override
    public void deleteByPegawai(Pegawai pegawai) {
        jabatanPegawaiRepository.deleteByPegawai(pegawai);
    }
}
