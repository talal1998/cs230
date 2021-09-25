/**
 * The Class DumbAI.
 */
public class DumbAI extends Enemy {

	/**
	 * Instantiates a new dumb AI.
	 *
	 * @param xCoord the x coord.
	 * @param yCoord the y coord.
	 * @param board1 the board 1.
	 */
	public DumbAI(int xCoord, int yCoord, Board board1) {
		super(xCoord, yCoord, board1);
	}

	/**
	 * Instantiates a new dumb AI.
	 *
	 * @param settings the settings
	 * @param board the board
	 */
	public DumbAI(String[] settings, Board board) {
		super(Integer.parseInt(settings[1]), Integer.parseInt(settings[2]), board);
	}

	/**
	 * Moves the dumb AI to a new cell based on the player position.
	 *
	 * @param player1 the player 1
	 */
	public void doNextMove(Player player1) {
		int[] playerCoords = getPlayerPosition(player1);
		int xDifference = playerCoords[0] - this.getXCoord();
		int yDifference = playerCoords[1] - this.getYCoord();
		System.out.println(xDifference + " " + yDifference);
		if (xDifference == 0 && yDifference == 0) {
			return;
		} else if (Math.abs(xDifference) > Math.abs(yDifference)) {
			if (xDifference > 0) {
				if (checkValidMove(getXCoord() + 1, getYCoord())) {
					this.setCurrentPos(getXCoord() + 1, getYCoord());
				}
			} else {
				if (checkValidMove(getXCoord() - 1, getYCoord())) {
					this.setCurrentPos(getXCoord() - 1, getYCoord());
				}
			}
		} else {
			if (yDifference > 0) {
				if (checkValidMove(getXCoord(), getYCoord() + 1)) {
					this.setCurrentPos(getXCoord(), getYCoord() + 1);
				}
			} else {
				if (checkValidMove(getXCoord(), getYCoord() - 1)) {
					this.setCurrentPos(getXCoord(), getYCoord() - 1);
				}
			}
		}
	}


	/**
	 * COnverts the dumb AI x and y position to a string.
	 *
	 * @return the string
	 */
	public String toFile() {
		String toReturn = "dumb,";
		toReturn += xCoord + "," + yCoord;
		toReturn += "\n";
		return toReturn;
	}
}