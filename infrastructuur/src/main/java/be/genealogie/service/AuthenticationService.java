package be.genealogie.service;

import be.genealogie.controller.dto.LoginUserDto;
import be.genealogie.controller.dto.RegisterUserDto;
import be.genealogie.domein.entiteit.Gebruiker;
import be.genealogie.domein.repository.GebruikerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final GebruikerRepository gebruikerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public Gebruiker signup(RegisterUserDto input) {
        Gebruiker gebruiker = Gebruiker.builder()
                .voornaam(input.getNaam())
                .naam(input.getVoornaam())
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()))
                .build();

        return gebruikerRepository.save(gebruiker);
    }

    public Gebruiker authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return gebruikerRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}