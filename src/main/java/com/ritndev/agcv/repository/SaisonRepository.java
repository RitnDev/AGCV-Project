package com.ritndev.agcv.repository;

import com.ritndev.agcv.model.Saison;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ritn
 */
@Repository
public interface SaisonRepository extends JpaRepository<Saison, Long> {

    public boolean existsByAnneeDebut(int anneeDebut);
    public boolean existsByAnneeFin(int anneeFin);
    public Saison findByAnneeDebut(int anneeDebut);
    public Saison findByAnneeFin(int anneeFin);

}
