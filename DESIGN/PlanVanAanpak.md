![](RackMultipart20220527-1-5f8315_html_a361432e5b77764a.png)

# Plan van Aanpak

# ArchiveTrawler

| Datum | : | 12-05-2022 |
 |
 |
| --- | --- | --- | --- | --- |
| Versie | : | 1.0 |
 |
 |
| AuteurStudent # | :: | Ryan L.J. van Lil 1818885
 |
 |
 |

| Datum | Versie | Omschrijving |
| --- | --- | --- |
| 12-05 | 1.0 | Eerste PvA, alles ingevuld en opgeschreven. |
| 19-05 | 1.1 |
 |

# Inhoudsopgave

[1Inleiding 3](#_Toc35506917)

[2Project beschrijving 3](#_Toc35506918)

[2.1Afbakening 4](#_Toc35506919)

[2.2Op te leveren eindresultaat 5](#_Toc35506920)

[2.3Uitgangspunten, randvoorwaarden en aannames 5](#_Toc35506921)

[2.4Relaties met andere projecten 5](#_Toc35506922)

[3Projectaanpak/fasering 6](#_Toc35506923)

[3.1Planning 7](#_Toc35506924)

[4Risico&#39;s 8](#_Toc35506925)

[5Referenties 8](#_Toc35506926)

# 1 Inleiding

Mijn klant in deze casus is mijn vader, Ton van Lil.

Een jarenlange hobby genealoog, verzamelt data over zijn voorouders.
Hiervoor beheert hij zijn eigen database en verwerkt deze data op zijn eigen website. 

Hiervoor zoekt hij in meerdere (stads)archieven en bezoekt deze ook. 


# 2 Project beschrijving

Het doel is dus om vraag afhankelijk van meerdere archieven URL&#39;s/informatie te verzamelen en te tonen.

De archieven worden nog steeds doorgaand bijgevuld dus een herhaaldelijke zoekfunctie zou handig zijn.

####Goed mogelijk doen:

- Ik wil het zo maken dat een user kan inloggen en zijn eigen instellingen heeft.
- Ik wil het mogelijk maken om zoekacties te beheren en te wijzigen.
- Ik wil het mogelijk maken om te kunnen zoeken zonder op slaan. Zo kan de gebruiker zoekfuncties vrij testen.

####Minder goed:

- Het lijkt me fijn als de gebruiker alerts kan instellen en hier veel keuze in krijgt, maar ik vermoed dat dit voorlopig nog geautomatiseerd zal zijn.
- Voldoende beveiliging op de gebruikersdata, maar dit wordt niet de hoofdmissie.

## 2.1 Afbakening

###Binnen dit project valt:

####- Een gebruikersdatabase met:

1. Inlogmogelijkheid.
2. Nieuwe gebruikers profiel toevoegen.
3. Persoonlijke account informatiepagina, inclusief wijzigingen doorgeven.
4. Wachtwoord wijzigen.
5. Wachtwoord vergeten.

####- Het aanmaken van zoekvragen die:

1. Live uitvoerbaar/aanpasbaar zijn.
2. Herbruikbare zoekacties.
3. Toegepast kunnen worden op meerdere websites tegelijk.
4. Automatisch door het systeem herhaald kunnen worden.
5. Het systeem de gebruiker melding geeft wanneer er nieuwe zoekresultaten zijn.

####- Het weergeven van een lijst URL&#39;s met de gevraagde content.

1. Een log bijhoudt van links waar de gebruiker al eerder op heeft geklikt.
2. Deze kan je als gebruiker zien dat er nieuwe/wijzigingen zijn.

- Als gebruiker een &quot;notificatie&quot; pagina met nieuwe bevindingen gebaseerd op opgeslagen zoekresultaten.

###Buiten de voorlopige scope van dit huidige project, valt:

- Het verwerken en weergeven wat er op deze pagina&#39;s staat, hier zal nog een archivist aan te pas komen.
- Het als gebruiker toevoegen van websites waarop gezocht wordt. Dit is voorlopig alleen voor dev-team.

![](RackMultipart20220527-1-5f8315_html_19423ad0d41fa099.png)

## 2.2 Op te leveren eindresultaat

### Use-Cases met samenvatting:

- Inloggen
_Inloggen in het systeem als gebruiker. Hierin worden je eigen instellingen opgeslagen, en afgeschermd voor het publiek._ ![](RackMultipart20220527-1-5f8315_html_90ed1369e2f4dab2.png)

  - ![](RackMultipart20220527-1-5f8315_html_fdec4423ebf28b8a.png)Nieuwe Gebruiker aanmaken.
_Mocht je nog geen gebruiker zijn kan je hier een account aanmaken._

  - ![](RackMultipart20220527-1-5f8315_html_4c25e117f80f5acc.png)Wachtwoord wijzigen of opvragen.
_Wachtwoord wijzigen of opnieuw instellen, mocht je deze kwijt zijn._

  - ![](RackMultipart20220527-1-5f8315_html_ed609d090c3f15cd.png)Persoonlijke informatie wijzigen.
_Je accountinformatie kan je hier wijzigen._

![](RackMultipart20220527-1-5f8315_html_1767a1b16d52ee35.png)

- Zoekopdracht aanmaken/Uitvoeren.

![](RackMultipart20220527-1-5f8315_html_84b6cfd95c0c39a0.png)_Hier kan je sleutelwoorden aangeven, zoeken op specifiekere datapunten en kiezen welke databases je in wil zoeken. Het systeem geeft duidelijke feedback d.m.v. pop-ups of alternatieven._

- ![](RackMultipart20220527-1-5f8315_html_d4f3152af79d7e36.png)Zoekresultaten bekijken
_Uiteraard kun je de zoekresultaten bekijken en direct op de link klikken. Op deze pagina wordt ook een log getoond hoe vaak de gebruiker deze link heeft geopend.
 De gebruiker kan zien wat zijn zoektermen zijn, en vervolgens kiezen om deze zoekopdracht op te slaan, te herhalen, een nieuwe zoekopdracht uit te voeren, of een al bestaande opnieuw uit te voeren._
  - Zoekopdracht opslaan.
_De gebruiker kan zoekopdrachten uitvoeren, opslaan._
    - Zoekopdracht opnieuw uitvoeren.
    - ![](RackMultipart20220527-1-5f8315_html_bc89ef16c823c7e5.png)Meldingen instellen voor nieuwe zoekresultaten.

  - Zoekresultaten exporteren/opslaan.
_Met een klik op de knop kunt u de getoonde resultaten exporteren. Deze worden dan op uw systeem opgeslagen in een user-vriendelijk format._
  - Opgeslagen zoekopdracht wijzigen
_In principe wordt het zoekscherm hier herladen met ingevulde gegevens. Deze kunt u dan wijzigen, en zullen vervolgens opgeslagen worden als nieuwe zoekopdracht._
    - Filters kiezen.
    - Bron websites kiezen.

- Nieuwe database om te doorzoeken toevoegen.
_Dit is voorlopig iets dat alleen de developer kan doen, wellicht als het project goed verloopt kunnen we in een later stadium dit als beheerder ook implementeren._

Er worden voorlopig geen verdere randvoorwaarden gesteld.

## 2.3Relaties met andere projecten

![](RackMultipart20220527-1-5f8315_html_176cf79c8b905269.png)

# 3 ![](RackMultipart20220527-1-5f8315_html_52e86a95669253e5.png)Projectaanpak/fasering

- Fase 1: Plan van aanpak
 Tools: Visual Paradigm en Word.
 \&lt;beslismoment?\&gt;

- Fase 2: Iteratie 1

Tools: Visual Paradigm, Javascript (VCS), Java (IntelliJ)
 \&lt;beslismoment?\&gt;

- Fase 3: Iteratie 2

Tools: Visual Paradigm, Javascript (VCS), Java (IntelliJ)

\&lt;Beslismoment?\&gt;

- Fase 4: Iteratie 3

Tools: Visual Paradigm, Javascript (VCS), Java (IntelliJ)

\&lt;Beslismoment?\&gt;

- Fase 5: Iteratie 4

Tools: Visual Paradigm, Javascript (VCS), Java (IntelliJ)

\&lt;Beslismoment?\&gt;

- Fase 6: Eindassessment met een poster
 \&lt;beslismoment?\&gt;

## 3.1Planning

![](RackMultipart20220527-1-5f8315_html_677c57aa13ad590c.png)

# 4Risico&#39;s

- Dataverlies:

_Tegengaan d.m.v. back-ups, intern en extern._

- Er te veel werk van maken:
_Iteratief te werken aan een opdracht per keer, en die ook helemaal af maken voor verder gaan met het volgende._
- Nog niet genoeg ervaring waardoor tijdsinschatting uit de hand loopt.

Niet echt een tegenmaatregel tegen, maar goede sprint reviews en contact met klant om teleurstellingen te voorkomen.

# 5Referenties

HU. (2022). _IPASS - Plan van Aanpak Template_.docx

![Shape1](RackMultipart20220527-1-5f8315_html_423d18d839da97fe.gif)

10
