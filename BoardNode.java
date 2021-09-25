
// TODO: Auto-generated Javadoc
/**
 * The Class BoardNode.
 */
public class BoardNode implements Comparable<Object> {

	/** The parent. */
	public BoardNode parent;
	
	/** The y. */
	public int x, y;
	
	/** The g. */
	public double g;
	
	/** The h. */
	public double h;

	/**
	 * Instantiates a new board node.
	 *
	 * @param parent the parent
	 * @param xpos the xpos
	 * @param ypos the ypos
	 * @param g the g
	 * @param h the h
	 */
	BoardNode(BoardNode parent, int xpos, int ypos, double g, double h) {
		this.parent = parent;
		this.x = xpos;
		this.y = ypos;
		this.g = g;
		this.h = h;
	}

	/**
	 * Compare to.
	 *
	 * @param o the o
	 * @return the int
	 */
	// Compare by f value (g + h)
	@Override
	public int compareTo(Object o) {
		BoardNode that = (BoardNode) o;
		return (int) ((this.g + this.h) - (that.g + that.h));
	}

	/**
	 * String to.
	 *
	 * @return the string
	 */
	public String stringTo() {
		String string = "";
		string += ("X:" + this.x + "\n");
		string += ("Y:" + this.y + "\n");
		string += ("G:" + this.g + "\n");
		string += ("H:" + this.h + "\n");
		return string;
	}

}
