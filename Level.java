
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.scene.layout.Pane;
import javafx.scene.input.KeyEvent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

/**
 * Draws and runs one specific level.
 */
public class Level {

	/** The Constant WINDOW_WIDTH. */
	private static final int WINDOW_WIDTH = 1000;

	/** The Constant WINDOW_HEIGHT. */
	private static final int WINDOW_HEIGHT = 1000;

	/** The Constant SCREEN_WIDTH_IN_TILES. */
	private static final int SCREEN_WIDTH_IN_TILES = 9;

	/** The Constant SCREEN_HEIGHT_IN_TILES. */
	private static final int SCREEN_HEIGHT_IN_TILES = 9;

	/** The tile width. */
	private int tileWidth = WINDOW_WIDTH / SCREEN_WIDTH_IN_TILES;
	/** The tile height. */
	private int tileHeight = WINDOW_HEIGHT / SCREEN_HEIGHT_IN_TILES;

	/** The side tiles. */
	private int sideTiles = Math.floorDiv(SCREEN_WIDTH_IN_TILES, 2);

	/** The vertical tiles. */
	private int verticalTiles = Math.floorDiv(SCREEN_HEIGHT_IN_TILES, 2);

	private Instant start;
	private ArrayList<Enemy> enemies;
	private Board board;
	private String loadName;
	private String saveName;
	private Canvas canvas;
	private Stage gameStage = new Stage();
	private Player player;
	private Boolean pauseGame = false;
	private LeaderBoard leaderboard = new LeaderBoard();
	private Boolean complete = false;

	/**
	 * Creates a level with a board, player and enemies.
	 * The timer for the level is also started at this point.
	 * @param loadName The String that indicates where the level should be loaded from
	 * @param saveName The String that indicates where the level should be saved to
	 */
	public Level(String loadName, String saveName) {
		start = Instant.now();
		this.saveName = saveName;
		this.loadName = loadName;
		board = new Board(loadName);
		ReadFile read = new ReadFile();
		board.setGrid(read.createBoard(read.fileReader(loadName)));
		player = read.getPlayer();
		enemies = read.createEnemies(board, player);
		//System.out.println(enemies.size());
	}

	/**
	 * Builds the GUI.
	 *
	 * @return the pane
	 */
	private Pane buildGUI() {
		// Create top-level panel that will hold all GUI
		BorderPane root = new BorderPane();

		// Create canvas
		canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
		root.setCenter(canvas);
		return root;

	}

