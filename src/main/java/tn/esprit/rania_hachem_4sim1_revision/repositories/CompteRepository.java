package tn.esprit.rania_hachem_4sim1_revision.repositories;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.rania_hachem_4sim1_revision.entities.Compte;

public interface CompteRepository extends CrudRepository<Compte, Long> {
    Compte findByCode(long code);
}
