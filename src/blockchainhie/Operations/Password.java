package blockchainhie.Operations;

import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class Password {

    public static String Username;
    private static final String ALGO = "MD5";
//    private static final String ALGO = "SHA-256";
//    private static final String ALGO = "SHA-512";
    public static final Logger logger = null;
    private static final String FILE = "secret.txt";
    private static final String FILE_LOGIN = "login.txt";
    private static final String uuid;

    static {
        uuid = UUID.randomUUID().toString();
    }

    public static boolean compare(String username, String passwd) throws Exception {

        Username = username;
        boolean yes = false;
        int iter = 0;
        List<String> compary = IO.read(FILE_LOGIN);
        List<String> compary1 = IO.read(FILE);
        String founder;

        for (int i = 0; i < compary.size(); i++) {
            if (compary.get(i).contains(username) == true) {
                yes = true;
                founder = compary.get(i);
                iter = i;
            }
        }

        String data1 = compary1.get(iter);
        for (String compary11 : compary1) {
            String[] splitter = data1.split("|");
            String total = "";
            for (String w : splitter) {
                total = total + w;
            }
            String one = total.substring(0, 36);
            String two = total.substring(37, 61);
            String three = total.substring(62, total.length());
            String hashedpass = Hashed.hash(Txt.append(two, passwd), ALGO);
            if (hashedpass.equals(three) && yes == true) {
                return true;
            }
        }
        return false;
    }

    //Secret file FORMAT: UID|SALT|PASSWORDHASH
    //Login file FORMAT: USERNAME|UUID
    public static void create(String username, String passwd) {

        try {
            String rand = Base64.getEncoder().encodeToString(Hashed.getSecureRand());
            String hash = Hashed.hash(Txt.append(rand, passwd), ALGO);

            IO.write(FILE, String.join("|", uuid, rand, hash));
            IO.write(FILE_LOGIN, String.join("|", username, uuid));

            System.out.println("==>User added in File Successfully<==");
        } catch (Exception ex) {
            System.out.println("!!>Error in Password class create Method<!!");
            System.out.println("Exception : " + ex.toString());
        }
    }
    //Secret file FORMAT: UID|SALT|PASSWORDHASH
    //Login file FORMAT: USERNAME|UUID
    public static void create(String username, String passwd, String file_name) {

        try {
            String rand = Base64.getEncoder().encodeToString(Hashed.getSecureRand());
            String hash = Hashed.hash(Txt.append(rand, passwd), ALGO);

            IO.write(FILE, String.join("|", uuid, rand, hash));
            IO.write(FILE_LOGIN, String.join("|", username, uuid));

            System.out.println("==>User added in File Successfully<==");
        } catch (Exception ex) {
            System.out.println("!!>Error in Password class create Method<!!");
            System.out.println("Exception : " + ex.toString());
        }
    }
}
