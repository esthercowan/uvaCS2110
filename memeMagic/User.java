/**
 * Homework 7 
 * Esther Cowan, elc7hfp 
 * 
 * Collaborators: Mary Grace Giles, Bailey Greggs, Mihika Rao
 */

import java.util.ArrayList;
import java.util.TreeSet;

public class User implements Comparable<User> {

    /**
     * Holds the user's username as a string
     */
    private String userName;

    /**
     * An ArrayList of all the memes a user has created
     */
    private ArrayList<Meme> memesCreated;

    /**
     * A TreeSet of all the memes a user has viewed
     */
    private TreeSet<Meme> memesViewed;

    /**
     * Default constructor for the User class
     */
    public User() {
        this.userName = "";
        this.memesCreated = new ArrayList<Meme>();
        this.memesViewed = new TreeSet<Meme>();
    }

    /**
     * Overloaded constructor for the User class
     * 
     * @param u A user's username
     */
    public User(String u) {
        this.userName = u;
        this.memesCreated = new ArrayList<Meme>();
        this.memesViewed = new TreeSet<Meme>();
    }

    /**
     * Retrieves a user's username
     * 
     * @return the user's username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Updates a user's username
     * 
     * @param newName A new username
     */
    public void setUserName(String newName) {
        this.userName = newName;
    }

    /**
     * Retrieves the ArrayList of all the memes a user has created
     * 
     * @return the memes a user has created
     */
    public ArrayList<Meme> getMemesCreated() {
        return memesCreated;
    }

    /**
     * Updates the ArrayList of all the memes a user has created
     * 
     * @param newMemesCreated A new ArrayList of a user's memes
     */
    public void setMemesCreated(ArrayList<Meme> newMemesCreated) {
        this.memesCreated = newMemesCreated;
    }

    /**
     * Retrieves the ArrayList of all the memes a user has viewed
     * 
     * @return the memes a user has viewed
     */
    public ArrayList<Meme> getMemesViewed() {
        ArrayList<Meme> memesViewedArrayList = new ArrayList<Meme>(this.memesViewed);
        return memesViewedArrayList;
    }

    /**
     * Updates the ArrayList of all the memes a user has viewed
     * 
     * @param newMemesViewed A new ArrayList of a user's viewed memes
     */
    public void setMemesViewed(ArrayList<Meme> newMemesViewed) {
        TreeSet<Meme> newTreeSet = new TreeSet<Meme>(newMemesViewed);
        this.memesViewed = newTreeSet;
    }

    /**
     * Records a meme as having been seen by a user and gives it a rating
     * 
     * @param meme   A Meme argument
     * @param rating An integer rating score
     */
    public void rateMeme(Meme meme, int rating) {
        Rating newRating = new Rating(this, rating);
        meme.addRating(newRating);
        this.getMemesViewed().add(meme);
    }

    /**
     * Creates a new Meme object with the current user set as the creator
     * 
     * @param backgroundImage The background image for the new Meme
     * @param caption         The caption for the new Meme
     * @return The new Meme
     */
    public Meme createMeme(BackgroundImage backgroundImage, String caption) {
        Meme newMeme = new Meme(backgroundImage, caption, this);
        this.getMemesCreated().add(newMeme);
        return newMeme;
    }

    /**
     * Deletes a meme a user has created, but only if it has not been shared
     * 
     * @param meme A Meme object
     * @return True if the meme was deleted; false otherwise
     */
    public boolean deleteMeme(Meme meme) {
        if ((meme.getShared() == false) && (this.getMemesCreated().contains(meme))) {
            this.getMemesCreated().remove(meme);
            return true;
        }
        return false;
    }

    /**
     * Marks a meme as shared and adds it to the feed
     * 
     * @param meme The meme to be shared
     * @param feed The feed the meme should be added to
     */
    public void shareMeme(Meme meme, Feed feed) {
        meme.setShared(true);
        feed.getMemes().add(meme);
    }

    /**
     * Gets a meme from the feed, adds it to the user's memesViewed ArrayList, and rates it
     * 
     * @param feed        The feed from which the meme is coming from
     * @param ratingScore The rating to be given to the meme
     * @return True if a meme was successfully retrieved from the feed; false otherwise
     */
    public boolean rateNextMemeFromFeed(Feed feed, int ratingScore) {
        Meme meme = feed.getNewMeme(this);
        if (feed.getNewMeme(this) != null) {
            this.getMemesViewed().add(meme);
            Rating newRating = new Rating(this, ratingScore);
            meme.addRating(newRating);
            return true;
        }
        return false;
    }

    /**
     * Calculates the average of all overall ratings for memes created by the user
     * 
     * @return The user's average rating for all memes they've created
     */
    public double calculateReputation() {
        if (this.getMemesCreated().isEmpty() == false) {
            double ratingSum = 0;
            for (Meme meme : this.getMemesCreated()) {
                ratingSum += meme.calculateOverallRating();
            }
            return ratingSum / this.getMemesCreated().size();
        }
        return 0.0;
    }

    /**
     * Returns a string representation of the User
     */
    @Override
    public String toString() {
        return this.getUserName() + " has rated (" + this.getMemesViewed().size() + ") memes, (" + this.calculateReputation()
                + ")";
    }

    /**
     * Compares the equality of the current User with another object
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof User) {
            User newObject = (User) object;
            if (newObject.getUserName().equals(this.getUserName())) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Compare this user to another
     */
    public int compareTo(User other) {
        int retval = this.getUserName().compareTo(other.getUserName());
        if (retval != 0) {
            return retval;
        }
        Integer thisCreatedSize = this.getMemesCreated().size();
        Integer otherCreatedSize = other.getMemesCreated().size();
        return otherCreatedSize.compareTo(thisCreatedSize);
    }

}
