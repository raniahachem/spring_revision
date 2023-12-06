package tn.esprit.rania_hachem_4sim1_revision.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.rania_hachem_4sim1_revision.entities.Bank;
import tn.esprit.rania_hachem_4sim1_revision.entities.Compte;
import tn.esprit.rania_hachem_4sim1_revision.entities.Transaction;
import tn.esprit.rania_hachem_4sim1_revision.services.IBankServices;

import java.util.List;

@RestController
@RequestMapping ("/bank")
@RequiredArgsConstructor
public class BankRestController {
    private final IBankServices iBankServices;
    @PostMapping ("/add")
    public Bank addBank(@RequestBody Bank bank)
    {
        return iBankServices.ajouterBank(bank);
    }
    @PostMapping ("/addCompte/{agence}")
    public Compte addCompte (@RequestBody Compte compte, @PathVariable String agence)
    {
        return iBankServices.ajouterCompteEtAffecterAAgence(compte, agence);
    }

    @PostMapping ("/virement")
    public String transaction (@RequestBody Transaction transaction)
    {
        return iBankServices.ajouterVirement(transaction);
    }

    @GetMapping ("/transactions/{idBank}")
    public List<Transaction> transactions (@PathVariable long idBank)
    {
        return iBankServices.getAllTransactionByBankId(idBank);
    }
}
