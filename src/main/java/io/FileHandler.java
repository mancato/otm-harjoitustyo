package io;

import java.util.ArrayList;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
 /**
 *  Tulosten tallennus ja lataus tiedostojen avulla. Kirjoittaa tulokset, pelaajien nimet ja päivämäärät.
 *  @param fname Nimi tiedostolle johon tulokset tallennetaan.
 */
public class FileHandler {

    int maxScores; //MAX NUM. OF SCORES TO BE SAVED
    File scores; //FILE FOR SAVING SCORES
    FileWriter w; 
    BufferedReader r;

    public FileHandler(String fname) {

    
        maxScores = 15;        
        this.scores = new File(fname);
        try {
            scores.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
    * Kirjoittaa tuloksen tiedostoon.
    * @param score Tulos.
    * @param pname Pelaajan nimi.
    */
    public void writeScore(int score, String pname) {
        try {
            int n = 1;
            String line;
            boolean found = false;
            ArrayList<String> lines = new ArrayList<String>();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDateTime now = LocalDateTime.now();
            if (scores.length() == 0) {  //IF FILE IS EMPTY
                this.w = new FileWriter(scores, true);
                w.write(dtf.format(now) + "    " + pname + "    " + String.valueOf(score) + "\n");
                w.close();
            } else { //IF FILE IS NOT EMPTY
                this.r = new BufferedReader(new FileReader(scores));
                while ((line = r.readLine()) != null) {
                    n++; //KEEPING TRACK OF LINES
                    if (score > Integer.parseInt(line.split("    ")[2]) && !found) { //IF POSITION OF NEW SCORE IS FOUND
                        lines.add(dtf.format(now) + "    " + pname + "    " + String.valueOf(score));
                        found = true;
                    }
                    if (n > maxScores) { //IF SCORE IS OUT OF RANGE
                        found = true;
                        break;
                    }
                    lines.add(line);
                }
                if (!found) { //ADDING SCORE TO THE END OF FILE
                    lines.add(dtf.format(now) + "    " + pname + "    " + String.valueOf(score));
                }
                this.w = new FileWriter(scores, false);
                w.write("");
                this.w = new FileWriter(scores, true);            
                for (String s: lines) { //REWRITE FILE
                    w.write(s + "\n");
                }
                w.close();
            }                           
        } catch (IOException e) {
            e.printStackTrace();
        }         
    }
    /**
    * Lataa korkeimman tuloksen
    * @return Korkein pistemäärä.
    */
    public int readHighScore() { //READS THE HIGHEST SCORE
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
    /**
    * Lataa korkeimmat tulokset.
    * @return Lista korkeimmista tuloksista.
    */
    public ArrayList<String> getTopScores() { //GETS TOP 15 SCORES AS ARRAYLIST
        ArrayList<String> scoreList = new ArrayList<>();
        String s;
        try {
            this.r = new BufferedReader(new FileReader(scores));
            for (int i = 0; i < maxScores; i++) {
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
