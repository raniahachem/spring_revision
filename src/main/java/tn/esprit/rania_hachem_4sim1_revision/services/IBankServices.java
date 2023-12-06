package tn.esprit.rania_hachem_4sim1_revision.services;

import org.springframework.data.repository.query.Param;
import tn.esprit.rania_hachem_4sim1_revision.entities.Bank;
import tn.esprit.rania_hachem_4sim1_revision.entities.Compte;
import tn.esprit.rania_hachem_4sim1_revision.entities.Transaction;

import java.util.List;

public interface IBankServices {
    public Bank ajouterBank(Bank bank);
    public Compte ajouterCompteEtAffecterAAgence(Compte compte, String agenceBank);
    public String ajouterVirement(Transaction transaction);
    public void getAllTransactionByDate();
    public List<Transaction> getAllTransactionByBankId(long idBank);
}
