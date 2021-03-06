package ma.octo.assignement.service;

import ma.octo.assignement.domain.Versement;
import ma.octo.assignement.dto.VersementDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;


public interface VersementService {
	
	public Versement createVersement(VersementDto versementDto)   
			throws SoldeDisponibleInsuffisantException, 
	               CompteNonExistantException, 
	               TransactionException;
}
