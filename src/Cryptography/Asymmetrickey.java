package Cryptography;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class Asymmetrickey {
    /*
    public static void main(String[] args) throws Exception {
        // data
        String msg = "There are so many things I want to do but I am so lazy to do that.";
        
        AsymmetricEncryption crypto = new AsymmetricEncryption();
        String ciphertext = crypto.encrypt(msg, getPublicKey(AppConfig.PUBLICKEY_FILE));
        System.out.println("Encypted message: "+ ciphertext);
        
        Thread.sleep(2000);
        
        String originalmsg = crypto.decrypt(ciphertext, getPrivateKey(AppConfig.PRIVATEKEY_FILE));
        System.out.println("Originial message: "+ originalmsg);        
    }

    //get the key from file system in files 
    private static PublicKey getPublicKey(String filename) throws Exception {

        byte[] keyBytes = Files.readAllBytes(Paths.get(filename));
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance(AppConfig.ALGORITHM).generatePublic(spec);
    }

    private static PrivateKey getPrivateKey(String filename) throws Exception {

        byte[] keyBytes = Files.readAllBytes(Paths.get(filename));
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance(AppConfig.ALGORITHM).generatePrivate(spec);
    }
*/
}
