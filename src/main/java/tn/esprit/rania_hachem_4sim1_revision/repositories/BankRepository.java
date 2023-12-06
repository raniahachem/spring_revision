package tn.esprit.rania_hachem_4sim1_revision.repositories;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.rania_hachem_4sim1_revision.entities.Bank;

public interface BankRepository  extends CrudRepository<Bank, Long> {
 Bank findByAgence(String nom);
}
