Plan van aanpak (uitgebreid)

Doelen
- Project up-to-date brengen met recente stabiele platformversies (Java/Spring Boot & Angular) zonder functionaliteit te breken.
- Beveiligingsrisico’s reduceren via dependency- en code-audits, en configuratie-hardening.
- Testdekking verhogen (Cucumber + unit/integratie) om regressies te voorkomen.

Scope en uitgangspunten
- Geen functionele uitbreidingen; uitsluitend updates, fixes en testen.
- Minimale downtime; voorkeur voor incrementele upgrades met tussentijdse validatie.
- Doel is upgraden naar de meest recente stabiele/LTS-versies per onderdeel op moment van uitvoeren. Exacte versies worden bevestigd vóór uitvoering (release notes check + compatibiliteitsmatrix).

Architectuurcontext (samenvatting)
- Backend: Spring Boot (Java) – infrastructuur/build.gradle bevat Spring afhankelijkheden.
- Frontend: Angular (directory: frontend).
- End-to-end: Cucumber-tests (directory: cucumber) met Testcontainers-ondersteuning.

1. Backend: Spring Boot upgrade
1.1 Voorbereiding
- Inventariseer huidige Spring Boot- en Java-versie in build.gradle (root en infrastructuur module).
- Controleer compatibiliteit: Java-versie, Spring Boot release notes (breaking changes), Spring Security wijzigingen, Testcontainers/junit.
- Stel doelversie vast (meest recente stabiele) en noteer migratienota.

1.2 Migratiestappen
- Update plugins en dependency management (spring-boot-gradle-plugin, spring-dependency-management indien gebruikt).
- Pas Spring Boot BOM/versies aan in build.gradle (alle modules).
- Herbouw en fix compile errors; vervang/verwijder deprecated API’s conform release notes.
- Spring Security: verifieer WebSecurityConfigurerAdapter-migratie of filter chain-config; update SecurityConfiguration indien nodig.
- Configuratie: herzie application YAML’s (infrastructuur/src/main/resources: genealogie.yml en genealogie-in-memory.yml) op gewijzigde property-namen.
- Testcontainers/JUnit: verifieer versies en lifecycle-annotaties.

1.3 Validatie
- Run unit- en integratietests lokaal.
- Start applicatie met in-memory profiel; voer rooktest (health, basisflows) uit.
- Documenteer migratiebevindingen en benodigde codewijzigingen in CHANGELOG-upgrade-sectie.

2. Frontend: Angular upgrade
2.1 Voorbereiding
- Controleer huidige Angular major-versie (package.json in frontend).
- Bepaal doelversie (meest recente stabiele) en upgradepad (eventueel per major stap).

2.2 Migratiestappen
- Voer ng update @angular/core @angular/cli (eventueel stapsgewijs per major) uit en synchroniseer peer dependencies.
- Update third-party libs (RxJS, Angular Material, router, forms) conform ng update hints.
- Los breaking changes op (standalone APIs, form typing, strictness flags, ESLint regels).

2.3 Validatie
- npm run build en npm test (indien aanwezig) moeten zonder fouten slagen.
- Handmatige rooktest op kritieke schermen (bijv. persoon-fiche component): laden, CRUD, navigatie.

3. Beveiliging en kwetsbaarheden
3.1 Dependency-audit
- Backend: voer Gradle dependency scan (OWASP Dependency-Check of Snyk) uit; bekijk CVE’s en maak prioriteitenlijst (Critical/High eerst).
- Frontend: npm audit en (indien mogelijk) Snyk. Update kwetsbare packages binnen compatibiliteit; vervang indien nodig.

3.2 Code- en config-hardening
- Spring Security headers (HSTS, X-Content-Type-Options, X-Frame-Options/Frame-ancestors, CSP waar mogelijk).
- Verifieer CORS-instellingen, session/cookie flags (SameSite, Secure), en CSRF-beleid afhankelijk van frontend-backend interactie.
- Geheimenbeheer: geen secrets in broncode of YAML; gebruik env vars of secrets manager.

3.3 Rapportage
- Voeg SECURITY-REPORT.md toe met overzicht: bevindingen, CVE’s, genomen acties, resterende risico’s met mitigatie en planning.

4. Testuitbreiding (Cucumber + overige)
4.1 Analyse huidige dekking
- Inventariseer bestaande features (cucumber/src/test/resources/features). Bepaal ontbrekende scenario’s rond: authenticatie/autorisatie, validatiefouten, randgevallen (null/lege velden), foutafhandeling.

4.2 Nieuwe scenario’s (indicatief)
- Persoon toevoegen: succesvolle flow, validatiefout (verplicht veld), duplicaatdetectie (indien van toepassing).
- Autorisatie: endpoint-toegang voor niet-ingelogde/geautoriseerde gebruiker (403/401).
- Zoeken/filtreren: resultaten, lege resultaten.
- Edge cases: lange input, speciale tekens.

4.3 Technisch
- Gebruik Testcontainers voor isolatie; seed testdata waar nodig.
- Voeg step-definitions toe en hergebruik bestaande BrowserSteps waar passend.

4.4 Validatie
- Alle Cucumber-suites moeten groen draaien in CI.

5. CI/CD aanpassingen
- Voeg jobs toe: backend build+tests, frontend build+tests, security scans (periodiek), e2e (Cucumber).
- Publiceer artefacten en rapportages (test reports, SECURITY-REPORT.md, dependency-check HTML).

6. Planning (indicatief)
- Week 1: inventarisatie, versiekeuze, POC-upgrade backend en frontend, risicoanalyse.
- Week 2: volledige backend upgrade + fixes + tests.
- Week 3: volledige frontend upgrade + fixes + tests.
- Week 4: security-audits, hardening, extra Cucumber-scenario’s, CI/CD.

7. Risico’s en mitigatie
- Breaking changes → incrementele upgrades, uitgebreide tests, feature flags waar nodig.
- Build failures → lock versies, rollback via branches/tags.
- Performance regressie → meet basislijn vooraf, voer micro-benchmarks/rooktests uit na upgrade.

8. Rollback-strategie
- Tag huidige main vóór start.
- Werk op feature branches; merge pas na groene pipeline.
- Documenteer config-wijzigingen expliciet om rollback te vereenvoudigen.

9. Definition of Done (DoD)
- Build groen voor alle modules (backend + frontend) en alle tests slagen.
- Security-rapport aanwezig, critical/high issues opgelost of gedocumenteerd met plan.
- Handleidingen/migratienota bijgewerkt (CHANGELOG, README waar nodig).
- Geen open TODO’s of FIXME’s gerelateerd aan de upgrade.