
/**
 * Creates the leaderboard that displays the top scores obtained
 */
public class LeaderBoard {

	private Score[] leaderboard;
	
	/**
	 * Creates a new array of 10 Score objects
	 */
	public LeaderBoard() {
		this.leaderboard = new Score[10];
	}
	
	/**
	 * Adds the score to the leader board .
	 *
	 * @param score the score
	 */
	public void addScore(Score score) {
		if(this.leaderboard.length < 10) {
			for(int i = 0; i < 9; i++) {
				if (this.leaderboard[i].getTime() < score.getTime()) {
					this.leaderboard[i] = score;
					//TODO NEED TO ALSO MOVE ALL OTHER ELEMENTS DOWN
				}
			}
		}
	}

}
