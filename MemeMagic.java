
/**
 * Homework 7 
 * Esther Cowan, elc7hfp 
 * Collaborators: Mary Grace Giles, Bailey Greggs, Mihika Rao
 */
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * MemeMagic Graphical User Interface This class contains the graphical user interface for the Meme Magic Software You
 * will need to implement certain portions of this class, marked with comments starting with "TODO" to connect it with
 * your existing code. This class provides an example layout for the GUI. You are encouraged to be creative in your
 * design. More information about Swing is online at:
 * https://docs.oracle.com/javase/tutorial/uiswing/components/componentlist.html.
 */
public class MemeMagic extends JFrame {

    /**
     * Serialization string required by extending JFrame
     */
    private static final long serialVersionUID = 1L;

    private User user;
    private GraphicalMeme currentMeme;

    private String backgroundImageFilename;

    private BorderLayout panelLayout;
    private JLabel backgroundImageFileNameLabel;
    private JLabel imageDisplayLabel;
    private JPanel controlPanel;
    private JPanel memeViewPanel;
    private JPanel panelPane;

    public MemeMagic() {
        this.user = new User();
    }

    public MemeMagic(User user) {
        this.user = user;
    }

    /**
     * Main method. This method initializes a PhotoViewer, loads images into a PhotographContainer, then initializes the
     * Graphical User Interface.
     * 
     * @param args Optional command-line arguments
     */
    public static void main(String[] args) {

        // Create a User object for this instance of Meme Magic
        User user = new User();

        // Instantiate the PhotoViewer Class
        MemeMagic myViewer = new MemeMagic(user);

        // Invoke and start the Graphical User Interface
        javax.swing.SwingUtilities.invokeLater(() -> myViewer.initialize());
    }

