package blockchainhie;

import Cryptography.AppConfig;
import Cryptography.AsymmetricEncryption;
import Cryptography.DigitalSignature;
import Cryptography.KeyMaker;
import blockchain.Blockchain;
import blockchain.ExcelReader;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.javatuples.Decade;
import org.javatuples.Octet;
import org.javatuples.Sextet;

/**
 *
 * @author Shahiryar
 */
public class BlockchainHIE {

    public static void main(String[] args) {
        try {

            /**
             * EHR firstRecord; firstRecord = new EHR("121233", "cdBdesG34");
             * Block genesis = new Block(firstRecord, "0");
             * Blockchain.nextBlock(genesis);
             *
             * Blockchain.nextBlock(new Block(new EHR("fsdc1231",
             * "1dsadf3"),Blockchain.getLeafHash()));
             *
             * Blockchain.distribute(); //-----------------------
             *
             */
            String filename;
            filename = "Patients.xlsx";
            ExcelReader reader = new ExcelReader(filename);
            List<Decade<String, String, String, String, Integer, String, String, String, Integer, String>> patientData = reader.getAllPatientRecords();

            filename = "Doctors.xlsx";
            ExcelReader readerDoc = new ExcelReader(filename);
            List<Sextet<String, String, String, String, String, String>> doctorData = readerDoc.getAllDoctorRecords();
            /*
            filename = "gmp.xlsx";
            reader = new ExcelReader(filename);
            List<Octet<String, String, String, String, String, String, String, String>> healthcareProviderData = reader.getAllHospitalRecords();
            patientData.forEach(System.out::println);
            healthcareProviderData.forEach(System.out::println);
             */
            //patientData.forEach(System.out::println);
            //make a patient
            Patient patient = reader.getPatient("Ada Hunt", "19");
            //make a key for that patient and store it
            AppConfig config = new AppConfig(patient);
            KeyMaker keymaker = new KeyMaker(config);
            keymaker.mkkeypair();
            //make a doctor
            //doctorData.forEach(System.out::println);
            Doctor doctor = readerDoc.getDoctor("Shahiryar dogar", "32");
            //System.out.println(doctor);
            //make a key pair for the doctor
            config = new AppConfig(doctor);
            keymaker = new KeyMaker(config);
            keymaker.mkkeypair();
            //create an EHR
            EHR record = new EHR(patient.getId(), doctor.getId());
            //System.out.println(record);
            //-->Encryption of EHR
            //get patients public key
            config = new AppConfig(patient);
            AsymmetricEncryption encryptionFactory = new AsymmetricEncryption(config);
            PublicKey patientPublicKey = encryptionFactory.getPublicKey();
            PrivateKey patientPrivateKey = encryptionFactory.getPrivateKey();
            //System.out.println("EHR BEFORE ENCRYPTION : " + record);
            //encrypt the EHR with that key
            String recordString = record.toJointString();
            String encrypted = "", decrypted = "";
            encrypted = encryptionFactory.encryptLongText(recordString, patientPublicKey);
            System.out.println("encrypted text " + encrypted);
            //sign the encrypted message with the patient's private key
            byte[] signature = DigitalSignature.CreateSignature(encrypted.getBytes(), patientPrivateKey);
            
            //verify the data and then decrypt the message
            if (DigitalSignature.VerifySignature(encrypted.getBytes(), signature, patientPublicKey)) {
                decrypted = encryptionFactory.decryptLongCipher(encrypted, patientPrivateKey);
                System.out.println("Decrypted text " + decrypted);
                //get the EHR object out of the string
                EHR recordRecieved = EHR.extractFromString(decrypted);
                System.out.println(recordRecieved);
            }else{
                System.out.println("the signatures seems to have been forged");
                System.out.println("the message: " +encrypted);
            }

            //String orignal = encryptionFactory.decrypt(cipherRecord, patientPrivateKey);
            //System.out.println(" CIPHERED EHR (AFTER ENCRYPTION )  : " + cipherRecord);
            //-->Decryt the EHR
            //get the chiper
            //get the patients private key
            //decryt the EHR with this private key
        } catch (Exception ex) {
            Logger.getLogger(BlockchainHIE.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("!>Exception :: " + ex.getMessage() + "<!");
        }
    }

}
