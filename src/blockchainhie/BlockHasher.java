package blockchainhie;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 *
 * @author Shahiryar
 */
public class BlockHasher {

    private static final int SIZE = 16;
    private static final String ALGO = "SHA-512";
//    private static final String ALGO = "SHA-256";
//    private static final String ALGO = "MD5";;

    /**
     * Calculates has for a given object ( in this case a block)
     * @param block byte[] from the block object
     * @return String hash of the block
     */
    public static String hash(byte[] block) {
        try {
            MessageDigest digest;
            digest = MessageDigest.getInstance(ALGO);
            digest.update(block);
            byte[] hashcode = digest.digest();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < hashcode.length; i++) {
                sb.append(
                        Integer.toHexString(0xFF & hashcode[i])
                );
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println("error");
            return null;
        }
    }

    /**
     * Generates a random byte array used for salting
     * @return byte[] random salt
     * @throws Exception
     */
    public static byte[] getSecureRand() throws Exception {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] bytes = new byte[SIZE];
        sr.nextBytes(bytes);
        return bytes;
    }
}
