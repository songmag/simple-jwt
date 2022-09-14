package mark.personal.simplejwt;

import mark.personal.simplejwt.model.Header;
import mark.personal.simplejwt.model.JWT;
import mark.personal.simplejwt.model.Payload;
import mark.personal.simplejwt.model.Signature;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SimpleJwtApplicationTests {

    @Test
    @DisplayName("JWT 를 생성하여 검증한다 예제는 jwt.io 에서 가져옴")
    void testJWT(){
        String jwtResult = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJtYXJrIiwiaWF0IjoxNjYzMjE0NDAwMDAwLCJleHAiOjE2NjMyMTQ3MDAwMDAsImh0dHBzOi8vdGVzdC5jb20iOnRydWV9.CpfXCIttMrbIKg2bo3Enh_fddO7h-SEwPZVD25FXtGc";

        List<String> usableList = new ArrayList<>();
        usableList.add("https://test.com");
        Header header = new Header();
        Payload payload = new Payload(
                LocalDateTime.of(2022,9,15,13,0,0),
                "mark",
                usableList
        );
        JWT jwt = new JWT(header, payload, "your-256-bit-secret");
        Assertions.assertThat(jwt.serialize()).isEqualTo(jwtResult);
    }

    @Test
    @DisplayName("16진수 변환을 Java bigInteger 와 web 에 있는 알고리즘이 같은지 검증")
    void hexTest(){
        String testItem = "hexItem";
        String hexString = byteToHexString(testItem.getBytes(StandardCharsets.UTF_8));
        String bigIntegerHexString = new BigInteger(testItem.getBytes(StandardCharsets.UTF_8)).toString(16);
        Assertions.assertThat(hexString).isEqualTo(bigIntegerHexString);
    }
    private String byteToHexString(byte[] byteArray) {
        StringBuffer sb = new StringBuffer(byteArray.length * 2);
        for(byte b : byteArray) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    @Test
    void contextLoads() {
    }

}
