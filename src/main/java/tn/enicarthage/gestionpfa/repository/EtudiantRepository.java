package tn.enicarthage.gestionpfa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.enicarthage.gestionpfa.entity.Etudiant;
import tn.enicarthage.gestionpfa.entity.Niveau;
import tn.enicarthage.gestionpfa.entity.Groupe;

import java.util.List;
import java.util.Optional;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

    Optional<Etudiant> findByEmail(String email);

    Optional<Etudiant> findByCin(String cin);

    Optional<Etudiant> findByNumInscription(String numInscription);

    List<Etudiant> findByNiveau(Niveau niveau);

    List<Etudiant> findBySpecialite(String specialite);

    List<Etudiant> findByGroupe(Groupe groupe);

    @Query("SELECT e FROM Etudiant e WHERE e.pfa IS NULL")
    List<Etudiant> findEtudiantsSansPFA();

    List<Etudiant> findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(String nom, String prenom);
}