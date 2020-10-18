package blockchainhie;

import blockchainhie.Operations.ObjectToByte;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author Shahiryar
 * @version 0.1
 */
public class Block implements Serializable {

    Integer index;
    String patientID;
    EHR healthRecord;
    String hash, previousHash;
    long timestamp;

    /**
     * Takes a new healthRecord and hash from previous block to create a new
     * block
     *
     * @param healthRecord object of class EHR
     * @param previousHash String hash (from another (previous) block)
     */
    public Block(EHR healthRecord, String previousHash) throws IOException, Exception {
        this.healthRecord = healthRecord;
        this.previousHash = previousHash;
        this.timestamp = new Timestamp(System.currentTimeMillis()).getTime();
        byte[] healthRecordBytes = ObjectToByte.<EHR>toBytes(healthRecord),
                blockBytes = ObjectToByte.<Block>toBytes(this),
                previousHashBytes = previousHash.getBytes(),
                timestampBytes = Long.toString(timestamp).getBytes(),
                thisObjectBytes;
        if (healthRecordBytes != null && blockBytes != null) {
            thisObjectBytes = ObjectToByte.concatBytes(healthRecordBytes, blockBytes, previousHashBytes, timestampBytes);
            this.hash = BlockHasher.hash(thisObjectBytes);
        } else {
            throw new Exception("!!>ERROR in converting block to bytes<!!");
        }
    }
    /**
     * Converts block object to byte[]
     *
     * @param block a block object
     * @return bytes for the block object
     */
    public static byte[] getBytes(Block block) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream objOutputStream = new ObjectOutputStream(byteArrayOutputStream);) {
            objOutputStream.writeObject(block);
            return byteArrayOutputStream.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
/**
 * 
 * @return hash for this object
 */
    public String getHash() {
        return hash;
    }
/**
 * 
 * @return string representation of this Block object
 */
    @Override
    public String toString() {
        return "Block{" + "hash=" + hash + ", previousHash=" + previousHash + ", timestamp=" + timestamp + '}';
    }

}
