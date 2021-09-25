import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.image.Image;

/**
 * The Class ReadFile this reads the file and creates the player, enemy , tiles
 * and collectibles.
 */
public class ReadFile {

	/** The map height. */
	private int MAP_HEIGHT = 0;

	/** The map width. */
	private int MAP_WIDTH = 0;

	/** The items. */
	private Collectible[][] items;

	/** The tiles. */
	private Cell[][] tiles;

	/** The settings. */
	private String[][][] settings = null;

	/** The current file. */
	private String currentFile;

	/** The tele link. */
	private ArrayList<String[]> teleLink = new ArrayList<String[]>();

	/** The start X. */
	private int startX;

	/** The start Y. */
	private int startY;

	/** The player. */
	private Player player = null;

	/** The enemy settings. */
	private ArrayList<String[]> enemySettings = new ArrayList<String[]>();

	/** The enemies. */
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();

	/**
	 * Make default player.
	 *
	 * @throws NullPointerException the null pointer exception
	 */
	private void makeDefaultPlayer() throws NullPointerException {
		try {
			player = new Player(startX, startY);
			// System.out.println(startX);
		} catch (NullPointerException e) {
			System.out.println("No start tile specified in the file. Please use S and add it");
		}
	}

	/**
	 * Creates the player.
	 *
	 * @param settings the settings
	 */
	private void createPlayer(String[] settings) {
		player = new Player(settings);
		// System.out.println(player.getXPos());
	}

	/**
	 * Creates the enemies.
	 *
	 * @param board  the board
	 * @param player the player
	 * @return the array list
	 */
	public ArrayList<Enemy> createEnemies(Board board, Player player) {
		for (String[] enemySetting : enemySettings) {
			switch (enemySetting[0]) {
			case "straight":
				enemies.add(new StraightLineEnemy(enemySetting, board));
				break;
			case "wall":
				enemies.add(new WallEnemy(enemySetting, board));
				break;
			case "dumb":
				enemies.add(new DumbAI(enemySetting, board));
				break;
			case "smart":
				enemies.add(new SmartAI(enemySetting, board, player));
				break;
			}
		}
		return enemies;
	}

	/**
	 * Gets the player.
	 *
	 * @return the player
	 */
	public Player getPlayer() {
		if (player == null) {
			makeDefaultPlayer();
		}
		return this.player;
	}

	/**
	 * Creates the board from the string 2d of the read file.
	 *
	 * @param readFile the read file
	 * @return the cell[][]
	 */
	public Cell[][] createBoard(String[][] readFile) {

		Cell[][] temp = new Cell[MAP_HEIGHT][MAP_WIDTH];
		int row = 0;
		int column = 0;
		for (String[] i : readFile) {
			// Loop through all elements of current row
			column = 0;
			for (String j : i) {
				// System.out.print(readFile[i][j]);

				// System.out.println(temp[i][j]);
				if (!(j.equals(null))) {
					switch (j) {
					case ("W"):
						// # for creating a wall
						Wall wall = new Wall(row, column, j);
						// adds it to the grid.
						temp[row][column] = wall;
						column++;
						break;
					case ("F"):
						Floor floor = new Floor(row, column);
						temp[row][column] = floor;
						column++;
						break;
					case ("S"):
						// S for creating Start position
						startX = column;
						startY = row;
						Start start = new Start(row, column);
						temp[row][column] = start;
						column++;
						break;
					case ("G"):
						Goal goal = new Goal(row, column);
						temp[row][column] = goal;
						column++;
						break;
					case ("H"):
						FireCell fire = new FireCell(row, column);
						temp[row][column] = fire;
						column++;
						break;
					case ("A"):
						WaterCell water = new WaterCell(row, column);
						temp[row][column] = water;
						column++;
						break;
					default:
						try {
							temp[row][column] = specialCase(settings[row][column]);
						} catch (ArrayIndexOutOfBoundsException e) {
							System.out.println("No settings for grid square: " + column + " " + row
									+ "\nPlease correct the map file: " + currentFile + "\nExiting...");
							Thread.currentThread().getStackTrace();
							System.exit(0);
						}
						column++;
						break;
					}
				}
			}
			row++;
		}
		link(temp);
		return temp;
	}

