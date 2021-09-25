
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * The Class StartGameController.
 */
public class StartGameController {

	/** The root. */
	@FXML
	private Pane root;

	/** The background image. */
	@FXML
	private ImageView bgImage;

	/**
	 * Load next scene.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void loadNextScene(MouseEvent event) throws IOException {
		Pane nextScene = FXMLLoader.load(getClass().getResource("MessageOTD.fxml"));
		root.getChildren().setAll(nextScene);

	}

	/**
	 * Key pressed goes to the message of the day.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// TODO fix key pressed.
	@FXML
	void keyPressed(KeyEvent event) throws IOException {
		Pane nextScene = FXMLLoader.load(getClass().getResource("MessageOTD.fxml"));
		root.getChildren().setAll(nextScene);
	}
}