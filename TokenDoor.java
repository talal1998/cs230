import javafx.scene.image.Image;

public class TokenDoor extends Door {

    /**
     * Constructor TokenDoor create token Door cell
     *
     * @param xPos - get position in row X
     * @param yPos - position in column Y
     */
    public TokenDoor(int xPos, int yPos) {
        super(xPos, yPos);
        this.image = new Image("file:tokenDoor.png");
        this.isWalkable = false;
    }

    /**
     * Constructor TokenDoor create token Door cell
     *
     * @param settings - get position in row X , position in column Y and boolean
     *                 if cell is walkable or not
     */
    public TokenDoor(String[] settings) {
        super(Integer.parseInt(settings[1]), Integer.parseInt(settings[2]));
        this.image = new Image("file:tokenDoor.png");
        this.isWalkable = Boolean.parseBoolean(settings[3]);
    }

    /**
     * If is of type player, tries to consume a token, and then returns the
     * isWalkable Status. Otherwise returns false.
     *
     * @param entity  - get entity of something that wants to visit that cell
     * @return A boolean to indicate if the entity can walk on to this space
     */
    public Boolean getIsWalkable(Entity entity) {
        if (entity.getEntityType().equals("Player")) {
            Player player = (Player) entity;
            if (player.getTokens() > 0) {
                isWalkable = true;
            }
            return isWalkable;
        } else {
            return false;
        }
    }

    /**
     * Method nameToAscii return name of the cell in Ascii
     * @return name of the cell
     */
    public String nameToAscii() {
        return "D";
    }

    /**
     * Method settingsToString return variable isWalkable in String
     * @return variable isWalkable in String
     */
    public String settingsToString() {
        return (isWalkable.toString());
    }
}
