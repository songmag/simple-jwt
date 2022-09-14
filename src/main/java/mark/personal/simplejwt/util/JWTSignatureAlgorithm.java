package mark.personal.simplejwt.util;

import mark.personal.simplejwt.model.Header;
import mark.personal.simplejwt.model.HeaderAlgorithm;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class JWTSignatureAlgorithm {
    private Mac mac;
    private SecretKeySpec secretKeySpec;

    private JWTSignatureAlgorithm() {
    }

    private JWTSignatureAlgorithm(Mac mac, SecretKeySpec secretKeySpec) {
        this.mac = mac;
        this.secretKeySpec = secretKeySpec;
    }

    public static JWTSignatureAlgorithm getSignatureAlgorithm(String key, Header header) {
        try {
            Mac mac = Mac.getInstance(HeaderAlgorithm.valueOf(header.getAlg()).getName());
            SecretKeySpec secret_key = new SecretKeySpec(
                    key.getBytes(StandardCharsets.UTF_8),
                    HeaderAlgorithm.valueOf(header.getAlg()).getName()
            );
            mac.init(secret_key);
            return new JWTSignatureAlgorithm(
                    mac,
                    secret_key
            );
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    private String byteToHexString(byte[] byteArray) {
        return new BigInteger(byteArray).toString(16);
    }

    public byte[] getDataSignature(String data) {
        return mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
    }
}
