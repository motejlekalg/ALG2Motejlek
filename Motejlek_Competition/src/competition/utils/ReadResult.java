package competition.utils;

import competition.app.Runner;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalTime;

/**
 *
 * @author Martin Motejlek
 */
public class ReadResult {

    public static void main(String[] args) {
        try {
            readResult("result.dat");
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void readResult(String resultFilepath) throws FileNotFoundException, IOException {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(resultFilepath))) {
            boolean isEnd = false;
            System.out.println(dis.readUTF());
            while(!isEnd) {
                try {
                    int order = dis.readInt();
                    
                    String firstName = dis.readUTF();
                    
                    int nChars = dis.readInt(); // simulace readUTF
                    String lastName = "";
                    for (int i = 0; i < nChars; i++) {
                        lastName += dis.readChar();
                    }
                    
                    LocalTime startTime = LocalTime.ofSecondOfDay(dis.readInt());                    
                    LocalTime finishTime = LocalTime.ofNanoOfDay(dis.readLong());                    
                    LocalTime runningTime = LocalTime.ofNanoOfDay(dis.readLong());
                    
                    System.out.printf(
                            "%2d. %-10s %-10s %s %s %s%n",
                            order,
                            firstName,
                            lastName,
                            startTime.format(Runner.dtfStart),
                            finishTime.format(Runner.dtfFinish),
                            runningTime.format(Runner.dtfFinish)
                    );
                } catch (EOFException e) {
                    isEnd = true;
                }
            }
        }
    }
    
}
