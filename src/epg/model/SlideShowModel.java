/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.model;

import epg.controller.SlideShowController;
import epg.view.SlideEditView;
import java.util.Collections;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Ray
 */
public class SlideShowModel {

    SlideShowController ui;
    String title;
    ObservableList<Slide> slides;
    Slide selectedSlide;
    Slide slide;
    SlideEditView slideEditor;

    public SlideShowModel(SlideShowController initUI) {
        ui = initUI;
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
}
