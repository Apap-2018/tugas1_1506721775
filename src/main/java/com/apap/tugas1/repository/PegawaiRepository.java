package com.apap.tugas1.repository;

import com.apap.tugas1.model.Pegawai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface PegawaiRepository extends JpaRepository<Pegawai, Long> {

    Pegawai findPegawaiByNipEquals(String nip);

    @Query("select p.nip, p.nama, p.tempatLahir, p.tanggalLahir, p.tahunMasuk, i.nama as nama_instansi, j.nama as nama_jabatan, pv.nama as nama_provinsi from Pegawai p " +
            "JOIN p.instansi i " +
            "JOIN i.provinsi pv " +
            "JOIN p.listJabatanPegawai lj " +
            "JOIN lj.jabatan j " +
            "WHERE (:idInstansi is null or i.id = :idInstansi) " +
            "AND (:idProvinsi is null or pv.id = :idProvinsi) " +
            "AND (:idJabatan is null or j.id = :idJabatan)")
    List<Object[]> findAdvance(
            @Param("idInstansi") Long id_instansi,
            @Param("idProvinsi") Integer id_provinsi,
            @Param("idJabatan") Long id_jabatan);

    Pegawai findFirstByInstansi_idAndTahunMasukAndTanggalLahirOrderByNipDesc(Long instansiId, String tahunMasuk, Date tanggalLahir);

    Pegawai findFirstByInstansi_IdOrderByTanggalLahirDesc(Long id);

    Pegawai findFirstByInstansi_IdOrderByTanggalLahirAsc(Long id);
}
