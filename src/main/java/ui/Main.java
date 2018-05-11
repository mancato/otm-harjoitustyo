package ui;

import io.FileHandler;
import io.Controls;
import logic.Asteroid;
import logic.AsteroidField;
import logic.Ship;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.animation.AnimationTimer;
import javafx.scene.shape.Shape;
/**
* Pääluokka.
*/
public class Main extends Application {

    int score, mode, index;
    char[] name;
    boolean hold;
    
    AsteroidField asteroids;
    Ship ship;

    Scene scene;
    Controls controls;
    FileHandler fh;

    GamePane gp;
    MenuPane mp;
    ScorePane sp;
    GuidePane gup;

    @Override
    public void start(Stage stage) throws Exception {
        
        fh = new FileHandler("scores.txt");
        
        mp = new MenuPane(fh.readHighScore());
        gp = new GamePane();
        sp = new ScorePane();
        gup = new GuidePane();

        scene = new Scene(mp, 640, 480);
        stage.setScene(scene);
        stage.setTitle("ASTEROIDS");
        stage.setMinWidth(640);
        stage.setMinHeight(480);
        stage.setMaxWidth(640);
        stage.setMaxHeight(480);
        stage.setOnCloseRequest(e -> Platform.exit());
        stage.show(); 

        mode = 2;

        this.name = new char[3];
        this.name[0] = 'A';
        this.name[1] = 'A';
        this.name[2] = 'A';

        sp.getScores(fh.getTopScores());

        controls = new Controls(scene);

        asteroids = new AsteroidField();
        ship = new Ship(320.0, 240.0, 3.0);

        score = 0;

        controls.menuControls();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                switch (mode) {
                    case 0: //GAME MODE
                        cycleGame();
                        break;
                    case 1: //GAME OVER MODE
                        cycleGameOver();
                        break;
                    case 2: //MENU MODE
                        cycleMenu();                        
                        break;
                    case 3: //HIGH SCORES MODE
                        if (!controls.hkey) {
                            mode = 2;
                            scene.setRoot(mp);
                        }
                        break;
                    case 4: //GUIDE MODE
                        if (!controls.gkey) {
                            mode = 2;
                            scene.setRoot(mp);
                        }
                        break;
                }
            }
        };
        timer.start();        
    }
    /**
    * Suorittaa yhden "syklin" pelissä, jonka aikana päivitetään kaikki pelissä näkyvät oliot.
    */
    public void cycleGame() {
        if (controls.quit) { //EXIT TO MENU
            quitGame();
        }
        if (controls.reset) {
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
    }
    /**
    * Suorittaa yhden "syklin" "GAME OVER"-ruudussa, jossa pelaaja voi syöttää nimensä.
    */
    public void cycleGameOver() {
        updateAsteroids(false); //ASTEROIDS KEEP ON MOVING IN THE BACKGROUND
        if (controls.reset) { //RESETTING THE GAME
            gp.getChildren().add(ship);
            controls.gameControls();
            mode = 0;
            resetGame();
        }
        if (controls.quit) { //EXIT TO MENU
            quitGame();
        }
        if (!hold) {
            if (controls.up) {
                name[index]++;
            }
            if (controls.down) {
                name[index]--;
            }
            if (controls.right) {
                index++;
            }
            if (controls.left) {
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
        if (controls.up || controls.down || controls.right || controls.left) {
            gp.setName(new String(name));
            hold = true; //FOR PREVENTION OF SHOOTING sarrrrrrrrja
        } else {
            hold = false;
        } 
        if (controls.enter) {
            controls.enter = false;
            controls.gameControls();
            fh.writeScore(score, gp.pname.getText());
        }
    }
    /**
    * Suorittaa yhden "syklin" päävalikkonäkymässä.
    */
    public void cycleMenu() {
        if (controls.pkey) {
            controls.pkey = false;
            mode = 0;
            gp.getChildren().add(ship);
            resetGame();
            scene.setRoot(gp);
            controls.gameControls();
            
        }
        if (controls.hkey) {
            mode = 3;
            sp.getScores(fh.getTopScores());
            scene.setRoot(sp);
        }
        if (controls.gkey) {
            mode = 4;
            scene.setRoot(gup);
        }
    }
    /**
    * Resetoi pelinäkymän.
    */
    public void resetGame() {
        score = 0;
        ship.lives = 3;
        ship.spawn();
        asteroids.level = 4;
        //UPDATE G.U.I.
        gp.setLives(3);
        gp.setScore(0);
        gp.getChildren().removeAll(asteroids);
        gp.getChildren().removeAll(ship.ammos);
        gp.getChildren().removeAll(gp.gm, gp.pname);
        cleanUp(); //CLEAR ASTEROIDS & AMMOS
    }
    /**
    * Lopettaa pelin ja siirtyy takaisin päävalikkoon.
    */
    public void quitGame() {
        controls.quit = false;
        gp.getChildren().remove(ship);
        gp.getChildren().removeAll(asteroids);
        gp.getChildren().removeAll(ship.ammos);
        mp.setHighScore(fh.readHighScore());
        cleanUp();
        mode = 2;
        scene.setRoot(mp);
        controls.menuControls();
    }
    /**
    * Siivoaa ammukset ja asteroidit pois pelistä.
    */ 
    public void cleanUp() {
        ship.ammos.clear(); 
        asteroids.clear();      
    }
    /**
    * Lisää asteroideja peliin jos niitä ei ole.
    */
    public void spawnAsteroids() {
        if (asteroids.asteroidTimer > 0) {
            asteroids.asteroidTimer--;
        } else {        
            asteroids.spawn(asteroids.level, ship.getTranslateX(), ship.getTranslateY());
            for (Asteroid a: asteroids) {
                gp.getChildren().add(a);
            }
            if (asteroids.level < 10) {
                asteroids.level++;
            }
        }
    }
    /**
    * Lisää aluksen peliin, huomioiden ettei alus osu asteroidiin syntyessään.
    */
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
    /**
    *   Päivittää aluksen koordinaatit, orientaation ja nopeuden.
    */   
    public void updateShip() {
        if (ship.lives > 0 && ship.spawnCounter == -1) {
            double thrust = 0;              
            if (controls.up) {
                thrust += 0.08;
            } 
            if (controls.right) {
                ship.rotate(5.0);
            }
            if (controls.left) {
                ship.rotate(-5.0);
            }
            ship.accelerate(thrust);
            ship.checkBounds();
            ship.translate(ship.vX, ship.vY);
        }
    }
    /**
    * Ampuu ammuksen aluksesta.
    */
    public void shoot() {
        if (ship.turretTemp > 0) {
            ship.turretTemp--;
        }
        if (!hold && controls.spacebar && ship.turretTemp + ship.spawnCounter == -1 && ship.lives > 0) {
            ship.shoot();
            gp.getChildren().add(ship.ammos.get(ship.ammos.size() - 1));                    
        }
        if (controls.spacebar) {
            hold = true; //FOR PREVENTION OF SHOOTING sarrrrrrrrja
        } else {
            hold = false;
        }        
    }
    /**
    * Päivittää ammusten koordinaatit. Poistaa ne pelistä jos ne ovat edenneet tarpeeksi kauas.
    */
    public void updateAmmos() {
        for (int i = 0; i < ship.ammos.size(); i++) {
            ship.ammos.get(i).checkBounds();
            ship.ammos.get(i).translate(ship.ammos.get(i).vX, ship.ammos.get(i).vY);
            ship.ammos.get(i).range--;
            if (ship.ammos.get(i).range == 0) {
                gp.getChildren().remove(ship.ammos.get(i));
                ship.ammos.remove(i);
            }
        }        
    }
    /** 
    *   Päivittää asteroidien koordinaatit.
    * @param collCheck Määrittää tarkistetaanko asteroidien ja muiden objektien väliset törmäykset, false valitaan "GAME OVER"-näkymässä.
    */
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
    /**
    * Tarkistaa aluksen ja asteroidin välisen törmäyksen.
    * @param a Asteroidi jonka törmäystä tarkastellaan.
    */
    public void shipCollisionCheck(Asteroid a) {
        if (Shape.intersect(a, ship).getBoundsInLocal().getWidth() != -1) {
            ship.lives--;       //UPDATE LIFE COUNT
            gp.setLives(ship.lives);
            if (ship.lives == 0) {      //GAME OVER, NO LIVES LEFT
                ship.setTranslateX(1000.0);
                ship.vX = 0.0;
                ship.vY = 0.0;
                gp.getChildren().remove(ship);
                gp.getChildren().removeAll(ship.ammos);
                ship.ammos.clear();                
                gp.gameOver();
                controls.inputControls();
                this.mode = 1;
            } else {        //LIVES LEFT, SET DELAY FOR SPAWNING A NEW SHIP
                ship.spawnCounter = 50;
                ship.setTranslateX(1000.0);
            }  
        }
    }
    /**
    * Tarkistaa ammusten ja asteroidin välisen törmäyksen.
    * @param a Asteroidi jota tarkastellaan.
    * @param i Asteroidin indeksi AsteroidField-luokan asteroidilistassa.
    */
    public void ammosCollisionCheck(Asteroid a, int i) {
        for (int j = 0; j < ship.ammos.size(); j++) {
            if (Shape.intersect(a, ship.ammos.get(j)).getBoundsInLocal().getWidth() != -1) {
                gp.getChildren().remove(ship.ammos.get(j));
                ship.ammos.remove(j);
                score += (int) (100 / a.size); //UPDATE SCORE WITH HIT
                gp.setScore(score);
                if (a.size > 0.25) {    //SPAWN 2 NEW ASTEROIDS IF ASTEROID SIZE IS LARGE ENOUGH
                    asteroids.add(
                        new Asteroid(13, a.size / 2.0,
                        a.getTranslateX(), a.getTranslateY())
                    );
                    asteroids.add(
                        new Asteroid(13, a.size / 2.0,
                        a.getTranslateX(), a.getTranslateY())
                    );
                    gp.getChildren().add(asteroids.get(asteroids.size() - 1));
                    gp.getChildren().add(asteroids.get(asteroids.size() - 2));
                }
                //REMOVE PREVIOUS ASTEROID
                gp.getChildren().remove(a);
                asteroids.remove(i); 
            }
        }
    }

    public static void main(String args[]) {
        Application.launch(args);        
    }   
}
