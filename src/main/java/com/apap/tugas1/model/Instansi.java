package com.apap.tugas1.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Setter;
import lombok.Getter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "instansi")
public class Instansi implements Serializable {

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

    @OneToMany(mappedBy = "instansi", fetch = FetchType.LAZY)
    @Setter
    @Getter
    @JsonIgnore
    private List<Pegawai> pegawais;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_provinsi")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Getter
    @Setter
    @JsonIgnore
    private Provinsi provinsi;
}
