/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.model;

import javafx.collections.ObservableList;
import javafx.scene.control.Toggle;

/**
 *
 * @author Ray
 */
public class Page {
    private String bannerImageFileName;
    private String bannerImageFilePath;
    private String font;
    private String layout;
    private String color;
    private ObservableList<Component> components;
    private String pagetitle;
    
    public Page(){
        
    }

    /**
     * @return the bannerImageFileName
     */
    public String getBannerImageFileName() {
        return bannerImageFileName;
    }

    /**
     * @param bannerImageFileName the bannerImageFileName to set
     */
    public void setBannerImageFileName(String bannerImageFileName) {
        this.bannerImageFileName = bannerImageFileName;
    }

    /**
     * @return the bannerImageFilePath
     */
    public String getBannerImageFilePath() {
        return bannerImageFilePath;
    }

    /**
     * @param bannerImageFilePath the bannerImageFilePath to set
     */
    public void setBannerImageFilePath(String bannerImageFilePath) {
        this.bannerImageFilePath = bannerImageFilePath;
    }

    /**
     * @return the font
     */
    public String getFont() {
        return font;
    }

    /**
     * @param font the font to set
     */
    public void setFont(String font) {
        this.font = font;
    }

    /**
     * @return the layout
     */
    public String getLayout() {
        return layout;
    }

    /**
     * @param layout the layout to set
     */
    public void setLayout(String layout) {
        this.layout = layout;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the components
     */
    public ObservableList<Component> getComponents() {
        return components;
    }

    /**
     * @param components the components to set
     */
    public void setComponents(ObservableList<Component> components) {
        this.components = components;
    }

    /**
     * @return the pagetitle
     */
    public String getPagetitle() {
        return pagetitle;
    }

    /**
     * @param pagetitle the pagetitle to set
     */
    public void setPagetitle(String pagetitle) {
        this.pagetitle = pagetitle;
    }
}
