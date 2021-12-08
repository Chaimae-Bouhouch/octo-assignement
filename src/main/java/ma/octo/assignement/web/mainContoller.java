package ma.octo.assignement.web;

import ma.octo.assignement.domain.Compte;


import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.domain.Versement;
import ma.octo.assignement.domain.Virement;
import ma.octo.assignement.dto.VersementDto;
import ma.octo.assignement.dto.VirementDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.UtilisateurRepository;
import ma.octo.assignement.repository.VersementRepository;
import ma.octo.assignement.repository.VirementRepository;
import ma.octo.assignement.service.VersementService;
import ma.octo.assignement.service.VirementService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
class MainContoller {

	public static final BigDecimal MONTANT_MAXIMAL = new BigDecimal(10000);

	Logger LOGGER = LoggerFactory.getLogger(MainContoller.class);

	@Autowired
	private VirementService virementService;
	@Autowired
	private VersementService versementService;
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private VirementRepository virementRepository;
	@Autowired
	private VersementRepository versementRepository;

	@Autowired
	private UtilisateurRepository utilisateurRepository;

	public MainContoller() {
		super();
	}

	@PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
	@GetMapping("/listeVirements")
	List<Virement> loadAllVirements() {
		List<Virement> all = virementRepository.findAll();

		if (CollectionUtils.isEmpty(all)) {
			return null;
		} else {
			return all;
		}
	}

	@PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
	@GetMapping("/listeVersements")
	List<Versement> loadAllVersements() {
		List<Versement> all = versementRepository.findAll();

		if (CollectionUtils.isEmpty(all)) {
			return null;
		} else {
			return all;
		}
	}

	@PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
	@GetMapping("/listeComptes")
	List<Compte> loadAllCompte() {
		List<Compte> all = compteRepository.findAll();

		if (CollectionUtils.isEmpty(all)) {
			return null;
		} else {
			return all;
		}
	}

	@PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
	@GetMapping("/listeUtilisateurs")
	List<Utilisateur> loadAllUtilisateur() {
		List<Utilisateur> all = utilisateurRepository.findAll();

		if (CollectionUtils.isEmpty(all)) {
			return null;
		} else {
			return all;
		}
	}

	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	@PostMapping("/newVirement")
	@ResponseStatus(HttpStatus.CREATED)
	public Virement createVirement(@RequestBody VirementDto virementDto)
			throws SoldeDisponibleInsuffisantException, CompteNonExistantException, TransactionException {

		return virementService.createVirement(virementDto);

	}

	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	@PostMapping("/newVersement")
	@ResponseStatus(HttpStatus.CREATED)
	public Versement createVersement(@RequestBody VersementDto versementDto)
			throws SoldeDisponibleInsuffisantException, CompteNonExistantException, TransactionException {

		return versementService.createVersement(versementDto);

	}

}
