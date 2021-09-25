import javafx.scene.image.Image;

/**
 * The Class Wall.
 */
public class Wall extends Cell {
	
	/**
	 * Constructor to create a new Wall type using the respective image.
	 *
	 */
	private String ascii;
	
	/**
	 * Instantiates a new wall for java .
	 *
	 * @param xPos the x pos
	 * @param yPos the y pos
	 * @param ascii the ascii
	 */
	public Wall(int xPos, int yPos, String ascii) {
		super(xPos, yPos);
		setImage();
		this.isWalkable = false;
	}
	
	/**
	 * Sets the image of the wall.
	 */
	private void setImage() {
		this.image = new Image("file:brickWall.png");
	} 

	/**
	 * Name to ascii.
	 *
	 * @return the string
	 */
	public String nameToAscii() {
		return "W";
	}

}
