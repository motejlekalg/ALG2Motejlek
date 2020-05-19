/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package competition.app;

import competition.filehandling.BinaryReaderFinish;
import competition.filehandling.BinaryReaderStart;
import competition.filehandling.BinaryWriter;
import competition.filehandling.ReaderFinish;
import competition.filehandling.ReaderStart;
import competition.filehandling.TextReaderFinish;
import competition.filehandling.TextReaderStart;
import competition.filehandling.TextWriter;
import competition.filehandling.Writer;
import competition.utils.IllegalFilenameException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * @author Martin Motejlek
 */
public class Competition {
    private List<Runner> runners = null;
    
    public void load(String startFilepath, String finishFilepath) throws FileNotFoundException, IOException {
//        startFilepath.matches("^[Ss]tart(20)?([0-2][0-9]|30).*");
//        if(!(startFilepath.endsWith(".txt") && finishFilepath.endsWith(".txt"))) {
//            throw new IllegalArgumentException("Přípona musí být .txt");
//        }
        if(!startFilepath.contains("start")) {
            throw new IllegalFilenameException("Start soubor musi v nazvu obsahovat \"start\".");
        }
        ReaderStart rs;
        if(startFilepath.endsWith(".txt")) {
            rs = new TextReaderStart();
        } else if (startFilepath.endsWith(".dat")) {
            rs = new BinaryReaderStart();
        } else {
            throw new IllegalFilenameException("Nepodporovana koncovka souboru.");
        }
        runners = rs.loadData(startFilepath);
        
        if(!finishFilepath.contains("finish")) {
            throw new IllegalFilenameException("Soubor s casy bezcu musi v nazvu obsahovat \"finish\".");
        }
        ReaderFinish rf;
        if(finishFilepath.endsWith(".txt")) {
            rf = new TextReaderFinish();
        } else if (finishFilepath.endsWith(".dat")) {
            rf = new BinaryReaderFinish();
        } else {
            throw new IllegalFilenameException("Nepodporovana koncovka souboru.");
        }
        assignFinishTimes(rf.loadData(finishFilepath));
        
    }

    public String getResults() {
        Collections.sort(runners);
        Iterator<Runner> iterator = runners.iterator();
        
        StringBuilder sb = new StringBuilder();
        int n = 1;
        Runner r;
        while(iterator.hasNext()) {
            r = iterator.next();
            sb.append(String.format("%2d. %s", n, r));
            if (iterator.hasNext()) {
                sb.append(String.format("%n"));
            }
            n++;
        }
        return sb.toString();
    }

    public void saveResults(String resultFilepath) throws IOException {
        Collections.sort(runners);
        Writer w;
        if(resultFilepath.endsWith(".txt")) {
            w = new TextWriter();
        } else if (resultFilepath.endsWith(".dat")) {
            w = new BinaryWriter();
        } else {
            throw new IllegalFilenameException("Nepodporovana koncovka souboru.");
        }
        w.saveResults(resultFilepath, runners);
    }
    
    private void assignFinishTimes(List<FinishStat> stats) {
        for (FinishStat stat : stats) {
            try {
                Runner r = findRunner(stat.getNumber(), runners);
                r.setFinishTime(stat.getFinishTime());
            } catch (NoSuchElementException e) {
                // System.err.print(e.getMessage());
            }
        }
    }
    
    private Runner findRunner(int number, List<Runner> runners) {
        for (Runner runner : runners) {
            if (runner.getNumber() == number) {
                return runner;
            }
        }
        throw new NoSuchElementException("Bezec s cislem " + number + " nebyl na startu.");
    }
    
}
