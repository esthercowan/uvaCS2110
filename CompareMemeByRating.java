/**
 * Homework 7 
 * Esther Cowan, elc7hfp 
 * 
 * Collaborators: Mary Grace Giles, Bailey Greggs, Mihika Rao
 */

import java.util.Comparator;

public class CompareMemeByRating implements Comparator<Meme>{

    /**
     * Compares two Memes by overall rating, then caption, then background image, and finally by creator.
     */
    public int compare(Meme a, Meme b) {
        Double aRating = a.calculateOverallRating();
        Double bRating = b.calculateOverallRating();
        int retval = bRating.compareTo(aRating);
        if (retval != 0) {
            return retval;
        }
        int retval2 = a.getCaption().compareTo(b.getCaption());
        if (retval2 != 0) {
            return retval2;
        }
        int retval3 = a.getBackgroundImage().compareTo(b.getBackgroundImage());
        if (retval3 != 0) {
            return retval3;
        }
        return a.getCreator().compareTo(b.getCreator());
    }
}
