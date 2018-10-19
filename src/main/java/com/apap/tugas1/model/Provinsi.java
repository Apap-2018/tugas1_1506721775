package com.apap.tugas1.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "provinsi")
public class Provinsi implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    @Column(name = "nama")
    private String nama;

    @Getter
    @Setter
    @Column(name = "presentase_tunjangan")
    private Double presentase_tunjangan;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "provinsi")
    @Getter
    @Setter
    private List<Instansi> instansi;
}
