package mark.personal.simplejwt.model;

import lombok.Getter;
import mark.personal.simplejwt.util.Base64UrlEncoder;
import mark.personal.simplejwt.util.JWTSignatureAlgorithm;


@Getter
public class Signature implements JWTSerializable {

    private Header header;
    private Payload payload;

    private String key;

    private JWTSignatureAlgorithm jwtSignatureAlgorithm;

    public Signature(Header header, Payload payload, String key) {
        this.header = header;
        this.payload = payload;
        this.key = key;
        this.jwtSignatureAlgorithm = JWTSignatureAlgorithm.getSignatureAlgorithm(key, header);
    }

    @Override
    public String serialize() {
        String signData = String.format("%s.%s", header.serialize(), payload.serialize());
        return Base64UrlEncoder.encoding(jwtSignatureAlgorithm.getDataSignature(signData));
    }
}
