package blockchainhie;

import java.io.Serializable;

/**
 *
 * @author Shahiryar
 */
public class EHR implements Serializable{
    private String patientID, doctor, healthcareProvider,observations,
            prescriptions, physicalExaminations, recommendations;

    public EHR(String patientID, String doctor) {
        this.patientID = patientID;
        this.doctor = doctor;
    }

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
        this.observations = observations;
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
    
    
}
