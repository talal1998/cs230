import javafx.scene.image.Image;

/**
 * Represents a colour key that can be picked up and used to open a colour door
 */
public class Key extends Collectible {

	private String colour;

	/**
	 * Constructs a key with a given x and y position and a string that represents
	 * the colour of the key
	 * 
	 * @param xPos   this represents the x position of the key in the map
	 * @param yPos   this represents the y position of the key in the map
	 * @param colour this represents the colour of the key and should match the
	 *               colour of the door it needs to open
	 */
	public Key(int xPos, int yPos, String colour) {
		super(xPos, yPos);
		this.image = setImage(colour);
		setColour(colour);
	}

	/**
	 * Constructs a key given a list of settings. This is used when creating a class
	 * from a save file
	 * 
	 * @param settings The list of parameters with which to build the class. These
	 *                 should be the state the key was in when it was last saved.
	 */
	public Key(String[] settings) {
		super(Integer.parseInt(settings[1]), Integer.parseInt(settings[2]));
		setStomped(Boolean.parseBoolean(settings[3]));
		setColour(settings[4]);
		if (isStomped()) {
			this.image = new Image("floor.png");
		} else {
			this.image = setImage(colour);
		}
	}

	/**
	 * Used to set the colour of the key
	 * 
	 * @param colour the colour that the key should be set to
	 */
	public void setColour(String colour) {
		this.colour = colour;
	}

	/**
	 * Returns the current colour of the key
	 * 
	 * @return The name of the colour of the key in a String format.
	 */
	public String getColour() {
		return colour;
	}

	/**
	 * Sets the image of the key based on the colour of the key
	 * 
	 * @param colour The colour of the key passed through as a String
	 */
	public Image setImage(String colourKey) {
		System.out.println(colourKey);
		String imageString;
		if (colourKey.equals("blue")) {
				imageString = "file:blueKey.png";
		} else if (colourKey.equals("red")) {
				imageString = "file:redkey.png";
		} else if (colourKey.equals("green")) {
				imageString = "file:greenkey.png";
		} else {
				imageString = "file:yellowkey.png";
		}
		System.out.println(imageString);
		return new Image(imageString);
	}


	/**
	 * Tells an entity if it is possible for it to walk on to this key. If it is a
	 * player, the player will pick up the key and be able to walk on to the Cell.
	 * If it is an enemy it will not be able to walk on to the cell unless the item
	 * has already been picked up.
	 * 
	 * @param entity The entity that has tried to walk on to this tile.
	 * @return A boolean value that indicates whether the entity can walk on to the
	 *         Cell
	 */
	public Boolean getIsWalkable(Entity entity) {
		if (entity.getEntityType().equals("Player")
				|| (entity.getEntityType().equals("Enemy") && isStomped() == true)) {
			if (isStomped() == false) {
				Player player = (Player) entity;
				player.addKey(getColour());
				// System.out.println("picked up " + getColour() +"key");
				this.image = new Image("floor.png");
				setStomped(true);
			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns a string that indicates what type of Cell this is
	 * 
	 * @return A string that will represent this Cell in a map
	 */
	public String nameToAscii() {
		return "K";
	}

	/**
	 * Converts all current settings of this cell to an ASCII form, to be written to
	 * file.
	 * 
	 * @return The current settings of this cell. Or a blank string if it has none
	 *         required to recreate it.
	 */
	public String settingsToString() {
		return (String.valueOf(Stomped) + "," + colour);
	}
}