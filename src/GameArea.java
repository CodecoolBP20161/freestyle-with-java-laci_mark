import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by milu_laci on 02.11.16.
 */
public class GameArea extends Application {

    public static final int TILE_SIZE = 20;
    private static final int TILES_PER_ROW = 10;
    private static final int TILES_PER_COL = 10;
    private Tile[][] grid = new Tile[TILES_PER_ROW][TILES_PER_COL];

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(TILES_PER_ROW * TILE_SIZE, TILES_PER_COL * TILE_SIZE);
        for (int x = 0; x < TILES_PER_ROW; x++) {
            for (int y = 0; y < TILES_PER_COL; y++) {
                Tile tile = new Tile(x, y, Math.random() < 0.2);

                grid[x][y] = tile;
                root.getChildren().add(grid[x][y]);
            }
        }

        return root;
    }

    public static void main(String[] args){
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("MineSweeper 1.0");
        Scene scene = new Scene(createContent());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
