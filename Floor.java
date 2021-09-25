import javafx.scene.image.Image;

/**
 * The Class Floor.
 */
public class Floor extends Cell {

	/**
	 * Instantiates a new floor.
	 *
	 * @param xPos the x pos
	 * @param yPos the y pos
	 */
	public Floor(int xPos, int yPos) {
		super(xPos, yPos);
		this.image = new Image("floor.png");
		this.isWalkable = true;
	}

	/**
	 * The floor Name to ascii.
	 *
	 * @return the string
	 */
	public String nameToAscii() {
		return "F";
	}
}
