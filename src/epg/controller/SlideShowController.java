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
import static epg.StartupConstants.CSS_CLASS_DESELECT_SLIDE;
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
import epg.model.Slide;
import epg.model.SlideShowModel;
import epg.view.EPortfolioGeneratorView;
import epg.view.SlideEditView;
import javafx.collections.ObservableList;
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
    ObservableList<Slide> slides;
    VBox slidesEditorPane;
    private SlideEditView slideEditor;
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
    
    // FOR componenet
    int count;
    String slideshowname;


    public SlideShowController(EPortfolioGeneratorView initUI) {
        ui = initUI;
        slideShow = new SlideShowModel(this);
    }

    public void displayAddSlideShowDialog() {
        addSlideShowStage = new Stage();
        slideShowBorderPane = new BorderPane();
        workspace = new HBox();
        slideShowBorderPane.setCenter(workspace);
        // THIS WILL GO IN THE LEFT SIDE OF THE SCREEN
        slideEditToolbar = new VBox();
        slideShowBorderPane.setLeft(slideEditToolbar);
        okCancelHBox = new HBox(10);
        okCancelHBox.setAlignment(Pos.BOTTOM_RIGHT);
        okButton = new Button("Ok");
        cancelButton = new Button("Cancel");
        okCancelHBox.getChildren().addAll(okButton,cancelButton);
        okButton.setOnAction(e -> {
            if(ui.getListData().isEmpty()){
                count = 0;
            }
            count++;
            slideshowname = "SlideShow Component " + count;
            ui.getListData().add(slideshowname);
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
    
    public void displayEditSlideShowDialog(){
        editSlideShowStage = new Stage();
        editSlideShowBorderPane = new BorderPane();
        editSlideShowBorderPane.setCenter(workspace);
        editSlideShowBorderPane.setLeft(slideEditToolbar);
        editSlideShowBorderPane.setBottom(okCancelHBox);
        okButton.setOnAction(e -> {
           editSlideShowStage.close();
        });
        cancelButton.setOnAction(e -> {
            editSlideShowStage.close();
        });
        editSlideShowScene = new Scene(editSlideShowBorderPane,1000,500);
        editSlideShowScene.getStylesheets().add(STYLE_SHEET_UI);
        editSlideShowStage.setScene(editSlideShowScene);
        editSlideShowStage.setTitle("Edit SlideShow");
        ui.setWindowIcon(ICON_FIRE, editSlideShowStage);
        editSlideShowStage.show();
    }

}
