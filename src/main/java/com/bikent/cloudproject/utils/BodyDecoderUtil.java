package com.bikent.cloudproject.utils;

import java.util.Base64;

public class BodyDecoderUtil {
    public String getJsonBody(String base64EncodedBody) {
        byte[] decodedBytes = Base64.getDecoder().decode(base64EncodedBody);
        return new String(decodedBytes);
    }
}