	/**
	 * Gets the setting of the cell.
	 * 
	 * @param settings the settings
	 * @return the cell
	 */
	private Cell specialCase(String[] settings) {
		switch (settings[0]) {
		case ("C"):
			return new ColourDoor(settings);
		case ("D"):
			return new TokenDoor(settings);
		case ("T"):
			return new Teleporter(settings);
		case ("Q"):
			return new Flippers(settings);
		case ("B"):
			return new FireBoots(settings);
		case ("K"):
			return new Key(settings);
		case ("Z"):
			return new Token(settings);
		default:
			return new Floor(0, 0);
		}
	}

	/**
	 * Level reader reads each row and adds it to an arrayList.
	 *
	 * @param string the string
	 * @return the array list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public ArrayList<String> levelReader(String string) throws IOException {

		// verification that it is not empty
		String currentLine = null;
		// arraylist to store the rows.
		ArrayList<String> row = new ArrayList<>();
		currentLine = string;
		// trims the white spaces and delimits the line by ",".
		String[] values = currentLine.trim().split("");

		for (String s : values) {
			if (!(string.isEmpty())) {
				row.add(s);

			}
		}

		return row;

	}

	/**
	 * This links the teleporter to each other.
	 *
	 * @param board the board
	 */
	private void link(Cell[][] board) {
		if (teleLink.size() > 0) {
			for (String[] tele : teleLink) {
				// System.out.println(tele[0]);
				// System.out.println(tele[1]);
				// System.out.println(tele[2]);
				// System.out.println(board[6][3]);
				Teleporter teleporter1 = (Teleporter) board[Integer.parseInt(tele[2])][Integer.parseInt(tele[1])];
				Teleporter teleporter2 = (Teleporter) board[Integer.parseInt(tele[4])][Integer.parseInt(tele[3])];

				teleporter1.linkTeleporter(teleporter2);
				teleporter2.linkTeleporter(teleporter1);
			}
		}
	}

	/**
	 * File reader reads the actual file from a buffer reader and sends each row to
	 * level reader() nethod.
	 *
	 * @param fileName the file name
	 * @return the string[][]
	 */
	public String[][] fileReader(String fileName) {
		currentFile = fileName;
		// 2d array list to store each line of the level
		ArrayList<ArrayList<String>> levelList = new ArrayList<ArrayList<String>>();
		// Reads in the file to a buffer reader
		try (BufferedReader level = new BufferedReader(new FileReader(fileName))) {
			String[] mapSize = level.readLine().split(" ");
			MAP_WIDTH = Integer.parseInt(mapSize[0]);
			MAP_HEIGHT = Integer.parseInt(mapSize[1]);
			String currentLine = "";
			settings = new String[MAP_HEIGHT][MAP_WIDTH][0];
			while (!(currentLine = level.readLine()).equals("MAP")) {
				String[] setting = currentLine.split(",");
				settings[Integer.parseInt(setting[2])][Integer.parseInt(setting[1])] = setting;
			}
			for (int line = 0; line < MAP_HEIGHT; line++) {
				// System.out.println(currentLine);
				currentLine = level.readLine();
				if (currentLine.isEmpty()) {
					continue;
				}
				ArrayList<String> row = levelReader(currentLine);
				levelList.add(row);
			}
			String entityLine;
			while ((entityLine = level.readLine()) != null) {
				String[] entityArray = entityLine.split(",");
				if (entityArray[0].equals("link")) {
					teleLink.add(entityArray);
				} else if (entityArray[0].equals("player")) {
					// System.out.println("PLAYER MADE");
					createPlayer(entityArray);
				} else if (entityArray[0].equals("straight")) {
					enemySettings.add(entityArray);
				} else if (entityArray[0].equals("wall")) {
					enemySettings.add(entityArray);
				} else if (entityArray[0].equals("dumb")) {
					enemySettings.add(entityArray);
				} else if (entityArray[0].equals("smart")) {
					enemySettings.add(entityArray);
				}
			}
		} catch (IOException e) {
			System.out.println("File was not read ");
		}
		String[][] levelArray = new String[levelList.size()][];

		int k = 0;
		for (ArrayList<String> nestedList : levelList) {
			levelArray[k++] = nestedList.toArray(new String[nestedList.size()]);
		}
		return levelArray;

	}

}