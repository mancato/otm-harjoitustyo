package logictest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.collections.ObservableList;
import logic.Asteroid;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tarvus
 */
public class AsteroidTest {
    
    Asteroid asteroid;
    @Before
    public void setUp() {
        asteroid = new Asteroid(9,1.0,0.0,0.0);
    }
    @Test
    public void testShape(){
        ObservableList<Double> points = asteroid.getPoints();
        int xsign = 0;
        int ysign = 0;
        for (int i=0;i<points.size()-3;i++){
            if (Math.signum(points.get(i+2)) != Math.signum(points.get(i))) xsign++;
            if (Math.signum(points.get(i+3)) != Math.signum(points.get(i+1))) ysign++;
        }
        assertEquals(xsign,3);
        assertEquals(ysign,3);
    }
}
