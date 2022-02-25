/**
 * Homework 7 
 * Esther Cowan, elc7hfp 
 * Collaborators: Mary Grace Giles, Bailey Greggs, Mihika Rao
 */

public class Rating {

    /**
     * Holds an individual rating score
     */
    private int score;

    /**
     * Holds the user object
     */
    private User user;

    /**
     * Default constructor for the Rating class
     */
    public Rating() {
        this.score = 0;
        this.user = null;
    }

    /**
     * Overloaded constructor for the Rating class
     * 
     * @param u A user
     * @param s A score
     */
    public Rating(User u, int s) {
        this.user = u;
        if (setScore(s)) {
            this.score = s;
        } else {
            this.score = 0;
        }
    }

    /**
     * Retrieves the score from a rating
     * 
     * @return The rating score
     */
    public int getScore() {
        return score;
    }

    /**
     * Updates the score in a rating
     * 
     * @param newScore The score as an integer value
     * @return True if the score is a valid value, false otherwise
     */
    public boolean setScore(int newScore) {
        if ((newScore == 1) || (newScore == -1) || (newScore == 0)) {
            this.score = newScore;
            return true;
        }
        return false;
    }

    /**
     * Retrieves the user value for the rating
     * 
     * @return the user object
     */
    public User getUser() {
        return user;
    }

    /**
     * Updates the user value for the rating
     * 
     * @param newUser A new user object
     */
    public void setUser(User newUser) {
        this.user = newUser;
    }

    /**
     * Returns a string representation of the rating
     */
    @Override
    public String toString() {
        if (this.getScore() == 1) {
            return this.getUser().getUserName() + " rated as an upvote";
        } else if (this.getScore() == -1) {
            return this.getUser().getUserName() + " rated as a downvote";
        } else if (this.getScore() == 0) {
            return this.getUser().getUserName() + " rated as a pass";
        }
        return "";
    }

    /**
     * Compares the equality of the current Rating with another object
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof Rating) {
            Rating newObject = (Rating) object;
            if ((newObject.getScore() == this.getScore()) && (newObject.getUser() == this.getUser())) {
                return true;
            }
        }
        return false;
    }

}
