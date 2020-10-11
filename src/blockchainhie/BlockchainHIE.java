package blockchainhie;
/**
 *
 * @author Shahiryar
 */
public class BlockchainHIE {

    public static void main(String[] args) {
        //create a new EHR
        EHR firstRecord;
        firstRecord = new EHR("121233", "CBDG34");
        firstRecord.setObservations("Mild fever; yellowish eyes");
        firstRecord.setPrescriptions("Ghondius pills");
        //create a block of this EHR
        Block firstBlock = new Block(firstRecord, "0");
        System.out.println(firstBlock.toString());
        //print Block
    }
    
}
