/**
 * Homework 7 
 * Esther Cowan, elc7hfp 
 * 
 * Collaborators: Mary Grace Giles, Bailey Greggs, Mihika Rao
 */

import java.util.ArrayList;

public class Feed {

    /**
     * An ArrayList of all the memes on the feed
     */
    private ArrayList<Meme> memes;

    /**
     * Default constructor for the Feed class
     */
    public Feed() {
        this.memes = new ArrayList<Meme>();
    }

    /**
     * Retrieves the ArrayList of all the memes on the feed
     * 
     * @return the memes on the feed
     */
    public ArrayList<Meme> getMemes() {
        return memes;
    }

    /**
     * Updates the ArrayList of all the memes on the feed
     * 
     * @param newMemes An ArrayList of memes
     */
    public void setMemes(ArrayList<Meme> newMemes) {
        this.memes = newMemes;
    }

    /**
     * Finds a meme on the feed that the user has not viewed or created
     * 
     * @param user The user who wants to see a new meme
     * @return A new meme if one was found; null otherwise
     */
    public Meme getNewMeme(User user) {
        for (Meme meme : this.getMemes()) {
            if ((user.getMemesCreated().contains(meme) == false) && (user.getMemesViewed().contains(meme) == false)) {
                return meme;
            }
        }
        return null;
    }

    /**
     * Returns a string representation of all the memes on the feed
     */
    @Override
    public String toString() {
        String string = "";
        for (Meme meme : this.getMemes()) {
            string += meme + "\n";
        }
        return string;
    }

}
