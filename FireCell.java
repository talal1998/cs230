import javafx.scene.image.Image;

public class FireCell extends Cell {

	/**
	 * FireCell creates a firecell that requires fireboots to move on
	 */
	/**
	 * Instantiates a new fire cell.
	 *
	 * @param xPos the x pos
	 * @param yPos the y pos
	 */
	public FireCell(int xPos, int yPos) {
		super(xPos, yPos);
		this.image = new Image("file:fire.jpg");
		this.isWalkable = false;
	}

	/**
	 * Instantiates a new fire cell. *
	 * 
	 * @param xPos
	 * @param yPos
	 * @param isWalkable
	 */
	public FireCell(int xPos, int yPos, Boolean isWalkable) {
		super(xPos, yPos);
		this.image = new Image("file:fire.jpg");
		this.isWalkable = isWalkable;
	}

	/**
	 * Gets the checks if the fire cell is walkable.
	 *
	 * @param entity the entity
	 * @return the checks if is walkable
	 */
	public Boolean getIsWalkable(Entity entity) {
		if (entity.getEntityType().equals("Player")) {
			Player player = (Player) entity;
			if (!player.hasFireBoots()) {
				player.killPlayer();
			}
			return true;
		} else {
			return false;
		}
	}

	public String nameToAscii() {
		return "H";
	}
	/*
	 * public String settingsToString() { return (isWalkable.toString()); }
	 */
}
