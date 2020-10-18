package blockchainhie.Operations;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author Shahiryar
 */
public class ObjectToByte {
    /**
     * Converts a provided object into a byte[]
     * @param <O> The type of object passed as parameter
     * @param object the object to be converted to byte[]
     * @return byte[] of the parameter object
     */
    public static <O> byte[] toBytes(O object) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream objOutputStream = new ObjectOutputStream(byteArrayOutputStream);) {
            objOutputStream.writeObject(object);
            return byteArrayOutputStream.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Concatinates a provided set of byte[] parameters
     * @param bytes set of parameters of type byte[]
     * @return byte[]
     * @throws IOException 
     */
    public static byte[] concatBytes(byte[] ...bytes) throws IOException
    {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        for(byte[] b : bytes){
            output.write(b);
        }
        return output.toByteArray();
        
    }
    
}
