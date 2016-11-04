import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * Created by makaimark on 2016.11.04..
 */
public class Message extends StackPane {
    private static Text messageField = new Text();

    Message() {
        setMessage("Try not to die!");
        getChildren().add(messageField);
    }

    void setMessage(String message) {
        messageField.setText(message);
    }

    public static Text messageGetter() {
        return messageField;
    }
}
