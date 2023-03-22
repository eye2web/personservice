package eye2web.personservice.personservice.util;

import java.util.Base64;

public class Base64Utils {
    private Base64Utils() {
    }

    public static String Encode(final String input) {
        return Base64.getEncoder().encodeToString(input.getBytes());
    }
}
