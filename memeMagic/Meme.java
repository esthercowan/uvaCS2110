/**
 * Homework 7 
 * Esther Cowan, elc7hfp 
 * Collaborators: Mary Grace Giles, Bailey Greggs, Mihika Rao
 */

public class Meme implements Comparable<Meme> {

    /**
     * Holds the creator of the meme
     */
    private User creator;

    /**
     * Holds the background image object
     */
    private BackgroundImage backgroundImage;

    /**
     * Holds an array containing the meme's rating
     */
    private Rating[] ratings;

    /**
     * Holds the caption for the meme
     */
    private String caption;

    /**
     * Tells how the caption will be aligned on the meme
     */
    private String captionVerticalAlign;

    /**
     * Tells if the meme is shared or not
     */
    private boolean shared;

    /**
     * Default constructor for the Meme class
     */
    public Meme() {
        this.backgroundImage = new BackgroundImage();
        this.caption = "";
        this.creator = new User();
        this.ratings = new Rating[10];
        this.captionVerticalAlign = "bottom";
        this.shared = false;
    }

    /**
     * Overloaded constructor for the Meme class
     * 
     * @param b  A background image
     * @param c  A caption
     * @param cr A creator
     */
    public Meme(BackgroundImage b, String c, User cr) {
        // constructor
        this.backgroundImage = b;
        this.caption = c;
        this.creator = cr;
        this.ratings = new Rating[10];
        this.captionVerticalAlign = "bottom";
        this.shared = false;
    }

    /**
     * Retrieves the name of the creator
     * 
     * @return the User object for the creator
     */
    public User getCreator() {
        return creator;
    }

    /**
     * Updates the name of a creator
     * 
     * @param newCreator An updated name for a creator
     */
    public void setCreator(User newCreator) {
        this.creator = newCreator;
    }

    /**
     * Retrieves the background image for the meme
     * 
     * @return the BackgroundImage object
     */
    public BackgroundImage getBackgroundImage() {
        return backgroundImage;
    }

    /**
     * Updates the background image for a meme
     * 
     * @param newBackgroundImage An updated BackgroundImage object
     */
    public void setBackgroundImage(BackgroundImage newBackgroundImage) {
        this.backgroundImage = newBackgroundImage;
    }

    /**
     * Retrieves the array of ratings for the meme
     * 
     * @return An array of ratings
     */
    public Rating[] getRatings() {
        return ratings;
    }

    /**
     * Updates the array of ratings for a meme
     * 
     * @param newRatings An updated array of ratings
     */
    public void setRatings(Rating[] newRatings) {
        this.ratings = newRatings;
    }

    /**
     * Retrieves the caption for a meme
     * 
     * @return The caption string
     */
    public String getCaption() {
        return caption;
    }

    /**
     * Updates the caption for a meme
     * 
     * @param newCaption An updated caption string
     */
    public void setCaption(String newCaption) {
        this.caption = newCaption;
    }

    /**
     * Retrieves the vertical alignment of a caption
     * 
     * @return A string describing the caption's vertical alignment
     */
    public String getCaptionVerticalAlign() {
        return captionVerticalAlign;
    }

    /**
     * Updates the vertical alignment of a caption
     * 
     * @param newCaptionVerticalAlign A string describing the caption's vertical alignment
     * @return True or False, depending on whether the new alignment is a valid value
     */
    public boolean setCaptionVerticalAlign(String newCaptionVerticalAlign) {
        if ((newCaptionVerticalAlign == "top") || (newCaptionVerticalAlign == "middle")
                || (newCaptionVerticalAlign == "bottom")) {
            this.captionVerticalAlign = newCaptionVerticalAlign;
            return true;
        }
        return false;
    }

    /**
     * Retrieves the "shared" value of a meme
     * 
     * @return True or False depending on whether the meme is shared or not
     */
    public boolean getShared() {
        return shared;
    }

    /**
     * Updates the "shared" value of a meme
     * 
     * @param newShared A boolean telling whether the meme is shared or not
     */
    public void setShared(boolean newShared) {
        this.shared = newShared;
    }

    /**
     * Adds the rating object to the Meme's array of ratings
     * 
     * @param newRating A new rating object
     * @return True if successful, false otherwise
     */
    public boolean addRating(Rating newRating) {
        for (int i = 0; i < this.getRatings().length; i++) {
            if (this.getRatings()[i] == null) {
                this.getRatings()[i] = newRating;
                return true;
            }
        }
        this.getRatings()[0] = null;
        for (int i = 1; i < this.getRatings().length; i++) {
            this.getRatings()[i - 1] = this.getRatings()[i];
            this.getRatings()[i] = null;
        }
        if (this.getRatings()[9] == null) {
            this.getRatings()[9] = newRating;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Calculates the overall rating of a meme
     * 
     * @return A double that is a sum of all rating scores for the meme
     */
    public double calculateOverallRating() {
        double sum = 0.0;
        for (int i = 0; i < this.getRatings().length; i++) {
            if ((this.getRatings()[i] == null) || (this.getRatings() == null)) {
                continue;
            } else {
                sum = sum + this.getRatings()[i].getScore();
            }
        }
        return sum;
    }

    /**
     * Returns a string representation of the meme
     */
    @Override
    public String toString() {
        int upvotes = 0;
        int downvotes = 0;
        for (int i = 0; i < this.getRatings().length; i++) {
            if (this.getRatings()[i] != null) {
                if (this.getRatings()[i].getScore() == 1) {
                    upvotes = upvotes + 1;
                } else if (this.getRatings()[i].getScore() == -1) {
                    downvotes = downvotes + 1;
                }
            } else {
                continue;
            }
        }
        return this.getBackgroundImage() + " '" + this.getCaption() + "' " + this.calculateOverallRating() + " [+1: " + upvotes
                + ", -1: " + downvotes + "] - created by " + this.getCreator().getUserName();
    }

    /**
     * Compares the equality of the current Meme with another object
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof Meme) {
            Meme newObject = (Meme) object;
            if ((newObject.getCreator().equals(this.getCreator())) && (newObject.getCaption().equals(this.getCaption()))
                    && (newObject.getBackgroundImage().equals(this.getBackgroundImage()))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Compare this meme to another
     */
    public int compareTo(Meme other) {
        int retval = this.getCaption().compareTo(other.getCaption());
        if (retval != 0) {
            return retval;
        }
        int retval2 = this.getBackgroundImage().compareTo(other.getBackgroundImage());
        if (retval2 != 0) {
            return retval2;
        }
        Double thisRating = this.calculateOverallRating();
        Double otherRating = other.calculateOverallRating();
        int retval3 = otherRating.compareTo(thisRating);
        if (retval3 != 0) {
            return retval3;
        }
        Boolean otherShared = other.getShared();
        Boolean thisShared = this.getShared();
        return otherShared.compareTo(thisShared);
    }

}
