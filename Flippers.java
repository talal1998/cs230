import javafx.scene.image.Image;

public class Flippers extends Collectible {
	/**
	 * The flippers are used to walk on the water cell.
	 */

	/**
	 * Instantiates a new flippers.
	 *
	 * @param xPos the x pos
	 * @param yPos the y pos
	 */
	public Flippers(int xPos, int yPos) {
		super(xPos, yPos);
		this.image = new Image("flippers.png");
	}

	/**
	 * Instantiates a new flippers.
	 *
	 * @param settings the settings
	 */
	public Flippers(String[] settings) {
		super(Integer.parseInt(settings[1]), Integer.parseInt(settings[2]));
		setStomped(Boolean.parseBoolean(settings[3]));
		if (isStomped()) {
			this.image = new Image("floor.png");
		} else {
			this.image = new Image("flippers.png");
		}
	}

	/**
	 * Gets the checks if is walkable.
	 *
	 * @param entity the entity
	 * @return the checks if is walkable
	 */
	public Boolean getIsWalkable(Entity entity) {
		if (entity.getEntityType().equals("Player") || (entity.getEntityType().equals("Enemy") && isStomped())) {
			if (!isStomped()) {
				Player player = (Player) entity;
				player.gotFlippers();
				this.image = new Image("floor.png");
				setStomped(true);
			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Flippers Name to ascii.
	 *
	 * @return the string
	 */
	public String nameToAscii() {
		return "Q";
	}

	public String settingsToString() {
		return (String.valueOf(Stomped));
	}
}