    /**
     * Initialize all the GUI components. This method will be called by SwingUtilities when the application is started.
     */
    private void initialize() {

        // Tell Java to exit the program when the window is closed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Tell Java to title the window to Meme Magic
        this.setTitle("Meme Magic");

        // We will use border layout on the main panel, since it is much easier for organizing panels.
        panelLayout = new BorderLayout();
        panelPane = new JPanel(panelLayout);

        // Create a label to display the full image.
        imageDisplayLabel = new JLabel();
        imageDisplayLabel.setHorizontalAlignment(JLabel.CENTER);
        imageDisplayLabel.setPreferredSize(new Dimension(550, 550));

        // Create a panel on which to display the full image
        memeViewPanel = new JPanel(new BorderLayout());
        memeViewPanel.setPreferredSize(new Dimension(550, 550));
        memeViewPanel.add(imageDisplayLabel, BorderLayout.CENTER);

        // Create a panel on which to display the controls for building a Meme
        controlPanel = new JPanel(new BorderLayout());

        // Create a panel that holds BackgroundImage information and give it a title
        JPanel backgroundImagePanel = new JPanel(new BorderLayout());
        backgroundImagePanel.setBorder(BorderFactory.createTitledBorder("Background Image"));

        // Create a panel that provides input for the BackgroundImage fileName
        JPanel backgroundImageFilePanel = new JPanel();

        // Label
        JLabel backgroundImageFileLabel = new JLabel("Filename: ");
        backgroundImageFileLabel.setPreferredSize(new Dimension(100, 20));
        backgroundImageFilePanel.add(backgroundImageFileLabel);

        // Button
        JButton backgroundImageButton = new JButton("Browse");
        backgroundImageFilePanel.add(backgroundImageButton);
        backgroundImageButton.setPreferredSize(new Dimension(85, 20));
        OpenButtonListener backgroundImageButtonListener = new OpenButtonListener();
        backgroundImageButton.addActionListener(backgroundImageButtonListener);

        // Label that will contain the filename of the image
        backgroundImageFileNameLabel = new JLabel("<choose>");
        backgroundImageFilePanel.add(backgroundImageFileNameLabel);
        backgroundImageFileNameLabel.setPreferredSize(new Dimension(265, 20));

        // Add the panel about the BackgroundImage fileName to the BackgroundImage information panel
        backgroundImagePanel.add(backgroundImageFilePanel, BorderLayout.NORTH);

        // Create a panel for the Title label and text field
        JPanel backgroundImageTitlePanel = new JPanel();

        // Label
        JLabel backgroundImageTitleLabel = new JLabel("Title: ");
        backgroundImageTitleLabel.setPreferredSize(new Dimension(100, 20));
        backgroundImageTitlePanel.add(backgroundImageTitleLabel);

        // Title text field
        JTextField backgroundImageTitleTextField = new JTextField();
        backgroundImageTitleTextField.setPreferredSize(new Dimension(350, 20));
        backgroundImageTitlePanel.add(backgroundImageTitleTextField);

        // Add panel about BackgroundImage title to the BackgroundImage information panel
        backgroundImagePanel.add(backgroundImageTitlePanel, BorderLayout.CENTER);

        // Create a panel for the Description label and text field
        JPanel backgroundImageDescriptionPanel = new JPanel();

        // Label
        JLabel backgroundImageDescriptionLabel = new JLabel("Description: ");
        backgroundImageDescriptionLabel.setPreferredSize(new Dimension(100, 20));
        backgroundImageDescriptionPanel.add(backgroundImageDescriptionLabel);

        // Description text field
        JTextField backgroundImageDescriptionTextField = new JTextField();
        backgroundImageDescriptionTextField.setPreferredSize(new Dimension(350, 20));
        backgroundImageDescriptionPanel.add(backgroundImageDescriptionTextField);

        // Add panel about BackgroundImage description to the BackgroundImage information panel
        backgroundImagePanel.add(backgroundImageDescriptionPanel, BorderLayout.SOUTH);

        // Creating meme panel
        JPanel memePanel = new JPanel(new BorderLayout());
        memePanel.setBorder(BorderFactory.createTitledBorder("Meme"));

        // Create a panel for the caption label and text field
        JPanel memeCaptionPanel = new JPanel();

        // Label
        JLabel memeCaptionLabel = new JLabel("Caption: ");
        memeCaptionLabel.setPreferredSize(new Dimension(100, 20));
        memeCaptionPanel.add(memeCaptionLabel);

        // Caption text field
        JTextField memeCaptionTextField = new JTextField();
        memeCaptionTextField.setPreferredSize(new Dimension(350, 20));
        memeCaptionPanel.add(memeCaptionTextField);

        // Add panel about meme caption to the meme information panel
        memePanel.add(memeCaptionPanel, BorderLayout.NORTH);

        // Create a panel for the vertical align label and combo box
        JPanel memeVerticalAlignPanel = new JPanel();

        // Label
        JLabel memeVerticalAlignLabel = new JLabel("Vertical Align: ");
        memeVerticalAlignLabel.setPreferredSize(new Dimension(100, 20));
        memeVerticalAlignPanel.add(memeVerticalAlignLabel);

        // Creating JComboBox for Meme JPanel
        String[] memeVerticalAlignOptions = { "top", "middle", "bottom" };
        JComboBox<String> memeVerticalAlignDropDown = new JComboBox<String>(memeVerticalAlignOptions);
        memeVerticalAlignDropDown.setPreferredSize(new Dimension(350, 20));
        memeVerticalAlignPanel.add(memeVerticalAlignDropDown);

        // Add panel about vertical align to meme info panel
        memePanel.add(memeVerticalAlignPanel, BorderLayout.CENTER);

        // Create a panel for the generate and save buttons
        JPanel buttonPanel = new JPanel();

        // Generate button
        JButton generateButton = new JButton("Generate");
        generateButton.setPreferredSize(new Dimension(85, 20));
        buttonPanel.add(generateButton);
        generateButton.addActionListener(new ActionListener() {
            /**
             * ActionListener for the generate button. When the button is pressed, this ActionListener instantiates a
             * GraphicalMeme object with the caption, alignment, and background image information provided by the user in the GUI
             * interface, then uses GraphicalMeme's compileMeme() method to compile the meme into a BufferedImage and displays the
             * graphical version of the meme onto the imageDisplayLabel.
             */
            @Override
            public void actionPerformed(ActionEvent evt) {
                String imageFileName = backgroundImageFileNameLabel.getText();
                String title = backgroundImageTitleTextField.getText();
                String description = backgroundImageDescriptionTextField.getText();
                BackgroundImage b = new BackgroundImage(imageFileName, title, description);

                String caption = memeCaptionTextField.getText();

                currentMeme = new GraphicalMeme(b, caption, user);

                currentMeme.setCaptionVerticalAlign((String) memeVerticalAlignDropDown.getSelectedItem());

                try {
                    imageDisplayLabel.setIcon(new ImageIcon(currentMeme.compileMeme()));
                    memeViewPanel.repaint();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Invalid image file!");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }

            }
        });

        // Save button
        JButton saveButton = new JButton("Save");
        saveButton.setPreferredSize(new Dimension(85, 20));
        buttonPanel.add(saveButton);
        SaveButtonListener saveButtonListener = new SaveButtonListener();
        saveButton.addActionListener(saveButtonListener);

        // Add panel with generate and save buttons to control panel
        controlPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the BackgroundImage information panel to the control panel
        controlPanel.add(backgroundImagePanel, BorderLayout.NORTH);

        // Add the Meme information panel to the control panel
        controlPanel.add(memePanel, BorderLayout.CENTER);

        // Add all the panels to the main display based on BorderLayout
        controlPanel.setPreferredSize(new Dimension(500, 570));
        panelPane.add(controlPanel, BorderLayout.WEST);
        panelPane.add(memeViewPanel, BorderLayout.CENTER);

        // Add the panelPane to the contentPane of the Frame (Window)
        this.getContentPane().add(panelPane);

        // Set the preferred size and show the main application window
        this.setPreferredSize(new Dimension(1150, 570));
        this.pack();
        this.setVisible(true);
    }

    /**
     * ActionListener for the open button. When the button is pressed, this ActionListener opens a FileChooser, asks the
     * user to choose a JPG image file, then sets the field backgroundImageFilename in the main class.
     */
    private class OpenButtonListener implements ActionListener {
        /**
         * Action performed operation. Opens a save FileChooser, asks the user to choose a JPG image file, then sets the field
         * backgroundImageFilename in the main class.
         * 
         * @param evt The event that was performed
         */
        @Override
        public void actionPerformed(ActionEvent evt) {
            JFileChooser chooser2 = new JFileChooser();
            chooser2.setDialogTitle("Choose a Background Image");
            chooser2.setFileFilter(new FileNameExtensionFilter("JPEG Images", "jpg", "jpeg"));
            int returnVal = chooser2.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                backgroundImageFilename = chooser2.getSelectedFile().getAbsolutePath();
                backgroundImageFileNameLabel.setText(backgroundImageFilename);
            }

        }
    }

