package tn.enicarthage.gestionpfa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.enicarthage.gestionpfa.entity.Entreprise;

import java.util.List;
import java.util.Optional;

@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {

    Optional<Entreprise> findByNomIgnoreCase(String nom);

    List<Entreprise> findBySecteurContainingIgnoreCase(String secteur);

    List<Entreprise> findByVilleIgnoreCase(String ville);

    List<Entreprise> findByPaysIgnoreCase(String pays);

    // Recherche par nom (contient)
    List<Entreprise> findByNomContainingIgnoreCase(String nom);
}