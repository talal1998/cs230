import javafx.scene.image.Image;

/**
 * The Class Goal.
 * 		create the goal cell.
 */
public class Goal extends Cell {

	/**
 * Instantiates a new goal.
 *
 * @param xPos the x pos
 * @param yPos the y pos
 */
public Goal(int xPos, int yPos) {
		super(xPos, yPos);
		this.image = new Image("endGoal.png");
		this.isWalkable = true;
	}

	/**
	 * Gets the checks if is walkable.
	 *
	 * @param entity the entity
	 * @return the checks if is walkable
	 */
	public Boolean getIsWalkable(Entity entity) {
		if (entity.getEntityType().equals("Player")) {
			Player player = (Player) entity;
			player.endGame();
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Name to ascii.
	 *
	 * @return the string
	 */
	public String nameToAscii() {
		return "G";
	}
}
