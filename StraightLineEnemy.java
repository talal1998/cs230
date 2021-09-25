import java.util.Scanner;

public class StraightLineEnemy extends Enemy {

    private int currentDirection; // This is an int as 1 = right, 2 being down, -1 = left and -2 = up

    /**
     * Create Enemy that is going only in the straight line
     *
     * @param xCoord - get position in row X on the board
     * @param yCoord - get position in row Y on the board
     * @param currentDirection - get direction in which enemy is moving
     * @param board1 - get board
     */
    public StraightLineEnemy(int xCoord, int yCoord, int currentDirection, Board board1) {
        super(xCoord, yCoord, board1);
        setCurrentDirection(currentDirection);
    }

    /**
     * Create Enemy that is going only in the straight line
     *
     * @param board1 - get board
     * @param settings - get position in row X, position in row Y on the board
     * @param board - get board
     */
    public StraightLineEnemy(String[] settings, Board board) {
        super(Integer.parseInt(settings[1]), Integer.parseInt(settings[2]), board);
        this.currentDirection = Integer.parseInt(settings[3]);
    }

    /**
     * Return direction in which enemy is moving
     *
     * @return direction in which enemy is moving
     */
    public int getCurrentDirection() {
        return currentDirection;
    }

    /**
     * Setdirection in which enemy is moving
     *
     * @param currentDirection direction in which enemy is moving
     */
    public void setCurrentDirection(int currentDirection) {
        this.currentDirection = currentDirection;
    }

    /**
     * Change irection in which enemy is moving to the opposite of current
     */
    private void turnAround() {
        this.setCurrentDirection(-getCurrentDirection());
    }

    /**
     * Move the enemy in direction that it was set up, checks if the next move is valid
     * if true do the next move, if not change direction
     *
     * @param player - entity of the player
     */
    public void doNextMove(Player player) {
        int newX = xCoord;
        int newY = yCoord;
        switch (currentDirection) {
            case 1:
                newX = xCoord + 1;
                break;
            case 2:
                newY = yCoord + 1;
                break;
            case -1:
                newX = xCoord - 1;
                break;
            case -2:
                newY = yCoord - 1;
                break;
        }
        if (checkValidMove(newX, newY)) {
            // System.out.println(xCoord + " " + yCoord);
            // System.out.println(newX + " " + newY);
            moveTo(newX, newY);
            // System.out.println(xCoord + " " + yCoord);

        } else {
            turnAround();
            doNextMove(player);
        }
    }
    

    /**
     * Override File where String of the position and direction of the enemy is stored
     * @return String of the position and direction of the enemy
     */
    @Override
    public String toFile() {
        String toReturn = "straight,";
        toReturn += xCoord + "," + yCoord + "," + currentDirection;
        toReturn += "\n";
        return toReturn;
    }

}
