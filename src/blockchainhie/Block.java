package blockchainhie;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author Shahiryar
 * @version 0.1
 */
public class Block implements Serializable{
    Integer index;
    String patientID;
    EHR healthRecord;
    String hash , previousHash;
    long timestamp;
/**
 * This constructor takes a new healthRecord and hash from previous block to create a new block
 * @param healthRecord object of class EHR
 * @param previousHash String hash (from another (previous) block)
 */
    public Block(EHR healthRecord, String previousHash) {
        this.healthRecord = healthRecord;
        this.previousHash = previousHash;
        this.timestamp = new Timestamp(System.currentTimeMillis()).getTime();
        byte[] thisBlock = Block.getBytes(this);
        if(thisBlock != null){
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            byteStream.write()
            this.hash = BlockHasher.hash(thisBlock);
        }
        //intialize hash here using the hash function from the BlockHasher class
    }
    public static byte[] getBytes(Block block){
        try(ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream objOutputStream = new ObjectOutputStream(byteArrayOutputStream);)
        {
            objOutputStream.writeObject(block);
            return byteArrayOutputStream.toByteArray();
            
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String getHash() {
        return hash;
    }

    @Override
    public String toString() {
        return "Block{" + "hash=" + hash + ", previousHash=" + previousHash + ", timestamp=" + timestamp + '}';
    }
    
    
    
}
