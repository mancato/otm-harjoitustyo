
package logic;

import javafx.scene.paint.Color;
import java.util.Random;
/**
* Asteroidia kuvaava luokka.
*/
public class Asteroid extends PolygonObject {
    
    public double size;
    Random rnd;
/**
 * Konstruktori
 * @param n Asteroidin muodon virittävien pisteiden määrä.
 * @param size Asteroidin koko.
 * @param x0 Asteroidin x-koordinaatti.
 * @param y0 Asteroidin y-koordinaatti.
 */    

    
    public Asteroid(int n, double size, double x0, double y0) {
        this.size = size;
        this.rnd = new Random();
        this.setTranslateX(x0);
        this.setTranslateY(y0);
        this.setFill(Color.TRANSPARENT);
        this.setStroke(Color.WHITE);
        this.vX = Math.pow(size, -0.7) * (2.0 * rnd.nextDouble() - 1.0); //RANDOM SPEED
        this.vY = Math.pow(size, -0.7) * (2.0 * rnd.nextDouble() - 1.0);
        this.getPoints().addAll(initialize(n)); //SET ASTEROID FORM       
    }
    /**
 *  Luo asteroidin muodon
 *  @param n Asteroidin muodon virittävien pisteiden lukumäärä.
 *  @return Random-generoidut pisteet, joilla asteroidi muodostetaan.
 */
    Double[] initialize(int n) { //GENERATE RANDOM SURFACE FOR ASTEROID, n=AMOUNT OF VERTICES
        double rMin = size * 30.0;
        double rMax = size * (rMin + 25.0);
        double radius = 0.0;
        double angle = 0.0;
        Double[] p = new Double[2 * n];
        for (int i = 0; i < 2 * n - 1; i += 2) {
            radius = rMax * rnd.nextDouble() + rMin;
            angle = (rnd.nextDouble() + i) * Math.PI / n;;
            p[i] = radius * Math.cos(angle);
            p[i + 1] = radius * Math.sin(angle);
        }
        return p;
    }

}
