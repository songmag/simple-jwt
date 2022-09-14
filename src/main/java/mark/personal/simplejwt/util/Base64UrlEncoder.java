package mark.personal.simplejwt.util;

import java.util.Base64;

public class Base64UrlEncoder {

    public static String encoding(byte[] data){
        String result =  Base64.getUrlEncoder().encodeToString(data);
        return result.replaceAll("=","");
    }
}
