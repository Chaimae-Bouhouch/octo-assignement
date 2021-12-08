package ma.octo.assignement.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TRANSACTION")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name= "TYPE_TRANSACTION", discriminatorType= DiscriminatorType.STRING)
public abstract class Transaction {
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private Long id;

	  public Transaction() {
		super();
	}

	public Transaction(BigDecimal montant, Date dateExecution, Compte compteBeneficiaire, String motif) {
		super();
		this.montant = montant;
		this.dateExecution = dateExecution;
		this.compteBeneficiaire = compteBeneficiaire;
		this.motif = motif;
	}

	@Column(precision = 16, scale = 2, nullable = false)
	  private BigDecimal montant;

	  @Column
	  @Temporal(TemporalType.TIMESTAMP)
	  private Date dateExecution;

	  @ManyToOne
	  private Compte compteBeneficiaire;

	  @Column(length = 200)
	  private String motif;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getMontant() {
		return montant;
	}

	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}

	public Date getDateExecution() {
		return dateExecution;
	}

	public void setDateExecution(Date dateExecution) {
		this.dateExecution = dateExecution;
	}

	public Compte getCompteBeneficiaire() {
		return compteBeneficiaire;
	}

	public void setCompteBeneficiaire(Compte compteBeneficiaire) {
		this.compteBeneficiaire = compteBeneficiaire;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}
}
