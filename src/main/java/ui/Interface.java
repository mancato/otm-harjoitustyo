
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
import java.util.ArrayList;
public class Interface{

    Stage stage;
    Scene scene;
    Pane pane;
    Text score;
    Text lives;
    Polygon lifeSymbol;
    boolean up,left,right,down,spacebar,reset,quit,pkey,gkey,hkey,enter;
    int hiscore;
    Text pname;

    public Interface(Stage stage, String title, int width, int height, int hiscore){
        this.stage = stage;
        this.pane = new Pane();
        pane.setStyle("-fx-background-color: black;");
        this.scene = new Scene(pane,640,480);
        this.hiscore = hiscore;
        stage.setScene(scene);
        stage.setTitle(title);
        stage.setMinWidth(width);
        stage.setMinHeight(height);
        stage.setMaxWidth(width);
        stage.setMaxHeight(height);
        stage.setOnCloseRequest(e -> Platform.exit());
        stage.show();
        startScreen();         
    }

    void startScreen(){ // TITLE MENU
        this.pane.getChildren().clear();  
        Text titleText = new Text(160.0,100.0,"ASTEROIDS");
        Text play = new Text(280.0,200.0,"PLAY");
        Text guide = new Text(270.0,250.0,"GUIDE");
        Text hof = new Text(210.0,300.0,"HALL OF FAME");
        Text highScore = new Text(210.0,400.0,"HIGH SCORE: " + String.valueOf(hiscore));
        titleText.setFill(Color.WHITE);
        titleText.setFont(Font.font(Font.getDefault().getName(), 50.0));
        play.setFill(Color.WHITE);
        play.setFont(Font.font(Font.getDefault().getName(), 30.0));
        guide.setFill(Color.WHITE);
        guide.setFont(Font.font(Font.getDefault().getName(), 30.0));
        hof.setFill(Color.WHITE);
        hof.setFont(Font.font(Font.getDefault().getName(), 30.0));
        highScore.setFill(Color.WHITE);
        highScore.setFont(Font.font(Font.getDefault().getName(), 20.0));
        this.pane.getChildren().addAll(titleText,play,guide,hof,highScore);
        menuControls();                        
    }

    void guide() { //GUIDE SCREEN
        this.pane.getChildren().clear();                
        String wallOfText = "*HOW TO PLAY:\n    The objective of the game is to destroy asteroids by\nshooting them. Hitting an asteroid will cause it to break\n into two smaller asteroids, until they completely\ndisappear. If you hit an asteroid with the ship, amount of\nlives is decreased by one. When all asteroids are\ndestroyed,more will appear after a short break.\n\n\n*CONTROLS:\n   Accelerate ship: UP KEY\n   Rotate ship: LEFT/RIGHT KEY\n   Shoot: SPACEBAR\n   Reset Game: R\n   Quit to Menu: Q";
        Text g = new Text(50.0,50.0,wallOfText);
        g.setFill(Color.WHITE);
        g.setFont(Font.font(Font.getDefault().getName(), 20.0));
        this.pane.getChildren().add(g);
    }

    void gameScreen() { //GAME SCREEN
        resetScreen();
        gameControls();
        
    }

    void scoreScreen(ArrayList<String> scores) {
        this.pane.getChildren().clear();
        String sc = "";
        for (String s: scores) {
            sc += s + "\n";
        }
        Text topTen = new Text(180.0,50.0,sc);
        topTen.setFill(Color.WHITE);
        topTen.setFont(Font.font(Font.getDefault().getName(), 20.0));
        this.pane.getChildren().add(topTen);
    }

    void resetScreen(){ // (RE)SET SCREEN FOR GAME
        this.pane.getChildren().clear();
        //DRAW SCORE
        score = new Text(20,30,String.valueOf(0)); //GAME SCORE AS TEXT
        score.setFill(Color.WHITE);
        score.setFont(Font.font(Font.getDefault().getName(),30.0));
        pane.getChildren().add(score);       
        //DRAW NUMBER OF LIVES
        lives = new Text(45.0,65.0,"x");
        lives.setFill(Color.WHITE);
        lives.setFont(Font.font(Font.getDefault().getName(), 20.0));
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
    }

    void gameOver(){ //TRIGGER GAME OVER MESSAGE
        Text gm = new Text(160.0,240.0,"GAME OVER");
        gm.setFill(Color.WHITE);
        gm.setFont(Font.font(Font.getDefault().getName(), 50.0));
        pname = new Text(240.0,300.0,"AAA");
        pname.setFill(Color.WHITE);
        pname.setFont(Font.font(Font.getDefault().getName(), 50.0));
        this.pane.getChildren().addAll(gm,pname);
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

    void menuControls() {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case P: pkey = true;break;
                    case G: gkey = true;break;
                    case H: hkey = true;break;  
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case P: pkey = false;break;
                    case G: gkey = false;break;
                    case H: hkey = false;break;
                }
            }
        });
    }

    void gameControls() {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    up = true; break;
                    case LEFT:  left = true; break;
                    case RIGHT: right = true; break;
                    case SPACE: spacebar = true; break;
                    case R: reset = true; break;
                    case Q: quit = true; break;
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() { 
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    up = false; break;
                    case LEFT:  left = false; break;
                    case RIGHT: right = false; break;
                    case SPACE: spacebar = false; break;
                    case R: reset = false; break;
                    case Q: quit = false; break;
                }
            }
        });
    }

    void inputControls() {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() { 
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    up = true; break;
                    case DOWN:  down = true; break;
                    case LEFT:  left = true; break;
                    case RIGHT: right = true; break;
                    case R: reset = true; break;
                    case Q: quit = true; break;
                    case ENTER: enter = true; break;
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() { 
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    up = false; break;
                    case DOWN:  down = false; break;
                    case LEFT:  left = false; break;
                    case RIGHT: right = false; break;
                    case R: reset = false; break;
                    case Q: quit = false; break;
                    case ENTER: enter = false; break;
                }
            }
        });
    }

    void setName(String name) {
        this.pname.setText(name);
    }       
}
