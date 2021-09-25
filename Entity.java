public abstract class Entity {
	
	/** The entity type. */
	protected String entityType;

	/**
	 * Gets the entity type.
	 *
	 * @return the entity type
	 */
	public String getEntityType() {
		return entityType;
	}

	/**
	 * To file.
	 *
	 * @return the string
	 */
	public abstract String toFile();
}