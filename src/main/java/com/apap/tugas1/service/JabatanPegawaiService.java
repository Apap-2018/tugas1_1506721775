package com.apap.tugas1.service;

import com.apap.tugas1.model.JabatanPegawai;
import com.apap.tugas1.model.Pegawai;

public interface JabatanPegawaiService {

    void addJabatanPegawai(JabatanPegawai jabatanPegawai);

    void deleteByPegawai(Pegawai pegawai);
}
