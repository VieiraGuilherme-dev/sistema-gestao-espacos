package com.bancodedados.gestaoespaco.repository; // Corrected package name

import com.bancodedados.gestaoespaco.model.EspacoFisico; // Corrected model package name
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; // Recommended for clarity


@Repository
public interface EspacoFisicoRepository extends JpaRepository<EspacoFisico, Long> {


}