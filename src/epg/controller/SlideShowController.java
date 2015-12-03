/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.controller;

import static epg.PropertyType.TOOLTIP_ADD_SLIDE;
import static epg.PropertyType.TOOLTIP_MOVE_DOWN;
import static epg.PropertyType.TOOLTIP_MOVE_UP;
import static epg.PropertyType.TOOLTIP_REMOVE_SLIDE;
import static epg.StartupConstants.CSS_CLASS_ALIGN_CENTER;
import static epg.StartupConstants.CSS_CLASS_DESELECT_SLIDE;
import static epg.StartupConstants.CSS_CLASS_IMAGE_VBOX;
import static epg.StartupConstants.CSS_CLASS_SCROLL_PANE;
import static epg.StartupConstants.CSS_CLASS_SELECT_SLIDE;
import static epg.StartupConstants.CSS_CLASS_SLIDE_SHOW_EDIT_VBOX;
import static epg.StartupConstants.CSS_CLASS_VERTICAL_TOOLBAR_BUTTON;
import static epg.StartupConstants.DEFAULT_SLIDE_CAPTION;
import static epg.StartupConstants.DEFAULT_SLIDE_IMAGE;
import static epg.StartupConstants.ICON_ADD_SLIDE;
import static epg.StartupConstants.ICON_FIRE;
import static epg.StartupConstants.ICON_MOVE_DOWN;
import static epg.StartupConstants.ICON_MOVE_UP;
import static epg.StartupConstants.ICON_REMOVE_SLIDE;
import static epg.StartupConstants.PATH_SLIDE_SHOW_IMAGES;
import static epg.StartupConstants.STYLE_SHEET_UI;
import epg.model.Component;
import epg.model.Slide;
import epg.model.SlideShow;
import epg.model.SlideShowModel;
import epg.view.EPortfolioGeneratorView;
import epg.view.SlideEditView;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Ray
 */
public class SlideShowController {

    private EPortfolioGeneratorView ui;

    //For the slideshow add dialog window
    Stage addSlideShowStage;
    Scene addSlideShowScene;
    HBox workspace;
    ScrollPane slidesEditorScrollPane;
    VBox slideEditToolbar;
    Button addSlideButton;
    Button removeSlideButton;
    Button moveUpSlideButton;
    Button moveDownSlideButton;

    VBox slidesEditorPane;
    SlideEditView slideEditor;
    // THIS IS THE SLIDE SHOW WE'RE WORKING WITH
    SlideShowModel slideShow;

    //General cases
    BorderPane slideShowBorderPane;
    HBox okCancelHBox;
    Button okButton;
    Button cancelButton;

    //For editSlideShowDialog
    Stage editSlideShowStage;
    Scene editSlideShowScene;
    BorderPane editSlideShowBorderPane;
    VBox slideEditToolbar2;
    Button addSlideButton2;
    Button removeSlideButton2;
    Button moveUpSlideButton2;
    Button moveDownSlideButton2;

    Component c;
    SlideShow ss;
    boolean newSlideShow;
    SlideShowModel temp2;

    public SlideShowController(EPortfolioGeneratorView initUI) {
        ui = initUI;

    }

