/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logictest;

import logic.Ammo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tarvus
 */
public class AmmoTest {
    
    Ammo ammo;
    
    @Before
    public void setUp() {
        ammo = new Ammo(0.0,0.0,10.0,10.0);
    }
    @Test 
    public void translateTest(){
        ammo.translate(10.0,10.0);
        assertEquals(ammo.getCenterX(),10.0,0.001);
        assertEquals(ammo.getCenterY(),10.0,0.001);
    }
    @Test
    public void testBounds(){
        ammo.translate(10.0,10.0);
        ammo.checkBounds();
        assertEquals(ammo.getCenterX(),10.0,0.001);
        assertEquals(ammo.getCenterY(),10.0,0.001);        
    }
    @Test
    public void testBounds2(){
        ammo.translate(641.0,1.0);
        ammo.checkBounds();
        assertEquals(ammo.getCenterX(),1.0,0.001);
        assertEquals(ammo.getCenterY(),1.0,0.001);        
    }
}
