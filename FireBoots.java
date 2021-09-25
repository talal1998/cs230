import javafx.scene.image.Image;

public class FireBoots extends Collectible {

	/**
	 * Instantiates a new fire boots.
	 *
	 * @param xPos the x pos
	 * @param yPos the y pos
	 */
	public FireBoots(int xPos, int yPos) {
		super(xPos, yPos);
		this.image = new Image("boots.png");
	}

	/**
	 * Instantiates a new fire boots.
	 *
	 * @param settings the settings
	 */
	public FireBoots(String[] settings) {
		super(Integer.parseInt(settings[1]), Integer.parseInt(settings[2]));
		setStomped(Boolean.parseBoolean(settings[3]));
		if (isStomped()) {
			this.image = new Image("floor.png");
		} else {
			this.image = new Image("boots.png");
		}
	}

	/**
	 * Method that return if player stomps on the cell.
	 * 
	 * @ @return return true if Player stomp on the fire boots cell, false otherwise
	 */
	public Boolean getIsWalkable(Entity entity) {
		if (entity.getEntityType().equals("Player")
				|| (entity.getEntityType().equals("Enemy") && isStomped() == true)) {
			if (!isStomped()) {
				Player player = (Player) entity;
				player.gotFireBoots();
				this.image = new Image("file:floor.png");
				setStomped(true);
			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * fire boots Name to ascii.
	 *
	 * @return the string
	 */
	public String nameToAscii() {
		return "B";
	}

	/**
	 * Settings to string.
	 *
	 * @return the string
	 */
	public String settingsToString() {
		return (String.valueOf(Stomped));
	}
}
