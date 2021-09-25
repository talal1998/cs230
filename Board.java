import javafx.scene.image.Image;

public class Board {
	/**
	 * A Class that contains the grid of tile objects. that is wall water cells etc.
	 * String fileName
	 * 		the name of the board.
	 * String unknownTile 
	 * 		the unkwon tile image.
	 * Cell[][] grid
	 * 		the grid used to store the cells.
	 */
	private String fileName;
	private Image unknownTile = new Image("file:brickWall.png");
	private Cell[][] grid;

	public Board(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Sets the grid.
	 *
	 * @param grid the new grid
	 */
	public void setGrid(Cell[][] grid) {
		this.grid = grid;
	}

	/**
	 * Gets the tile image for a given x and y coordinate
	 * 
	 * @param int x represents the x coordinate of the image in the table
	 * @param int y represents the y coordinate of the image in the table
	 * @return An image of the tile at the given coordinates. If the tile can't be
	 *         found it returns a question mark image
	 */
	public Image getImage(int x, int y) {
		try {
			return grid[y][x].getImage();
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			return (unknownTile);
		}
	}

	/**
	 * Returns the Cell at a given x and y coordinate
	 * 
	 * @param int x represents the x coordinate of the cell in the table
	 * @param int y represents the y coordinate of the cell in the table
	 * @return The cell object for the given coordinates.
	 */
	public Cell getCell(int x, int y) {
		return grid[y][x];
	}

	/**
	 * Converts the grid to a string.
	 *
	 * @return the string of the file.
	 */
	public String toFile() {
		String toReturn = "";
		toReturn += String.valueOf(grid.length) + " " + String.valueOf(grid[0].length) + "\n";
		for (int y = 0; y < grid.length; y++) {
			for (int x = 0; x < grid[y].length; x++) {
				try {
					String toWrite = (x + "," + y + "," + grid[y][x].settingsToString());
					if (grid[y][x].settingsToString().length() > 0) {
						toReturn += grid[y][x].nameToAscii() + ",";
						toReturn += toWrite;
						toReturn += "\n";
					}
				} catch (NullPointerException e) {
					System.out.println("No cell at:" + x + " " + y);
				}
			}
		}
		toReturn += "MAP\n";
		for (int y = 0; y < grid.length; y++) {
			for (int x = 0; x < grid[y].length; x++) {
				toReturn += grid[y][x].nameToAscii();
			}
			toReturn += "\n";
		}
		for (int y = 0; y < grid.length; y++) {
			for (int x = 0; x < grid[y].length; x++) {
				if (grid[y][x] instanceof Teleporter) {
					Teleporter tele1 = (Teleporter) grid[y][x];
					Teleporter tele2 = tele1.getLinkedTeleporter();
					toReturn += "link," + tele1.getX() + "," + tele1.getY() + "," + tele2.getX() + "," + tele2.getY()
							+ "\n";
				}
			}
		}
		return toReturn;
	}

	public Cell[][] getGrid() {
		return this.grid;
	}
}
