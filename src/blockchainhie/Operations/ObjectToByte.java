package blockchainhie.Operations;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Shahiryar
 */
public class ObjectToByte {
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
}
