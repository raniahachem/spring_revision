package tn.esprit.rania_hachem_4sim1_revision.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import tn.esprit.rania_hachem_4sim1_revision.entities.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository  extends CrudRepository<Transaction, Long> {
    List<Transaction> findByDateTransaction(LocalDate date);

    @Query ("Select t from Transaction t join Bank b on (( t.destinataire member b.comptes) or (t.expediteur member b.comptes )) where b.idBank =:id")
    public List<Transaction> getAllTransactionByBankId(@Param("id") long idBank);
}
