package com.apap.tugas1.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Setter;
import lombok.Getter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Instansi implements Serializable {

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

    @OneToMany(mappedBy = "instansi", fetch = FetchType.LAZY)
    @Setter
    @Getter
    @OrderBy("tanggalLahir desc")
    @JsonIgnore
    private List<Pegawai> pegawais = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_provinsi", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Getter
    @Setter
    @JsonIgnore
    private Provinsi provinsi;
}
