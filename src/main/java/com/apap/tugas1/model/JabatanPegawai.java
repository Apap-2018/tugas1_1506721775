package com.apap.tugas1.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="jabatan_pegawai")
public class JabatanPegawai implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_pegawai", referencedColumnName="id", nullable=false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @Getter
    @Setter
    private Pegawai pegawai;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "id_jabatan", referencedColumnName="id", nullable=false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @Getter
    @Setter
    private Jabatan jabatan;

}