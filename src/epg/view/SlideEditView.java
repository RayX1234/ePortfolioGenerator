/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.view;

import static epg.StartupConstants.CSS_CLASS_SLIDE_EDIT_VIEW;
import static epg.StartupConstants.DEFAULT_THUMBNAIL_WIDTH;
import static epg.StartupConstants.SLASH;
import epg.controller.ImageSelectionController;
import epg.controller.SlideShowController;
import epg.model.Slide;
import epg.model.SlideShowModel;
import java.io.File;
import java.net.URL;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Ray
 */
public class SlideEditView extends HBox {

    // SLIDE THIS COMPONENT EDITS
    Slide slide;

    // DISPLAYS THE IMAGE FOR THIS SLIDE
    ImageView imageSelectionView;

    // CONTROLS FOR EDITING THE CAPTION
    VBox captionVBox;
    Label captionLabel;
    TextField captionTextField;

    // PROVIDES RESPONSES FOR IMAGE SELECTION
    ImageSelectionController imageController;

    File file;

    public SlideEditView(Slide initSlide, SlideShowModel slideShow, SlideShowController ui) {

        // FIRST SELECT THE CSS STYLE CLASS FOR THIS COAINER
        this.getStyleClass().add(CSS_CLASS_SLIDE_EDIT_VIEW);

        // KEEP THE SLIDE FOR LATER
        slide = initSlide;

        // MAKE SURE WE ARE DISPLAYING THE PROPER IMAGE
        imageSelectionView = new ImageView();
        updateSlideImage();

        // SETUP THE CAPTION CONTROLS
        captionVBox = new VBox();
        //SELECTING A SLIDESHOW PROBLEM CAN ONLY CHANGE TWICE FROM UNSELETED TO SELECTED

        captionLabel = new Label("Enter Caption:");
        captionTextField = new TextField();
        captionTextField.setOnAction(e -> {
            slide.setCaption(captionTextField.getText());

        });

        captionVBox.setOnMousePressed(e -> {

            slideShow.setSelectedSlide(slide);
            if (ui.isNewSlideShow()) {

                ui.reloadSlideShowPane(slideShow);

            } else {

                ui.reloadSlideShowPane2(ui.getTemp2());

            }
            if (file.exists()) {

            }
        });

        updateCaptionImage();

        captionVBox.getChildren().add(captionLabel);
        captionVBox.getChildren().add(captionTextField);

        // LAY EVERYTHING OUT INSIDE THIS COMPONENT
        getChildren().add(imageSelectionView);
        getChildren().add(captionVBox);

        // SETUP THE EVENT HANDLERS
        imageController = new ImageSelectionController();
        imageSelectionView.setOnMousePressed(e -> {
            imageController.processSelectImage(slide, this);
        });
    }

    public void updateSlideImage() {
        String imagePath = slide.getImagePath() + SLASH + slide.getImageFileName();

        file = new File(imagePath);

        try {
            // GET AND SET THE IMAGE
            URL fileURL = file.toURI().toURL();

            Image slideImage = new Image(fileURL.toExternalForm());

            imageSelectionView.setImage(slideImage);

            // AND RESIZE IT
            double scaledWidth = DEFAULT_THUMBNAIL_WIDTH;
            double perc = scaledWidth / slideImage.getWidth();
            double scaledHeight = slideImage.getHeight() * perc;
            imageSelectionView.setFitWidth(scaledWidth);
            imageSelectionView.setFitHeight(scaledHeight);

        } catch (Exception e) {

        }
    }

    public void updateCaptionImage() {
        String caption = slide.getCaption();

        captionTextField.setText(caption);

    }
}
