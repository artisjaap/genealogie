Feature: Belgisch Koningshuis stamboom
  Scenario: Volledige opbouw van het belgische kongingshuis
    Given een lege applicatie met testgebruiker john, doe met email john.doe@gmail.com en wachtwoord test
      And Gebruiker logged zich in
     When een natuurlijk persoon [P1] Koning der Belgen, Leopold I, man geboren op 16/12/1790 te plaats, overleden op 10/12/1865 te plaats wordt toegevoegd
      And gehuwd met [P2] van Orleans, Louisa-Maria, vrouw op 01/01/1812 te plaats, die geboren is op 01/01/1812 te plaats, overleden op 01/01/1850 te plaats
      And kind [P3] met Koning der Belgen, Leopold II, man geboren op 09/04/1835 te plaats, overleden op 17/12/1909 te plaats
      And kind [P4] met Koning der Belgen, Philippe, man geboren op 01/01/1837 te plaats, overleden op 01/01/1905 te plaats
      And kind [P5] met Koning der Belgen, Charlotte, man geboren op 01/01/1840 te plaats, overleden op 01/01/1927 te plaats
      And en natuurlijk persoon [P3] gehuwd met [P6] Aartshertoging van Oostenrijk, Maria-Henriette, vrouw op 01/01/1812 te plaats, die geboren is op 01/01/1836 te plaats, overleden op 01/01/1902 te plaats
      And kind [P7] met Koning der Belgen, Louise, vrouw geboren op 09/04/1835 te plaats, overleden op 17/12/1909 te plaats
      And kind [P8] met Koning der Belgen, Leopold, man geboren op 09/04/1835 te plaats, overleden op 17/12/1909 te plaats
      And kind [P9] met Koning der Belgen, Stefanie, vrouw geboren op 09/04/1835 te plaats, overleden op 17/12/1909 te plaats
      And kind [P10] met Koning der Belgen, Clementine, vrouw geboren op 09/04/1835 te plaats, overleden op 17/12/1909 te plaats
      And en natuurlijk persoon [P11] gehuwd met [P6] van Hohenzollern, Maria, vrouw op 01/01/1812 te plaats, die geboren is op 01/01/1836 te plaats, overleden op 01/01/1902 te plaats
      And kind [P12] met Koning der Belgen, Boudewijn, man geboren op 09/04/1835 te plaats, overleden op 17/12/1909 te plaats
      And kind [P12] met Koning der Belgen, Henriette, vrouw geboren op 09/04/1835 te plaats, overleden op 17/12/1909 te plaats
      And kind [P12] met Koning der Belgen, Josephine, vrouw geboren op 09/04/1835 te plaats, overleden op 17/12/1909 te plaats
      And kind [P13] met Koning der Belgen, Albert I, vrouw geboren op 09/04/1835 te plaats, overleden op 17/12/1909 te plaats
    Then overzicht alle personen