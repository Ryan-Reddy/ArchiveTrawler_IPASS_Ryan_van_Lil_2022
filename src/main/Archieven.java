package main;

import java.util.Objects;

public class Archieven {
    private String naam;
    private String APIofURIConstructor;

     public Archieven(String naam, String APIofURIConstructor) {
         this.naam = naam;
         this.APIofURIConstructor = APIofURIConstructor;
     }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getAPIofURIConstructor() {
        return APIofURIConstructor;
    }

    public void setAPIofURIConstructor(String APIofURIConstructor) {
        this.APIofURIConstructor = APIofURIConstructor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Archieven)) return false;
        Archieven archieven = (Archieven) o;
        return Objects.equals(getNaam(), archieven.getNaam()) && Objects.equals(getAPIofURIConstructor(), archieven.getAPIofURIConstructor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNaam(), getAPIofURIConstructor());
    }

    @Override
    public String toString() {
        return "Archieven{" +
                "naam='" + naam + '\'' +
                ", APIofURIConstructor='" + APIofURIConstructor + '\'' +
                '}';
    }
}
