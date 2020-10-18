package Cryptography;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Shahiryar
 */
public class SymmetricEncryption {
    //secret char
    private static final String SECRET = "twinkletwinlelittlestars";

    //algo
    private static final String ALGORITHM = "AES";
    private Key key;

    public Key getKey() {
        return key;
    }

    private static Key genKey() {
        
        return new SecretKeySpec(Arrays.copyOf(SECRET.getBytes(), 16), ALGORITHM);
    }

    //cipher
    private Cipher cipher;

    public SymmetricEncryption() {
        try {
            cipher = Cipher.getInstance(ALGORITHM);
            key = genKey();
        } catch (NoSuchAlgorithmException | NoSuchPaddingException ex) {
            ex.printStackTrace();
        }
    }

    //encrypt //input: original data // output: ciphertext
    public String encrpyt (String data) throws Exception{
        cipher.init(Cipher.ENCRYPT_MODE, key);
        //cipher intrpduces fundamnetal methods: upadte(), doFinal()
        byte[] ciphertext = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(ciphertext);
    }
    
    //decrpyt //input: ciphertext  // output: original data
    public String decrypt(String cipherText)throws Exception{
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] databytes = cipher.doFinal(Base64.getDecoder().decode(cipherText));
        return new String (databytes);
    }
}
