package ma.octo.assignement.repository;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;
import ma.octo.assignement.domain.Versement;
import static org.assertj.core.api.Assertions.assertThat;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@TestMethodOrder(OrderAnnotation.class)
public class VersementRepositoryTest {

	@Autowired
	private VersementRepository versementRepository;
	@Autowired
	private CompteRepository compteRepository;

	@SuppressWarnings("deprecation")
	@Order(1)
	@Test
	public void save() {

		Versement versement = new Versement();
		versement.setCompteBeneficiaire(compteRepository.findByNrCompte("010000A000001000"));
		versement.setNom_prenom_emetteur("Chaimae Bouhouch");
		versement.setMontant(new BigDecimal(100));
		versement.setDateExecution(new Date());
		versement.setMotif("premier Motif");

		Long id = versementRepository.save(versement).getId();

		Assert.notNull(versementRepository.findById(id).get());

	}

	@SuppressWarnings("deprecation")
	@Order(2)
	@Test
	public void findOne() {
		Assert.notNull(versementRepository.findById((long) 6).get());
	}

	@Order(3)
	@Test
	public void findAll() {
		List<Versement> l = versementRepository.findAll();
		assertThat(l).size().isGreaterThan(0);
	}
}
