package blockchain;

import blockchainhie.Doctor;
import blockchainhie.Patient;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.javatuples.Decade;
import org.javatuples.Octet;
import org.javatuples.Sextet;

/**
 *
 * @author Shahiryar
 */
public class ExcelReader {

    private String targetFile;
    private FileInputStream fileStreamIn;
    private XSSFWorkbook workbook;

    public ExcelReader(String targetFile) {
        this.targetFile = targetFile;
        //initialize inputStream and workbook
        try {
            fileStreamIn = new FileInputStream(targetFile);
            workbook = new XSSFWorkbook(fileStreamIn);
        } catch (Exception e) {
            System.err.println("!!>Exception <!!" + e.getMessage());
        }
    }

    public List<Decade<String, String, String, String, Integer, String, String, String, Integer, String>> getAllPatientRecords() {
        XSSFSheet sheet = workbook.getSheetAt(0);
        List<Decade<String, String, String, String, Integer, String, String, String, Integer, String>> decadeList = new ArrayList<>();
        int rowNum;
        Decade<String, String, String, String, Integer, String, String, String, Integer, String> tempDecade = null;

        for (Row row : sheet) {
            rowNum = row.getRowNum();
            try {
                if (rowNum > 1) {
                    tempDecade = new Decade(
                            row.getCell(0).getStringCellValue(),//id
                            row.getCell(1).getStringCellValue(), //Fisrt name
                            row.getCell(2).getStringCellValue(), //Last name
                            row.getCell(3).getStringCellValue(), //Gender
                            row.getCell(4).getNumericCellValue(), //age
                            row.getCell(5).getStringCellValue(), //email
                            row.getCell(6).getStringCellValue(), //phone
                            row.getCell(7).getStringCellValue(), //Matrial status
                            row.getCell(8).getNumericCellValue(), //number of children
                            row.getCell(9).getStringCellValue()); //Diseas
                    decadeList.add(tempDecade);
                    //this can cause to break at an empty record therefore it is suggested to keep the dataset from having empty records
                }
            } catch (NullPointerException e) {
                break;
            }

        }
        return decadeList;
    }

    /**
     * takes an octect from the excel of Healthcare providers
     *
     * @return List<Octet<>>
     */
    public List<Octet<String, String, String, String, String, String, String, String>> getAllHospitalRecords() {
        XSSFSheet sheet = workbook.getSheetAt(0);
        List<Octet<String, String, String, String, String, String, String, String>> octetList = new ArrayList<>();
        int rowNum;
        Octet<String, String, String, String, String, String, String, String> tempOctet = null;
        for (Row row : sheet) {
            rowNum = row.getRowNum();
            if (rowNum > 1) {
                tempOctet = new Octet(
                        row.getCell(0).getNumericCellValue(),
                        row.getCell(1).getStringCellValue(),
                        row.getCell(2).getStringCellValue(),
                        row.getCell(3).getStringCellValue(),
                        row.getCell(4).getNumericCellValue(),
                        row.getCell(5).getNumericCellValue(),
                        row.getCell(6).getStringCellValue(),
                        row.getCell(7).getStringCellValue());
                octetList.add(tempOctet);
                //this can cause to break at an empty record therefore it is suggested to keep the dataset from having empty records
                if (tempOctet.getValue1().isEmpty()
                        && tempOctet.getValue2().isEmpty()
                        && tempOctet.getValue3().isEmpty()
                        && tempOctet.getValue6().isEmpty()
                        && tempOctet.getValue7().isEmpty()) {
                    octetList.remove(tempOctet);
                    break;
                }
            }
        }
        System.out.println("The octet class : " + tempOctet.getClass().toGenericString());
        return octetList;
    }
    /**
     * takes an octect from the excel of Healthcare providers
     *
     * @return List<Octet<>>
     */
    public List<Sextet<String, String, String, String, String, String>> getAllDoctorRecords() {
        XSSFSheet sheet = workbook.getSheetAt(0);
        List<Sextet<String, String, String, String, String, String>> sextetList = new ArrayList<>();
        int rowNum;
        Sextet<String, String, String, String, String, String> tempsextet = null;
        for (Row row : sheet) {
            rowNum = row.getRowNum();
            if (rowNum > 1) {
                tempsextet = new Sextet(
                        row.getCell(0).getStringCellValue(),
                        row.getCell(1).getStringCellValue(),
                        row.getCell(2).getStringCellValue(),
                        row.getCell(3).getStringCellValue(),
                        row.getCell(4).getStringCellValue(),
                        row.getCell(5).getStringCellValue());
                sextetList.add(tempsextet);
                //this can cause to break at an empty record therefore it is suggested to keep the dataset from having empty records
                if (tempsextet.getValue1().isEmpty()
                        && tempsextet.getValue2().isEmpty()
                        && tempsextet.getValue3().isEmpty()
                        && tempsextet.getValue4().isEmpty()
                        && tempsextet.getValue5().isEmpty()) {
                    sextetList.remove(tempsextet);
                    break;
                }
            }
        }
        System.out.println("The sextet class : " + tempsextet.getClass().toGenericString());
        return sextetList;
    }

