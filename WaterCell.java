import javafx.scene.image.Image;

public class WaterCell extends Cell {
//Requires flippers to move on.
	private String ascii;
	/**
	 * Create water cell in Position X and Y
	 *
	 * @param xPos - get Position in row X 
	 * @param yPos - get Position in column Y
	 */
	public WaterCell(int xPos, int yPos) {
		super(xPos, yPos);
		this.isWalkable = false;
		this.ascii = ascii;
		this.image = new Image("file:water.png");
	}

	/**
	 * Create water cell in Position X and Y with and specify if cell is walkable
	 *
	 * @param xPos - get Position in row X 
	 * @param yPos - get Position in column Y
	 * @param isWalkable - get true or false if we can walk on the cell
	 */
	public WaterCell(int xPos, int yPos, Boolean isWalkable) {
		super(xPos, yPos);
		this.isWalkable = isWalkable;
		this.image = new Image("file:water.png");
	}
	

	/**
	 * getIsWalkable check if player wants to go on this cell and return true or false respectively
	 *
	 * @param Entity - get entity of something that wants to visit that cell 
	 * @return true if player want to step on the cell, otherwise false 
	 */
	public Boolean getIsWalkable(Entity entity) {
		//Check if player wants to go on this cell
		if (entity.getEntityType().equals("Player")) {
			Player player = (Player) entity;
			//Check if player has flippers
			if (!player.hasFlippers()) {
				player.killPlayer();
			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * method nameToAscii return name of cell in Ascii
	 * @return String Ascii name
	 */
	public String nameToAscii() {
		return "A";
	}
	
}
