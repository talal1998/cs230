import javafx.scene.image.Image;

// TODO: TEST
public class DeathHole extends Cell {
	
	/**
	 * Constructor to create a death hole cell.
	 * 
	 * @param xPos
	 * @param yPos
	 */
	public DeathHole(int xPos, int yPos) {
		super(xPos, yPos);
		this.image = new Image("file:death.png");
		this.isWalkable = true;
	}
	
	/**
	 * Method to check whether the player tries to walk through the 
	 * DeathHole cell. If so, the player dies and the game is over.
	 * Enemies can walk through it.
	 * 
	 * 
	 * @param entity
	 * @return
	 */
	public Boolean isDeathHoleWalked(Entity entity) {
		if (entity.getEntityType().equals("Player")) {
			Player player = (Player) entity;
			player.killPlayer();
		return true;
	} else {
		return false;
	}
}
	
	public String nameToAscii() {
		return "!";
    }
}

