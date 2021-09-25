import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class SmartAI extends Enemy {

	private List<BoardNode> open; // list of nodes still being checked
	private List<BoardNode> closed; // list of nodes that have been checked and dont lead to the goal
	private List<BoardNode> path; // list of nodes to traverse through
	private BoardNode now;
	private final Player player1;
	private int xend, yend;

	/**
	 * Create wall following enemy
	 *
	 * @param x       - position in the row X on the board
	 * @param y       - position in the row Y on the board
	 * @param player1 - the player the enemy is following
	 * @param board   - get board
	 */
	public SmartAI(int xCoord, int yCoord, Player player1, Board board1) {
		super(xCoord, yCoord, board1);
		this.open = new ArrayList<>();
		this.closed = new ArrayList<>();
		this.path = new ArrayList<>();
		this.now = new BoardNode(null, xCoord, yCoord, 0, 0);
		this.player1 = player1;
	}

	/**
	 * Create wall following enemy
	 *
	 * @param settings - array of strings from the file containing the x and y
	 *                 coords
	 * @param player1  - the player the enemy is following
	 * @param board    - get board
	 */
	public SmartAI(String[] settings, Board board, Player player) {
		super(Integer.parseInt(settings[1]), Integer.parseInt(settings[2]), board);
		this.open = new ArrayList<>();
		this.closed = new ArrayList<>();
		this.path = new ArrayList<>();
		this.now = new BoardNode(null, xCoord, yCoord, 0, 0);
		this.player1 = player;
	}

	/**
	 * Generates the path to the player
	 */
	public List<BoardNode> findPathTo() {
		this.xend = this.player1.getXPos();
		this.yend = this.player1.getYPos();
		this.closed.add(this.now);
		this.addNeigborsToOpenList();
		while (this.now.x != this.xend || this.now.y != this.yend) {
			if (this.open.isEmpty()) {
				System.out.print("null");
				return null;
			}
			this.now = this.open.get(0);
			this.open.remove(0);
			this.closed.add(this.now);
			this.addNeigborsToOpenList();
		}
		this.path.add(0, this.now);
		while (this.now.x != this.xCoord || this.now.y != this.yCoord) {
			this.now = this.now.parent;
			this.path.add(0, this.now);
		}
		return this.path;

	}

	/**
	 * converts the array to nodes
	 * 
	 * @param array - list of nodes already created
	 * @param node  = the node that the adjacent nodes are being added to the list
	 * 
	 */
	private static boolean findNeighborInList(List<BoardNode> array, BoardNode node) {
		return array.stream().anyMatch((n) -> (n.x == node.x && n.y == node.y));
	}

	/**
	 * 
	 * @param dx - the
	 * @param dy
	 * @return The distance between the enemy and the player
	 */
	public int distance(int dx, int dy) {
		return (Math.abs(this.now.x + dx - this.xend) + Math.abs(this.now.y + dy - this.xend));
	}

	/**
	 * loops over the neighbouring array records and converts them to nodes
	 */
	public void addNeigborsToOpenList() {
		BoardNode newNode;
		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				if (x != 0 && y != 0) {
					continue; // skip if diagonal movement is not allowed
				}
				newNode = new BoardNode(this.now, this.now.x + x, this.now.y + y, this.now.g, this.distance(x, y));
				if ((x != 0 || y != 0) // not this.now
						&& this.now.x + x >= 0 && this.now.x + x < this.board.getGrid()[0].length // check maze
																									// boundaries
						&& this.now.y + y >= 0 && this.now.y + y < this.board.getGrid().length
						&& checkValidMove(this.now.y + y, this.now.x + x)// check if square is walkable
						&& !findNeighborInList(this.open, newNode) && !findNeighborInList(this.closed, newNode)) { // if
																													// not
																													// already
																													// done
					newNode.g = newNode.parent.g + 1.; // Horizontal/vertical cost = 1.0
					this.open.add(newNode);
				}
			}
		}
		Collections.sort(this.open);
	}

	/**
	 * creates a path to the player and moves the enemy to the first position in
	 * that path
	 * 
	 * @param player - uses player to match the params of the super class doNextMove
	 */
	public void doNextMove(Player player) {
		this.findPathTo();
		if (!path.isEmpty()) {
			if (checkValidMove(path.get(0).x, path.get(0).y)) {
				setCurrentPos(path.get(0).x, path.get(0).y);
				path.remove(0);
			}
		} else {
			System.out.println("path has not been created");
		}
	}

	@Override
	public String toFile() {
		String toReturn = "smart,";
		toReturn += xCoord + "," + yCoord;
		toReturn += "\n";
		return toReturn;
	}
}
