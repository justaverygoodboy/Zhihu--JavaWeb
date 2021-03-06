package utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class Md5Util {
    private static String SALT = "%&^*^)";
    public static String crypt(String str) throws NoSuchAlgorithmException {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("String cannot be null or zero length");
        }
        String newStr = str + SALT;
        StringBuffer hexString = new StringBuffer();
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(newStr.getBytes());
        byte[] hash = md.digest();
        for (int i = 0; i < hash.length; i++) {
            if ((0xff & hash[i]) < 0x10) {
                hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
            } else {
                hexString.append(Integer.toHexString(0xFF & hash[i]));
            }
        }
        return hexString.toString();
    }
}
