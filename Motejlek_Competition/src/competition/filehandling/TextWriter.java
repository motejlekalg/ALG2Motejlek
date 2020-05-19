package competition.filehandling;

import competition.app.Runner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author Martin Motejlek
 */
public class TextWriter extends Writer {

    @Override
    public void saveResults(String resultFilepath, List<Runner> runners) throws IOException {
        //using PrintWriter
        //try with resources - automaticky zavře soubor i když nastane chyba
        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(resultFilepath, true)))) {
            //PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out, "Cp1250"), true); //nastevení kódovaní
            pw.println("Nové výsledky");
            int n = 1;
            for (Runner runner : runners) {
                pw.print(n + ". ");
                pw.println(runner.toString());
                n++;
            }
        }
    }
    
}
