package ma.octo.assignement.dto;

public class VersementDto extends TransactionDto{

	private String nomPrenomEmetteur;

	public String getNomPrenomEmetteur() {
		return nomPrenomEmetteur;
	}

	public void setNomPrenomEmetteur(String nomPrenomEmetteur) {
		this.nomPrenomEmetteur = nomPrenomEmetteur;
	}
	
}
