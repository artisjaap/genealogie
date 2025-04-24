package be.genealogie.domein.entiteit;

import be.genealogie.domein.GebruikerRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Table(name = "GEBRUIKER")
@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Gebruiker implements UserDetails {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="VOORNAAM", nullable = false)
    private String voornaam;

    @Column(name="NAAM", nullable = false)
    private String naam;

    @Column(name="EMAIL", unique = true, length = 100, nullable = false)
    private String email;

    @Column(name="WACHTWOORD", nullable = false)
    private String password;

    @CreationTimestamp
    @Column(updatable = false, name = "AANGEMAAKT_OP")
    private LocalDate createdAt;

    @UpdateTimestamp
    @Column(name = "GEWIJZIGD_OP")
    private LocalDate updatedAt;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "GEBRUIKER_MACHTIGING", joinColumns = @JoinColumn(name = "GEBRUIKER_ID"), inverseJoinColumns = @JoinColumn(name = "MACHTIGING_ID"))
    private List<Machtiging> machtigingen;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return machtigingen.stream()
                .map(Machtiging::getMachtiging)
                .map(GebruikerRole::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void voegMachtigingToe(GebruikerRole role) {
        this.machtigingen.add(Machtiging.builder().machtiging(role).build());
    }
}