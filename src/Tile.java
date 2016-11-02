import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


/**
 * Created by milu_laci on 02.11.16.
 */
public class Tile extends StackPane {
    int x, y;
    boolean hasBomb;

    private Rectangle border = new Rectangle(GameArea.TILE_SIZE - 2, GameArea.TILE_SIZE - 2);
    private Text text = new Text();

    public Tile (int x, int y, boolean hasBomb) {
        this.x = x;
        this.y = y;
        this.hasBomb = hasBomb;

        border.setStroke(Color.LIGHTGRAY);

        text.setText(hasBomb ? "X" : "");

        getChildren().addAll(border, text);

        setTranslateX(x * GameArea.TILE_SIZE);
        setTranslateY(y * GameArea.TILE_SIZE);
    }
}
