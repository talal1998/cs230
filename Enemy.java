public abstract class Enemy extends Entity {

	/**
	 * This creates a enemy class from entity
	 *  The x coord
	 *  	the x cordinates of the enemy 
	 *  The y coord
	 * 		 the x cordinates of the enemy
	 *  */
	protected int xCoord, yCoord;
	protected String entityType = "Enemy";
	protected Board board;

	/**
	 * Instantiates a new enemy.
	 *
	 * @param xCoord the x coord
	 * @param yCoord the y coord
	 * @param board the board
	 */
	public Enemy(int xCoord, int yCoord, Board board) {
		setXCoord(xCoord);
		setYCoord(yCoord);
		this.board = board;
	}

	/**
	 * Gets the entity type.
	 *
	 * @return the entity type
	 */
	public String getEntityType() {
		return entityType;
	}

	/**
	 * Gets the x coord.
	 *
	 * @return the x coord
	 */
	public int getXCoord() {
		return xCoord;
	}

	/**
	 * Sets the x coord of the enemy.
	 *
	 * @param xCoord the new x coord
	 */
	public void setXCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	/**
	 * Gets the y coord of the enemy.
	 *
	 * @return the y coord
	 */
	public int getYCoord() {
		return yCoord;
	}

	/**
	 * Sets the y coord of the enemy.
	 *
	 * @param yCoord the new y coord
	 */
	public void setYCoord(int yCoord) {
		this.yCoord = yCoord;
	}

	/**
	 * Moves the enemy to the new x and y position.
	 *
	 * @param xPos the x pos
	 * @param yPos the y pos
	 */
	protected void moveTo(int xPos, int yPos) {
		setXCoord(xPos);
		setYCoord(yPos);
	}

	/**
	 * Gets the player x and y position.
	 *
	 * @param player1 the player 1
	 * @return the player position
	 */
	protected int[] getPlayerPosition(Player player1) {
		int[] playerPos = new int[] { player1.getXPos(), player1.getYPos() };
		return playerPos;
	}

	/**
	 * Checks if player  hit.
	 *
	 * @param player1 the player 1
	 * @return true, if is player hit
	 */
	public boolean isPlayerHit(Player player1) {
		if (xCoord == player1.getXPos() && yCoord == player1.getYPos()) {
			System.out.println(xCoord + " " + yCoord);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Gets the current position of the enemy.
	 *
	 * @return the current pos
	 */
	public int[] getCurrentPos() {
		int[] currentPos = new int[2];
		currentPos[0] = getXCoord();
		currentPos[1] = getYCoord();
		return currentPos;
	}

	/**
	 * Converts the current pos values to string.
	 *
	 * @return the current pos values
	 */
	public String getCurrentPosValues() {
		int[] currentPos = getCurrentPos();
		String posValues = ("X: " + currentPos[0] + " Y: " + currentPos[1]);
		return posValues;
	}

	/**
	 * Sets the current position of the enemy.
	 *
	 * @param newXCoord the new X coord
	 * @param newYCoord the new Y coord
	 */
	public void setCurrentPos(int newXCoord, int newYCoord) {
		setXCoord(newXCoord);
		setYCoord(newYCoord);
	}

	/**
	 * Check if the enemy movement is a valid move.
	 *
	 * @param xCoord the x coord
	 * @param yCoord the y coord
	 * @return true, if successful
	 */
	public boolean checkValidMove(int xCoord, int yCoord) {
		return this.board.getCell(xCoord, yCoord).getIsWalkable(this);
	}

	public abstract void doNextMove(Player player);
}