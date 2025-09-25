Feature: Registreer een gebruiker
  @BrowserRecording
  Scenario: Registreer een gebruiker
    When Start chrome op de homepage
    And klik op registreer actie
    And Vul john.doe@gmail.com in als email
    And Vul wachtwoord in als wachtwoord
    And Vul John in als voornaam
    And Vul Doe in als achternaam
    And klik op registreer actie
    And wacht 2 seconden
    And Klik op menu zoeken

  @BrowserRecording
  Scenario: Login met gebruiker
    When Start chrome op de homepage
    And Vul john.doe@gmail.com in als email
    And Vul wachtwoord in als wachtwoord
    And klik op login actie