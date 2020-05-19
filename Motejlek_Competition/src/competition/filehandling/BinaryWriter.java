package competition.filehandling;

import competition.app.Runner;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Martin Motejlek
 */
public class BinaryWriter extends Writer {

    @Override
    public void saveResults(String resultFilepath, List<Runner> runners) throws IOException {
        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream (resultFilepath)))  {
            dos.writeUTF("Nové výsledky");
            int n = 1;
            for (Runner runner : runners) {
               dos.writeInt(n);
                
               dos.writeUTF(runner.getFirstName());
               
               int nChars = runner.getLastName().length(); //simulace writeUTF, uložení počtu znaků a pak řetězce
               dos.writeInt(nChars);
               for (int i = 0; i < nChars; i++) {
                    dos.writeChar(runner.getLastName().charAt(i));
               }
               
               dos.writeInt(runner.getStartTime().toSecondOfDay());
               dos.writeLong(runner.getFinishTime().toNanoOfDay());
               dos.writeLong(runner.runningTime().toNanoOfDay());
               n++;
            }
        }  
    }
    
}
