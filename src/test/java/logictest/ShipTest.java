/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logictest;

import logic.Ship;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tarvus
 */
public class ShipTest {
    
    Ship ship;    
    
    @Before
    public void setUp() {
        ship = new Ship(0.0,0.0,2.0);
    }
    
    @Test
    public void testPosition(){
        assertEquals(0.0,ship.getTranslateX(),0.001);
        assertEquals(0.0,ship.getTranslateY(),0.001);
    }
    @Test    
    public void testRotate1(){
        ship.rotate(180.0);
        assertEquals(180.0,ship.getRotate(),0.001);
    }
    @Test    
    public void testRotate2(){
        ship.rotate(361.0);
        assertEquals(1.0,ship.getRotate(),0.001);
    }
    @Test    
    public void testRotate3(){
        ship.rotate(-1.0);
        assertEquals(359.0,ship.getRotate(),0.001);
    }     
    @Test    
    public void testAccelerate(){
        ship.accelerate(5.0);
        assertEquals(ship.vX,5.0,0.001);
        assertEquals(ship.vY,0.0,0.001);
    }
    @Test    
    public void testDeccelerate(){
        ship.accelerate(1.0);
        for (int i=0;i<10000;i++) ship.accelerate(0.0);
        assertEquals(ship.vX,0.0,0.001);
        assertEquals(ship.vY,0.0,0.001);
    }
    @Test    
    public void testSpawn(){
        ship.spawn();
        assertEquals(ship.getTranslateX(),320.0,0.001);
        assertEquals(ship.getTranslateY(),240.0,0.001);
        assertEquals(ship.vX,0.0,0.001);
        assertEquals(ship.vY,0.0,0.001);
    }
    @Test
    public void turretTempTest(){
        ship.shoot();
        assertEquals(ship.turretTemp,8);
    }
    @Test
    public void shootingTest(){
        int ammos = ship.ammos.size();
        ship.shoot();
        assertEquals(ship.ammos.size(),1);
    }
    @Test
    public void burstShootingTest(){
        int ammos = ship.ammos.size();
        ship.shoot();
        ship.shoot();
        assertEquals(ship.ammos.size(),1);
    }
}
