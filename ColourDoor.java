import javafx.scene.image.Image;

public class ColourDoor extends Door {
	
/**the colour door class creates a colour door that 
 * extends from the door class.
 *  The colour
 *  	the colour of the door 
 *  The imageString 
 *  	the imageString used to get the image of the colourDoor */
 
	private String colour;
	private String imageString;

	public ColourDoor(int xPos, int yPos, String colour) {
		super(xPos, yPos);
		this.isWalkable = false;
		this.colour = colour;
		this.image = setImage();
	}
	/**
	 * For load file
	 **/
	public ColourDoor(String[] settings) {
		super(Integer.parseInt(settings[1]), Integer.parseInt(settings[2]));
		this.imageString = settings[3];
		this.image = new Image(imageString);
		this.isWalkable = Boolean.parseBoolean(settings[4]);
		this.colour = settings[5];
	}


	private Image setImage() {
		switch(colour) {
			case "Blue":
				imageString = "file:blueDoor.png";
			case "Red":
				imageString = "file:redDoor.png";
			case "Green":
				imageString = "file:greenDoor.png";
			default:
				imageString = "file:yellowDoor1.png";
		}
		return new Image(imageString);
	}
	// TODO: javadoc


	public Boolean getIsWalkable(Entity entity) {
		if (entity.getEntityType().equals("Player")) {
			Player player = (Player) entity;
			if (player.hasKey(colour) && !isWalkable) {
				player.useKey(colour);
				isWalkable = true;
			}
			return isWalkable;
		} else {
			return false;
		}
	}

	public String nameToAscii() {
		return "C";
	}

	public String settingsToString() {
		
		return (imageString + "," + isWalkable.toString() + "," + colour);
	}
}
