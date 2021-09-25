public abstract class Collectible extends Cell {

	/** The Stomped is set to true if the collectible has been taken by
	 * the player. 
	 *  */
	protected boolean Stomped = false;
	
	/**
	 * Instantiates a new collectible.
	 *
	 * @param xPos the x pos
	 * @param yPos the y pos
	 */
	public Collectible(int xPos, int yPos) {
		super(xPos, yPos);
	}
	
	/**
	 * Sets the stomped to true or false.
	 *
	 * @param stomped the new stomped
	 */
	public void setStomped(boolean stomped) {
		Stomped = stomped;
	}

	/**
	 * Checks if is stomped.
	 *
	 * @return true, if is stomped
	 */
	public boolean isStomped() {
		return Stomped;
	}

	

	/**
	 * Sets stomped to a string.
	 *
	 * @return the string
	 */
	public String settingsToString() {
		return (String.valueOf(Stomped));
	}
}