    /**
     * ActionListener for the save button. When the button is pressed, this ActionListener opens a save FileChooser, asks
     * the user to choose a location and filename, then writes the graphical meme data to a PNG image file.
     */
    private class SaveButtonListener implements ActionListener {
        /**
         * Action performed operation. Opens a save FileChooser, asks the user to choose a location and filename, then writes
         * the graphical meme data to a PNG file.
         * 
         * @param evt The event that was performed
         */
        @Override
        public void actionPerformed(ActionEvent evt) {
            JFileChooser chooser2 = new JFileChooser();
            chooser2.setDialogTitle("Save Meme");
            chooser2.setFileFilter(new FileNameExtensionFilter("PNG Images", "png"));
            int returnVal = chooser2.showSaveDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String destinationFile = chooser2.getSelectedFile().getAbsolutePath();
                if (!destinationFile.contains(".png"))
                    destinationFile += ".png";
                // TODO: Writing an image throws a checked exception that must be handled.
                // Catch the exceptions and provide the user with an appropriate message
                try {
                    ImageIO.write(currentMeme.compileMeme(), "png", new File(destinationFile));
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Improper data was entered");
                } catch (NullPointerException e) {
                    JOptionPane.showMessageDialog(null, "File does not exist or cannot be read");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }

            }

        }
    }

}
