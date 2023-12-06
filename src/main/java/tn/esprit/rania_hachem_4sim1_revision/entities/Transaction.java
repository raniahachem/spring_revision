package tn.esprit.rania_hachem_4sim1_revision.entities;

import com.fasterxml.jackson.databind.DatabindException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTransaction;
    private double montant;
    @Enumerated(EnumType.STRING)
    private TypeTransaction type;
    private LocalDate dateTransaction;

    @ManyToOne (cascade = CascadeType.PERSIST)
    Compte destinataire;

    @ManyToOne (cascade = CascadeType.PERSIST)
    Compte expediteur;
}
