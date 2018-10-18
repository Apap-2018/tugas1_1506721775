package com.apap.tugas1.service;

import com.apap.tugas1.model.Pegawai;

import java.sql.Date;
import java.util.List;

public interface PegawaiService {

    Pegawai findPegawaiByNip(String nip);

    List<Pegawai> findAllPegawai();

    List<Object[]> advanceSearch(Long id_instansi, Integer id_provinsi, Long id_jabatan);

    Pegawai savePegawai(Pegawai pegawai);

    Pegawai checkNip(Long instansiId, String tahunMasuk, Date tanggalLahir);

    String updatePegawai(Pegawai pegawai);

    long countGaji(Pegawai pegawai);

    Pegawai findFirstByInstansi_IdOrderByTanggalLahirDesc(Long id);

    Pegawai findFirstByInstansi_IdOrderByTanggalLahirAsc(Long id);
}