    /**
     * gets the list of names of all the patients registered with the "network"
     *
     * @return
     */
    public LinkedList<String> getPatientsNames() {
        List<Decade<String, String, String, String, Integer, String, String, String, Integer, String>> decadeList;
        List<String> names = new LinkedList<>();
        decadeList = this.getAllPatientRecords();
        decadeList.forEach(record -> names.add(record.getValue1() + " " + record.getValue2()));
//        names.forEach(System.out::println);
        return (LinkedList) names;
    }
    /**
     * gets the list of names of all the patients registered with the "network"
     *
     * @return
     */
    public LinkedList<String> getDoctorsNames() {
        List<Sextet<String, String, String, String, String, String>> sextetList;
        List<String> names = new LinkedList<>();
        sextetList = this.getAllDoctorRecords();
        sextetList.forEach(record -> names.add(record.getValue1() + " " + record.getValue2()));
//        names.forEach(System.out::println);
        return (LinkedList) names;
    }

    
    /**
     *
     * @param name String the full name entered by the doctor
     * @param id String the last two digits of the id
     * @return Patient the patient in the records who matches the name and the
     * ID
     */
    public Patient getPatient(String name, String id) {
        List<Decade<String, String, String, String, Integer, String, String, String, Integer, String>> decadeList;
        decadeList = this.getAllPatientRecords();
        Patient patient;
        for (Decade<String, String, String, String, Integer, String, String, String, Integer, String> el : decadeList) {
            Boolean containsLastChar = el.getValue0().substring(el.getValue0().length() - 2).equals(id);
            if ((el.getValue1() + " " + el.getValue2()).equalsIgnoreCase(name) && containsLastChar) {
                return new Patient(el.getValue0(),
                        el.getValue1(),
                        el.getValue2(),
                        el.getValue3(),
                        el.getValue5(),
                        el.getValue6(),
                        el.getValue7(),
                        el.getValue9(),
                        el.toArray()[4].toString(),
                        el.toArray()[8].toString());
            }
        }
        System.out.println(name + " not found with " + id);
        return null;
    }
    /**
     *
     * @param name String the full name entered by the doctor
     * @param id String the last two digits of the id
     * @return Patient the patient in the records who matches the name and the
     * ID
     */
    public Doctor getDoctor(String name, String id) {
        List<Sextet<String, String, String, String, String, String>> sextetList;
        sextetList = this.getAllDoctorRecords();
        for (Sextet<String, String, String, String,String, String> el : sextetList) {
            Boolean containsLastChar = el.getValue0().substring(el.getValue0().length() - 2).equals(id);
            if ((el.getValue1() + " " + el.getValue2()).equalsIgnoreCase(name) && containsLastChar) {
                return new Doctor(el.getValue0(),
                        el.getValue1(),
                        el.getValue2(),
                        el.getValue3(),
                        el.getValue4(),
                        el.getValue5());
            }
        }
        System.out.println(name + " not found with " + id);
        return null;
    }

}
