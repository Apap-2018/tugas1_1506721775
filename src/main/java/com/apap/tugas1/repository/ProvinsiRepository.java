package com.apap.tugas1.repository;

import com.apap.tugas1.model.Provinsi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinsiRepository extends JpaRepository<Provinsi, Long> {

    Provinsi findById(Integer id);
}
