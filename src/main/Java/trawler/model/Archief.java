package trawler.model;

import java.util.Objects;

public class Archief {
    private String naam;
    private String basisURI;

     public Archief(String naam, String basisURI) {
         this.naam = naam;
         this.basisURI = basisURI;
     }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBasisURI() {
        return basisURI;
    }

    public void setBasisURI(String basisURI) {
        this.basisURI = basisURI;
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
