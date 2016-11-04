import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;


/**
 * Created by milu_laci on 02.11.16.
 */
public class Tile extends StackPane {
    private int x, y;
    private boolean hasBomb;
    private int bombsAround;
    private boolean isOpen;

    private Rectangle border = new Rectangle(GameArea.TILE_SIZE - 2, GameArea.TILE_SIZE - 2);
    private Text text = new Text();

    Tile(int x, int y, boolean hasBomb) {
        this.x = x;
        this.y = y;
        this.hasBomb = hasBomb;
        if (!(this.hasBomb)) { GameArea.addNotBombCounter();}
        isOpen = false;


        // Fill the Tile with color
        border.setFill(Color.WHITE);

        // Color the border of tiles
        border.setStroke(Color.BLACK);

        // Color the text in the tiles
        text.setFill(Color.BLACK);

        // Append the text and the border to the Pane
        getChildren().addAll(border, text);

        // This part is responsible for the matrix form
        setTranslateX(x * GameArea.TILE_SIZE);
        setTranslateY(y * GameArea.TILE_SIZE);

        // click on tile event handler
        border.setOnMouseClicked(event -> {
            MouseButton button = event.getButton();
            if (!GameArea.gameOverGetter()) {
                if (button == MouseButton.PRIMARY) {
                    open();
                } else if (button == MouseButton.SECONDARY) {
                        text.setText("❗");
                }
            }

        });
    }

    // when tile is clicked
    private void open() {
            if (this.hasBomb()) {
                this.text.setText("☼");
                GameArea.messageField.setMessage("Your head is blown clean off!\n" +
                        "Your score was: " + String.valueOf(Score.getScore()));
                GameArea.gameOverSetter();
            } else {
                this.text.setText(String.valueOf(this.getBombsAround()));
                switch (this.getBombsAround()) {
                    case 1: this.text.setFill(Color.RED);
                        break;
                    case 2: this.text.setFill(Color.BLUE);
                        break;
                    case 3: this.text.setFill(Color.GREEN);
                        break;
                    case 4: this.text.setFill(Color.PURPLE);
                        break;
                    default: this.text.setFill(Color.BLACK);
                }
                if (!(this.isOpen)) {Score.addScore();}
                if (GameArea.getNonBombTiles() == Score.getScore()) {
                    GameArea.messageField.setMessage("You avoided all the bombs!\n" +
                            "Your score is: " + String.valueOf(Score.getScore()));
                }
            }
            this.isOpen = true;

    }


    // Find the tiles around the tile object in the argument
    List<Tile> getNeighbours(Tile tile) {
        List <Tile> neighbours = new ArrayList<>();
        int[] indicies = new int[] {
                -1, -1,
                -1, 0,
                -1, 1,
                0, -1,
                0, 1,
                1, -1,
                1, 0,
                1, 1
        };

        for ( int i = 0; i < indicies.length; i++ ) {
            int newX = tile.x + indicies[i];
            int newY = tile.y + indicies[++i];

            if ( newX < GameArea.TILES_PER_ROW && newX >= 0 && newY >= 0 && newY < GameArea.TILES_PER_COL) {
                neighbours.add(GameArea.getTileFromGrid(newX, newY));
            }
        }
        return neighbours;
    }

    boolean hasBomb() {
        return this.hasBomb;
    }

    void setBombsAround( int numberOfBombs) {
        this.bombsAround = numberOfBombs;
    }

    private int getBombsAround() {
        return this.bombsAround;
    }
}
