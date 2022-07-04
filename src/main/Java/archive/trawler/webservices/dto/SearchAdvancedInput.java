package archive.trawler.webservices.dto;

/** Een DTO om een nieuwe zoekopdracht mee uit te voeren.
 * @Attribute keywords; Keywords van de query op het moment de enige.
 * @Attribute achternaam;
 * @Attribute voorNaam;
 * @Attribute tussenvoegsel;
 * @Attribute jaarVan;
 * @Attribute jaarTot;
 * @Attribute archiveAmsterdam; Op het moment de enige in gebruik. Komt dan binnen als archiveAmsterdam=archiveAmsterdam
 * @Attribute openArchive;
 * @Attribute archiveDublin;
 * @Attribute archiveNoordHolland;
 */
public class SearchAdvancedInput {
    public String keywords;
    public String achternaam;
    public String voorNaam;
    public String tussenvoegsel;
    public int jaarVan;
    public int jaarTot;
    public boolean archiveAmsterdam;
    public boolean openArchive;
    public boolean archiveDublin; // no CORS misschien een latere implementatie
    public boolean archiveNoordHolland; // no CORS misschien een latere implementatie
}
