package com.apap.tugas1.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "jabatan")
public class Jabatan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String nama;

    @Getter
    @Setter
    private String deskripsi;

    @Getter
    @Setter
    private Double gaji_pokok;

//    @ManyToMany(mappedBy = "jabatans")
//    @Getter
//    @Setter
//    private List<Pegawai> pegawais;

    @OneToMany(mappedBy = "jabatan", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Getter
    @Setter
    private List<JabatanPegawai> listJabatanPegawai;

}
