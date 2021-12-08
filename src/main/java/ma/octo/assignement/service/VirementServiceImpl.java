package ma.octo.assignement.service;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Virement;
import ma.octo.assignement.dto.VirementDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.VirementRepository;

@Service
public class VirementServiceImpl implements VirementService{
	
	@Autowired
	AuditService auditService;
	@Autowired
	VirementRepository virementRepository;
	@Autowired
	CompteRepository compteRepository;
	
	public static final BigDecimal MONTANT_MAXIMAL = new BigDecimal(10000);
	
	@Override
	public Virement createVirement(VirementDto virementDto)   throws SoldeDisponibleInsuffisantException, CompteNonExistantException, TransactionException{
		
		Compte compteEmetteur = compteRepository.findByNrCompte(virementDto.getNrCompteEmetteur());
        Compte compteBeneficiaire = compteRepository.findByNrCompte(virementDto.getNrCompteBeneficiaire());

        if (compteEmetteur == null) {
            throw new CompteNonExistantException("Compte Non existant");
        }

        if (compteBeneficiaire == null) {
            throw new CompteNonExistantException("Compte Non existant");
        }

        if (virementDto.getMontant() == null) {
            throw new TransactionException("Montant vide");
        } else if (virementDto.getMontant().compareTo(BigDecimal.ZERO) == 0) {
            throw new TransactionException("Montant vide");
        }
        else if (virementDto.getMontant().compareTo(BigDecimal.ZERO) < 0) {
            throw new TransactionException("Montant négatif");
        }else if (virementDto.getMontant().compareTo(MONTANT_MAXIMAL) > 0) {
            throw new TransactionException("Montant maximal est 10 000");
        }else if (virementDto.getMontant().compareTo(BigDecimal.TEN) < 0) {
            throw new TransactionException("Montant inférieur à 10");
        }
        else if (compteEmetteur.getSolde().compareTo(virementDto.getMontant()) < 0) {
            throw new SoldeDisponibleInsuffisantException("Solde insuffisant");
        }
        
        if (virementDto.getMotif().length() <= 0) {
            throw new TransactionException("Motif vide");
        }

        compteEmetteur.setSolde(compteEmetteur.getSolde().subtract(virementDto.getMontant()));
        compteRepository.save(compteEmetteur);
        compteBeneficiaire.setSolde(compteBeneficiaire.getSolde().add(virementDto.getMontant()));
        compteRepository.save(compteBeneficiaire);
        

        Virement virement = new Virement();
        virement.setDateExecution(new Date());
        virement.setCompteBeneficiaire(compteBeneficiaire);
        virement.setCompteEmetteur(compteEmetteur);
        virement.setMontant(virementDto.getMontant());
        virement.setMotif(virementDto.getMotif());;
        
        virementRepository.save(virement);
        
        auditService.auditVirement("Virement depuis " + virementDto.getNrCompteEmetteur() + " vers " + virementDto
                .getNrCompteBeneficiaire() + " d'un montant de " + virementDto.getMontant().toString()+" motif: "+ virementDto.getMotif());
        
		return virement;
	}

	
	
}
