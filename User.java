public class User {
    private String name;
    private int maxLevelComplete;
    private String fileName;

    /**
     * Constructor User set user name
     * @param name - get user name 
     */
    public User(String name) {
        this.name = name;
    }

    /**
     * Constructor User set for user name number of completed levels and name of the save file  
     * 
     * @param name - User name
     * @param maxLevelComplete - how many levels user completed
     * @param fileName - Name of the file where user save progress
     */
    public User(String name, int maxLevelComplete, String fileName) {
        this.name = name;
        this.maxLevelComplete = maxLevelComplete;
        this.fileName = fileName;
    }

    public void incrementMaxLevel() {
        maxLevelComplete += 1;
    }
    /**
     * Method getName return user name
     * @return name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Method getMaxLevelComplete return number of completed levels
     * @return maxLevelComplete - number of completed levels
     */
    public int getMaxLevelComplete() {
        return maxLevelComplete;
    }

    /**
     * Method getFileName return name of the file where player save progress
     * @return name of the file where player save progress
     */
    public String getFileName() {
        return fileName;
    }
    public String[] getUser() {
        String[] toReturn = new String[]{name, String.valueOf(maxLevelComplete), fileName};
        return toReturn;
    }
}
