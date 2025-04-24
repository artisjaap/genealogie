package be.genealogie.controller;

import be.genealogie.controller.dto.LoginUserDto;
import be.genealogie.controller.dto.RegisterUserDto;
import be.genealogie.domein.entiteit.Gebruiker;
import be.genealogie.service.AuthenticationService;
import be.genealogie.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthenticatieController.class)
@org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc(addFilters = false)
class AuthenticatieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authenticationService;

    @MockBean
    private JwtService jwtService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testRegister() throws Exception {
        RegisterUserDto registerUserDto = RegisterUserDto.builder()
                .email("test@example.com")
                .password("password")
                .build();
        LoginUserDto loginUserDto = LoginUserDto.builder()
                .email("test@example.com")
                .password("password")
                .build();
        Gebruiker gebruiker = new Gebruiker();
        gebruiker.setNaam("Test");
        gebruiker.setVoornaam("User");

        when(authenticationService.signup(any(RegisterUserDto.class))).thenReturn(null);
        when(authenticationService.authenticate(any(LoginUserDto.class))).thenReturn(gebruiker);
        when(jwtService.generateToken(any(Gebruiker.class))).thenReturn("fake-jwt-token");
        when(jwtService.getExpirationTime()).thenReturn(3600L);

        mockMvc.perform(post("/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerUserDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("fake-jwt-token"))
                .andExpect(jsonPath("$.expiresIn").value(3600L))
                .andExpect(jsonPath("$.gebruiker.naam").value("Test"))
                .andExpect(jsonPath("$.gebruiker.voornaam").value("User"));
    }

    @Test
    void testAuthenticate() throws Exception {
        LoginUserDto loginUserDto = LoginUserDto.builder()
                .email("test@example.com")
                .password("password")
                .build();
        Gebruiker gebruiker = new Gebruiker();
        gebruiker.setNaam("Test");
        gebruiker.setVoornaam("User");

        when(authenticationService.authenticate(any(LoginUserDto.class))).thenReturn(gebruiker);
        when(jwtService.generateToken(any(Gebruiker.class))).thenReturn("fake-jwt-token");
        when(jwtService.getExpirationTime()).thenReturn(3600L);

        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginUserDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("fake-jwt-token"))
                .andExpect(jsonPath("$.expiresIn").value(3600L))
                .andExpect(jsonPath("$.gebruiker.naam").value("Test"))
                .andExpect(jsonPath("$.gebruiker.voornaam").value("User"));
    }
}
