package blockchain;

import blockchainhie.Block;
import com.google.gson.GsonBuilder;
import java.util.LinkedList;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shahiryar
 */
public class Blockchain {

    //File that contains the blockchain
    private String blockchainFile;
    Blockchain(){
        blockchainFile = "masterChain.dat";
    }
    Blockchain(String file){
        blockchainFile = file;
    }

    //data structure used for keeping the chain: LinkedList
    private static final LinkedList<Block> db = new LinkedList<>();

    /**
     * adds new block to the blockchain
     *
     * @param newBlock the block to add to the blockchain
     */
    public void nextBlock(Block newBlock) {
        Blockchain.db.add(newBlock);
        persist();
    }

    /**
     * updates the blockchain file
     */
    private void persist() {
        try (FileOutputStream fileOutput = new FileOutputStream(blockchainFile);
                ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput)) {
            outputStream.writeObject(db);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("!!>Exception <!! " + ex.getMessage());
        }
    }

    /**
     *
     * @return LinkeList of blockchain from the data file
     */
    public LinkedList<Block> get() {
        try (FileInputStream fileInput = new FileInputStream(blockchainFile);
                ObjectInputStream inputStream = new ObjectInputStream(fileInput)) {
            return (LinkedList<Block>) inputStream.readObject();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("!!>Exception <!! " + ex.getMessage());
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Blockchain.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Displays and stores the blockchain
     */
    public void distribute() {
        try {
            String chain = new GsonBuilder().setPrettyPrinting().create().toJson(db);
            System.out.println(chain);
            Files.write(Paths.get("ledger.txt"),
                    chain.getBytes(),
                    StandardOpenOption.CREATE);
            
        } catch (Exception e) {
            System.err.println("!!>Exception <!! " + e.getMessage());
        }
    }
    
    public String getLeafHash()
    {
        return Blockchain.db.getLast().getHash();
    }

}
