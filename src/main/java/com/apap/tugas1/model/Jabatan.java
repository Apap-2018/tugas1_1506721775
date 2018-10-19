package com.apap.tugas1.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

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
    @Column(name = "nama")
    private String nama;

    @Getter
    @Setter
    @Column(name = "deskripsi")
    private String deskripsi;

    @Getter
    @Setter
    @Column(name = "gaji_pokok")
    private Double gaji_pokok;

    @OneToMany(mappedBy = "jabatan", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Getter
    @Setter
    private List<JabatanPegawai> listJabatanPegawai;
}
