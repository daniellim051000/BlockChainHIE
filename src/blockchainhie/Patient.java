package blockchainhie;

import com.google.gson.GsonBuilder;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;
import org.javatuples.Decade;

/**
 *
 * @author Shahiryar
 */
public class Patient implements Serializable {

    private String id, firstName, lastName, gender, email, phone, martialStatus, disease, age, numberChildren;
    private List<RequestAccess> requests;
    private final String REQUESTS_FILENAME;
    //private Double age, numberChildren;

    /**
     * Create a patient record only when all this information is available
     *
     * @param id patient id
     * @param firstName patients first name
     * @param lastName patients last name
     * @param gender patients gender
     * @param email patients email address
     * @param phone patients contact number
     * @param martialStatus patients martial status
     * @param disease the disease that the patient is currently dignosed with
     * @param age patients age
     * @param numberChildren number of children the patient has
     */
    public Patient(String id, String firstName, String lastName, String gender, String email, String phone, String martialStatus, String disease, double age, double numberChildren) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.martialStatus = martialStatus;
        this.disease = disease;
        this.requests = new LinkedList<>();
        REQUESTS_FILENAME = id + "_Requests";
        //this.age = age;
        //this.numberChildren = numberChildren;
    }

    //this constructor is there as a adhoc solution to the bug in the excel reader, the excel reader should return an Integer but it returns a
    //double, so when we want to get a numeric value it returns a double and when we want to use it as a parameter it does not cast it as a double,
    //so one way it gives a runtime error while the other way it gives a complier error
    public Patient(String id, String firstName, String lastName, String gender, String email, String phone, String martialStatus, String disease, String age, String numberChildren) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.martialStatus = martialStatus;
        this.disease = disease;
        this.age = age;
        this.numberChildren = numberChildren;
        REQUESTS_FILENAME = id + "_Requests";
    }

    /**
     *
     * @return Decade from java tuple of this object of the Patient class
     */
    public Decade<String, String, String, String, Integer, String, String, String, Integer, String> getPatientTuple() {
        Decade<String, String, String, String, Integer, String, String, String, Integer, String> patientTuple;
        patientTuple = new Decade(this.id,
                this.firstName,
                this.lastName,
                this.gender,
                this.age,
                this.email,
                this.phone,
                this.martialStatus,
                this.numberChildren,
                this.disease);

        return patientTuple;
    }

    /**
     * Reads the list of requests for the patient and then puts the requests in the requests list
     * these requests are the requests to access data
     * The patient can either accept it or request it wait for it to expire
     */
    public void readRequestsInList() {
        try (FileOutputStream fileOutput = new FileOutputStream(REQUESTS_FILENAME);
                ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput)) {
            outputStream.writeObject(requests);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("!!>Exception <!! " + ex.getMessage());
        }
    }
    /**
     * Write the list of request to the file from the patient's current requests record with updated requests
     */

    public void writeRequestsToFile() {
        try {
            String request = new GsonBuilder().setPrettyPrinting().create().toJson(requests);
            Files.write(Paths.get(REQUESTS_FILENAME),
                    request.getBytes(),
                    StandardOpenOption.CREATE);
        } catch (Exception e) {
            System.err.println("!!>Exception <!! " + e.getMessage());
        }
    }
    
    public List<RequestAccess> getRequests(){
        return requests;
    }
    //accept request: update the status and then writeRequestToFile
    //each request could have a unique id
    //there could be two drop downs where one that contains all the requests
    // and another that lets the user update the status

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Patient{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender + ", email=" + email + ", phone=" + phone + ", martialStatus=" + martialStatus + ", disease=" + disease + ", age=" + age + ", numberChildren=" + numberChildren + ", requests=" + requests + ", REQUESTS_FILENAME=" + REQUESTS_FILENAME + '}';
    }
    

}
