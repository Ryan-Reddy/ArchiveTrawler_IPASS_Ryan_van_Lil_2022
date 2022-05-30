package main.archivetrawler.model;

import java.util.Objects;

public class Archieven {
    private String naam;
    private String basisURI;

     public Archieven(String naam, String basisURI) {
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
        Archieven archieven = (Archieven) o;
        return Objects.equals(naam,
                              archieven.naam) && Objects.equals(basisURI,
                                                                archieven.basisURI);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naam,
                            basisURI);
    }

    @Override
    public String toString() {
        return "Archieven{" +
                "naam='" + naam + '\'' +
                ", APIofURIConstructor='" + basisURI + '\'' +
                '}';
    }
}
