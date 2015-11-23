/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.controller;

import static epg.StartupConstants.ICON_FIRE;
import static epg.StartupConstants.PATH_ICONS;
import epg.view.EPortfolioGeneratorView;
import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Ray
 */
public class ImageController {

    // APP UI
    private EPortfolioGeneratorView ui;

    //For addImageDialog
    Stage imageStage;
    Scene imageScene;
    Label captionLabel;
    TextField captionTextField;
    Label selectImageLabel;
    Button selectImageButton;
    Label widthLabel;
    TextField widthTextField;
    Label heightLabel;
    TextField heightTextField;
    RadioButton rLeftButton;
    RadioButton rRightButton;
    RadioButton rNeitherButton;
    ToggleGroup rgroup;
    VBox imageVBox;

    //General cases
    HBox okCancelHBox;
    Button okButton;
    Button cancelButton;
    String image;
    int count;

    //For editImageDialog
    Stage image1Stage;
    Scene image1Scene;
    VBox image1VBox;

    public ImageController(EPortfolioGeneratorView initUI) {
        ui = initUI;
    }

    public void displayAddImageDialog() {
        imageStage = new Stage();
        imageVBox = new VBox();
        okCancelHBox = new HBox(10);
        okCancelHBox.setAlignment(Pos.CENTER_RIGHT);
        imageScene = new Scene(imageVBox, 500, 400);
        captionLabel = new Label("Enter Caption:");
        captionTextField = new TextField();
        selectImageLabel = new Label("Select Image:");
        selectImageButton = new Button("Choose");
        widthLabel = new Label("Enter Width:");
        widthTextField = new TextField();
        heightLabel = new Label("Enter Height");
        heightTextField = new TextField();
        rgroup = new ToggleGroup();
        rLeftButton = new RadioButton("Left");
        rRightButton = new RadioButton("Right");
        rNeitherButton = new RadioButton("Neither");
        rLeftButton.setToggleGroup(rgroup);
        rRightButton.setToggleGroup(rgroup);
        rNeitherButton.setToggleGroup(rgroup);
        okButton = new Button("Ok");
        cancelButton = new Button("Cancel");
        okButton.setOnAction(e -> {
            if (ui.getListData().isEmpty()) {
                count = 0;
            }
            count++;
            image = "Image Component " + count;
            ui.getListData().add(image);
            imageStage.close();
        });
        cancelButton.setOnAction(e -> {
            imageStage.close();
        });
        okCancelHBox.getChildren().addAll(okButton, cancelButton);
        selectImageButton.setOnAction(e -> {
            processSelectImage();
        });
        imageVBox.getChildren().addAll(selectImageLabel, selectImageButton, captionLabel, captionTextField, widthLabel, widthTextField, heightLabel, heightTextField, rLeftButton, rRightButton, rNeitherButton, okCancelHBox);
        imageStage.setTitle("Add Image");
        ui.setWindowIcon(ICON_FIRE, imageStage);
        imageStage.setScene(imageScene);
        imageStage.show();
    }

    public void processSelectImage() {
        FileChooser imageFileChooser = new FileChooser();
        // SET THE STARTING DIRECTORY
        imageFileChooser.setInitialDirectory(new File(PATH_ICONS));

        // LET'S ONLY SEE IMAGE FILES
        FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter pngFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        FileChooser.ExtensionFilter gifFilter = new FileChooser.ExtensionFilter("GIF files (*.gif)", "*.GIF");
        imageFileChooser.getExtensionFilters().addAll(jpgFilter, pngFilter, gifFilter);

        // LET'S OPEN THE FILE CHOOSER
        File file = imageFileChooser.showOpenDialog(null);
        if (file != null) {
            String path = file.getPath().substring(0, file.getPath().indexOf(file.getName()));
            String fileName = file.getName();
        }
    }

    public void displayEditImageDialog() {
        image1Stage = new Stage();
        image1VBox = new VBox();
        image1Scene = new Scene(image1VBox, 500, 400);
        image1VBox.getChildren().addAll(selectImageLabel, selectImageButton, captionLabel, captionTextField, widthLabel, widthTextField, heightLabel, heightTextField, rLeftButton, rRightButton, rNeitherButton, okCancelHBox);
        okButton.setOnAction(e -> {
            image1Stage.close();
        });
        cancelButton.setOnAction(e -> {
            image1Stage.close();
        });
        image1Stage.setScene(image1Scene);
        image1Stage.setTitle("Edit Image");
        ui.setWindowIcon(ICON_FIRE, image1Stage);
        image1Stage.show();
    }
}
