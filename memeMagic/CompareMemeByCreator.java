/**
 * Homework 7 
 * Esther Cowan, elc7hfp 
 * 
 * Collaborators: Mary Grace Giles, Bailey Greggs, Mihika Rao
 */

import java.util.Comparator;

public class CompareMemeByCreator implements Comparator<Meme>{
    
    /**
     * Compares two Memes by creator, then rating, then caption, then background image, and finally if shared.
     */
    public int compare(Meme a, Meme b) {
        int retval = a.getCreator().compareTo(b.getCreator());
        if (retval != 0) {
            return retval;
        }
        Double aRating = a.calculateOverallRating();
        Double bRating = b.calculateOverallRating();
        int retval2 = bRating.compareTo(aRating);
        if (retval2 != 0) {
            return retval2;
        }
        int retval3 = a.getCaption().compareTo(b.getCaption());
        if (retval3 != 0) {
            return retval3;
        }
        int retval4 = a.getBackgroundImage().compareTo(b.getBackgroundImage());
        if (retval4 != 0) {
            return retval4;
        }
        Boolean bShared = b.getShared();
        Boolean aShared = a.getShared();
        return bShared.compareTo(aShared);
    }

}
