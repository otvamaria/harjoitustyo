package syksy24.harjoitustyo.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import syksy24.harjoitustyo.domain.Henkilo;
import syksy24.harjoitustyo.domain.HenkiloRepository;
import syksy24.harjoitustyo.domain.Viesti;
import syksy24.harjoitustyo.domain.ViestiRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    HenkiloRepository henkilorepository;

    @Autowired
    ViestiRepository viestiRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {   
        Henkilo kayttaja = henkilorepository.findByUsername(username);

        if (kayttaja == null) {
            throw new UsernameNotFoundException("Käyttäjää ei löydy: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
            kayttaja.getUsername(),  
            kayttaja.getPasswordHash(), 
            AuthorityUtils.createAuthorityList(kayttaja.getRole()) 
        );
    }

    public Henkilo haeHenkiloKayttajatunnuksella(String username) {
        return henkilorepository.findByUsername(username);
    }

    public boolean isViestiOwner(Long id, String username) {
        Viesti viesti = viestiRepository.findById(id).orElse(null);
        if (viesti == null) {
            return false;
        }
        return viesti.getHenkilo().getUsername().equals(username);
    }


}


