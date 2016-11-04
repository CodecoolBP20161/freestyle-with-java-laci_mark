import javafx.scene.layout.StackPane;

/**
 * Created by makaimark on 2016.11.04..
 */
public class Score {
    private static int score;

    public static void addScore(){
        score++;
    }

    public static void resetScore() {
        score = 0;
    }


    public static int getScore() {
        return score;
    }
}
