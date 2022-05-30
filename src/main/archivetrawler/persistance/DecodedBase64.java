package main.archivetrawler.persistance;

public class DecodedBase64 {
    public final byte[] bytez;
    public final String mime;

    public DecodedBase64(byte[] bytez, String mime) {
        this.bytez = bytez;
        this.mime = mime;
    }
}
