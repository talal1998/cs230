
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;

/**
 * Draws the basic menu and is used to launch the game.
 */
public class SelectUserScreen extends Application {
	
	/** The Constant MENU_WIDTH. */
	private static final int MENU_WIDTH = 500;
	
	/** The Constant MENU_HEIGHT. */
	private static final int MENU_HEIGHT = 600;
	
	/** The primary stage. */
	private Stage primaryStage;

	/**
	 * Create the primary Stage .
	 *
	 * @param primaryStage the primary stage
	 */
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Select User");

		ScrollPane root = buildUserSelect();

		Scene buttons = new Scene(root, MENU_WIDTH, MENU_HEIGHT);
		this.primaryStage.setScene(buttons);
		this.primaryStage.show();
	}

	/**
	 * Creates the selected level.
	 *
	 * @return the scroll pane
	 */
	private ScrollPane buildUserSelect() {
		ScrollPane toReturn = new ScrollPane();
		// BorderPane root = new BorderPane();

		// canvas = new Canvas(MENU_WIDTH, MENU_HEIGHT);
		// root.setCenter(canvas);

		VBox toolbar = new VBox();
		toolbar.setSpacing(10);
		toolbar.setAlignment(Pos.CENTER);
		toolbar.setPadding(new Insets(35, 160, 35, 160));
		// root.setTop(toolbar);
		toReturn.setContent(toolbar);

		ArrayList<String[]> usersInfo = getUsers();
		for (int i = 0; i < usersInfo.size(); i++) {
			Integer index = new Integer(i);
			String[] userInfo = usersInfo.get(i);
			User user = new User(userInfo[0], Integer.parseInt(userInfo[1]), userInfo[2]);
			Text userName = new Text(user.getName());

			HBox thisUserButtons = new HBox();

			Button userButton = new Button("Play");
			thisUserButtons.getChildren().add(userButton);

			Button deleteButton = new Button("Delete user");
			thisUserButtons.getChildren().add(deleteButton);

			Text levelCounter = new Text("Max level completed = " + user.getMaxLevelComplete());
			Text spacing = new Text("");

			toolbar.getChildren().add(userName);
			toolbar.getChildren().add(thisUserButtons);
			toolbar.getChildren().add(levelCounter);
			toolbar.getChildren().add(spacing);

			userButton.setOnAction(e -> {
				SelectLevelScreen levelSelect = new SelectLevelScreen(primaryStage, user.getMaxLevelComplete(),
						user.getFileName());
				
				// Level level = new Level(user.getFileName(), user.getFileName());
				// level.drawGame();
			});
			deleteButton.setOnAction(e -> {
				usersInfo.remove(usersInfo.get(index));
				File file = new File(user.getFileName());
				file.delete();
				writeToFile(usersInfo);
			});
		}
		Text textBox = new Text("Enter new User's name (No spaces allowed)");
		toolbar.getChildren().add(textBox);
		TextField newUserName = new TextField();
		toolbar.getChildren().add(newUserName);

		Button newUserButton = new Button("Create new user");
		toolbar.getChildren().add(newUserButton);

		newUserButton.setOnAction(e -> {
			String name = newUserName.getText();
			String fileName = (name + ".txt");
			usersInfo.add(new String[] { name, "0", fileName });
			Level createSaveFile = new Level("SomeLevelName.txt", fileName);// TODO: INDICATES THE STARTING LEVEL FILE
			try {
				createSaveFile.toFile();
			} catch (Exception f) {
				System.out.println(e);
			}
			writeToFile(usersInfo);
		});

		Button backButton = new Button("Go back");
		toolbar.getChildren().add(backButton);
		backButton.setOnAction(e -> {
			primaryStage.close();
		});

		return toReturn;
	}

	/**
	 * Adds userInfo to to the save file .
	 *
	 * @param usersInfo the users info
	 */
	private void writeToFile(ArrayList<String[]> usersInfo) {
		try {
			FileWriter file = new FileWriter("users.txt");
			for (String[] user : usersInfo) {
				file.write(user[0] + ";" + user[1] + ";" + user[2] + "\n");
			}
			file.close();
		} catch (IOException e) {
			System.out.println(e);
		}

		start(primaryStage);
	}

	/**
	 * Gets the users from the read file.
	 *
	 * @return the users arrayList. 
	 */
	private ArrayList<String[]> getUsers() {
		File userFile = new File("users.txt");
		Scanner in = null;
		try {
			in = new Scanner(userFile);
		} catch (FileNotFoundException e) {
			System.out.println("User file not found");
		}
		ArrayList<String[]> users = new ArrayList<String[]>();
		while (in.hasNextLine()) {
			users.add(in.nextLine().split(";"));
		}
		in.close();
		return users;
	}
}