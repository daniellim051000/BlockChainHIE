/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchainhie;

import java.util.List;

/**
 *
 * @author Lenovo
 */
public class Doctor {
    private String id, firstName, lastName, LicenceID, affiliateHealthcareProviderID, specialization;
    private List<EHR> grantedRequestEHR; //contains the list of EHR that the doctor has not accessed yet once an EHR is read it is deleted
    private final String RECIEVED_EHR_FILENAME;

    public Doctor(String id, String firstName, String lastName, String LicenceID, String affiliateHealthcareProviderID, String specialization) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.LicenceID = LicenceID;
        this.affiliateHealthcareProviderID = affiliateHealthcareProviderID;
        this.specialization = specialization;
        this.RECIEVED_EHR_FILENAME = id + "_EHR_Requests_Granted";
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Doctor{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", LicenceID=" + LicenceID + ", affiliateHealthcareProviderID=" + affiliateHealthcareProviderID + ", specialization=" + specialization +'}';
    }
    
    
    
    

    
}
