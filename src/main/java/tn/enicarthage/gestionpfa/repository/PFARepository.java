package tn.enicarthage.gestionpfa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.enicarthage.gestionpfa.entity.PFA;

import java.util.List;
import java.util.Optional;

@Repository
public interface PFARepository extends JpaRepository<PFA, Long> {

    List<PFA> findByStatut(PFA.Statut statut);

    List<PFA> findByType(PFA.Type type);

    List<PFA> findByAnneeUniversitaire(String anneeUniversitaire);

    Optional<PFA> findByEtudiantId(Long etudiantId);

    List<PFA> findByEncadrantId(Long encadrantId);

    List<PFA> findByEntrepriseId(Long entrepriseId);

    // PFA par statut et année
    List<PFA> findByStatutAndAnneeUniversitaire(PFA.Statut statut, String anneeUniversitaire);

    // Recherche par mot-clé dans le titre ou la description
    List<PFA> findByTitreContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String titre, String description);

    // PFA en cours (statut EN_COURS ou VALIDE)
    @Query("SELECT p FROM PFA p WHERE p.statut IN ('EN_COURS', 'VALIDE')")
    List<PFA> findPFAActifs();

    // Statistiques : nombre de PFA par statut
    @Query("SELECT p.statut, COUNT(p) FROM PFA p GROUP BY p.statut")
    List<Object[]> countByStatut();
}