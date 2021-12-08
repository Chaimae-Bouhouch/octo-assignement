package ma.octo.assignement.dto;



public class VirementDto extends TransactionDto{
  private String nrCompteEmetteur;

public String getNrCompteEmetteur() {
	return nrCompteEmetteur;
}

public void setNrCompteEmetteur(String nrCompteEmetteur) {
	this.nrCompteEmetteur = nrCompteEmetteur;
}
  
}
