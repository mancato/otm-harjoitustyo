/**
* Generoi asteroidit, pitää kirjaa niiden lukumäärästä. 
*/
package logic;

import java.util.ArrayList;
import java.util.Random;

public class AsteroidField extends ArrayList<Asteroid> { //FOR CONTAINING AND SPAWNING ASTEROIDS

    public int level, asteroidTimer;
    Random rnd;
    public AsteroidField() {
        this.level = 4;
        this.rnd = new Random();
    }
/**
* Lisää peliin n kappaletta asteroideja. 
* @param n Generoitavien asteroidien lukumäärä.
* @param sx X-koordinaatti pisteelle, johon ei saa generoida asteroideja.
* @param sy Y-koordinaatti pisteelle, johon ei saa generoida asteroideja.
*/
    public void spawn(int n, double sx, double sy) {
        for (int i = 0; i < n; i++) {
            double x = 640.0 * rnd.nextDouble();
            double y = 480.0 * rnd.nextDouble();
            if (Math.abs(sx - x) < 100.0 && Math.abs(sy - y) < 100.0) {
                i--;
            } else {
                this.add(new Asteroid(7, 1.0, x, y));
            }
        }
        this.asteroidTimer = 100;        
    }
}
