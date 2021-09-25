
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

// TODO: Auto-generated Javadoc
/**
 * The Class Main which the game loads from.
 */
public class Main extends Application {
	
	/** The start window of the game . */
	Stage window;

	/**
	 * Start.
	 *
	 * @param primaryStage the primary stage
	 * @throws Exception the exception
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("StartGame.fxml"));
			Scene scene = new Scene(root, 600, 450);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Our Game");// TODO Rename window
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
