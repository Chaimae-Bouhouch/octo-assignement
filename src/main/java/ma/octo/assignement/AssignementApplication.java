package ma.octo.assignement;

import ma.octo.assignement.domain.Compte;

import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.domain.Versement;
import ma.octo.assignement.domain.Virement;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.UtilisateurRepository;
import ma.octo.assignement.repository.VersementRepository;
import ma.octo.assignement.repository.VirementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.expression.ParseException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

//cette classe permet de simplifier la visualisation des données de notre db

@SpringBootApplication(scanBasePackages = {"ma.octo.assignement", "ma.octo.assignement.service", "ma.octo.assignement.web", "ma.octo.assignement.dto", "ma.octo.assignement.domain"})
public class AssignementApplication implements CommandLineRunner {
	
	//PasswordEncoder pour crypter notre mot de passe
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//pour effectuer un verment/versement on a besoin d'instancier un CompteRepository
	@Autowired
	private CompteRepository compteRepository;
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	private VirementRepository virementRepository;
	
	@Autowired
	private VersementRepository versementRepository;
	
	

	public static void main(String[] args) {
		SpringApplication.run(AssignementApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		
		//instanciation de premier utilisateur avec un role=USER
		Utilisateur utilisateur1 = new Utilisateur();
		utilisateur1.setUsername("user1");
		utilisateur1.setPassword(passwordEncoder.encode("1234"));
		utilisateur1.setLastname("lastName1");
		utilisateur1.setFirstname("firstName1");
		utilisateur1.setGender("Female");
		Date date1 = StringToDate("2015-12-06");
		utilisateur1.setBirthdate(date1);
		
		//RoleUser pour l'enthentification (Spring Security)**************
		utilisateur1.setRole("USER");

		//permet d'enregistrer notre utilisateur
		utilisateurRepository.save(utilisateur1);

        //instanciation de deuxieme utilisateur avec un role=ADMIN
		Utilisateur utilisateur2 = new Utilisateur();
		utilisateur2.setUsername("user2");
		utilisateur2.setPassword(passwordEncoder.encode("azerty"));
		utilisateur2.setLastname("lastName2");
		utilisateur2.setFirstname("firstName2");
		utilisateur2.setGender("Male");
		Date date2 = StringToDate("2015-11-06");
		utilisateur2.setBirthdate(date2);
		
		//RoleUser pour l'enthentification (Spring Security)*****************
		utilisateur2.setRole("ADMIN");

		//permet d'enregistrer notre utilisateur
		utilisateurRepository.save(utilisateur2);

		//instanciation des comptes
		Compte compte1 = new Compte();
		compte1.setNrCompte("010000A000001000");
		compte1.setRib("RIB1");
		compte1.setSolde(BigDecimal.valueOf(200000L));
		compte1.setUtilisateur(utilisateur1);

		compteRepository.save(compte1);

		Compte compte2 = new Compte();
		compte2.setNrCompte("010000B025001000");
		compte2.setRib("RIB2");
		compte2.setSolde(BigDecimal.valueOf(140000L));
		compte2.setUtilisateur(utilisateur2);

		compteRepository.save(compte2);

		//instanciation d'un virement
		Virement v = new Virement();
		v.setMontant(BigDecimal.TEN);
		v.setCompteBeneficiaire(compte2);
		v.setCompteEmetteur(compte1);
		v.setDateExecution(new Date());
		v.setMotif("Octo Assignment 2021");

		virementRepository.save(v);
		
		//instanciation d'un versement
		Versement versement = new Versement();
		versement.setMontant(BigDecimal.TEN);
		versement.setCompteBeneficiaire(compte2);
		versement.setNom_prenom_emetteur("Chaimae Bouhouch");
		versement.setDateExecution(new Date());
		versement.setMotif("Octo Assignment 2021");

		versementRepository.save(versement);
	}
	public Date StringToDate(String s) throws java.text.ParseException {

	    Date result = null;
	    try{
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        result  = dateFormat.parse(s);
	    }

	    catch(ParseException e){
	        e.printStackTrace();

	    }
	    return result ;
	}
}
