package ma.octo.assignement.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.repository.UtilisateurRepository;

@Service
public class AuthentificationService implements UserDetailsService{

	 @Autowired
	    private UtilisateurRepository utilisateurRepository;
	
	  @Override
	    public User loadUserByUsername(String userName) throws UsernameNotFoundException {
	        Utilisateur u = utilisateurRepository.findByUsername(userName);
	        
	        if(u == null){
	            throw new UsernameNotFoundException("Utilisateur non trouvé : " + userName);
	        }

	        User user = createUser(u);

	        return user;
	    }
	  
	  	private User createUser(Utilisateur u) {
	        return new User(u.getUsername(), u.getPassword(), createAuthorities(u));
	    }
	  	
	  	private Collection<GrantedAuthority> createAuthorities(Utilisateur u) {
	        Collection<GrantedAuthority> authorities = new ArrayList<>();
	        authorities.add(new SimpleGrantedAuthority("ROLE_"+u.getRole()));
	        return  authorities;
	    }
	
	
}
