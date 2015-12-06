/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.model;

/**
 *
 * @author Ray
 */
public class Slide {

    String imageFileName;
    String imagePath;
    String caption;

    /**
     * Constructor, it initializes all slide data.
     *
     * @param initImageFileName File name of the image.
     *
     * @param initImagePath File path for the image.
     * @param initCaption
     *
     */
    public Slide(String initImageFileName, String initImagePath, String initCaption) {
        imageFileName = initImageFileName;
        imagePath = initImagePath;
        caption = initCaption;

    }

    // ACCESSOR METHODS
    public String getImageFileName() {
        return imageFileName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getCaption() {
        return caption;
    }

    // MUTATOR METHODS
    public void setImageFileName(String initImageFileName) {
        imageFileName = initImageFileName;
    }

    public void setImagePath(String initImagePath) {
        imagePath = initImagePath;
    }

    public void setImage(String initPath, String initFileName) {
        imagePath = initPath;
        imageFileName = initFileName;
    }

    public void setCaption(String initCaption) {
        caption = initCaption;
    }

}
