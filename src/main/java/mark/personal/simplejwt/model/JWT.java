package mark.personal.simplejwt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class JWT implements JWTSerializable {

    private Header header;
    private Payload payload;
    private Signature signature;

    private String key;

    public JWT(Header header, Payload payload, String key) {
        this.header = header;
        this.payload = payload;
        this.key = key;
    }

    public String serialize() {
        signature = new Signature(header, payload, key);
        return String.format("%s.%s.%s",header.serialize(), payload.serialize(), signature.serialize());
    }
}
