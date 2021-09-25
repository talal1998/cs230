public class WallEnemy extends Enemy {
    // TODO: If it spawns in the middle of a space it goes in circles
    private int[][] xyChange = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    private int directionFacing; // 0 for up, 1 for right, 2 for down and 3 for left
    private int clockwise; // 1 for clockwise and -1 for antiClockwise

    /**
     * Create wall following enemy
     *
     * @param x - position in the row X on the board
     * @param y - position in the row Y on the board
     * @param clockwise - specify if enemy is moving clockwise or not
     * @param directionFacing - specify direction that enemy is facing
     * @param board - get board
     */
    public WallEnemy(int x, int y, /* int[] wallPosition, */int clockwise, int directionFacing, Board board) {
        super(x, y, board);
        this.clockwise = clockwise;
        this.directionFacing = directionFacing;
    }

    /**
     * Create wall following enemy
     *
     * @param settings - pass position in the row X , position in the row Y on the board,
     *                 if enemy is moving clockwise or not and direction that enemy is facing
     * @param board - get board
     */
    public WallEnemy(String[] settings, Board board) {
        super(Integer.parseInt(settings[1]), Integer.parseInt(settings[2]), board);
        this.clockwise = Integer.parseInt(settings[3]);
        this.directionFacing = Integer.parseInt(settings[4]);
    }

    /**
     * Return direction that Enemy is currently facing
     * @return direction that Enemy is currently facing
     */
    public int getDirectionFacing() {
        return directionFacing;
    }

    /**
     * Set Direction that Enemy is facing
     * @param directionFacing - direction that enemy is facing
     */
    public void setdirectionFacing(int directionFacing) {
        this.directionFacing = directionFacing;
    }

    /**
     * Finds the next logical move for the enemy, turns towards it, and then moves
     * to it
     *
     * @param board The current state of the Board
     */
    public void doNextMove(Player player) {
        if (canTurnBack(board)) {
            directionFacing = turn(-clockwise);
            doMove();
            // System.out.println("Turned back");
        } else if (canMove(directionFacing)) {
            doMove();
            // System.out.println("Straight ahead");
        } else {
            // System.out.println("Turn");
            directionFacing = turn(clockwise);
            // System.out.println(this);
            doNextMove(player);
        }
    }

    /**
     * Moves the enemy forward in the direction it is currently facing
     */
    private void doMove() {
        xCoord = xCoord + xyChange[directionFacing][0];
        yCoord = yCoord + xyChange[directionFacing][1];
    }

    /**
     * Turns the enemy by 90 degrees in the direction specified.
     *
     * @param angle which way the enemy should turn. 1 is clockwise. 2 is
     *              anticlockwise
     */
    private int turn(int angle) {
        int newDirection = directionFacing + angle;
        newDirection = newDirection % 4;
        if (newDirection == -1) {
            newDirection = 3;
        }
        return newDirection;
    }

    /**
     * Returns a boolean value as to whether the cell in the diection specified can
     * be walked on to by this entity
     *
     * @param Board     The current state of the board.
     * @param Direction The direction in which to search for a walkable tile
     * @return Boolean value that indicates if the tile in that direction is
     *         walkable
     */
    private Boolean canMove(int direction) {
        int newX = xCoord + xyChange[direction][0];
        int newY = yCoord + xyChange[direction][1];
        return this.board.getCell(newX, newY).getIsWalkable(this);
    }

    /**
     * Returns a boolean value, as to whether there is a space towards the outside
     * of the loop
     *
     * @param Board The current state of the board
     * @return Whether the enemy can turn towards the outside of the loop
     */
    private Boolean canTurnBack(Board board) {
        int lastDirection = turn(-clockwise);
        return canMove(lastDirection);
    }

    /**
     * Return in string position X, position Y, value if enemy is going clockwise or not
     * and direction that enemy is facing
     *
     * @return Description of the wall
     */
    public String toFile() {
        String toReturn = "wall,";
        toReturn += xCoord + "," + yCoord + "," + clockwise + "," + directionFacing;
        toReturn += "\n";
        return toReturn;
    }

    /**
     * Return in String position X, position Y and direction that enemy is facing
     *
     * @return in String enemy description
     */
    public String toString() {
        String string = "";
        string += "X: " + this.getXCoord();
        string += "Y: " + this.getYCoord();
        string += "direction: " + this.directionFacing;
        string += "/n";
        System.out.println(string);
        return string;
    }
    
}
