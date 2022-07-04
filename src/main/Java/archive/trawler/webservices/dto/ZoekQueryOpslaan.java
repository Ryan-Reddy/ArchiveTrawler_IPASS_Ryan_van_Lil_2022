package archive.trawler.webservices.dto;

/** Een DTO om een nieuwe zoekopdracht mee op te slaan in de User Instance van deze gebruiker.
 * @Attribute keywords; Keywords van de query op het moment de enige.
 * @Attribute archiveAmsterdam; Op het moment de enige in gebruik. Komt dan binnen als archiveAmsterdam=archiveAmsterdam
 */
public class ZoekQueryOpslaan {
    public String keywords;
    public boolean archiveAmsterdam;
}
