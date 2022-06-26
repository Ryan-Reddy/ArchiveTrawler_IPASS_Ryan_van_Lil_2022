package archive.trawler.model;

import archive.trawler.persistance.Community;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/** Deze klasse behoud alle informatie van individuele (externe) archieven.
 **/
public class Archief implements Serializable {
    private @Getter @Setter String naam;
    private @Getter @Setter String basisURI;
    /**
     * Deze klasse behoud alle informatie van individuele (externe) archieven.
     * @param naam Hierin staat de naam van het archief
     * @param basisURI Hierin staat de basis URI, waarop je vervolgens kan bouwen met queryParameters
     */
     public Archief(String naam, String basisURI) {
         this.naam = naam;
         this.basisURI = basisURI;
         Community.addArchiefToMap(this);
     }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Archief archief = (Archief) o;
        return Objects.equals(naam,
                              archief.naam) && Objects.equals(basisURI,
                                                                archief.basisURI);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naam,
                            basisURI);
    }

    @Override
    public String toString() {
        return "Archief{" +
                "naam='" + naam + '\'' +
                ", APIofURIConstructor='" + basisURI + '\'' +
                '}';
    }
}
