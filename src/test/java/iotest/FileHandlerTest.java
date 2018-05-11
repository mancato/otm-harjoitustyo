/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iotest;

import io.FileHandler;
import java.io.File;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tarvus
 */
public class FileHandlerTest {
    
    FileHandler fh;
    File file;
    String date;
    
    public FileHandlerTest() {
    }
    
    
    @Before
    public void setUp() {
        fh = new FileHandler("test.txt");
        file = new File("test.txt");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        date = dtf.format(now);
    }
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void TestWriting() {
        fh.writeScore(1000, "AAA");
        String ans = date + "   " + "AAA    1000";
        int hiscore = fh.readHighScore();
        file.delete();
        assertEquals(1000,hiscore);
    }
    
    @Test
    public void TestHighScores() {
        String sc1 = date + "    " + "AAA    1000";
        String sc2 = date + "    " + "BBB    2000";
        String sc3 = date + "    " + "CCC    1500";
        String ans = "";
        fh.writeScore(1000, "AAA");
        fh.writeScore(2000, "BBB");
        fh.writeScore(1500, "CCC");
        ArrayList<String> scores = fh.getTopScores();
        for (String s: scores) {
            ans += s;
        }
        file.delete();
        System.out.println(sc2 + sc3 + sc1);
        System.out.println("--------------");
        System.out.println(ans);
        assertEquals(sc2 + sc3 + sc1,ans);
    }
}
