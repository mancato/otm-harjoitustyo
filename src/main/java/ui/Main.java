
package ui;
import logic.Ammo;
import logic.Asteroid;
import logic.AsteroidField;
import logic.Ship;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.scene.shape.Shape;

public class Main extends Application {
    int score;
    boolean hold;
    @Override
    public void start(Stage stage) throws Exception {
        //int score = 0;

        Interface ui = new Interface(stage, "ASTEROIDS", 640, 480);
        AsteroidField asteroids = new AsteroidField();
        Ship ship = new Ship(320.0, 240.0, 3.0);

        ui.setScore(0);
        ui.setLives(3);
        ui.addShape(ship);
        //THE GAME LOOP
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //SPAWN MORE ASTEROIDS
                if (asteroids.isEmpty()) {
                    if (asteroids.asteroidTimer > 0) {
                        asteroids.asteroidTimer--;
                    } else {        
                        asteroids.spawn(asteroids.level, ship.getTranslateX(), ship.getTranslateY());
                        for (Asteroid a: asteroids) {
                            ui.addShape(a);
                        }
                        if (asteroids.level < 10) {
                            asteroids.level++;
                        }
                    }
                }
                //DELAY FOR SPAWNING A NEW SHIP, spawnCounter = -1 MEANS SHIP IS IN PLAY
                if (ship.spawnCounter > 0) {
                    ship.spawnCounter--;
                } else if (ship.spawnCounter == 0) {
                    ship.spawn();
                }
                // SHIP CONTROLS & MOVEMENT
                if (ship.lives > 0 && ship.spawnCounter == -1) {
                    double thrust = 0;              
                    if (ui.up) {
                        thrust += 0.08;
                    } 
                    if (ui.right) {
                        ship.rotate(5.0);
                    }
                    if (ui.left) {
                        ship.rotate(-5.0);
                    }
                    ship.accelerate(thrust);
                    ship.checkBounds();
                    ship.translate(ship.vX, ship.vY);
                }
                //SHOOT!
                if (ship.turretTemp > 0) {
                    ship.turretTemp--;
                }
                if (!hold && ui.spacebar && ship.turretTemp + ship.spawnCounter == -1 && ship.lives > 0) {
                    ship.shoot();
                    ui.addShape(ship.ammos.get(ship.ammos.size() - 1));                    
                }
                if (ui.spacebar) {
                    hold = true; //FOR PREVENTION OF SHOOTING sarrrrrrrrja
                } else {
                    hold = false;
                }
                //MOVEMENT OF AMMUNITION
                for (int i = 0; i < ship.ammos.size(); i++) {
                    ship.ammos.get(i).checkBounds();
                    ship.ammos.get(i).translate(ship.ammos.get(i).vX, ship.ammos.get(i).vY);
                    ship.ammos.get(i).range--;
                    if (ship.ammos.get(i).range == 0) {
                        ui.removeShape(ship.ammos.get(i));
                        ship.ammos.remove(i);
                    }
                }
                //ASTEROID MOVEMENT & COLLISION CHECK WITH SHIP
                for (int i = 0; i < asteroids.size(); i++) {
                    Asteroid a = asteroids.get(i);
                    a.checkBounds();
                    a.translate(a.vX, a.vY);
                    //COLLISION CHECK
                    if (Shape.intersect(a, ship).getBoundsInLocal().getWidth() != -1) {
                        ship.lives--; //UPDATE LIFE COUNT
                        ui.setLives(ship.lives);
                        //GAME OVER, NO LIVES LEFT
                        if (ship.lives == 0) {
                            ship.setTranslateX(1000.0);
                            ship.vX = 0.0;
                            ship.vY = 0.0;
                            ui.removeShape(ship);
                            ui.gameOver();
                        } else { //LIVES LEFT, SET DELAY FOR SPAWNING A NEW SHIP
                            ship.spawnCounter = 50;
                            ship.setTranslateX(1000.0);
                        }  
                    }
                    for (int j = 0; j < ship.ammos.size(); j++) {
                        if (Shape.intersect(a, ship.ammos.get(j)).getBoundsInLocal().getWidth() != -1) {
                            ui.removeShape(ship.ammos.get(j));
                            ship.ammos.remove(j);
                            score += (int) (100 / a.size); //UPDATE SCORE WITH HIT
                            ui.setScore(score);
                            //SPAWN 2 NEW ASTEROIDS IF ASTEROID SIZE IS LARGE ENOUGH
                            if (a.size > 0.25) {
                                asteroids.add(
                                    new Asteroid(13, a.size / 2.0
                                    , a.getTranslateX(), a.getTranslateY())
                                );
                                asteroids.add(
                                    new Asteroid(13, a.size / 2.0
                                    , a.getTranslateX(), a.getTranslateY())
                                );
                                ui.addShape(asteroids.get(asteroids.size() - 1));
                                ui.addShape(asteroids.get(asteroids.size() - 2));
                            }
                            //REMOVE PREVIOUS ASTEROID
                            ui.removeShape(a);
                            asteroids.remove(i); 
                        }
                    }
                }              
            }
        };
        timer.start();
    }

    public static void main(String args[]) {
        Application.launch(args);        
    }
}
