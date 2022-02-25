/**
 * Homework 7 
 * Esther Cowan, elc7hfp 
 * 
 * Collaborators: Mary Grace Giles, Bailey Greggs, Mihika Rao
 */

public class BackgroundImage implements Comparable<BackgroundImage>{

    /**
     * Holds the file name of the background image
     */
    private String imageFileName;

    /**
     * Holds the title of the background image
     */
    private String title;

    /**
     * Holds a description of the background image
     */
    private String description;

    /**
     * Default constructor for the BackgroundImage class
     */
    public BackgroundImage() {
        this.imageFileName = "";
        this.title = "";
        this.description = "";
    }

    /**
     * Overloaded constructor for the BackgroundImage class
     * 
     * @param i An imageFileName
     * @param t A title
     * @param d A description
     */
    public BackgroundImage(String i, String t, String d) {
        // constructor
        this.imageFileName = i;
        this.title = t;
        this.description = d;
    }

    /**
     * Retrieves the value of an imageFileName
     * 
     * @return the imageFileName string
     */
    public String getImageFileName() {
        return imageFileName;
    }

    /**
     * Updates the value of an imageFileName
     * 
     * @param newImageFileName An updated value for an imageFileName
     */
    public void setImageFileName(String newImageFileName) {
        this.imageFileName = newImageFileName;
    }

    /**
     * Retrieves the value of a title
     * 
     * @return the title string
     */
    public String getTitle() {
        return title;
    }

    /**
     * Updates the value of a title
     * 
     * @param newTitle An updated value for a title
     */
    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    /**
     * Retrieves the value of a description
     * 
     * @return the description string
     */
    public String getDescription() {
        return description;
    }

    /**
     * Updates the value of a description
     * 
     * @param newDescription An updated value for a description
     */
    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * Returns a string representation of the BackgroundImage
     */
    @Override
    public String toString() {
        return this.title + " <" + this.description + ">";
    }

    /**
     * Compares the equality of the current BackgroundImage with another object
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof BackgroundImage) {
            BackgroundImage newObject = (BackgroundImage) object;
            if ((newObject.getImageFileName().equals(this.getImageFileName())) && (newObject.getTitle().equals(this.getTitle()))
                    && (newObject.getDescription().equals(this.getDescription()))) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Compare this BackgroundImage to another
     */
    public int compareTo(BackgroundImage other) {
        int retval = this.getImageFileName().compareTo(other.getImageFileName());
        if (retval != 0) {
            return retval;
        }
        int retval2 = this.getTitle().compareTo(other.getTitle());
        if (retval2 != 0) {
            return retval2;
        }
        return this.getDescription().compareTo(other.getDescription());
    }

}
