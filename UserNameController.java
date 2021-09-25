
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.io.IOException;

import javafx.event.ActionEvent;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * The Class UserNameController.
 */
public class UserNameController {
	
	/** The root. */
	@FXML
	private AnchorPane root;
	
	/** The bg image. */
	@FXML
	private ImageView bgImage;
	
	/** The name. */
	@FXML
	private TextField name;
	
	/** The back. */
	@FXML
	private Button back;
	
	/** The enter. */
	@FXML
	private Button enter;

	/**
	 * Creates the new player.
	 *
	 * @param event the event
	 */
	// Event Listener on TextField[#name].onAction and Button[#enter].onAction
	@FXML
	public void createNewPlayer(ActionEvent event) {
		System.out.println(name.getText());
	}

	/**
	 * Go back.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Event Listener on Button[#back].onAction
	@FXML
	public void goBack(ActionEvent event) throws IOException {

		Pane nextScene = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		root.getChildren().setAll(nextScene);
	}
}
