
# Ontwerp

# ArchiveTrawler

| Datum | : | 12-05-2022 |

| AuteurStudent # | :: | Ryan L.J. van Lil 1818885
 |
 |
 |

#### Revisiehistorie

| Datum | Versie | Omschrijving                                    |
|-------| --- |-------------------------------------------------|
| 27-05 | 1.0 | Eerste Ontwerp, alles ingevuld en opgeschreven. |
| 00-00 | 1.1 |
 |

## Inhoudsopgave
| nr.  | Naam                                                      |
|------|-----------------------------------------------------------|
 | 1    | [Inleiding](#1-inleiding)                                 |
 | 1.1 | [Doelgroep (lezersgroep)](#1.1-doelgroep) |
 | 1.2 | [Leeswijzer](#1.2-leeswijzer)                           |
| 2    | [Overzicht         ](#2-overzicht)                        |
| 3    | [Uses cases        ](#3-use-cases)                        |
| 3.1  | [Actoren           ](#3.1-actoren)                        |
| 3.2  | [Use case templates](#3.2-use-case-templates)             |
| 3.3  | [Wireframes        ](#3.3-wireframes)                     |
| 4    | [Domeinmodel       ](#4-domeinmodel)                      |
| 5    | [Technologieën     ](#5-technologieën)                    |
| 6    | [Overdracht        ](#6-overdracht)                       |
| 7    | [Referenties       ](#7-referenties)                      | 

## 1 Inleiding

Neem hier op hoe het ontwerp tot stand gekomen is alsmede voor wie het bedoeld is met eventueel een lezersadvies.

### 1.1 Doelgroep

Het type klant dat ik probeer te bereiken:


##### Psychografie

- Wil graag van bepaalde historische mensen informatie vinden.
- Wil graag in bepaalde archieven zoeken.
- Vindt het belangrijk dat ze niet dubbel werk verrichten.


##### Uitdagingen
- Het vinden van nieuwe data die betrekking heeft tot hun onderzoek.
- Het overzicht behouden met al bekeken data.

##### Voorkeurskanalen
- De website zelf.
- E-mail notificaties.
- Een desktopversie.


#### 1.2 Leeswijzer
///////////////////////////////////////////////////

## 2 Overzicht

Het systeem heeft een gebruikersomgeving waar je kan inloggen, registreren en uitloggen. 

Het systeem neemt zoektermen aan, 
deze zet hij vervolgens om in queries naar meerdere websites. 
De opgeleverde links worden vervolgens getoond.

Per gebruiker worden de links geregistreerd als onbekeken. 
Tot deze worden geopend, dan logt het systeem wanneer en hoe vaak deze bekeken zijn.
Dit is voor de gebruiker ook zichtbaar.

De zoektermen kunnen ook opgeslagen worden in een herhalende zoekopdracht. 
Het systeem voert opgeslagen zoekopdrachten met regelmaat uit (1x per week), 
en notificeert de gebruiker als er nieuwe links gevonden worden.

De gebruiker kan in zijn eigen omgeving ook zijn gegevens wijzigen. En zijn zoekopdrachten inzien.

## 3 Use cases
![](DIAGRAMS/Use Case Diagram1.png)

De use case diagram is redelijk uitvoerig. 
Per use case zijn er sub use-cases vermeldt, dit ter verdieping.

De user is feitelijk de enige gebruiker van het systeem.
Verder kan de developer ook nieuwe websites op af te speuren toevoegen, 
dit is heir gerepresenteerd omdat het wel een use case is die uiteindelijk de gebruiker wellicht zelf ook wil kunnen.

### 3.1 Actoren

##### User: 

De user is in dit geval de eindgebruiker. Dat zou de klant kunnen zijn, of andere eventuele klanten in de toekomst. 
De user is in zijn eigen omgeving oppermachtig, en grenst daarin niet aan andere users. 
Mag alles wijzigen en alle use cases uitvoeren, behalve een website toevoegen. 

##### Developer:

De developer is in dit geval het programmeer team. 
Deze voert alleen een use case uit die eigenlijk buiten de scope van dit project valt.


### 3.2 Use Case Templates

Use case templates, per use case een paragraaf met de template beschrijving e.d. zoals geleerd bij Modelling.



| nr.  | Naam                                                      |
|------|-----------------------------------------------------------|
 | 1    | [Inleiding](#1-inleiding)                                 |
 | 1.1 | [Doelgroep (lezersgroep)](#1.1-doelgroep) |
 | 1.2 | [Leeswijzer](#1.2-leeswijzer)                           |

<br/>

#### ID 0.1   Naam Zoekopdracht starten op meerdere sites.
| Actoren              | User                                                                                                                                               |
|----------------------|----------------------------------------------------------------------------------------------------------------------------------------------------|
| Samenvatting         | Proof of concept:<br/>Zoekopdracht starten op externe website, <br/>en deze geladen openen.                                                        |
| Precondities         | User heeft de site open, vult een zoekterm in en klikt op ok.                                                                                      |
| Stappenplan/scenario | De user vult zoektermen in de invoervakken,<br/>klikt op zoek, en het systeem opent meerdere tabs met de ingevulde zoektermen op aparte databases. |
| Postcondities        | De user kan nu navigeren naar de nieuw geopende tabs.                                                                                              |

<br/>

#### ID 1.0   Naam Inloggen.
| Actoren              | User                                                                                                                                                                                                                       |
|----------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Samenvatting         | Het inloggen als gebruiker in zijn eigen omgeving.                                                                                                                                                                         |
| Precondities         | Er is een database met alle gebruikers, en de gebruiker heeft hier een account in.                                                                                                                                         |
| Stappenplan/scenario | De user vult zijn inloggegevens in, en klikt op inloggen. <br/>Het systeem verifiert of deze overeenkomen met een gebruiker.<br/>Als dit lukt is de gebruiker nu ingelogd.<br/>Zo niet, dan toont het systeem een melding. |
| Postcondities        | De user is nu ingelogd in zijn eigen omgeving.<br/>Of<br/>De gebruiker kan het nogmaals proberen/een nieuw account aanmaken.                                                                                               |
<br/>

#### ID 1.1   Naam Nieuwe gebruiker aanmaken.
| Actoren              | User                                                                                                                                                                                                                                                       |
|----------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Samenvatting         | Nieuw gebruikersaccount aanmaken.                                                                                                                                                                                                                          |
| Precondities         | De gebruiker is nog niet ingelogd. En kijkt naar de inlogpagina.                                                                                                                                                                                           |
| Stappenplan/scenario | De gebruiker klikt op de link: <Nieuwe gebruiker aanmaken><br/>Vervolgens vult deze de gevraagde gegevens in. Het systeem verifieert of er nog geen gebruiker is met die gegevens. Als dit alles lukt is er een nieuw account aangemaakt, en krijgt de gebruiker bevestiging. |
| Postcondities        | De gebruiker heeft nu een account aangemaakt, of deze bestond al en wordt doorverwezen naar wachtwoord resetten: Use case 1.2                                                                                                                              |

<br/>

#### ID 1.2   Naam Wachtwoord wijzigen of opvragen.
| Actoren              | User                                                                                                                                                                    |
|----------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Samenvatting         | Wachtwoord van een bestaand account wijzigen.                                                                                                                           |
| Precondities         | De gebruiker heeft een account en is op de wachtwoord wijzig pagina.                                                                                                    |
| Stappenplan/scenario | De gebruiker vult de benodigde accountgegevens in, het systeem controleert dit en geeft vervolgens als de info klopt: Een email met mogelijkheden tot wachtwoord reset. |
| Postcondities        | De gebruiker heeft zijn wachtwoord gewijzigd en kan nu in het vervolg hiermee inloggen.                                                                                 |

<br/>

#### ID 1.3  Naam Persoonlijke informatie wijzigen.
| Actoren              | User                                                                                                                                       |
|----------------------|--------------------------------------------------------------------------------------------------------------------------------------------|
| Samenvatting         | Accountinformatie wijzigen. (behalve het wachtwoord)                                                                                       |
| Precondities         | De gebruiker is ingelogd en kijkt naar de Accountinformatie wijzigen pagina.                                                               |
| Stappenplan/scenario | De gebruiker vult de benodigde informatie in, en klikt op opslaan. Het systeem wijzigt de bestaande gegevens en geeft hiervan een melding. |
| Postcondities        | De gegevens van het account zijn gewijzigd.                                                                                                |

<br/>

#### ID 2.0   Naam Zoekopdracht aanmaken.
| Actoren              | User                                                                                                                                                                       |
|----------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Samenvatting         | De hoofdactie van dit systeem.                                                                                                                                             |
| Precondities         | De gebruiker is ingelogd, en wil iets zoeken.                                                                                                                              |
| Stappenplan/scenario | De gebruiker vult de gegevens in die opgezocht moeten worden. En klikt op zoeken.<br/>Het systeem neemt deze gegevens aan en maakt hier zoekqueries van en voert deze uit. |
| Postcondities        | De gebruiker kan nu de zoekresultaten laten tonen door het systeem.                                                                                                        |

<br/>

#### ID 2.1   Naam Zoekresultaten bekijken.
| Actoren              | User                                                                                                                                                                                                                                                      |
|----------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Samenvatting         | Zoekresultaten bekijken.                                                                                                                                                                                                                                  |
| Precondities         | Er is een zoekopdracht aangemaakt door de gebruiker.                                                                                                                                                                                                      |
| Stappenplan/scenario | De gebruiker kiest een zoekopdracht of heeft deze net aangemaakt. <br/>Vervolgens kiest deze om deze te tonen.<br/>Het systeem voert de zoekopdracht uit/heeft dit onlangs gedaan en toont de resultaten.<br/>De gebruiker kan ze nu zien en doorklikken. |
| Postcondities        | De gebruiker heeft de zoekresultaten gezien, kan doorklikken naar de betreffende links, <br/>en kan ook de resultaten zelf exporteren/opslaan. De zoekopdracht zelf kan nu ook opgeslagen worden.                                                              |

<br/>

#### ID 2.2   Naam Zoekopdracht opslaan.
| Actoren              | User                                                                                                                                                           |
|----------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Samenvatting         | Zoekopdracht opslaan.                                                                                                                                          |
| Precondities         | Er is een zoekopdracht aangemaakt door de gebruiker, de resultaten kunnen ingekeken worden.                                                                    |
| Stappenplan/scenario | De gebruiker kiest er voor om de zoekopdracht op te slaan. <br/>Het systeem controleert of deze nog niet opgeslagen staat, en schrijft vervolgens een log bij. |
| Postcondities        | De zoekopdracht is opgeslagen, de gebruiker kan deze van nu af aan op een later moment bekijken en beheren.                                                    |

<br/>

#### ID 2.2.1   Naam Zoekopdracht herhalen.
| Actoren              | User                                                                                                                     |
|----------------------|--------------------------------------------------------------------------------------------------------------------------|
| Samenvatting         | Een eerder opgeslagen zoekopdracht herhalen.                                                                             |
| Precondities         | Er is eerder een zoekopdracht opgeslagen.                                                                                |
| Stappenplan/scenario | De gebruiker selecteert een zoekopdracht uit de lijst. Het systeem voert deze uit en toont de resultaten.                |
| Postcondities        | De gebruiker kan nu de zoekresultaten aanklikken, maar ook inzien of de links al eerder bekeken zijn en zo ja, hoe vaak. |

<br/>

#### ID 2.2.2   Naam Meldingen van nieuwe resultaten ontvangen.
| Actoren              | User                                                                                                                                                                                                                 |
|----------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Samenvatting         | De gebruiker ontvangt meldingen van nieuwe resultaten op opgeslagen zoekresultaten. Of via de mail of in de gebruikers omgeving.                                                                                     |
| Precondities         | Er zijn zoekopdrachten opgeslagen.                                                                                                                                                                                   |
| Stappenplan/scenario | Met enige regelmaat, stel 1x per dag, voert het systeem alle zoekopdrachten uit. <br/>Het systeem controleert of er sinds de vorige keer nieuwe resultaten bij zijn gekomen. <br/>Zo ja, dan geeft deze een melding. |
| Postcondities        | De gebruiker weet nu dat er nieuwe resultaten zijn, en kan deze opzoeken via het systeem.                                                                                                                            |

<br/>

#### ID 2.3   Naam Zoekresultaten opslaan/exporteren.
| Actoren              | User                                                                                                                                    |
|----------------------|-----------------------------------------------------------------------------------------------------------------------------------------|
| Samenvatting         | Zoekresultaten die getoond worden kunnen ook geexporteerd worden. Deze worden als een lijst van links in een bestand gedownload.        |
| Precondities         | Er is een zoekopdracht uitgevoerd.                                                                                                      |
| Stappenplan/scenario | De gebruiker kiest om de zoekresultaten op te slaan. <br/>Het systeem maakt hier een mooi bestandje van en biedt deze ter download aan. |
| Postcondities        | De gebruiker kan nu op zijn/haar systeem kiezen waar deze op te slaan.                                                                  |

<br/>

#### ID 2.4   Naam Zoekopdracht wijzigen.
| Actoren              | User          |
|----------------------|---------------|
| Samenvatting         | zzzzzzzzzzzzz |
| Precondities         | zzzzzzzzzzzzz |
| Stappenplan/scenario | zzzzzzzzzzzzz |
| Postcondities        | zzzzzzzzzzzzz |

<br/>

#### ID 2.4.1   Naam Filters kiezen.
| Actoren              | User          |
|----------------------|---------------|
| Samenvatting         | zzzzzzzzzzzzz |
| Precondities         | zzzzzzzzzzzzz |
| Stappenplan/scenario | zzzzzzzzzzzzz |
| Postcondities        | zzzzzzzzzzzzz |

<br/>

#### ID 2.4.2   Naam Kiezen welke websites.
| Actoren              | User          |
|----------------------|---------------|
| Samenvatting         | zzzzzzzzzzzzz |
| Precondities         | zzzzzzzzzzzzz |
| Stappenplan/scenario | zzzzzzzzzzzzz |
| Postcondities        | zzzzzzzzzzzzz |

<br/>

#### ID 3.0   Naam Websites om te doorzoeken toevoegen.
| Actoren              | User          |
|----------------------|---------------|
| Samenvatting         | zzzzzzzzzzzzz |
| Precondities         | zzzzzzzzzzzzz |
| Stappenplan/scenario | zzzzzzzzzzzzz |
| Postcondities        | zzzzzzzzzzzzz |

<br/>

### 3.3 Wireframes

De wireframes bij de use cases.

## 4 Domeinmodel

De meeste entiteiten uit het conceptueel datamodel hebben een tegenhanger in de vorm van een JAVA domeinklasse. Van de student wordt verwacht dat er een UML klassen diagram wordt opgeleverd voor de domeinklassen. Zorg dat je UML klassendiagram volledig is.

Aandachtspunten:

Neem alle attributen op met bijbehorende types

Neem bij elke associatie de multipliciteiten en de rolnamen op.

Maak gebruik van de juiste notatie. Zie de cursus OOAD

Neem het diagram op met daarbij een korte beschrijving van alle entiteiten. Zie bij de cursus modelling hoe je dit model maakt.

Entiteit	Beschrijving
| | | | | | | | |

Daarnaast kent een domein model eventueel extra regels op het domein (business rules) die je niet goed in UML kan vertalen. Deze regels neem je hier puntsgewijs op.

## 5 Technologieën

Voor het realiseren van de applicatie wordt gebruik van de volgende methodieken en technieken: UML, Java, SQL, HTML, CSS, J2EE (Servlets), Rest (Jax-RS), Applicatieserver

& HTTP-protocol en een datastore. Beschrijf ook welke frameworks je gebruikt, hiervan neem je ook de versie en het licentiemodel op.

## 6 Overdracht

Hier wordt een installatiehandleiding en een sumiere gebruikershandleiding beschreven. Ook eventuele gebruikersnamen en wachtwoorden moet bekend worden om de werking van het programma aan te tonen.

## 7 Referenties

Bronnenlijst in APA stijl.


HU. (2022). _IPASS - Plan van Aanpak Template_.docx
HU. (2022). _IPASS - Ontwerp Template_.docx