    public void displayAddSlideShowDialog() {
        newSlideShow = true;
        slideShow = new SlideShowModel(this, ui);
        addSlideShowStage = new Stage();
        c = new Component();
        ss = new SlideShow();
        c.setSS(ss);
        slideShowBorderPane = new BorderPane();
        slideShowBorderPane.getStyleClass().add(CSS_CLASS_IMAGE_VBOX);
        workspace = new HBox();
        slideShowBorderPane.setCenter(workspace);
        // THIS WILL GO IN THE LEFT SIDE OF THE SCREEN
        slideEditToolbar = new VBox();
        slideEditToolbar.getStyleClass().add(CSS_CLASS_ALIGN_CENTER);
        slideShowBorderPane.setLeft(slideEditToolbar);
        okCancelHBox = new HBox(10);
        okCancelHBox.setAlignment(Pos.BOTTOM_RIGHT);
        okButton = new Button("Ok");
        cancelButton = new Button("Cancel");
        okCancelHBox.getChildren().addAll(okButton, cancelButton);
        okButton.setOnAction(e -> {
            ss.setSlideShowModel(slideShow);
            c.setSlideshow(true);
            ui.getListData().add(c);
            addSlideShowStage.close();
        });
        cancelButton.setOnAction(e -> {
            addSlideShowStage.close();
        });
        slideShowBorderPane.setBottom(okCancelHBox);
        slideEditToolbar.getStyleClass().add(CSS_CLASS_SLIDE_SHOW_EDIT_VBOX);
        addSlideButton = ui.initChildButton(slideEditToolbar, ICON_ADD_SLIDE, TOOLTIP_ADD_SLIDE, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);
        removeSlideButton = ui.initChildButton(slideEditToolbar, ICON_REMOVE_SLIDE, TOOLTIP_REMOVE_SLIDE, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);
        moveUpSlideButton = ui.initChildButton(slideEditToolbar, ICON_MOVE_UP, TOOLTIP_MOVE_UP, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);
        moveDownSlideButton = ui.initChildButton(slideEditToolbar, ICON_MOVE_DOWN, TOOLTIP_MOVE_DOWN, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);

        // AND THIS WILL GO IN THE CENTER
        slidesEditorPane = new VBox();

        slidesEditorScrollPane = new ScrollPane(slidesEditorPane);
        slidesEditorScrollPane.getStyleClass().add(CSS_CLASS_SCROLL_PANE);

        // NOW PUT THESE TWO IN THE WORKSPACE
        workspace.getChildren().add(slidesEditorScrollPane);

        initEventHandlers();

        addSlideShowScene = new Scene(slideShowBorderPane, 1000, 500);
        addSlideShowScene.getStylesheets().add(STYLE_SHEET_UI);
        addSlideShowStage.setScene(addSlideShowScene);
        ui.setWindowIcon(ICON_FIRE, addSlideShowStage);
        addSlideShowStage.setTitle("Add SlideShow");
        addSlideShowStage.show();
    }

    public void reloadSlideShowPaneEmpty(SlideShowModel slideShowToLoad) {
        slidesEditorPane.getChildren().clear();
        for (Slide slide : slideShowToLoad.getSlides()) {
            slideEditor = new SlideEditView(slide, slideShow, this);
            slidesEditorPane.getChildren().add(slideEditor);
        }
    }

    public void reloadSlideShowPane(SlideShowModel slideShowToLoad) {
        slidesEditorPane.getChildren().clear();
        for (Slide slide : slideShowToLoad.getSlides()) {
            slideEditor = new SlideEditView(slide, slideShow, this);
            slidesEditorPane.getChildren().add(slideEditor);
            int value = slideShow.getSlides().indexOf(slide);
            if (slideShow.getSlides().get(value) == slideShow.getSelectedSlide()) {
                slideEditor.getStyleClass().add(CSS_CLASS_SELECT_SLIDE);
            } else {
                slideEditor.getStyleClass().add(CSS_CLASS_DESELECT_SLIDE);
            }

        }
    }
    
     public void reloadSlideShowPaneEmpty2(SlideShowModel slideShowToLoad) {
        slidesEditorPane.getChildren().clear();
        for (Slide slide : slideShowToLoad.getSlides()) {
            slideEditor = new SlideEditView(slide, slideShowToLoad, this);
            slidesEditorPane.getChildren().add(slideEditor);
        }
    }

    public void reloadSlideShowPane2(SlideShowModel slideShowToLoad) {
        slidesEditorPane.getChildren().clear();
        for (Slide slide : slideShowToLoad.getSlides()) {
            slideEditor = new SlideEditView(slide, slideShowToLoad, this);
            slidesEditorPane.getChildren().add(slideEditor);
            int value = slideShowToLoad.getSlides().indexOf(slide);
            if (slideShowToLoad.getSlides().get(value) == slideShowToLoad.getSelectedSlide()) {
                slideEditor.getStyleClass().add(CSS_CLASS_SELECT_SLIDE);
            } else {
                slideEditor.getStyleClass().add(CSS_CLASS_DESELECT_SLIDE);
            }

        }
    }

    public void processAddSlideRequest() {
        slideShow.addSlide(DEFAULT_SLIDE_IMAGE, PATH_SLIDE_SHOW_IMAGES, DEFAULT_SLIDE_CAPTION);

    }

    public void processDeleteSlideRequest() {
        slideShow.removeSlide(slideShow.getSelectedSlide());
    }

    public void moveUpSlideRequest() {

        slideShow.moveUpSlide(slideShow.getSelectedSlide());

    }

    public void moveDownSlideRequest() {

        slideShow.moveDownSlide(slideShow.getSelectedSlide());
    }

