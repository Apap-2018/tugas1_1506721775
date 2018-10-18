package com.apap.tugas1.service;

import com.apap.tugas1.model.JabatanPegawai;
import com.apap.tugas1.model.Pegawai;
import com.apap.tugas1.repository.PegawaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService {

    private final
    PegawaiRepository pegawaiRepository;

    @Autowired
    public PegawaiServiceImpl(PegawaiRepository pegawaiRepository) {
        this.pegawaiRepository = pegawaiRepository;
    }

    @Override
    public Pegawai findPegawaiByNip(String nip) {
        return pegawaiRepository.findPegawaiByNipEquals(nip);
    }

    @Override
    public List<Pegawai> findAllPegawai() {
        return pegawaiRepository.findAll();
    }

    @Override
    public List<Object[]> advanceSearch(Long id_instansi, Integer id_provinsi, Long id_jabatan) {
        return pegawaiRepository.findAdvance(id_instansi, id_provinsi, id_jabatan);
    }

    @Override
    public Pegawai savePegawai(Pegawai pegawai) {

        String tanggalLahir = generateNipDate(pegawai);
        String rank = generateNipRank(pegawai);
        String nip = pegawai.getInstansi().getId() + tanggalLahir + pegawai.getTahunMasuk() + rank;
        pegawai.setNip(nip);
        pegawaiRepository.save(pegawai);

        return pegawai;
    }

    @Override
    public Pegawai checkNip(Long instansiId, String tahunMasuk, Date tanggalLahir) {
        return pegawaiRepository.findFirstByInstansi_idAndTahunMasukAndTanggalLahirOrderByNipDesc(instansiId, tahunMasuk, tanggalLahir);
    }

    @Override
    public String updatePegawai(Pegawai pegawai) {
        Pegawai pastData = pegawaiRepository.findPegawaiByNipEquals(pegawai.getNip());

        String nip;
        if (pastData.getTanggalLahir().equals(pegawai.getTanggalLahir()) && pastData.getTahunMasuk().equals(pegawai.getTahunMasuk()) && pastData.getInstansi().equals(pegawai.getInstansi())) {
            nip = pegawai.getNip();
        } else {
            String tgl = generateNipDate(pegawai);
            String newCount = generateNipRank(pegawai);
            nip = pegawai.getInstansi().getId() + tgl + pegawai.getTahunMasuk() + newCount;
        }


        pegawai.setNip(nip);
        pegawaiRepository.save(pegawai);

        return pegawai.getNip();
    }

    private String generateNipRank(Pegawai pegawai) {
        if (checkNip(pegawai.getInstansi().getId(), pegawai.getTahunMasuk(), pegawai.getTanggalLahir()) == null) {
            return "0" + 1;
        } else {
            String checkNip = checkNip(pegawai.getInstansi().getId(), pegawai.getTahunMasuk(), pegawai.getTanggalLahir()).getNip();
            String substring = checkNip.substring(14);
            int rankNip = Integer.parseInt(substring);

            if (rankNip < 9) {
                rankNip++;
                return "0" + rankNip;
            } else {
                rankNip++;
                return "" + rankNip;
            }
        }
    }

    private String generateNipDate(Pegawai pegawai) {
        SimpleDateFormat mdyFormat = new SimpleDateFormat("dd-MM-yy");
        String mdy = mdyFormat.format(pegawai.getTanggalLahir());
        return mdy.replace("-", "");

    }

    @Override
    public Pegawai findFirstByInstansi_IdOrderByTanggalLahirDesc(Long id) {
        return pegawaiRepository.findFirstByInstansi_IdOrderByTanggalLahirDesc(id);
    }

    @Override
    public Pegawai findFirstByInstansi_IdOrderByTanggalLahirAsc(Long id) {
        return pegawaiRepository.findFirstByInstansi_IdOrderByTanggalLahirAsc(id);
    }

    @Override
    public long countGaji(Pegawai pegawai) {
        List<JabatanPegawai> jabatanPegawais = pegawai.getListJabatanPegawai();
        double gaji = 0.0;
        for(JabatanPegawai jabatan : jabatanPegawais) {
            if(jabatan.getJabatan().getGaji_pokok() > gaji) {
                gaji = jabatan.getJabatan().getGaji_pokok();
            }
        }
        return (long) (gaji +  pegawai.getInstansi().getProvinsi().getPresentase_tunjangan()/100 * gaji);
    }
}

