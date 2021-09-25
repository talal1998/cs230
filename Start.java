import javafx.scene.image.Image;

public class Start extends Cell {
    /**
     * Create start tile
     *
     * @param xPos - position in the row X on the board
     * @param yPos - position in the column Y on the board
     */
    public Start(int xPos, int yPos) {
        super(xPos, yPos);
        this.image = new Image("start.png");
        this.isWalkable = true;
    }

    /**
     * Return name of the tile 
     *
     * @return name of the tile
     */
    public String nameToAscii() {
        return "S";
    }
}
