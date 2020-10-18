package blockchainhie.Operations;

import java.security.MessageDigest;
import java.security.SecureRandom;

/**
 *
 * @author Shahiryar
 */
public class Hashed {
    
    private static final int size = 16;
    /**
     * Generate salt for hashing
     * @return byte[] random salt
     * @throws Exception 
     */
    public static byte[] getSecureRand() throws Exception {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] bytes = new byte[size];
        sr.nextBytes(bytes);
        return bytes;
    }

    /**
     * 
     * @param passwd the password to hash
     * @param algo the algorithm used for hashing password
     * @return String hash
     * @throws Exception 
     */
    public static String hash(String passwd, String algo) throws Exception {
        MessageDigest md = MessageDigest.getInstance(algo);
        md.update(passwd.getBytes());
        byte[] hashcode = md.digest();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hashcode.length; i++) {
            sb.append(
                    Integer.toHexString(0xFF & hashcode[i])
            );
        }
        return sb.toString();
    }
}
