package Cryptography;

import blockchainhie.Patient;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.*;

public class KeyMaker {

    private final KeyPairGenerator keygen;
    private KeyPair keypair;
    private final String PUBLICKEY_FILE, PRIVATEKEY_FILE;
    private final String ALGORITHM;

    public  KeyMaker(String publicStore, String privateStore, String algo) throws Exception {
        keygen = KeyPairGenerator.getInstance(algo);
        keygen.initialize(1024);
        this.PUBLICKEY_FILE = publicStore;
        this.PRIVATEKEY_FILE = privateStore;
        this.ALGORITHM = "RSA";
    }

    public KeyMaker(AppConfig config) throws Exception {
        keygen = KeyPairGenerator.getInstance(config.getALGORITHM());
        keygen.initialize(1024);
        this.PUBLICKEY_FILE = config.getPUBLICKEY_FILE();
        this.PRIVATEKEY_FILE = config.getPRIVATEKEY_FILE();
        this.ALGORITHM = config.getALGORITHM();
    }

    //make-keypair
    public void mkkeypair() {
        try {
            this.keypair = this.keygen.genKeyPair();

            PublicKey pubkey = this.keypair.getPublic();
            PrivateKey prvkey = this.keypair.getPrivate();

            store(this.PUBLICKEY_FILE, pubkey.getEncoded());
            store(this.PRIVATEKEY_FILE, prvkey.getEncoded());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //store keypair to file
    public void store(String path, byte[] key) {
        File file = new File(path);
        file.getParentFile().mkdir();
        try {

            Files.write(Paths.get(path), key, StandardOpenOption.CREATE);
            System.out.println("Keypair stored in the file");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
