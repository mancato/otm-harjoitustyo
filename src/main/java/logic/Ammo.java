
package logic;

import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
/**
 * Ammus, jonka alus voi ampua.
 * @x0 Ammuksen x-koordinaatti.
 * @y0 Ammuksen y-koordinaatti.
 * @vX Ammuksen nopeus x-suunnassa.
 * @vY Ammuksen nopeus y-suunnassa.
 */
public class Ammo extends Circle {
    
    public double vX;
    public double vY;
    public int range; //DETERMINES HOW FAR AMMO CAN TRAVEL

    public Ammo(double x0, double y0, double vX, double vY) {
        this.vX = vX;
        this.vY = vY;
        this.range = 35;
        this.setFill(Color.TRANSPARENT);
        this.setStroke(Color.WHITE);
        this.setCenterX(x0);
        this.setCenterY(y0);
        this.setRadius(2);  
    }
/**
 * Siirtää ammusta dx:n ja dy:n verran.
 * @param   dx Paikan muutoksen x-komponentti.
 * @param   dy Paikan muutoksen y-komponentti. 
 */
    public void translate(double dx, double dy) { //MOVE BY AMOUNT DX,DY
        this.setCenterX(this.getCenterX() + dx);
        this.setCenterY(this.getCenterY() + dy);       
    }
/**
 * Varmistaa, että jos ammus menee ruudun ulkopuolelle niin se tulee toiselta puolelta takaisin.
 */
    public void checkBounds() { //SCREEN WRAPPING FEATURE
        if (this.getCenterX() > 640.0) {
            translate(-640.0, 0.0);
            return;
        }
        if (this.getCenterX() < 0.0) {
            translate(640.0, 0.0);
            return;          
        }
        if (this.getCenterY() > 480.0) {
            translate(0.0, -480.0);
            return; 
        }
        if (this.getCenterY() < 0.0) {
            translate(0.0, 480.0);
            return; 
        }
    }



}
