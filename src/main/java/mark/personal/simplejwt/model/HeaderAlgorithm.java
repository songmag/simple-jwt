package mark.personal.simplejwt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum HeaderAlgorithm {
    HS256("HmacSHA256");
    private final String name;
}
