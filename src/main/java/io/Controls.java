package io;

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
/**
* Sisältää näppäinten kartoitukset eri sovelluksen tiloille, kuten pelitila, päävalikko, nimen syöttö.
*/
public class Controls {

    Scene scene;
    public boolean up, left, right, down, spacebar, reset, quit, pkey, gkey, hkey, enter;

    public Controls(Scene scene) {
        this.scene = scene;
    }
    /**
    * Pelin näppäimet.
    */
    public void gameControls() {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        up = true;
                        break;
                    case LEFT:
                        left = true;
                        break;
                    case RIGHT: 
                        right = true;
                        break;
                    case SPACE: 
                        spacebar = true;
                        break;
                    case R: 
                        reset = true; 
                        break;
                    case Q: 
                        quit = true; 
                        break;
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() { 
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    
                        up = false;
                        break;
                    case LEFT:  
                        left = false;
                        break;
                    case RIGHT: 
                        right = false; 
                        break;
                    case SPACE: 
                        spacebar = false; 
                        break;
                    case R: 
                        reset = false; 
                        break;
                    case Q: 
                        quit = false; 
                        break;
                }
            }
        });
    }
    /**
    * Nimen syötön näppäimet.
    */
    public void inputControls() {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() { 
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    
                        up = true; 
                        break;
                    case DOWN:  
                        down = true; 
                        break;
                    case LEFT:  
                        left = true; 
                        break;
                    case RIGHT: 
                        right = true; 
                        break;
                    case R: 
                        reset = true; 
                        break;
                    case Q: 
                        quit = true; 
                        break;
                    case ENTER: 
                        enter = true; 
                        break;
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() { 
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    
                        up = false; 
                        break;
                    case DOWN:  
                        down = false; 
                        break;
                    case LEFT:  
                        left = false; 
                        break;
                    case RIGHT: 
                        right = false; 
                        break;
                    case R: 
                        reset = false; 
                        break;
                    case Q: 
                        quit = false; 
                        break;
                    case ENTER: 
                        enter = false; 
                        break;
                }
            }
        });
    }
    /**
    * Päävalikon näppäimet.
    */
    public void menuControls() {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case P: 
                        pkey = true;
                        break;
                    case G: 
                        gkey = true;
                        break;
                    case H: 
                        hkey = true;
                        break;  
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case P: 
                        pkey = false;
                        break;
                    case G: 
                        gkey = false;
                        break;
                    case H: 
                        hkey = false;
                        break;
                }
            }
        });
    }    


}
