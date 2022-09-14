package mark.personal.simplejwt.model;

import lombok.Data;
import mark.personal.simplejwt.util.Base64UrlEncoder;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Data
public class Payload implements JWTSerializable {
    private LocalDateTime issueAt;
    private LocalDateTime expiredAt;
    private String issue;
    private List<String> usableList;

    public Payload() {
        this.issue = "mark";
        this.issueAt = LocalDateTime.now();
        this.expiredAt = issueAt.plusMinutes(5);
        this.usableList = new ArrayList<>(10);
    }

    public Payload(LocalDateTime issueAt, LocalDateTime expiredAt, String issue, List<String> usableList) {
        this.issueAt = issueAt;
        this.expiredAt = expiredAt;
        this.issue = issue;
        this.usableList = usableList;
    }

    public Payload(LocalDateTime issueAt, String issue, List<String> usableList) {
        this.issueAt = issueAt;
        this.issue = issue;
        this.usableList = usableList;
        this.expiredAt = issueAt.plusMinutes(5);
    }

    @Override
    public String serialize() {
        Long iat = this.issueAt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        String iss = this.issue;
        Long exp = this.expiredAt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        StringBuilder builder = new StringBuilder();
        for (String usable : usableList) {
            builder.append(",\"")
                    .append(usable)
                    .append("\":true");
        }
        return Base64UrlEncoder.encoding(String.format("{\"iss\":\"%s\",\"iat\":%d,\"exp\":%d%s}", iss, iat, exp, builder).getBytes());
    }
}
