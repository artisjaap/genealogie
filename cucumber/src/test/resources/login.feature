Feature: the version can be retrieved
  Scenario: client makes call to GET /version
    When Open brower
    When Gebruiker meldt zich aan
    Then Gebruiker heeft een jwt token