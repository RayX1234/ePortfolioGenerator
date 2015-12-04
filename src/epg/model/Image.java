/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.model;

import javafx.scene.control.Toggle;

/**
 *
 * @author Ray
 */
public class Image {

    private String imageFileName;
    private String imagePath;
    private String caption;
    private String height;
    private String width;
    private String imagePosition;
    

    public Image() {

    }

    public void setImageFileName(String initImageFileName) {
        imageFileName = initImageFileName;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public String getImagePath() {
        return imagePath;
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

    public String getCaption() {
        return caption;
    }
    
    public void setHeight(String initHeight){
        height = initHeight;
    }
    
    public String getHeight(){
        return height;
    }
    
    public void setWidth(String initWidth){
        width = initWidth;
    }
    
    public String getWidth(){
        return width;
    }
    
    public void setImagePosition(String initImagePosition){
        imagePosition = initImagePosition;
    }
    
    public String getImagePosition(){
        return imagePosition;
    }
    
    @Override
    public String toString(){
        return "Image Component";
    }
}
