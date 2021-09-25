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
 * The Class SelectLevelScreen'
 *	This is the class to select the level.
 */
public class SelectLevelScreen {

	/** The Constant MENU_WIDTH. */
	private static final int MENU_WIDTH = 500;
	
	/** The Constant MENU_HEIGHT. */
	private static final int MENU_HEIGHT = 600;
	
	/** The primary stage. */
	private Stage primaryStage;
	
	/** The current max level. */
	private int currentMaxLevel;
	
	/** The file name. */
	private String fileName;
	
	/** The level complete. */
	private Boolean levelComplete = false;
	
	/** The level. */
	Level level;

	/**
	 * Instantiates a new select level screen.
	 *
	 * @param primaryStage the primary stage
	 * @param currentMaxLevel the current max level
	 * @param fileName the file name
	 */
	public SelectLevelScreen(Stage primaryStage, int currentMaxLevel, String fileName) {
		this.currentMaxLevel = currentMaxLevel;
		this.fileName = fileName;
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Select Level");

		ScrollPane root = buildLevelSelect();

		Scene buttons = new Scene(root, MENU_WIDTH, MENU_HEIGHT);
		this.primaryStage.setScene(buttons);
		this.primaryStage.show();
	}
	
	/**
	 * Creates the select level.
	 *
	 * @return the scroll pane
	 */
	private ScrollPane buildLevelSelect() {
		ScrollPane toReturn = new ScrollPane();

		VBox toolbar = new VBox();
		toolbar.setSpacing(10);
		toolbar.setAlignment(Pos.CENTER);
		toolbar.setPadding(new Insets(35, 160, 35, 160));
		// root.setTop(toolbar);
		toReturn.setContent(toolbar);
		ArrayList<String> levelNames = getLevelNames();
		for (int i = 0; i <= currentMaxLevel; i++) {
			Integer index = (Integer)i;
			// System.out.println(levelNames.get(0));
			Button levelButton = new Button(levelNames.get(i).substring(0, levelNames.get(i).length() - 4));
			toolbar.getChildren().add(levelButton);

			levelButton.setOnAction(e -> {
				level = new Level(levelNames.get(index), fileName);
				level.drawGame();
			});
		}
		Text loadGame = new Text("Load Saved game");
		toolbar.getChildren().add(loadGame);
		Button levelButton = new Button(fileName.substring(0, fileName.length() - 4));
		toolbar.getChildren().add(levelButton);

		Button backButton = new Button("Go back");
		toolbar.getChildren().add(backButton);

		levelButton.setOnAction(e -> {
			level = new Level(fileName, fileName);
			level.drawGame();
		});

		backButton.setOnAction(e -> {
			SelectUserScreen userScreen = new SelectUserScreen();
			userScreen.start(primaryStage);
		});
		return toReturn;
	}

	/**
	 * Gets the level names as an array list.
	 *
	 * @return the level names
	 */
	private ArrayList<String> getLevelNames() {
		File userFile = new File("levels.txt");
		Scanner in = null;
		try {
			in = new Scanner(userFile);
		} catch (FileNotFoundException e) {
			System.out.println("User file not found");
		}
		ArrayList<String> levels = new ArrayList<String>();
		while (in.hasNextLine()) {
			levels.add(in.nextLine());
		}
		in.close();
		return levels;
	}
}