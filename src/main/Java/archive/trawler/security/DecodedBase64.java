package archive.trawler.security;

public class DecodedBase64 {
    public final byte[] bytez;
    public final String mime;

    /**
     * DecodedBase64 decodeerd
     * @param bytez
     * @param mime
     */
    public DecodedBase64(byte[] bytez, String mime) {
        this.bytez = bytez;
        this.mime = mime;
    }
}
