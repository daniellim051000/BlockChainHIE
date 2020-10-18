package Cryptography;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

public class AsymmetricEncryption {

    private Cipher cipher;
    private final String ALGORITHM;
    private final String PUBLICKEY_FILE;
    private final String PRIVATEKEY_FILE;
    //constructor

    public AsymmetricEncryption(AppConfig config) {
        this.ALGORITHM = config.ALGORITHM;
        this.PUBLICKEY_FILE = config.getPUBLICKEY_FILE();
        this.PRIVATEKEY_FILE = config.getPRIVATEKEY_FILE();
        try {
            cipher = Cipher.getInstance(this.ALGORITHM);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
        }
    }

    //encrpyt
    public String encrypt(String data, PublicKey key) throws Exception {
        String ciphertext = "";
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherBytes = cipher.doFinal(data.getBytes());
        ciphertext = Base64.getEncoder().encodeToString(cipherBytes);
        return ciphertext;
    }

    /**
     * takes an input of text longer thab 117 bytes and converts that text into
     * blocks of 100 bytes (chars) then uses the encryption function to encrypt
     * each block and concat that to a string which in the end is returned
     *
     * @param recordString String of anysize
     * @param publicKey PublicKey for encryption
     * @return encrypted message (this message is not decryptable if put
     * directly from decryption, it has to be decrypted using the special
     * cooresponding funciton)
     * @throws Exception
     */
    public String encryptLongText(String recordString, PublicKey publicKey) throws Exception {
        String encrypted = "";
        List<String> recordSringBlocks = AsymmetricEncryption.getByteBlock(recordString, 100);
        for (String block : recordSringBlocks) {
            String thisC = encrypt(block, publicKey);
            encrypted = encrypted + thisC;
        }
        return encrypted;
    }

    /**
     * Takes a string which contains encrypted blocks of a long string, cuts
     * them in to blocks of 172 char and then decrypts each block these blocks
     * are concatinated to form the full message.
     *
     * @param encrypted String, this has to be output from the encrytedLongText
     * or else there are possibility of error or other logical issues
     * @param privateKey PrivateKey to decrypt the message
     * @return String decrypted from the input string
     * @throws Exception
     */
    public String decryptLongCipher(String encrypted, PrivateKey privateKey) throws Exception {
        String decrypted = "";
        List<String> recordEncryptedBlocks = AsymmetricEncryption.getByteBlock(encrypted, 172);
        for (String block : recordEncryptedBlocks) {
            decrypted = decrypted + decrypt(block, privateKey);
        }
        return decrypted;
    }

    /**
     * Converts the String into blocks (chunks) of given size
     *
     * @param data String to be converted in to chunks
     * @param size Int size of each chunk
     * @return LinkedList<String>() the chunks of the given string input
     */
    public static List<String> getByteBlock(String data, int size) {
        List<String> blocks = new ArrayList<>();
        int currentIndex = 0;
        int nextIndex = size;
        while (nextIndex < data.length()) {
            blocks.add(data.substring(currentIndex, nextIndex));
            currentIndex = nextIndex;
            nextIndex += size;
        }
        if (currentIndex != data.length()) {
            blocks.add(data.substring(currentIndex, data.length()));
        }

        return blocks;
    }

    //decrpyt
    public String decrypt(String ciphertext, PrivateKey key) throws Exception {
        String origin = "";
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] cipherBytes = Base64.getDecoder().decode(ciphertext);
        origin = new String(cipher.doFinal(cipherBytes));
        return origin;
    }

    //get the key from file system in files 
    public PublicKey getPublicKey() throws Exception {

        byte[] keyBytes = Files.readAllBytes(Paths.get(this.PUBLICKEY_FILE));
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance(this.ALGORITHM).generatePublic(spec);
    }

    public PrivateKey getPrivateKey() throws Exception {

        byte[] keyBytes = Files.readAllBytes(Paths.get(this.PRIVATEKEY_FILE));
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance(this.ALGORITHM).generatePrivate(spec);
    }

}
