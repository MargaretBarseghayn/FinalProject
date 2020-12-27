package md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class MD5 {
    public static String md5DigestJavaHexString(Integer message) {
        StringBuilder sb = new StringBuilder();
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(message.byteValue());
            byte[] digest = md.digest();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
