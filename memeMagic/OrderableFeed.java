/**
 * Homework 7 
 * Esther Cowan, elc7hfp 
 * 
 * Collaborators: Mary Grace Giles, Bailey Greggs, Mihika Rao
 */

import java.util.Collections;

public class OrderableFeed extends Feed{

    /**
     * Sorts the list of Memes in this feed by caption
     */
    public void sortByCaption() {
        Collections.sort(this.getMemes());
    }
    
    /**
     * Sorts the list of Memes in this feed by rating
     */
    public void sortByRating() {
        Collections.sort(this.getMemes(), new CompareMemeByRating());
    }
    
    /**
     * Sorts the list of Memes in this feed by creator
     */
    public void sortByCreator() {
        Collections.sort(this.getMemes(), new CompareMemeByCreator());
    }
}
