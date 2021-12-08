package ma.octo.assignement.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@DiscriminatorValue("VERSEMENT")
public class Versement extends Transaction{

  @Column
  private String nom_prenom_emetteur;

public String getNom_prenom_emetteur() {
	return nom_prenom_emetteur;
}

public void setNom_prenom_emetteur(String nom_prenom_emetteur) {
	this.nom_prenom_emetteur = nom_prenom_emetteur;
}

public Versement() {
	super();
}

public Versement(BigDecimal montant, Date dateExecution, Compte compteBeneficiaire, String motif,
		String nom_prenom_emetteur) {
	super( montant, dateExecution, compteBeneficiaire, motif);
	this.nom_prenom_emetteur = nom_prenom_emetteur;
}

}
