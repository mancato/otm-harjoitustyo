
package ui;
import io.FileHandler;
import logic.Asteroid;
import logic.AsteroidField;
import logic.Ship;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.scene.shape.Shape;

public class Main extends Application {
    int score, mode, index;
    char[] name;
    boolean hold;
    Interface ui;
    AsteroidField asteroids;
    Ship ship;

    @Override
    public void start(Stage stage) throws Exception {
        //INITIALIZATION
        this.name = new char[3];
        this.name[0] = 'A';
        this.name[1] = 'A';
        this.name[2] = 'A';

        mode = 0;

        FileHandler fh = new FileHandler();
        ui = new Interface(stage, "ASTEROIDS", 640, 480, fh.readHighScore());
        asteroids = new AsteroidField();

        //MAIN GAME LOOP
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                switch (mode) {
                    case 0: //MAIN MENU
                        if (ui.gkey) {
                            ui.guide();
                            mode = 1;                            
                        }
                        if (ui.hkey) {
                            ui.scoreScreen(fh.getTopScores());
                            mode = 2;
                        }
                        if (ui.pkey) {
                            ui.pkey = false;
                            mode = 3;
                            ui.gameScreen();
                        }
                        break;
                    case 1: //GUIDE
                        if (!ui.gkey) {
                            mode = 0;
                            ui.startScreen();
                        }
                        break;                        
                    case 2: //HIGH-SCORES
                        if (!ui.hkey) {
                            mode = 0;
                            ui.startScreen();
                        }
                        break;                       
                    case 3: //SETTING UP GAME
                        ship = new Ship(320.0, 240.0, 3.0);       
                        resetGame();
                        mode = 4;
                        break;    
                    case 4: //GAME LOOP
                        if (ui.quit) { //EXIT TO MENU
                            ui.quit = false;
                            clearUp();
                            mode = 0;
                            ui.startScreen();
                        }
                        if (ui.reset) { //RESETTING THE GAME
                            resetGame();
                        }
                        if (asteroids.isEmpty()) { //SPAWN MORE ASTEROIDS
                            spawnAsteroids();
                        }
                        spawnShip(); //DELAY FOR SPAWNING A NEW SHIP, spawnCounter = -1 MEANS SHIP IS IN PLAY
                        updateShip(); // SHIP CONTROLS & MOVEMENT
                        shoot(); //SHOOT!
                        updateAmmos(); //MOVEMENT OF AMMOS
                        updateAsteroids(true); //MOVEMENT OF ASTEROIDS
                        if (score > ui.hiscore) {
                            ui.hiscore = score;
                        }
                        break;
                    case 5: //INPUT PLAYER NAME
                        updateAsteroids(false);
                        if (ui.reset) { //RESETTING THE GAME
                            mode = 4;
                            ui.gameScreen();
                            resetGame();
                        }
                        if (ui.quit) { //EXIT TO MENU
                            ui.quit = false;
                            clearUp();
                            mode = 0;
                            ui.startScreen();
                        }
                        if (!hold) {
                            if (ui.up) {
                                name[index]++;
                            }
                            if (ui.down) {
                                name[index]--;
                            }
                            if (ui.right) {
                                index++;
                            }
                            if (ui.left) {
                                index--;
                            }
                            if (index > 2) {
                                index = 0;
                            }
                            if (index < 0) {
                                index = 2;
                            }
                            if (name[index] > 126) {
                                name[index] = 33;
                            }
                            if (name[index] < 33) {
                                name[index] = 126;
                            }
                        }
                        if (ui.up || ui.down || ui.right || ui.left) {
                            hold = true; //FOR PREVENTION OF SHOOTING sarrrrrrrrja
                        } else {
                            hold = false;
                        } 
                        ui.setName(new String(name));
                        if (ui.enter) {
                            ui.enter = false;
                            ui.gameControls();
                            fh.writeScore(Integer.parseInt(ui.score.getText()), ui.pname.getText());
                        }
                }         
            }
        };
        timer.start();
    }
    
    public void resetGame() {
        ui.resetScreen();
        ui.addShape(ship);
        ui.setScore(0);
        ui.setLives(3);
        ship.lives = 3;
        ship.spawn();
        asteroids.level = 4;
        score = 0;
        clearUp();
    }

    public void clearUp() {
        ship.ammos.clear(); 
        asteroids.clear();      
    }

    public void spawnAsteroids() {
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

    public void spawnShip() {
        if (ship.spawnCounter > 0) {
            ship.spawnCounter--;
        } else if (ship.spawnCounter == 0) {
            boolean noColl = true;
            ship.setTranslateX(320.0);
            ship.setTranslateY(240.0);
            for (Asteroid a: asteroids) {
                if (Shape.intersect(a, ship).getBoundsInLocal().getWidth() != -1) {
                    noColl = false;
                }
            }
            if (noColl) {
                ship.spawn();
            } else {
                ship.setTranslateX(1000.0);
            }
        }
    }
    
    public void updateShip() {
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
    }

    public void shoot() {
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
    }

    public void updateAmmos() {
        for (int i = 0; i < ship.ammos.size(); i++) {
            ship.ammos.get(i).checkBounds();
            ship.ammos.get(i).translate(ship.ammos.get(i).vX, ship.ammos.get(i).vY);
            ship.ammos.get(i).range--;
            if (ship.ammos.get(i).range == 0) {
                ui.removeShape(ship.ammos.get(i));
                ship.ammos.remove(i);
            }
        }        
    }

    public void updateAsteroids(boolean collCheck) {
        for (int i = 0; i < asteroids.size(); i++) {
            Asteroid a = asteroids.get(i);
            a.checkBounds();
            a.translate(a.vX, a.vY);
            if (collCheck) {
                shipCollisionCheck(a);            
                ammosCollisionCheck(a, i);
            }
        }
    }

    public void shipCollisionCheck(Asteroid a) {
        if (Shape.intersect(a, ship).getBoundsInLocal().getWidth() != -1) {
            ship.lives--;       //UPDATE LIFE COUNT
            ui.setLives(ship.lives);
            if (ship.lives == 0) {      //GAME OVER, NO LIVES LEFT
                ship.setTranslateX(1000.0);
                ship.vX = 0.0;
                ship.vY = 0.0;
                ui.removeShape(ship);
                ui.gameOver();
                ui.inputControls();
                this.mode = 5;
            } else {        //LIVES LEFT, SET DELAY FOR SPAWNING A NEW SHIP
                ship.spawnCounter = 50;
                ship.setTranslateX(1000.0);
            }  
        }
    }

    public void ammosCollisionCheck(Asteroid a, int i) {
        for (int j = 0; j < ship.ammos.size(); j++) {
            if (Shape.intersect(a, ship.ammos.get(j)).getBoundsInLocal().getWidth() != -1) {
                ui.removeShape(ship.ammos.get(j));
                ship.ammos.remove(j);
                score += (int) (100 / a.size); //UPDATE SCORE WITH HIT
                ui.setScore(score);
                if (a.size > 0.25) {    //SPAWN 2 NEW ASTEROIDS IF ASTEROID SIZE IS LARGE ENOUGH
                    asteroids.add(
                        new Asteroid(13, a.size / 2.0,
                        a.getTranslateX(), a.getTranslateY())
                    );
                    asteroids.add(
                        new Asteroid(13, a.size / 2.0,
                        a.getTranslateX(), a.getTranslateY())
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

    public static void main(String args[]) {
        Application.launch(args);        
    }
}
