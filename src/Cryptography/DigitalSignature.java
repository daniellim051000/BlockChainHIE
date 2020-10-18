package Cryptography;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

/**
 *
 * @author Shahiryar
 */
public class DigitalSignature {

    private static final String SIGNING_ALGORITHM = "SHA256withRSA";

    /**
     * Creates a signature byte
     *
     * @param input byte[] data to sign
     * @param Key PrivateKey to sign the input data
     * @return byte[] digital signature
     * @throws Exception
     */
    public static byte[] CreateSignature(byte[] input, PrivateKey Key) throws Exception {
        Signature signature = Signature.getInstance(SIGNING_ALGORITHM);
        signature.initSign(Key);
        signature.update(input);
        return signature.sign();
    }

    /**
     * Verifies a digital signature
     *
     * @param input byte[] the data that needs to verified the signature with
     * @param signatureToVerify byte[] the signature that needs to be verified
     * @param key PublicKey corresponding to the privatekey the data was signed
     * with
     * @return Boolean whether the signature is authentic or forged
     * @throws Exception
     */
    public static boolean VerifySignature(byte[] input, byte[] signatureToVerify, PublicKey key) throws Exception {
        Signature signature = Signature.getInstance(SIGNING_ALGORITHM);
        signature.initVerify(key);
        signature.update(input);
        return signature.verify(signatureToVerify);
    }
}
