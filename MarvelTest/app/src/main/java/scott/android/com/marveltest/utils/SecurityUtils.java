package scott.android.com.marveltest.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by android5 on 3/10/16.
 */
public class SecurityUtils {

    private static final String MD5 = "MD5";

    private SecurityUtils() {
        throw new UnsupportedOperationException("please don't instantiate this class");
    }

    public static String generateMD5(String text) throws NoSuchAlgorithmException {
        final MessageDigest messageDigest = MessageDigest.getInstance(MD5);
        messageDigest.reset();
        messageDigest.update(text.getBytes());
        byte[] digest = messageDigest.digest();
        BigInteger bigInt = new BigInteger(1, digest);
        StringBuilder hashText = new StringBuilder(bigInt.toString(16));
        while (hashText.length() < 32) {
            hashText.append("0").append(hashText);
        }
        return hashText.toString();
    }

}
