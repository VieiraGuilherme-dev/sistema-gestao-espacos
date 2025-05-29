package com.bancodedados.gestaoespaco.repository; // Corrected package name

import com.bancodedados.gestaoespaco.model.EspacoFisico; // Corrected model package name
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; // Recommended for clarity

/**
 * Repository interface for the EspacoFisico (Physical Space) entity.
 * Extends JpaRepository to provide standard CRUD (Create, Read, Update, Delete)
 * operations for EspacoFisico entities.
 */
@Repository
public interface EspacoFisicoRepository extends JpaRepository<EspacoFisico, Long> {


}