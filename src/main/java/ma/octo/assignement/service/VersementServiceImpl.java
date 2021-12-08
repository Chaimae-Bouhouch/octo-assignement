package ma.octo.assignement.service;


import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Versement;
import ma.octo.assignement.dto.VersementDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.VersementRepository;


@Service
public class VersementServiceImpl implements VersementService{
	

	public static final BigDecimal MONTANT_MAXIMAL = new BigDecimal(10000);
	
	@Autowired
	AuditService auditService;
	@Autowired
	VersementRepository versementRepository;
	@Autowired
	CompteRepository compteRepository;
	
	@Override
	public Versement createVersement(VersementDto versementDto) throws SoldeDisponibleInsuffisantException, CompteNonExistantException, TransactionException {
		
		
        Compte compteBeneficiaire = compteRepository.findByNrCompte(versementDto.getNrCompteBeneficiaire());

        
        if (compteBeneficiaire == null) {
            throw new CompteNonExistantException("Compte Non existant");
        }

        if (versementDto.getMontant() == null) {
            throw new TransactionException("Montant null");
        } else if (versementDto.getMontant().compareTo(BigDecimal.ZERO) == 0) {
            throw new TransactionException("Montant vide");
        }
        else if (versementDto.getMontant().compareTo(BigDecimal.ZERO) < 0) {
            throw new TransactionException("Montant négatif");
        }else if (versementDto.getMontant().compareTo(MONTANT_MAXIMAL) > 0) {
            throw new TransactionException("Montant maximal est 10 000");
        }else if (versementDto.getMontant().compareTo(BigDecimal.TEN) < 0) {
            throw new TransactionException("Montant inférieur à 10");
        }
        
        if (versementDto.getMotif().length() <= 0) {
            throw new TransactionException("Motif vide");
        }

        compteBeneficiaire.setSolde(compteBeneficiaire.getSolde().add(versementDto.getMontant()));
        compteRepository.save(compteBeneficiaire);
        

        Versement versement = new Versement();
        versement.setDateExecution(new Date());
        versement.setCompteBeneficiaire(compteBeneficiaire);
        versement.setNom_prenom_emetteur(versementDto.getNomPrenomEmetteur());
        versement.setMontant(versementDto.getMontant());
        versement.setMotif(versementDto.getMotif());;
        
        versementRepository.save(versement);
        
        auditService.auditVirement("Virement de la part de " + versementDto.getNomPrenomEmetteur() + " vers " + versementDto
                .getNrCompteBeneficiaire() + " d'un montant de " + versementDto.getMontant().toString()+" motif: "+ versementDto.getMotif());
        
		return versement;
	}
	}
