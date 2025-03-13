Feature: the version can be retrieved
  Scenario: client makes call to GET /version
    When Start chrome op de homepage
    And Vul username in
    When Gebruiker meldt zich aan
    Then Gebruiker heeft een jwt token


