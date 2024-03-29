package archive.trawler.model;

import java.util.List;
import java.util.Objects;

/**Deze klasse wordt gebruikt om bij te houden of er veranderingen zijn in de zoekresultaten van een opgeslagen zoekopdracht.
 * TODO implementeer de datastorage van de zoekresultaten! */
public class ZoekResultaat {
    private List<String> resultatenURLsLijst;
    private boolean bekeken;
    private int oudeTellingURLs;
    private int nieuweTellingURLs;
    private int verschilTelling;

    public ZoekResultaat(List<String> resultatenURLsLijst, boolean bekeken, int oudeTellingURLs, int nieuweTellingURLs,
                         int verschilTelling) {
        this.resultatenURLsLijst = resultatenURLsLijst;
        this.bekeken = bekeken;
        this.oudeTellingURLs = oudeTellingURLs;
        this.nieuweTellingURLs = nieuweTellingURLs;
        this.verschilTelling = verschilTelling;
    }

    public List<String> getresultatenURLsLijst() {
        return resultatenURLsLijst;
    }

    public void setURLs(List<String> resultatenURLsLijst) {
        this.resultatenURLsLijst = resultatenURLsLijst;
    }

    public boolean isBekeken() {
        return bekeken;
    }

    public void setBekeken(boolean bekeken) {
        this.bekeken = bekeken;
    }

    public int getOudeTellingURLs() {
        return oudeTellingURLs;
    }

    public void setOudeTellingURLs(int oudeTellingURLs) {
        this.oudeTellingURLs = oudeTellingURLs;
    }

    public int getNieuweTellingURLs() {
        return nieuweTellingURLs;
    }

    public void setNieuweTellingURLs(int nieuweTellingURLs) {
        this.nieuweTellingURLs = nieuweTellingURLs;
    }

    public int getVerschilTelling() {
        return verschilTelling;
    }

    public void setVerschilTelling(int verschilTelling) {
        this.verschilTelling = verschilTelling;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZoekResultaat that = (ZoekResultaat) o;
        return bekeken == that.bekeken && oudeTellingURLs == that.oudeTellingURLs && nieuweTellingURLs == that.nieuweTellingURLs && verschilTelling == that.verschilTelling && Objects.equals(resultatenURLsLijst,
                                                                                                                                                                                              that.resultatenURLsLijst);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resultatenURLsLijst,
                            bekeken,
                            oudeTellingURLs,
                            nieuweTellingURLs,
                            verschilTelling);
    }

    @Override
    public String toString() {
        return "ZoekResultaten{" +
                "URLs=" + resultatenURLsLijst +
                ", bekeken=" + bekeken +
                ", oudeTellingURLs=" + oudeTellingURLs +
                ", nieuweTellingURLs=" + nieuweTellingURLs +
                ", verschilTelling=" + verschilTelling +
                '}';
    }
}
