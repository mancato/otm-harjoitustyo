package ui;

import java.util.ArrayList;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
/**
* Näkymä pelin huipputuloksille.
*/
public class ScorePane extends Pane {

    Text topTen;

    public ScorePane() {
        this.setStyle("-fx-background-color: black;");
        topTen = new Text(180.0, 50.0, "");
        topTen.setFill(Color.WHITE);
        topTen.setFont(Font.font(Font.getDefault().getName(), 20.0));
        this.getChildren().add(topTen);
    }
    /**
    * Asettaa huipputulokset näkyviin.
    * @param scores Huipputulokset listana.
    */
    public void getScores(ArrayList<String> scores) {
        String sc = "";
        for (String s: scores) {
            sc += s + "\n";
        }
        topTen.setText(sc);       
    }
}
