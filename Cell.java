import javafx.scene.image.Image;

public abstract class Cell {

	/**
	 * this is an abstract class that is the parent of all tiles in the game
	 *  The x pos is the position of the x-axis of the cell.
	 *  The y pos is the position of the y-axis of the cell.
	 *  The image is the image of the cell.
	 *  This is walkable is true if you can walk in the cell.
	 *  linkedItem is the item on the cell.
	 *   */
	protected int xPos;
	protected int yPos;
	protected Image image;
	protected Boolean isWalkable;
	protected Collectible linkedItem = null;

	/**
	 * Instantiates a new cell.
	 *
	 * @param xPos the x pos
	 * @param yPos the y pos
	 */
	public Cell(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}

	/**
	 * Gets the image.
	 *
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * Gets the checks if is walkable.
	 *
	 * @param entity the entity
	 * @return the checks if is walkable
	 */
	public Boolean getIsWalkable(Entity entity) {
		return isWalkable;
	}

	/**
	 * Check if the player interacts with the linked Item.
	 *
	 * @param player the player
	 */
	public void interact(Player player) {
		if (linkedItem != null) {
			linkedItem.interact(player);
		}
	}
	/**
	 * Returns a string that indicates what type of Cell this is
	 * @return A string that will represent this Cell in a map
	 */
	public abstract String nameToAscii();
	/**
	 * Converts all current settings of this cell to an ASCII form, to be written to file.
	 * @return The current settings of this cell. Or a blank string if it has none required
	 * to recreate it.
	 */
	public String settingsToString() {
		return "";
	}
}
