/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logictest;

import logic.Asteroid;
import logic.AsteroidField;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tarvus
 */
public class AsteroidFieldTest {
    
    AsteroidField af;
    
    @Before
    public void setUp() {
        af = new AsteroidField();
    }
    @Test
    public void spawnTest(){
        af.spawn(4, 0.0, 0.0);
        assertEquals(af.size(),4);
    }
    @Test
    public void crashTest(){
        af.spawn(4, 0.0, 0.0);
        for (Asteroid a: af){
            boolean tx = (a.getTranslateX() != 0.0);
            boolean ty = (a.getTranslateY() != 0.0);
            assertEquals(tx,true);
            assertEquals(ty,true);
        }
    }
}
