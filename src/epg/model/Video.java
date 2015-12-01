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
public class Video {

    String videoFileName;
    String videoPath;
    String caption;
    String width;
    String height;

    public Video() {

    }

    public void setVideoFileName(String initVideoFileName) {
        videoFileName = initVideoFileName;
    }

    public String getVideoFileName() {
        return videoFileName;
    }

    public void setVideoPath(String initVideoPath) {
        videoFileName = initVideoPath;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setCaption(String initCaption) {
        caption = initCaption;
    }

    public String getCaption() {
        return caption;
    }

    public void setWidth(String initWidth) {
        width = initWidth;
    }

    public String getWidth() {
        return width;
    }

    public void setHeight(String initHeight) {
        height = initHeight;
    }

    public String getHeight() {
        return height;
    }

    public void setVideo(String initPath, String initFileName) {
        videoPath = initPath;
        videoFileName = initFileName;
    }
    
    @Override
    public String toString(){
       
        return "Video Component";
    }

}
