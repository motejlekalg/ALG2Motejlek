package competition.utils;

import competition.app.FinishStat;
import competition.app.Runner;
import competition.filehandling.TextReaderFinish;
import competition.filehandling.TextReaderStart;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Martin Motejlek
 */
public class TextToBinSF {
    
    private static String textStart = "start.txt";
    private static String textFinish = "finish.txt";
    private static String binStart = "start.dat";
    private static String binFinish = "finish.dat";
    
    public static void main(String[] args) {
        try {
            writeBinStart(binStart, (new TextReaderStart()).loadData(textStart));
            writeBinFinish(binFinish, (new TextReaderFinish()).loadData(textFinish));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Hotovo");
    }

    private static void writeBinStart(String binStart, List<Runner> runners) throws IOException {
        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream (binStart)))  {
            for (Runner runner : runners) {
               dos.writeInt(runner.getNumber());
               dos.writeUTF(runner.getFirstName());
               dos.writeUTF(runner.getLastName());
               dos.writeInt(runner.getStartTime().toSecondOfDay());
            }
        }  
    }
    
    private static void writeBinFinish(String binFinish, List<FinishStat> stats) throws IOException {
        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream (binFinish)))  {
            for (FinishStat stat : stats) {
               dos.writeInt(stat.getNumber());
               dos.writeLong(stat.getFinishTime().toNanoOfDay());
            }
        }  
    }
    
}
