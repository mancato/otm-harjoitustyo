package io;

import java.util.ArrayList;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class FileHandler {

    File scores;
    FileWriter w;
    BufferedReader r;

    public FileHandler() {
        
        this.scores = new File("scores.txt");
        try {
            scores.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeScore(int score, String pname) {
        try {
            String line;
            boolean found = false;
            ArrayList<String> lines = new ArrayList<String>();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDateTime now = LocalDateTime.now();
            if (scores.length() == 0) {
                this.w = new FileWriter(scores, true);
                w.write(dtf.format(now) + "    " + pname + "    " + String.valueOf(score) + "\n");
                w.close();
            } else {
                this.r = new BufferedReader(new FileReader(scores));
                while ((line = r.readLine()) != null) {
                    System.out.println("LINE: " + line);
                    if (score > Integer.parseInt(line.split("    ")[2]) && !found) {
                        lines.add(dtf.format(now) + "    " + pname + "    " + String.valueOf(score));
                        found = true;
                    }
                    lines.add(line);
                }
                if (!found) {
                    lines.add(dtf.format(now) + "    " + pname + "    " + String.valueOf(score));
                }
                this.w = new FileWriter(scores, false);
                w.write("");
                this.w = new FileWriter(scores, true);            
                for (String s: lines) {
                    w.write(s + "\n");
                }
                w.close();
            }                           
        } catch (IOException e) {
            e.printStackTrace();
        }         
    }

    public int readHighScore() {
        int hs = 0;
        String line;
        String h;
        try {
            if (scores.length() == 0) {
                return 0;
            }
            this.r = new BufferedReader(new FileReader(scores));
            return Integer.parseInt(r.readLine().split("    ")[2]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hs;
    }

    public ArrayList<String> getTopScores() {
        ArrayList<String> scoreList = new ArrayList<>();
        String s;
        try {
            this.r = new BufferedReader(new FileReader(scores));
            for (int i = 0; i < 20; i++) {
                if ((s = r.readLine()) == null) {
                    break;
                } else {
                    scoreList.add(s);    
                }    
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scoreList;
    }
}
