Feature: Registreer een gebruiker
  @Browser @Recording
  Scenario: Registreer een gebruiker
    When Start chrome op de homepage
    And Klik op registreer link
    And Vul john.doe@gmail.com in als email
    And Vul wachtwoord in als wachtwoord
    And Vul John in als voornaam
    And Vul Doe in als achternaam
    And Klik op registreer link
    And wacht 2 seconden
    And Klik op menu zoeken

  @Browser @Recording
  Scenario: Login met gebruiker
    When Start chrome op de homepage
    And Vul john.doe@gmail.com in als email
    And Vul wachtwoord in als wachtwoord
    And Klik op login link