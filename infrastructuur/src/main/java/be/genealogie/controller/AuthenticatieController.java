package be.genealogie.controller;

import be.genealogie.controller.dto.LoginResponseDto;
import be.genealogie.controller.dto.LoginUserDto;
import be.genealogie.controller.dto.LoginUserResponseDto;
import be.genealogie.controller.dto.RegisterUserDto;
import be.genealogie.domein.entiteit.Gebruiker;
import be.genealogie.service.AuthenticationService;
import be.genealogie.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticatieController {

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;


    @PostMapping("/signup")
    public ResponseEntity<LoginResponseDto> register(@RequestBody RegisterUserDto registerUserDto) {
        Gebruiker registeredUser = authenticationService.signup(registerUserDto);
        return authenticate(LoginUserDto.builder()
                .email(registerUserDto.getEmail())
                .password(registerUserDto.getPassword())
                .build());
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> authenticate(@RequestBody LoginUserDto loginUserDto) {
        Gebruiker authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponseDto loginResponse = LoginResponseDto.builder()
                .token(jwtToken)
                .expiresIn(jwtService.getExpirationTime())
                .gebruiker(LoginUserResponseDto.builder()
                        .email(loginUserDto.getEmail())
                        .naam(authenticatedUser.getNaam())
                        .voornaam(authenticatedUser.getVoornaam())
                        .build())
                .build();

        return ResponseEntity.ok(loginResponse);
    }
}

