import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * Created by makaimark on 2016.11.04..
 */
public class Message extends StackPane {
    private static Text messageField = new Text();

    public Message() {
        messageSetter("Try not to die!");
        getChildren().add(messageField);

    }

    public void messageSetter(String message) {
        messageField.setText(message);
        //getChildren().add(messageField);
    }

    public static Text messageGetter() {
        return messageField;
    }
//    messageField.setLayoutX(TILES_PER_ROW * TILE_SIZE / 2 -75 );
//    messageField.setLayoutY(TILES_PER_COL * TILE_SIZE + 80);
}
