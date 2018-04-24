
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
