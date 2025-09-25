Feature: een nieuw natuurlijk persoon toevoegen en dan opzoeken
  @BrowserRecording
  Scenario: Een natuurlijk persoon toevoegen
    Given een lege applicatie met testgebruiker john, doe met email john.doe@gmail.com en wachtwoord test
    And Start chrome op de homepage
    When gebruiker john.doe@gmail.com logt in met wachtwoord test
    And klik op nieuw persoon actie
    And Vul John in als voornaam
    And Vul Doe in als achternaam
    And Klikt op geslacht vrouw
    And Vult 01/01/2000 in als geboortedatum
    And klik op opslaan actie

    And klik op relatie toevoegen actie
    And Vul test in als voornaam
    And Vul test in als achternaam
    And Klikt op geslacht man
    And Vult 10/12/1999 in als geboortedatum
    And klik op opslaan actie