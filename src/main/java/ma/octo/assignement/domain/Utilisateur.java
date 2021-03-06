package ma.octo.assignement.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "UTILISATEUR")
public class Utilisateur implements Serializable {
  
	private static final long serialVersionUID = 1L;

@Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(length = 10, nullable = false, unique = true)
  private String username;
  
  //pour authentifier*********************
  @Column
  private String password;
  
  //Spring security********************
  @Column
  private String role;
  
  @Column(length = 10, nullable = false)
  private String gender;

  @Column(length = 60, nullable = false)
  private String lastname;

  @Column(length = 60, nullable = false)
  private String firstname;

  @Temporal(TemporalType.DATE)
  private Date birthdate;

  public String getRole() {
	return role;
}

public void setRole(String role) {
	this.role = role;
}

public String getPassword() {
	return password;
	
}

public void setPassword(String password) {
	this.password = password;
}


  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public Date getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(Date birthdate) {
    this.birthdate = birthdate;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
