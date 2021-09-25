import javafx.scene.image.Image;
import java.util.*;

/**
 * Contains position, and inventory of player.
 */
public class Player extends Entity {
	private int xPos;
	private int yPos;
	private int tokens = 0;
	private Boolean completedLevel = false;
	private Boolean dead = false;
	private Map<String, Integer> colourKeys = new HashMap<String, Integer>();
	private Boolean fireBoots = false;
	private Boolean flippers = false;
	private Image image = new Image("file:player.png");

	/**
	 * Initiates the player with a given x and y position
	 */
	public Player(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.entityType = "Player";

	}

	/**
	 * Creates a Player with coordinates and inventory settings contained in an
	 * Array of Strings
	 * 
	 * @param settings The list of parameters to re-initialise the player.
	 */
	public Player(String settings[]) {
		this.xPos = Integer.parseInt(settings[1]);
		this.yPos = Integer.parseInt(settings[2]);
		this.tokens = Integer.parseInt(settings[3]);
		this.entityType = "Player";
		this.fireBoots = Boolean.parseBoolean(settings[4]);
		this.flippers = Boolean.parseBoolean(settings[5]);
		for (int i = 6; i < settings.length; i += 2) {
			String colourOfKey = settings[i];
			int noOfKeys = Integer.parseInt(settings[i + 1]);
			setKey(colourOfKey, noOfKeys);
		}
	}

	/**
	 * Moves the player to a given x and y coordinate
	 * 
	 * @param newX The new X coordinate
	 * @param newY The new Y coordiante
	 */
	public void moveTo(int newX, int newY) {
		this.xPos = newX;
		this.yPos = newY;
	}

	/**
	 * Gets current X position of the player
	 * 
	 * @return The X coordinate
	 */
	public int getXPos() {
		return xPos;
	}

	/**
	 * Gets current Y position of the player
	 * 
	 * @return The y coordinate
	 */
	public int getYPos() {
		return yPos;
	}

	/**
	 * Returns the image of the player
	 * 
	 * @return The image of the player
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * Gets the number of tokens
	 * 
	 * @return The number of tokens
	 */
	public int getTokens() {
		return tokens;
	}

	/**
	 * Sets the number of tokens
	 * 
	 * @param newTokens The number of tokens the player will be at after this
	 */
	public void setTokens(int newTokens) {
		this.tokens = newTokens;
	}

	/**
	 * Adds the token.
	 */
	public void addToken() {
		tokens = tokens + 1;
	}

	/**
	 * Sets the number of tokens to 1 less than it is currently
	 */
	public void useToken() {
		tokens = tokens - 1;
	}

	/**
	 * Adds one key of a given colour. If the key doesn't exist within the table, it
	 * will create an entry for it with an initial value of 1
	 * 
	 * @param String The colour of the key
	 */
	public void addKey(String colour) {
		if (colourKeys.containsKey(colour)) {
			int prevValue = colourKeys.get(colour);
			colourKeys.put(colour, prevValue + 1);
		} else {
			colourKeys.put(colour, 1);
		}
	}

	/**
	 * Sets a key value to a certain value, should only be used for inital setup of
	 * a level
	 * 
	 * @param String The Key colour
	 * @param int    How many keys the player has
	 */
	public void setKey(String colour, int value) {
		colourKeys.put(colour, value);
	}

	/**
	 * Indicates whether the player has a key of a given colour.
	 * 
	 * @param String The colour that should be checked for.
	 * @return True of false as to whether the player has at least one key of the
	 *         given colour
	 */
	public Boolean hasKey(String colour) {
		if (colourKeys.containsKey(colour)) {
			if (colourKeys.get(colour) > 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Consumes one key of a given colour.
	 * 
	 * @param String The colour of the key to use up.
	 */
	public void useKey(String colour) {
		int startValue = colourKeys.get(colour);
		colourKeys.put(colour, startValue - 1);
	}

	/**
	 * Removes all keys from the inventory. Should only be used to reset at the end
	 * of a level or between levels
	 */
	public void resetKeys() {
		colourKeys.clear();
	}

	/**
	 * Marks the level as completed
	 */
	public void endGame() {
		completedLevel = true;
	}

	/**
	 * Marks the player as dead
	 */
	public void killPlayer() {
		dead = true;
	}

	/**
	 * Returns whether the level has been completed
	 * 
	 * @return a Boolean indicating if the player has sucessfully finished the
	 *         level.
	 */
	public Boolean finishedLevel() {
		return completedLevel;
	}

	/**
	 * Returns whether the player has been killed
	 * 
	 * @return a Boolean indicating whether the player is dead
	 */
	public Boolean isDead() {
		return dead;
	}

	/**
	 * Returns whether the player currently has fire boots
	 * 
	 * @return A boolean indicating if the player has fire boots
	 */
	public Boolean hasFireBoots() {
		return fireBoots;
	}

	/**
	 * Sets the player as owning fire boots
	 */
	public void gotFireBoots() {
		fireBoots = true;
	}

	/**
	 * Returns whether the player currently has flippers
	 * 
	 * @return A boolean indicating if the player has flippers
	 */
	public Boolean hasFlippers() {
		return flippers;
	}

	/**
	 * Sets the player as having flippers
	 */
	public void gotFlippers() {
		flippers = true;
	}

	/**
	 * Outputs the type as player and the settings of the player to a String which
	 * can then be saved in a file and the player can be recreated later from the
	 * saved information
	 * 
	 * @return the string to be written to file
	 */
	public String toFile() {
		String toReturn = "player," + xPos + "," + yPos + "," + tokens + "," + fireBoots + "," + flippers;
		String[] keyNames = colourKeys.keySet().toArray(new String[0]);
		for (String name : keyNames) {
			toReturn += "," + name + "," + colourKeys.get(name);
		}
		toReturn += "\n";
		return toReturn;
	}
}