	/**
	 * Draws a window, then moves the player and enemies, then draws the map with
	 * cells, items, the player and enemies. (Doesn't do all of this yet)
	 */
	public void drawGame() {
		Pane root;
		Scene gameBoard;
		root = buildGUI();
		gameBoard = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		gameBoard.addEventFilter(KeyEvent.KEY_RELEASED, event -> getInput(event));
		if (!pauseGame) {
			drawBoard();
			drawPlayer();
			gameStage.setScene(gameBoard);
			gameStage.show();
			if (tryToKill()) {
			}
			if (player.finishedLevel()) {
				levelComplete();
			}
			for (Enemy enemy : enemies) {
				enemy.doNextMove(player);
				drawEnemy(enemy);
				if (tryToKill()) {
				}
				gameStage.setScene(gameBoard);
				gameStage.show();
			}
		} else {
			pauseGame();
		}
		pauseGame = false;
	}
	/**
	 * Draws the enemy on to the existing canvas in the position that it should be.
	 * @param enemy The Enemy class that has to be drawn on to the map.
	 */
	private void drawEnemy(Enemy enemy) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		int firstXTile = player.getXPos() - sideTiles;
		int firstYTile = player.getYPos() - verticalTiles;
		int enemyX = (enemy.getXCoord() - firstXTile) * tileWidth;
		int enemyY = (enemy.getYCoord() - firstYTile) * tileHeight;
		Image enemyImage = new Image("file:Enemy.jpg");
		gc.drawImage(enemyImage, enemyX, enemyY, tileWidth, tileHeight);
	}

	/**
	 * Draws the tiles that are in the current viewport on to the canvas.
	 * Gets the image from the board based on the x and y coordinates and draws it.
	 */
	private void drawBoard() {
		// create board using the level file data.

		// Get the Graphic Context of the canvas. This is what we draw on.
		GraphicsContext gc = canvas.getGraphicsContext2D();

		// Clear canvas
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		int firstXTile = player.getXPos() - sideTiles;
		int firstYTile = player.getYPos() - verticalTiles;
		for (int x = 0; x < SCREEN_WIDTH_IN_TILES; x++) {
			for (int y = 0; y < SCREEN_HEIGHT_IN_TILES; y++) {
				gc.drawImage(board.getImage((firstXTile + x), (firstYTile + y)), x * tileWidth, y * tileHeight,
						tileWidth, tileHeight);
			}
		}
	}

	/**
	 * Draws the player to the canvas at the tile in the centre
	 */
	private void drawPlayer() {
		GraphicsContext gc = canvas.getGraphicsContext2D();

		int playerX = sideTiles * tileWidth;
		int playerY = verticalTiles * tileHeight;
		Image playerImage = player.getImage();
		gc.drawImage(playerImage, playerX, playerY, tileWidth, tileHeight);
	}
	/**
	 * Each enemy kills the player if they are occupying the same cell. 
	 * @return A boolean that indicates if any of the enemies managed to kill the enemy
	 */
	private Boolean tryToKill() {
		for (Enemy enemy : enemies) {
			if (enemy.isPlayerHit(player)) {
				player.killPlayer();
			}
		}
		if (player.isDead()) {
			gameOver();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Gets a diection of travel from an arrow key press.
	 *
	 * @param event When a key is pressed
	 * @return The direction ranging from 0 to 4. Where 0 indicates an invalid
	 *         input. 1 is up, 2 is right, 3 is down, 4 is left
	 */
	public void getInput(KeyEvent event) {
		int xChange = 0;
		int yChange = 0;
		switch (event.getCode()) {
		case UP:
			// Up key was pressed so return direction 1
			yChange = -1;
			break;
		case RIGHT:
			// Right key was pressed so return direction 2
			xChange = 1;
			break;
		case DOWN:
			// Down key was pressed so return direction 3
			yChange = 1;
			break;
		case LEFT:
			// Left key was pressed so return direction 4
			xChange = -1;
			break;
		case ESCAPE:
			pauseGame = true;
			break;
		default:
			// do nothing
			break;
		}
		event.consume();
		doMove(xChange, yChange);
		drawGame();
	}

	/**
	 * Finds the new X and Y coordinates, if these are valid, it updates the players
	 * position.
	 *
	 * @param xChange How much the x position is changing by
	 * @param yChange How much the y position is changing by
	 */
	private void doMove(int xChange, int yChange) {
		int newX = player.getXPos() + xChange;
		int newY = player.getYPos() + yChange;
		if (validPosition(newX, newY)) {
			if (board.getCell(newX, newY) instanceof Teleporter) {// This if
				// statement, continues movement if moveing on to a teleporter.
				newX = player.getXPos() + xChange;
				newY = player.getYPos() + yChange;
				validPosition(newX, newY);
			}
			player.moveTo(newX, newY);
			board.getCell(newX, newY).interact(player);
		}
	}
	/**
	 * Writes the current state of the game including inventory and the settings of 
	 * the various tiles to a file. It goes size of map, settings of tiles, MAP, 
	 * grid of ASCII, linked teleporters, the player, and then the enemies.
	 * @throws IOException If the file is invalid then the IOException is thrown.
	 */
	public void toFile() throws IOException {
		try {
			FileWriter saveFile = new FileWriter(saveName);
			saveFile.write(board.toFile());
			saveFile.write(player.toFile());

			for (Enemy enemy : enemies) {
				saveFile.write(enemy.toFile());
			}
			saveFile.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Checks if the new position is valid. Currently this just checks that both
	 * coordiantes are not negative. Will need expanding
	 * 
	 * @param newX The x coordinate to validate
	 * @param newY The y coordinate to validate
	 * @return True if the position is valid, or false if not.
	 */
	private Boolean validPosition(int newX, int newY) {
		Cell newCell = board.getCell(newX, newY);
		if (newCell.getIsWalkable(player)) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Displays the pause game menu with options to resume, save to file, or quit.
	 * This can be pulled up using the escape button
	 */
	private void pauseGame() {
		Stage endStage = new Stage();
		BorderPane root = new BorderPane();
		canvas = new Canvas(300, 100);
		root.setCenter(canvas);

		VBox toolbar = new VBox();
		toolbar.setAlignment(Pos.CENTER);
		root.setCenter(toolbar);

		Text textBox = new Text("GAME PAUSED");
		toolbar.getChildren().add(textBox);

		Button resumeButton = new Button("Resume");
		toolbar.getChildren().add(resumeButton);

		Button saveGame = new Button("Save");
		toolbar.getChildren().add(saveGame);

		Button quitGame = new Button("Quit");
		toolbar.getChildren().add(quitGame);

		resumeButton.setOnAction(e -> {
			endStage.close();
		});

		saveGame.setOnAction(e -> {
			try {
				toFile();
				System.out.println("Sucessful");
			} catch (Exception f) {
				System.out.println("Level.java problem");
			}
		});

		quitGame.setOnAction(e -> {
			endStage.close();
			gameStage.close();
		});
		Scene buttons = new Scene(root, 300, 100);
		endStage.setScene(buttons);
		endStage.show();
	}

	/**
	 * Comes up with a text box saying the level complete and with a button saying
	 * okay which closes both the text box and the game.
	 */
	private void levelComplete() {
	    Instant finish = Instant.now();
	    long timeElapsed = Duration.between(start, finish).toMinutes();  //in minutes
	    Score score = new Score("username", timeElapsed);
	    this.leaderboard.addScore(score);
		Stage endStage = new Stage();
		BorderPane root = new BorderPane();
		canvas = new Canvas(300, 100);
		root.setCenter(canvas);

		VBox toolbar = new VBox();
		toolbar.setAlignment(Pos.CENTER);
		root.setCenter(toolbar);

		Text textBox = new Text("Level Complete");
		toolbar.getChildren().add(textBox);
		Button okayButton = new Button("Okay :)");
		toolbar.getChildren().add(okayButton);
		okayButton.setOnAction(e -> {
			gameStage.close();
			endStage.close();
		});
		Scene buttons = new Scene(root, 300, 100);
		endStage.setScene(buttons);
		endStage.show();

	}

	/**
	 * Comes up with a text box saying game over and with a button saying okay which
	 * closes both the text box and the game.
	 */
	private void gameOver() {
		Stage endStage = new Stage();
		BorderPane root = new BorderPane();
		canvas = new Canvas(300, 100);
		root.setCenter(canvas);

		VBox toolbar = new VBox();
		toolbar.setAlignment(Pos.CENTER);
		root.setCenter(toolbar);

		Text textBox = new Text("Game Over");
		toolbar.getChildren().add(textBox);
		Button okayButton = new Button("Okay :(");
		toolbar.getChildren().add(okayButton);
		okayButton.setOnAction(e -> {
			gameStage.close();
			endStage.close();
		});
		Scene buttons = new Scene(root, 300, 100);
		endStage.setScene(buttons);
		endStage.show();

	}
}