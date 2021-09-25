import javafx.scene.image.Image;

public class Teleporter extends Cell {
    private Teleporter linkedTeleporter;
    // number used to link teleporters.
    private int no;

    /**
     * Instantiates a new teleporter.
     *
     * @param xPos the x pos
     * @param yPos the y pos
     * @param no   the no
     */
    public Teleporter(int xPos, int yPos, int no) {
        super(xPos, yPos);
        this.image = new Image("file:teleport.jpg");
        this.isWalkable = true;
        this.no = no;
    }

    /**
     * Instantiates a new teleporter.
     *
     * @param settings - get from the array position in the row X and position in the column Y
     */
    public Teleporter(String[] settings) {
        super(Integer.parseInt(settings[1]), Integer.parseInt(settings[2]));
        this.image = new Image("file:teleport.jpg");
        this.isWalkable = true;
    }

    /**
     * Gets the linked teleporter.
     *
     * @return the linked teleporter
     */
    public Teleporter getLinkedTeleporter() {
        return linkedTeleporter;
    }

    /**
     * Sets the linked teleporter.
     *
     * @param linkedTeleporter the new linked teleporter
     */
    public void setLinkedTeleporter(Teleporter linkedTeleporter) {
        this.linkedTeleporter = linkedTeleporter;
    }

    /**
     * Gets the no.
     *
     * @return the no
     */
    public int getNo() {
        return no;
    }

    /**
     * Interact.
     *
     * @param player the player
     */
    /*
     * public void interact(Player player) { int newX = linkedTeleporter.getX(); int
     * newY = linkedTeleporter.getY(); player.moveTo(newX, newY); }
     */

    /**
     * After player steps on it teleports him to the linked teleport
     *
     * @param entity - get entity of something that wants to visit that cell
     * @return variable isWalkable if Player visit it, false otherwise
     */
    public Boolean getIsWalkable(Entity entity) {
        if (entity.getEntityType().equals("Player")) {
            Player player = (Player) entity;
            player.moveTo(linkedTeleporter.getX(), linkedTeleporter.getY());
            return isWalkable;
        } else {
            return false;
        }
    }

    /**
     * Gets the x.
     *
     * @return the x
     */
    public int getX() {
        return xPos;
    }

    /**
     * Gets the y.
     *
     * @return the y
     */
    public int getY() {
        return yPos;
    }

    /**
     * Link teleporter.
     *
     * @param teleporter the teleporter
     */
    public void linkTeleporter(Teleporter teleporter) {
        linkedTeleporter = teleporter;
    }

    /**
     *  Return ascii name
     *
     * @return ascii name
     */
    public String nameToAscii() {
        return "T";
    }

    /**
     * Return variable isWalkable in String
     * @return variable isWalkable in String
     */
    public String settingsToString() {
        return (isWalkable.toString());
    }
}
