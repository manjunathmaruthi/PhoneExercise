package com.cisco.phonexercise.utility;

import java.util.Base64;

public class UserUtility {

    /**
     * Encode a string into Base64.
     *
     * @param pwd the password to be encrypted
     * @return the encrypted string
     */
    public static String encode(String pwd) {
        return Base64.getEncoder().encodeToString(pwd.getBytes());
    }

    /**
     * Decode Base64 encoded value
     *
     * @param encodedPassword the password to be decrypted
     * @return the decrypted string
     */
    public static String decode(String encodedPassword) {
        String decodedPWD = "";
        byte[] byteValueBase64Decoded = Base64.getDecoder().decode(encodedPassword);
        return new String(byteValueBase64Decoded);
    }

}
