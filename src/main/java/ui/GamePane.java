package ui;

import java.util.ArrayList;
import javafx.scene.layout.Pane;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Polygon;
import javafx.event.EventHandler;
/**
* Pelinäkymä. Piirtää ruudulle kaiken pelissä tarvittavan.
*/
public class GamePane extends Pane {

    Text score, lives, pname, gm;
    Polygon lifeSymbol;

    public GamePane() {
        this.setStyle("-fx-background-color: black;");
        //DRAW SCORE
        score = new Text(20, 30, String.valueOf(0)); //GAME SCORE AS TEXT
        score.setFill(Color.WHITE);
        score.setFont(Font.font(Font.getDefault().getName(), 30.0));      
        //DRAW NUMBER OF LIVES
        lives = new Text(45.0, 65.0, "x");
        lives.setFill(Color.WHITE);
        lives.setFont(Font.font(Font.getDefault().getName(), 20.0));
        //DRAW LIVES SYMBOL
        Polygon lifeSymbol = new Polygon();
        lifeSymbol.setFill(Color.TRANSPARENT);
        lifeSymbol.setStroke(Color.WHITE);
        lifeSymbol.setTranslateX(30.0);
        lifeSymbol.setTranslateY(60.0);
        lifeSymbol.getPoints().addAll(new Double[]{ //FORM OF SHIP SYMBOL
            0.0, -12.0,
            -8.0, 12.0,
            0.0, 6.0,
            8.0, 12.0
        });
        this.getChildren().addAll(score, lives, lifeSymbol);
    }
    /**
    * Asettaa ruutuun "GAME OVER"-näkymän.
    */
    public void gameOver() { //TRIGGER GAME OVER MESSAGE
        gm = new Text(160.0, 240.0, "GAME OVER");
        gm.setFill(Color.WHITE);
        gm.setFont(Font.font(Font.getDefault().getName(), 50.0));
        pname = new Text(240.0, 300.0, "AAA");
        pname.setFill(Color.WHITE);
        pname.setFont(Font.font(Font.getDefault().getName(), 50.0));
        this.getChildren().addAll(gm, pname);
    }
    /**
    * Päivittää ruudulla näkyvän pistemäärän.
    * @param score Pistemäärä.
    */    
    public void setScore(int score) { //UPDATE SCORE SHOWN ON SCREEN
        this.score.setText(String.valueOf(score));
    }
    /**
    * Päivittää ruudulla näkyvän elämien määrän.
    * @param lives Elämien määrä.
    */  
    public void setLives(int lives) { //UPDATE NUMBER OF LIVES SHOWN ON SCREEN
        this.lives.setText("x" + String.valueOf(lives));
    }
    /**
    * Päivittää "GAME OVER"-ruudulla näkyvän pelaajan nimen.
    * @param name Nimi.
    */ 
    public void setName(String name) {
        this.pname.setText(name);
    } 
    
}
