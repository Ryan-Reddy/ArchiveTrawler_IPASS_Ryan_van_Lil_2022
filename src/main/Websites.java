package main;

import java.util.Objects;

public class Websites {
    private String URL;
    private int aantalKeerBezocht;

    public Websites(String URL, int aantalKeerBezocht) {
        this.URL = URL;
        this.aantalKeerBezocht = aantalKeerBezocht;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public int getAantalKeerBezocht() {
        return aantalKeerBezocht;
    }

    public void setAantalKeerBezocht(int aantalKeerBezocht) {
        this.aantalKeerBezocht = aantalKeerBezocht;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Websites)) return false;
        Websites websites = (Websites) o;
        return getAantalKeerBezocht() == websites.getAantalKeerBezocht() && Objects.equals(getURL(), websites.getURL());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getURL(), getAantalKeerBezocht());
    }

    @Override
    public String toString() {
        return "Websites{" +
                "URL='" + URL + '\'' +
                ", aantalKeerBezocht=" + aantalKeerBezocht +
                '}';
    }
}
