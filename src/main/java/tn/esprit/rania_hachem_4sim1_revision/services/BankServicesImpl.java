package tn.esprit.rania_hachem_4sim1_revision.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.rania_hachem_4sim1_revision.entities.*;
import tn.esprit.rania_hachem_4sim1_revision.repositories.BankRepository;
import tn.esprit.rania_hachem_4sim1_revision.repositories.CompteRepository;
import tn.esprit.rania_hachem_4sim1_revision.repositories.TransactionRepository;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BankServicesImpl implements IBankServices{
    private final BankRepository bankRepository;
    private final CompteRepository compteRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public Bank ajouterBank(Bank bank) {
        return bankRepository.save(bank);
    }

    @Override
    public Compte ajouterCompteEtAffecterAAgence(Compte compte, String agenceBank) {
        Bank bank= bankRepository.findByAgence(agenceBank);
        bank.getComptes().add(compte);

        return compteRepository.save(compte);
    }

    @Transactional
    @Override
    public String ajouterVirement(Transaction transaction) {
        if (transaction.getType()== TypeTransaction.VIREMENT && transaction.getExpediteur().getType()== TypeCompte.COURANT) {
            return "on peut pas faire un virement à partir d'un compte EPARGNE";
        }
        if (transaction.getDestinataire().getSolde() > transaction.getMontant() + 3 && transaction.getType()== TypeTransaction.VIREMENT && transaction.getExpediteur().getType()== TypeCompte.COURANT){
            return "solde insuffisant";
        }
        else {
            Compte compteDestinataire = compteRepository.findByCode(transaction.getDestinataire().getCode());
            Compte compteExpediteur = compteRepository.findByCode(transaction.getExpediteur().getCode());
compteDestinataire.setSolde(compteDestinataire.getSolde() + transaction.getMontant());
compteExpediteur.setSolde(compteExpediteur.getSolde() - transaction.getMontant());
transaction.setDateTransaction(LocalDate.now());

            transactionRepository.save(transaction);
        }
        return "virement avec succés";
    }

@Scheduled (cron = "*/30 * * * * *")
    @Override
    public void getAllTransactionByDate() {
        log.info("transaction du jour ");
    for (Transaction t: transactionRepository.findByDateTransaction(LocalDate.of(2023,12,05))
         ) {

        log.info(String.valueOf(t.getIdTransaction()));
    }
    }

    @Override
    public List<Transaction> getAllTransactionByBankId(long idBank) {
        return transactionRepository.getAllTransactionByBankId(idBank);
    }

}
