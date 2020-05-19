package competition.filehandling;

import competition.app.Runner;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Martin Motejlek
 */
public class BinaryReaderStart extends ReaderStart {

    @Override
    public List<Runner> loadData(String startFilepath) throws FileNotFoundException, IOException {
        List<Runner> runners = new ArrayList<>();
 
        try (DataInputStream dis = new DataInputStream(new FileInputStream(startFilepath))) {
            boolean isEnd = false;
            while(!isEnd) {
                try {
                    int number = dis.readInt();
                    String firstName = dis.readUTF();
                    String lastName = dis.readUTF();
                    int startTime = dis.readInt();
                    Runner r = new Runner(firstName, lastName, number);
                    r.setStartTime(startTime);
                    runners.add(r);
                } catch (EOFException e) {
                    isEnd = true;
                }
            }
        }
        
        return runners;
    }
    
}
