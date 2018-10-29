package com.apap.tugas1.model;

import javax.persistence.*;
import java.io.Serializable;

import java.sql.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="pegawai")
public class Pegawai implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Column(name = "nip",unique = true)
    private String nip;

    @Getter
    @Setter
    @Column(name = "nama")
    private String nama;

    @Getter
    @Setter
    @Column(name = "tempat_lahir")
    private String tempatLahir;

    @Getter
    @Setter
    @Column(name = "tanggal_lahir")
    private Date tanggalLahir;

    @Getter
    @Setter
    @Column(name = "tahun_masuk")
    private String tahunMasuk;

    @ManyToOne(fetch = FetchType.LAZY)
    @Setter
    @Getter
    @JoinColumn(name = "id_instansi")
    private Instansi instansi;

    @OneToMany(mappedBy = "pegawai", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Setter
    @Getter
    private List<JabatanPegawai> listJabatanPegawai;

    public long countGaji() {
        List<JabatanPegawai> jabatanPegawais = this.getListJabatanPegawai();
        double gaji = 0.0;
        for(JabatanPegawai jabatan : jabatanPegawais) {
            if(jabatan.getJabatan().getGaji_pokok() > gaji) {
                gaji = jabatan.getJabatan().getGaji_pokok();
            }
        }
        return (long) (gaji +  this.getInstansi().getProvinsi().getPresentase_tunjangan()/100 * gaji);
    }
}
