package com.finalproject.client_app_etickpark.helper;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class BasicHeaderHelper {
    public static String createBasicToken(String username, String password) {
        String auth = username + ":" + password;
        return Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.US_ASCII));    
    }
}
