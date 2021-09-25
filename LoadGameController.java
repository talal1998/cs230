
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.io.IOException;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * The Class LoadGameController.
 */
public class LoadGameController {

	/** The root. */
	@FXML
	private AnchorPane root;
	
	/** The background image. */
	@FXML
	private ImageView bgImage;
	
	/** The enter user name. */
	@FXML
	private Label enterUserName;
	
	/** The user name textfield. */
	@FXML
	private TextField userName;
	
	/** The back. */
	@FXML
	private Button back;

	/**
	 * Goes back to the previous scene.
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
