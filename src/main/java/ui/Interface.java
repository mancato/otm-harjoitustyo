
package ui;

import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Polygon;
import javafx.event.EventHandler;

public class Interface{

    Stage stage;
    Scene scene;
    Pane pane;
    Text score;
    Text lives;
    Polygon lifeSymbol;
    boolean up,left,right,spacebar;

    public Interface(Stage stage, String title, int width, int height){
        this.stage = stage;
        this.pane = new Pane();
        pane.setStyle("-fx-background-color: black;");
        this.scene = new Scene(pane,640,480);
        //DRAW SCORE
        score = new Text(20,30,String.valueOf(0)); //GAME SCORE AS TEXT
        score.setFill(Color.WHITE);
        //score.setFont(Font.font("Tlwg Typo", 30.0));
        pane.getChildren().add(score);       
        //DRAW NUMBER OF LIVES
        lives = new Text(45.0,65.0,"x");
        lives.setFill(Color.WHITE);
        //lives.setFont(Font.font("Tlwg Typo", 20.0));
        pane.getChildren().add(lives);
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
        pane.getChildren().add(lifeSymbol);
        //SETUP        
        stage.setScene(scene);
        stage.setTitle(title);
        stage.setMinWidth(width);
        stage.setMinHeight(height);
        stage.setMaxWidth(width);
        stage.setMaxHeight(height);
        stage.setOnCloseRequest(e -> Platform.exit());
        stage.show();
        //KEY INPUT - KEY PRESS
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    up = true; break;
                    case LEFT:  left = true; break;
                    case RIGHT: right = true; break;
                    case SPACE: spacebar = true; break;
                }
            }
        });
        //KEY INPUT - RELEASE
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() { 
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    up = false; break;
                    case LEFT:  left = false; break;
                    case RIGHT: right = false; break;
                    case SPACE: spacebar = false; break;
                }
            }
        });          
    }

    void addShape(Shape shape){ //FOR DISPLAYING OBJECTS ON SCREEN
        this.pane.getChildren().add(shape);
    }

    void removeShape(Shape shape){ //FOR REMOVING OBJECTS FROM SCREEN
        this.pane.getChildren().remove(shape);
    }
    
    void setScore(int score){ //UPDATE SCORE SHOWN ON SCREEN
            this.score.setText(String.valueOf(score));
    }

    void setLives(int lives){ //UPDATE NUMBER OF LIVES SHOWN ON SCREEN
        this.lives.setText("x" + String.valueOf(lives));
    }

    void gameOver(){ //TRIGGER GAME OVER MESSAGE
        Text gm = new Text(270.0,240.0,"GAME OVER"); //x = 180.0
        gm.setFill(Color.WHITE);
        //gm.setFont(Font.font("Tlwg Typo", 50.0));
        this.pane.getChildren().add(gm);        
    }
}
