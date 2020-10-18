package blockchainhie;

import java.io.Serializable;

/**
 *
 * @author Shahiryar
 */
public class EHR implements Serializable {

    private String patientID, doctor, healthcareProvider, observations,
            prescriptions, physicalExaminations, recommendations;
    private static final String delimiter = "{X}";

    /**
     *
     * @param patientID patient ID
     * @param doctor doctor ID
     */
    public EHR(String patientID, String doctor) {
        this.patientID = patientID;
        this.doctor = doctor;
        this.healthcareProvider = "No associated healthcareProvider";
        this.observations = "No observation";
        this.prescriptions = "No Prescription";
        this.physicalExaminations = "No Physical Examination";
        this.recommendations = "No recommendation";

    }

    /**
     *
     * @param patientID patient ID
     * @param doctor doctor ID
     * @param observations the observations made my the doctor during
     * examinations of patient
     * @param prescriptions the prescribed medicines by the doctor
     * @param recommendations any recommendation from the the doctor
     *
     */
    public EHR(String patientID, String doctor, String observations, String prescriptions, String recommendations) {
        this.patientID = patientID;
        this.doctor = doctor;
        this.healthcareProvider = "No associated healthcareProvider";
        this.observations = observations;
        this.prescriptions = prescriptions;
        this.physicalExaminations = "No Physical Examination";
        this.recommendations = recommendations;
    }

    /**
     *
     * @param patientID patient ID
     * @param doctor doctor ID
     * @param observations the observations made my the doctor during
     * examinations of patient
     * @param prescriptions the prescribed medicines by the doctor
     * @param healthcareProvider The hospital or liboratory name
     * @param physicalExaminations Results of the test done by the doctor or
     * Healthcare provider
     * @param recommendations Any recommendations from the doctor
     */
    public EHR(String patientID, String doctor, String healthcareProvider, String observations, String prescriptions, String physicalExaminations, String recommendations) {
        this.patientID = patientID;
        this.doctor = doctor;
        this.healthcareProvider = healthcareProvider;
        this.observations = observations;
        this.prescriptions = prescriptions;
        this.physicalExaminations = physicalExaminations;
        this.recommendations = recommendations;
    }

    /**
     *
     * Getter and setter functions for this EHR
     * ----------------------------------------------------------------------->>
     */
    public String getPatientID() {
        return patientID;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getHealthcareProvider() {
        return healthcareProvider;
    }

    public String getObservations() {
        return observations;
    }

    public String getPrescriptions() {
        return prescriptions;
    }

    public String getPhysicalExaminations() {
        return physicalExaminations;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setHealthcareProvider(String healthcareProvider) {
        this.healthcareProvider = healthcareProvider;
    }

    public void setObservations(String objservations) {
        this.observations = objservations;
    }

    public void setPrescriptions(String prescriptions) {
        this.prescriptions = prescriptions;
    }

    public void setPhysicalExaminations(String physicalExaminations) {
        this.physicalExaminations = physicalExaminations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    // <<-----------------------------------------------------------------------
    //---------MANUAL EXPLAINATIONS---------------------------------------------
    /*
    Create an EHR
    The doctor when logs in could create a new EHR
    A patient can access the EHR created by the doctor but can not edit it
    A doctor can access the EHR created by the doctor but can not edit it
    A healthcare provider can request the patient to access it's record but can not edit it
    Another doctor could request the patient to access the records but cannot edit it
     */
 /*
    The implementatio of create EHR function
    -> The doctor logs in to his account the doctor's id is recorded
    -> The doctor input the Patient's ID
    -> The doctor enters:
        *Observations
        *Physical examination
        *Prescriptions
        *Recommendations
    ->When the EHR is submitted:
        *-> The EHR is encrypted by the patient's public key
        *-> added as a block to the blockchain
        *-> Stored in a shared ledger
     */
    //--------------------------------------------------------------------------
    @Override
    public String toString() {
        return "EHR{" + "patientID=" + patientID + ", doctor=" + doctor + ", healthcareProvider=" + healthcareProvider + ", observations=" + observations + ", prescriptions=" + prescriptions + ", physicalExaminations=" + physicalExaminations + ", recommendations=" + recommendations + '}';
    }

    public String toJointString() {
        return String.join("::::",patientID, doctor, healthcareProvider, observations, prescriptions, physicalExaminations, recommendations);
    }

    public static EHR extractFromString(String stringObject) {
        String[] attributes;
        attributes = stringObject.split("::::");

        EHR record = new EHR(attributes[0], attributes[1], attributes[2], attributes[3], attributes[4], attributes[5], attributes[6]);
        System.out.println("from String : " + record.toString());
        return record;
    }
}
