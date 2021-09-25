import javafx.scene.image.Image;

public class Token extends Collectible {

    /**
     * Construct Token create token on the board
     *
     * @param xPos - get position in the row X
     * @param yPos - get position in the column Y
     */
    public Token(int xPos, int yPos) {
        super(xPos, yPos);
        this.image = new Image("file:tokenFloor.png");
    }

    /**
     * Construct Token create token on the board
     *
     * @param settings - get position in the row X, position in the column Y
     *                 and check if cell was visited by the player
     */
    public Token(String[] settings) {
        super(Integer.parseInt(settings[1]), Integer.parseInt(settings[2]));
        setStomped(Boolean.parseBoolean(settings[3]));
        if (isStomped()) {
            this.image = new Image("floor.png");
        } else {
            this.image = new Image("tokenFloor.png");
        }
    }

    /**
     * Method getIsWalkable return true or false respectively
     *
     * @param entity  get entity of something that wants to visit that cell
     * @return true if player wants to visit this cell or enemy wants to visit this cell
     *         and method is isStomped return true, otherwise false
     */
    public Boolean getIsWalkable(Entity entity) {
        if (entity.getEntityType().equals("Player")
                || (entity.getEntityType().equals("Enemy") && isStomped() == true)) {
            if (!isStomped()) {
                Player player = (Player) entity;
                player.addToken();
                this.image = new Image("floor.png");
                setStomped(true);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method nameToAscii return name of the cell in ascii
     * @return name of the cell in ascii
     */
    public String nameToAscii() {
        return "Z";
    }

    /**
     * Method settingsToString return variable Stomped in String
     * @return variable Stomped in String
     */
    public String settingsToString() {
        return (String.valueOf(Stomped));
    }
}
