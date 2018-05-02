/**
 * Emoluokka monikulmaisille objekteille, eli alukselle ja asteroideille.
 */
package logic;

import javafx.scene.shape.Polygon;

public class PolygonObject extends Polygon { //THE BASE CLASS FOR A MOVABLE POLYGONAL OBJECT
    
    public double vX;
    public double vY;
/**
 * Siirtää objektia dx, dy:n verran.
 * @param dx X-suuntainen siirtymä
 * @param dy Y-suuntainen siirtymä
 */
    public void translate(double dx, double dy) { //MOVE BY AMOUNT DX,DY
        this.setTranslateX(this.getTranslateX() + dx);
        this.setTranslateY(this.getTranslateY() + dy);        
    }
/**
 * Pitää huolen siitä, että jos objekti menee pelialueen ulkopuolelle, se tulee toiselta puolelta takaisin.
 */    
    public void checkBounds() { //SCREEN WRAPPING FEATURE
        if (this.getTranslateX() > 640.0) {
            translate(-640.0, 0.0);
            return;
        }
        if (this.getTranslateY() > 480.0) {
            translate(0.0, -480.0);
            return; 
        }
        if (this.getTranslateX() < 0.0) {
            translate(640.0, 0.0);
            return;          
        }
        if (this.getTranslateY() < 0.0) {
            translate(0.0, 480.0);
            return; 
        }
    }
}
