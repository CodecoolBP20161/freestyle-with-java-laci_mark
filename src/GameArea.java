import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Created by milu_laci on 02.11.16.
 */
public class GameArea extends Application {

    public static final int TILE_SIZE = 20;
    public static final int TILES_PER_ROW = 10;
    public static final int TILES_PER_COL = 10;
    private static Tile[][] grid = new Tile[TILES_PER_ROW][TILES_PER_COL];

    /*
     * Create the Tiles, put them in the matrix and append them to the Pane one by one
     * Return with the game area
     */
    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(TILES_PER_ROW * TILE_SIZE, TILES_PER_COL * TILE_SIZE);

        for (int x = 0; x < TILES_PER_ROW; x++) {
            for (int y = 0; y < TILES_PER_COL; y++) {
                Tile tile = new Tile(x, y, Math.random() < 0.2);
                //tile.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, new ClickOnTileActionListener());
                grid[x][y] = tile;
                root.getChildren().add(grid[x][y]);
            }
        }

        for (int x = 0; x < TILES_PER_ROW; x++) {
            for (int y = 0; y < TILES_PER_COL; y++) {
                Tile tile = grid[x][y];

                if ( tile.hasBomb()) {
                    continue;
                }

                long numberOfBombs = tile.getNeighbours(tile).stream().filter(actualTile -> actualTile.hasBomb()).count();
                tile.setBombsAround((int)numberOfBombs);
                //tile.text.setText(String.valueOf(numberOfBombs));
            }
        }
        return root;
    }

    public static void main(String[] args){
        launch(args);
    }

    static Tile getTileFromGrid(int x, int y) {
        return grid[x][y];
    }

    /*
     * Responsible for the display
     */
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("MineSweeper 1.0");

        Scene scene = new Scene(createContent());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
