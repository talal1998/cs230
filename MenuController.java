
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * The Class MenuController.
 */
public class MenuController {

	/** The root. */
	@FXML
	private AnchorPane root;

	/** The start game. */
	@FXML
	private Button startGame;

	/** The exit. */
	@FXML
	private Button exit;

	/**
	 * Exits the game .
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void exit(ActionEvent event) throws IOException {
		Boolean answer = ConfirmBox.display("Close Window", "Are you sure you want to exit");
		if (answer) {
			Stage s = (Stage) exit.getScene().getWindow();
			System.out.println("the program is close");
			s.close();
		}
	}

	/**
	 * Starts the game.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void startGame(ActionEvent event) throws IOException {
		SelectUserScreen userScreen = new SelectUserScreen();
		userScreen.start(new Stage());
	}

}
