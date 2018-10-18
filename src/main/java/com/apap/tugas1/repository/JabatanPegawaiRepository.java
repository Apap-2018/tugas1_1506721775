package com.apap.tugas1.repository;

import com.apap.tugas1.model.JabatanPegawai;
import com.apap.tugas1.model.Pegawai;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JabatanPegawaiRepository extends JpaRepository<JabatanPegawai, Long> {
    void deleteByPegawai(Pegawai pegawai);
}
