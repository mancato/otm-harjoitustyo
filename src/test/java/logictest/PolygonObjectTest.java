/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logictest;

import logic.PolygonObject;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tarvus
 */
public class PolygonObjectTest {

    PolygonObject poly;
    @Before
    public void setUp() {
        poly = new PolygonObject();
    }
    @Test
    public void testTranslate(){
        poly.translate(10.0, 10.0);
        assertEquals(poly.getTranslateX(),10.0,0.001);
        assertEquals(poly.getTranslateY(),10.0,0.001);
    }
    @Test
    public void testBounds(){
        poly.translate(10.0,10.0);
        poly.checkBounds();
        assertEquals(poly.getTranslateX(),10.0,0.001);
        assertEquals(poly.getTranslateY(),10.0,0.001);
    }
    @Test
    public void testBounds2(){
        poly.translate(641.0,1.0);
        poly.checkBounds();
        assertEquals(poly.getTranslateX(),1.0,0.001);
        assertEquals(poly.getTranslateY(),1.0,0.001);        
    }

}
