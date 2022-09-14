package mark.personal.simplejwt.model;

import lombok.Data;
import mark.personal.simplejwt.util.Base64UrlEncoder;
import java.nio.charset.StandardCharsets;

@Data
public class Header implements JWTSerializable{
    private String type;
    private String alg;

    public Header() {
        this.type = "JWT";
        this.alg = "HS256";
    }

    public Header(String type, String alg) {
        this.type = type;
        this.alg = alg;
    }

    @Override
    public String serialize() {
        return Base64UrlEncoder.encoding(String.format("{\"typ\":\"%s\",\"alg\":\"%s\"}", type, alg).getBytes(StandardCharsets.UTF_8));
    }
}
