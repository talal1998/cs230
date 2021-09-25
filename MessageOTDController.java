
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

// TODO: Auto-generated Javadoc
/**
 * The Class MessageOTDController.
 */
public class MessageOTDController implements Initializable {

	/** The root of the scene. */
	@FXML
	private AnchorPane root;

	/** The next button . */
	@FXML
	private Button next;

	/** The back button . */
	@FXML
	private Button back;

	/** The message label . */
	@FXML
	private Label message;

	/** The puzzle. */
	private String puzzle;

	/**
	 * Gets the message of the day from the server.
	 *
	 * @param location  the location
	 * @param resources the resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			puzzle = GetMessage.sendGET();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		puzzle = GetMessage.decode(puzzle);
		try {
			puzzle = GetMessage.sendGETQuery(puzzle);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		message.setText(puzzle);
	}

	/**
	 * go to the Next scene.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Event Listener on Button[#next].onAction
	@FXML
	public void nextScene(ActionEvent event) throws IOException {
		Pane nextScene = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		root.getChildren().setAll(nextScene);
	}

	/**
	 * Go to the previous scene.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Event Listener on Button[#back].onAction
	@FXML
	public void goBack(ActionEvent event) throws IOException {

		Pane nextScene = FXMLLoader.load(getClass().getResource("StartGame.fxml"));
		root.getChildren().setAll(nextScene);
	}

}
