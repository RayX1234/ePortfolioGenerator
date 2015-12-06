/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.model;

import epg.controller.SlideShowController;
import epg.view.EPortfolioGeneratorView;
import java.util.Collections;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Ray
 */
public class SlideShowModel {

    SlideShowController ui;
    EPortfolioGeneratorView ui2;
    ObservableList<Slide> slides;
    Slide selectedSlide;
    Slide slide;
    private String slideShowIndex;
    //   SlideEditView slideEditor;
    int count;

    public SlideShowModel(SlideShowController initUI, EPortfolioGeneratorView initUI2) {
        ui = initUI;
        ui2 = initUI2;
        slides = FXCollections.observableArrayList();
        reset();
    }

    public void reset() {
        slides.clear();
        selectedSlide = null;
    }

    // ACCESSOR METHODS
    public boolean isSlideSelected() {
        return selectedSlide != null;
    }

    public ObservableList<Slide> getSlides() {
        return slides;
    }

    public Slide getSelectedSlide() {
        return selectedSlide;
    }

    // MUTATOR METHODS
    public void setSelectedSlide(Slide initSelectedSlide) {
        selectedSlide = initSelectedSlide;
    }

    public void addSlide(String initImageFileName,
            String initImagePath, String initCaption) {
        Slide slideToAdd = new Slide(initImageFileName, initImagePath, initCaption);
        slides.add(slideToAdd);

        ui.reloadSlideShowPaneEmpty(this);

    }

    public void removeSlide(Slide s) {
        slides.remove(s);
        ui.reloadSlideShowPane(this);
        setSelectedSlide(null);
    }

    public void moveUpSlide(Slide s) {
        int slideIndex = slides.indexOf(s);
        try {

            Collections.swap(slides, slideIndex, slideIndex - 1);
            ui.reloadSlideShowPane(this);
        } catch (IndexOutOfBoundsException e) {

        }
    }

    public void moveDownSlide(Slide s) {
        int slideIndex = slides.indexOf(s);
        try {

            Collections.swap(slides, slideIndex, slideIndex + 1);
            ui.reloadSlideShowPane(this);
        } catch (IndexOutOfBoundsException e) {
        }

    }

    public void addSlide2(String initImageFileName,
            String initImagePath, String initCaption) {
        Slide slideToAdd = new Slide(initImageFileName, initImagePath, initCaption);

        SlideShowModel temp2 = ui.getTemp2();
        ObservableList<Slide> temp = temp2.getSlides();
        temp.add(slideToAdd);

        ui.reloadSlideShowPaneEmpty2(temp2);

    }

    public void removeSlide2(Slide s) {

        SlideShowModel temp2 = ui.getTemp2();
        ObservableList<Slide> temp = temp2.getSlides();
        temp.remove(s);
        ui.reloadSlideShowPane2(temp2);
        setSelectedSlide(null);
    }

    public void moveUpSlide2(Slide s) {

        SlideShowModel temp2 = ui.getTemp2();
        ObservableList<Slide> temp = temp2.getSlides();
        int slideIndex = temp.indexOf(s);
        try {

            Collections.swap(temp, slideIndex, slideIndex - 1);
            ui.reloadSlideShowPane2(temp2);
        } catch (IndexOutOfBoundsException e) {

        }
    }

    public void moveDownSlide2(Slide s) {

        SlideShowModel temp2 = ui.getTemp2();
        ObservableList<Slide> temp = temp2.getSlides();
        int slideIndex = temp.indexOf(s);
        try {

            Collections.swap(temp, slideIndex, slideIndex + 1);
            ui.reloadSlideShowPane2(temp2);
        } catch (IndexOutOfBoundsException e) {
        }

    }

    @Override
    public String toString() {
        return "Slide Show Component";
    }

    /**
     * @return the slideShowIndex
     */
    public String getSlideShowIndex() {
        return slideShowIndex;
    }

    /**
     * @param slideShowIndex the slideShowIndex to set
     */
    public void setSlideShowIndex(String slideShowIndex) {
        this.slideShowIndex = slideShowIndex;
    }

}
