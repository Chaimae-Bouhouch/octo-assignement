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
import ma.octo.assignement.domain.Virement;
import static org.assertj.core.api.Assertions.assertThat;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@TestMethodOrder(OrderAnnotation.class)
public class VirementRepositoryTest {

  @Autowired
  private VirementRepository virementRepository;
  @Autowired
  private CompteRepository compteRepository;


  
  @SuppressWarnings("deprecation")
  @Order(1)
  @Test
  public void save() {
	  
	  Virement virement = new Virement();
	  virement.setCompteBeneficiaire(compteRepository.findByNrCompte("010000A000001000"));
	  virement.setCompteEmetteur(compteRepository.findByNrCompte("010000B025001000") );
	  virement.setMontant(new BigDecimal(200));
	  virement.setDateExecution(new Date());
	  virement.setMotif("premier Motif");
	  
	  Long id=virementRepository.save(virement).getId();
	  
	  Assert.notNull(virementRepository.findById(id).get());

  }

  @SuppressWarnings("deprecation")
  @Order(2)
  @Test
  public void findOne() {
	  Assert.notNull(virementRepository.findById((long) 5).get());
  }

  @Order(3)
  @Test
  public void findAll() {
	  List<Virement> l= virementRepository.findAll();
	  assertThat(l).size().isGreaterThan(0);
  }
}

