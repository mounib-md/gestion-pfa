package tn.enicarthage.gestionpfa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.enicarthage.gestionpfa.entity.Encadrant;

import java.util.List;
import java.util.Optional;

@Repository
public interface EncadrantRepository extends JpaRepository<Encadrant, Long> {

    Optional<Encadrant> findByEmail(String email);

    List<Encadrant> findByDepartement(String departement);

    List<Encadrant> findByGrade(Encadrant.Grade grade);

    List<Encadrant> findBySpecialiteContainingIgnoreCase(String specialite);

    // Encadrants qui n'ont pas encore atteint leur quota
    @Query("SELECT e FROM Encadrant e WHERE SIZE(e.pfas) < e.quotaMax")
    List<Encadrant> findEncadrantsDisponibles();

    // Nombre de PFA encadrés par un encadrant
    @Query("SELECT COUNT(p) FROM PFA p WHERE p.encadrant.id = :encadrantId")
    long countPFAsByEncadrant(Long encadrantId);
}