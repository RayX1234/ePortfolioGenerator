/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Toggle;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author Ray
 */
public class Page {

    private String bannerImageFileName;
    private String bannerImageFilePath;
    private String font;
    private String layout;
    private String footer;
    private String color;
    private ObservableList<Component> components;
    private ObservableList<Heading> headings;
    private ObservableList<Image> images;
    private ObservableList<ListObject> lists;
    private ObservableList<Paragraph> paragraphs;
    private ObservableList<Slide> slides;
    private ObservableList<Video> videos;
    private String pagetitle;
    private BorderPane contentPane;
    private String componentsSize;

    public Page() {
        components = FXCollections.observableArrayList();
        headings = FXCollections.observableArrayList();
        paragraphs = FXCollections.observableArrayList();
        videos = FXCollections.observableArrayList();
        images = FXCollections.observableArrayList();
        lists = FXCollections.observableArrayList();
        slides = FXCollections.observableArrayList();
    }

    public BorderPane getContentPane() {
        return contentPane;
    }

    public void setContentPane(BorderPane initContentPane) {
        contentPane = initContentPane;
    }

    public String getComponentsSize() {
        return componentsSize;
    }

    public void setComponentsSize(String initComponentsSize) {
        componentsSize = initComponentsSize;
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

    public String getFooter() {
        return footer;
    }

    public void setFooter(String initFooter) {
        footer = initFooter;
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

    /**
     * @return the headings
     */
    public ObservableList<Heading> getHeadings() {
        return headings;
    }

    /**
     * @return the images
     */
    public ObservableList<Image> getImages() {
        return images;
    }

    /**
     * @return the paragraphs
     */
    public ObservableList<Paragraph> getParagraphs() {
        return paragraphs;
    }

    /**
     * @return the videos
     */
    public ObservableList<Video> getVideos() {
        return videos;
    }

    /**
     * @return the lists
     */
    public ObservableList<ListObject> getLists() {
        return lists;
    }

    /**
     * @return the slides
     */
    public ObservableList<Slide> getSlides() {
        return slides;
    }
}
