/**
 * Pelaajan alus
 */
package logic;

import java.util.ArrayList;
import javafx.scene.paint.Color;

public class Ship extends PolygonObject {

    public int lives;
    public int spawnCounter;
    public int turretTemp;
    public ArrayList<Ammo> ammos; 

    public Ship(double x0, double y0, double scale) {
        this.lives = 3; //NUMBER OF INITIAL LIVES
        this.setTranslateX(x0);
        this.setTranslateY(y0);
        this.setFill(Color.TRANSPARENT);
        this.setStroke(Color.WHITE);
        this.getPoints().addAll(new Double[] { //SHIP FORM
            scale * 6.0, 0.0,
            -scale * 6.0, -scale * 4.0,
            -scale * 3.0, 0.0,
            -scale * 6.0, scale * 4.0
        });
        this.spawnCounter = -1; //FOR DELAYING SPAWN TIME, -1 MEANS SHIP IS IN PLAY
        this.turretTemp = 0;
        this.ammos = new ArrayList<Ammo>();
    }
/**
 * Aluksen "liikeyhtälö", laskee aluksen nopeuden ottaen huomioon työntövoiman ja "kitkan".
 * @param thrust Aluksen työntövoima
 */
    public void accelerate(double thrust) { //ACCELERATING THE SHIP
        vX += thrust * Math.cos(Math.toRadians(this.getRotate())) - vX * 0.007;
        vY += thrust * Math.sin(Math.toRadians(this.getRotate())) - vY * 0.007;
    }
/**
 * Kiertää alusta.
 * @param angle Kulma jonka verran alusta kierretään.
 */
    public void rotate(double angle) { //ROTATING THE SHIP
        double a = this.getRotate() + angle;
        if (a > 360.0) {
            this.setRotate(a - 360.0);
        } else if (a < 0.0) {
            this.setRotate(a + 360.0);
        } else {
            this.setRotate(a);
        }
    }
/**
 * Alustaa aluksen peliin.
 */
    public void spawn() { //SPAWNING A NEW SHIP
        this.vX = 0.0;
        this.vY = 0.0;
        this.setTranslateX(320.0);
        this.setTranslateY(240.0);
        this.spawnCounter = -1;
    }
/**
 * Aluksella ampuminen ja aluksen aseen "jäähtyminen" ampumisen välissä.
 */
    public void shoot() { //SHOOT AKA. ADD NEW AMMO TO PLAY
        if (this.turretTemp == 0) {
            this.turretTemp = 8;
            this.ammos.add(new Ammo(this.getTranslateX(), this.getTranslateY(),
                10.0 * Math.cos(Math.toRadians(this.getRotate())) + this.vX,
                10.0 * Math.sin(Math.toRadians(this.getRotate())) + this.vY)
            );
        }
    }
}
