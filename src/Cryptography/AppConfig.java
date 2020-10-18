package Cryptography;

import blockchainhie.Doctor;
import blockchainhie.Patient;

public class AppConfig {

    //algorithm
    public final String ALGORITHM;

    //location of key pair
    public final String PUBLICKEY_FILE;
    public final String PRIVATEKEY_FILE;

    public AppConfig(Patient patient) {
        this.ALGORITHM = "RSA";
        this.PUBLICKEY_FILE = "Patients_Keys/"+patient.getId() + "_KeyPair/PUBLICKEY";
        this.PRIVATEKEY_FILE = "Patients_Keys/"+patient.getId() + "_KeyPair/PRIVATEKEY";
    }
    public AppConfig(Doctor doctor) {
        this.ALGORITHM = "RSA";
        this.PUBLICKEY_FILE = "Doctors_Keys/" + doctor.getId() + "_KeyPair/PUBLICKEY";
        this.PRIVATEKEY_FILE = "Doctors_Keys/"+doctor.getId() + "_KeyPair/PRIVATEKEY";
    }

    public String getALGORITHM() {
        return ALGORITHM;
    }

    public String getPUBLICKEY_FILE() {
        return PUBLICKEY_FILE;
    }

    public String getPRIVATEKEY_FILE() {
        return PRIVATEKEY_FILE;
    }
    

}
