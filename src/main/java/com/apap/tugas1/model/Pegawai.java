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
public class Pegawai implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Column(unique = true)
    private String nip;

    @Getter
    @Setter
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

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "jabatan_pegawai", joinColumns = @JoinColumn(name = "id_pegawai", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "id_jabatan", referencedColumnName = "id"))
//    @Getter
//    @Setter
//    @OrderBy("gaji_pokok DESC")
//    private List<Jabatan> jabatans;

    @OneToMany(mappedBy = "pegawai", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Setter
    @Getter
    private List<JabatanPegawai> listJabatanPegawai;

}
