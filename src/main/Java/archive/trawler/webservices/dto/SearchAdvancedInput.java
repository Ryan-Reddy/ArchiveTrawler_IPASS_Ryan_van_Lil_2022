package archive.trawler.webservices.dto;

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
