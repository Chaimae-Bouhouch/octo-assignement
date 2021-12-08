package ma.octo.assignement.web;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import javax.transaction.Transactional;
import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.domain.Versement;
import ma.octo.assignement.domain.Virement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;




@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@TestMethodOrder(OrderAnnotation.class)
@WithMockUser(username="user2", roles= {"ROLE_ADMIN"})
class mainControllerTest {
	
	@Order(1)
	@Test
	public void getComptesTest()
	  throws Exception {
		MainContoller controller = new MainContoller();
		List<Compte> response = controller.loadAllCompte();
	    assertThat(response).size().isGreaterThan(0);
	}
	@Order(2)
	@Test
	public void getUtilisateursTest()
	  throws Exception {
		MainContoller controller = new MainContoller();
		List<Utilisateur> response = controller.loadAllUtilisateur();
	    assertThat(response).size().isGreaterThan(0);
	}
	
	@Order(3)
	@Test
	public void getVirementTest()
	  throws Exception {
		MainContoller controller = new MainContoller();
		List<Virement> response = controller.loadAllVirements();
	    assertThat(response).size().isGreaterThan(0);
	}
	
	@Order(4)
	@Test
	public void getVersementTest()
	  throws Exception {
		MainContoller controller = new MainContoller();
		List<Versement> response = controller.loadAllVersements();
	    assertThat(response).size().isGreaterThan(0);
	}
	
}
