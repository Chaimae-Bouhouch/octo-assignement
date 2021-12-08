package ma.octo.assignement.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@DiscriminatorValue("VIREMENT")
public class Virement extends Transaction{
  public void setCompteEmetteur(Compte compteEmetteur) {
		this.compteEmetteur = compteEmetteur;
	}

@ManyToOne
  private Compte compteEmetteur;

  
public Virement() {
	super();
}

public Virement(Compte compteEmetteur) {
	super();
	this.compteEmetteur = compteEmetteur;
}

public Compte getCompteEmetteur() {
	return compteEmetteur;
}

public Virement( BigDecimal montant, Date dateExecution, Compte compteBeneficiaire, String motif,
		Compte compteEmetteur) {
	super( montant, dateExecution, compteBeneficiaire, motif);
	this.compteEmetteur = compteEmetteur;
}

}