    public void processAddSlideRequest2() {
        SlideShowModel temp = ui.getList().getSelectionModel().getSelectedItem().getSS().getSlideShowModel();
        temp.addSlide2(DEFAULT_SLIDE_IMAGE, PATH_SLIDE_SHOW_IMAGES, DEFAULT_SLIDE_CAPTION);

    }

    public void processDeleteSlideRequest2() {
        SlideShowModel temp = ui.getList().getSelectionModel().getSelectedItem().getSS().getSlideShowModel();
        temp.removeSlide2(temp.getSelectedSlide());
    }

    public void moveUpSlideRequest2() {
        SlideShowModel temp = ui.getList().getSelectionModel().getSelectedItem().getSS().getSlideShowModel();
        temp.moveUpSlide2(temp.getSelectedSlide());

    }

    public void moveDownSlideRequest2() {
        SlideShowModel temp = ui.getList().getSelectionModel().getSelectedItem().getSS().getSlideShowModel();
        temp.moveDownSlide2(temp.getSelectedSlide());
    }

    public void initEventHandlers() {
        addSlideButton.setOnAction(e -> {

            processAddSlideRequest();

        });

        removeSlideButton.setOnAction(e -> {
            processDeleteSlideRequest();
        });

        moveUpSlideButton.setOnAction(e -> {
            moveUpSlideRequest();
        });

        moveDownSlideButton.setOnAction(e -> {
            moveDownSlideRequest();
        });
    }

    public void displayEditSlideShowDialog() {
        newSlideShow = false;
        editSlideShowStage = new Stage();
        Component temp = ui.getList().getSelectionModel().getSelectedItem();
        reloadSlideShowPaneEmpty(temp.getSS().getSlideShowModel());
        temp2 = temp.getSS().getSlideShowModel();
        
        editSlideShowBorderPane = new BorderPane();
        slideEditToolbar2 = new VBox();
        editSlideShowBorderPane.setLeft(slideEditToolbar2);
        slideEditToolbar2.getStyleClass().add(CSS_CLASS_ALIGN_CENTER);
        slideEditToolbar2.getStyleClass().add(CSS_CLASS_SLIDE_SHOW_EDIT_VBOX);
        addSlideButton2 = ui.initChildButton(slideEditToolbar2, ICON_ADD_SLIDE, TOOLTIP_ADD_SLIDE, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);
        removeSlideButton2 = ui.initChildButton(slideEditToolbar2, ICON_REMOVE_SLIDE, TOOLTIP_REMOVE_SLIDE, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);
        moveUpSlideButton2 = ui.initChildButton(slideEditToolbar2, ICON_MOVE_UP, TOOLTIP_MOVE_UP, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);
        moveDownSlideButton2 = ui.initChildButton(slideEditToolbar2, ICON_MOVE_DOWN, TOOLTIP_MOVE_DOWN, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);
        initEventHandlers2();
        editSlideShowBorderPane.getStyleClass().add(CSS_CLASS_IMAGE_VBOX);
        editSlideShowBorderPane.setCenter(workspace);
        editSlideShowBorderPane.setBottom(okCancelHBox);
        okButton.setOnAction(e -> {
            temp.getSS().setSlideShowModel(ui.getList().getSelectionModel().getSelectedItem().getSS().getSlideShowModel());
            
            editSlideShowStage.close();
        });
        cancelButton.setOnAction(e -> {
            editSlideShowStage.close();
        });
        editSlideShowScene = new Scene(editSlideShowBorderPane, 1000, 500);
        editSlideShowScene.getStylesheets().add(STYLE_SHEET_UI);
        editSlideShowStage.setScene(editSlideShowScene);
        editSlideShowStage.setTitle("Edit SlideShow");
        ui.setWindowIcon(ICON_FIRE, editSlideShowStage);
        editSlideShowStage.show();
    }

    public void initEventHandlers2() {
        addSlideButton2.setOnAction(e -> {

            processAddSlideRequest2();

        });

        removeSlideButton2.setOnAction(e -> {
            processDeleteSlideRequest2();
        });

        moveUpSlideButton2.setOnAction(e -> {
            moveUpSlideRequest2();
        });

        moveDownSlideButton2.setOnAction(e -> {
            moveDownSlideRequest2();
        });
    }
    
    public Boolean isNewSlideShow(){
        return newSlideShow;
    }
    
    public SlideShowModel getTemp2(){
        return temp2;
    }
    
   

}
