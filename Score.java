
/**
 * The Class Score
 * 		this is used to store the user time in completing a level.
 */
public class Score {
    
    /** The user name. */
    private String username;
    
    /** The time. */
    private long time;

    /**
     * Store score for user.
     *
     * @param username the user name
     * @param time - completion time of the level
     */
    public Score(String username, long time) {
        this.username = username;
        this.time = time;
    }

    /**
     * Return time of completion.
     *
     * @return time of completion
     */
    public long getTime() {
        return time;
    }

    /**
     * Set the time of completion.
     *
     * @param time - completion time of the level
     */
    public void setTime(long time) {
        this.time = time;
    }

}
