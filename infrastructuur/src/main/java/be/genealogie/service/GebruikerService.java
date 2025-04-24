package be.genealogie.service;

import be.genealogie.domein.GebruikerRole;
import be.genealogie.domein.entiteit.Gebruiker;
import be.genealogie.domein.entiteit.Machtiging;
import be.genealogie.domein.repository.GebruikerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GebruikerService  implements UserDetailsService {
    private final GebruikerRepository gebruikerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Gebruiker gebruiker = gebruikerRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
        List<GrantedAuthority> authorities = gebruiker.getMachtigingen().stream()
                .map(Machtiging::getMachtiging)
                .map(GebruikerRole::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toUnmodifiableList());
        return new User(gebruiker.getEmail(), gebruiker.getPassword(), authorities
        );
    }
}
