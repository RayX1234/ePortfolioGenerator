/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.controller;

import static epg.StartupConstants.CSS_CLASS_IMAGE_VBOX;
import static epg.StartupConstants.ICON_FIRE;
import static epg.StartupConstants.PATH_ICONS;
import static epg.StartupConstants.STYLE_SHEET_UI;
import epg.model.Component;
import epg.model.Image;
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
    Label chooseFloat;
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
    Label imageLabel;
    Image i;
    Component c;

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
        i = new Image();
        c = new Component();
        c.setI(i);
        imageVBox.getStyleClass().add(CSS_CLASS_IMAGE_VBOX);
        okCancelHBox = new HBox(10);
        okCancelHBox.setAlignment(Pos.CENTER_RIGHT);
        imageScene = new Scene(imageVBox, 500, 550);
        imageScene.getStylesheets().add(STYLE_SHEET_UI);
        captionLabel = new Label("Enter Caption:");
        captionTextField = new TextField();
        selectImageLabel = new Label("Select Image:");
        selectImageButton = new Button("Choose");
        widthLabel = new Label("Enter Width:");
        widthTextField = new TextField();
        heightLabel = new Label("Enter Height");
        heightTextField = new TextField();
        chooseFloat = new Label("Choose Float:");
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
            i.setImagePosition(rgroup.getSelectedToggle());
            i.setCaption(captionTextField.getText());
            i.setHeight(heightTextField.getText());
            i.setWidth(widthTextField.getText());
            c.setImage(true);
            ui.getListData().add(c);
            imageStage.close();
        });
        cancelButton.setOnAction(e -> {
            imageStage.close();
        });
        okCancelHBox.getChildren().addAll(okButton, cancelButton);
        selectImageButton.setOnAction(e -> {
            processSelectImage();
        });
        imageVBox.getChildren().addAll(selectImageLabel, selectImageButton, captionLabel, captionTextField, widthLabel, widthTextField, heightLabel, heightTextField, chooseFloat, rLeftButton, rRightButton, rNeitherButton, okCancelHBox);
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
            i.setImageFileName(fileName);
            i.setImagePath(path);
            i.setImage(path, fileName);
        }
    }

    public void displayEditImageDialog() {
        image1Stage = new Stage();

        Component temp = ui.getList().getSelectionModel().getSelectedItem();
        if (temp.getI().getImagePosition().toString().contains("Left")) {
            rgroup.selectToggle(rLeftButton);
        }
        if (temp.getI().getImagePosition().toString().contains("Right")) {
            rgroup.selectToggle(rRightButton);
        }
        if (temp.getI().getImagePosition().toString().contains("Neither")) {
            rgroup.selectToggle(rNeitherButton);
        }
        captionTextField.setText(temp.getI().getCaption());
        heightTextField.setText(temp.getI().getHeight());
        widthTextField.setText(temp.getI().getWidth());
        image1VBox = new VBox();
        image1VBox.getStyleClass().add(CSS_CLASS_IMAGE_VBOX);
        image1Scene = new Scene(image1VBox, 500, 550);
        image1Scene.getStylesheets().add(STYLE_SHEET_UI);
        image1VBox.getChildren().addAll(selectImageLabel, selectImageButton, captionLabel, captionTextField, widthLabel, widthTextField, heightLabel, heightTextField, chooseFloat, rLeftButton, rRightButton, rNeitherButton, okCancelHBox);
        okButton.setOnAction(e -> {
            temp.getI().setImagePosition(rgroup.getSelectedToggle());
            temp.getI().setCaption(captionTextField.getText());
            temp.getI().setHeight(heightTextField.getText());
            temp.getI().setWidth(widthTextField.getText());
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
