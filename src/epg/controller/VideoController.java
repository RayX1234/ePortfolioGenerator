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
import epg.model.Video;
import epg.view.EPortfolioGeneratorView;
import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Ray
 */
public class VideoController {

    private EPortfolioGeneratorView ui;

    // For add Video
    Stage videoStage;
    Scene videoScene;
    VBox videoVBox;
    Label selectVideoLabel;
    Button selectVideoButton;
    Label captionLabel;
    TextField captionTextField;
    Label widthLabel;
    TextField widthTextField;
    Label heightLabel;
    TextField heightTextField;

    //General cases
    HBox okCancelHBox;
    Button okButton;
    Button cancelButton;

    Stage video1Stage;
    Scene video1Scene;
    VBox video1VBox;

    int count;
    Label videoLabel;
    Video v;

    public VideoController(EPortfolioGeneratorView initUI) {
        ui = initUI;
    }

    public void displayAddVideoDialog() {
        videoStage = new Stage();
        captionLabel = new Label("Enter Caption");
        captionTextField = new TextField();
        widthLabel = new Label("Enter Width");
        widthTextField = new TextField();
        heightLabel = new Label("Enter Height");
        heightTextField = new TextField();
        okCancelHBox = new HBox(10);
        v = new Video();
        okCancelHBox.setAlignment(Pos.CENTER_RIGHT);
        videoStage.setTitle("Add Video");
        videoVBox = new VBox();
        videoVBox.getStyleClass().add(CSS_CLASS_IMAGE_VBOX);
        videoScene = new Scene(videoVBox, 500, 420);
        videoScene.getStylesheets().add(STYLE_SHEET_UI);
        selectVideoLabel = new Label("Select Video");
        selectVideoButton = new Button("Choose");
        selectVideoButton.setOnAction(e -> {
            processSelectVideo();
        });
        okButton = new Button("Ok");
        cancelButton = new Button("Cancel");
        okButton.setOnAction(e -> {
            if (ui.getListData().isEmpty()) {
                count = 0;
            }
            count++;
            captionTextField.setText(captionTextField.getText());
            v.setCaption(captionTextField.getText());
            heightTextField.setText(heightTextField.getText());
            v.setHeight(heightTextField.getText());
            widthTextField.setText(widthTextField.getText());
            v.setWidth(widthTextField.getText());
            videoLabel = new Label("Video Component " + count);
            ui.getListData().add(videoLabel);
            videoStage.close();
        });
        cancelButton.setOnAction(e -> {
            videoStage.close();
        });
        okCancelHBox.getChildren().addAll(okButton, cancelButton);

        videoVBox.getChildren().addAll(selectVideoLabel, selectVideoButton, captionLabel, captionTextField, widthLabel, widthTextField, heightLabel, heightTextField, okCancelHBox);
        videoStage.setScene(videoScene);
        ui.setWindowIcon(ICON_FIRE, videoStage);
        videoStage.show();
    }

    public void processSelectVideo() {
        FileChooser videoFileChooser = new FileChooser();
        // SET THE STARTING DIRECTORY
        videoFileChooser.setInitialDirectory(new File(PATH_ICONS));

        // LET'S ONLY SEE IMAGE FILES
        FileChooser.ExtensionFilter mp4Filter = new FileChooser.ExtensionFilter("MP4 files (*.mp4)", "*.MP4");
        videoFileChooser.getExtensionFilters().addAll(mp4Filter);

        // LET'S OPEN THE FILE CHOOSER
        File file = videoFileChooser.showOpenDialog(null);
        if (file != null) {
            String path = file.getPath().substring(0, file.getPath().indexOf(file.getName()));
            String fileName = file.getName();
            v.setVideo(path, fileName);
            v.setVideoFileName(fileName);
            v.setVideoPath(path);
        }
    }

    public void displayEditVideoDialog() {
        video1Stage = new Stage();
        video1VBox = new VBox();
        video1VBox.getStyleClass().add(CSS_CLASS_IMAGE_VBOX);
        video1Scene = new Scene(video1VBox, 500, 420);
        video1Scene.getStylesheets().add(STYLE_SHEET_UI);
        video1VBox.getChildren().addAll(selectVideoLabel, selectVideoButton, captionLabel, captionTextField, widthLabel, widthTextField, heightLabel, heightTextField, okCancelHBox);
        ui.setWindowIcon(ICON_FIRE, video1Stage);
        video1Stage.setTitle("Edit Video");
        video1Stage.setScene(video1Scene);
        okButton.setOnAction(e -> {
            captionTextField.setText(captionTextField.getText());
            v.setCaption(captionTextField.getText());
            heightTextField.setText(heightTextField.getText());
            v.setHeight(heightTextField.getText());
            widthTextField.setText(widthTextField.getText());
            v.setWidth(widthTextField.getText());
            video1Stage.close();
        });
        cancelButton.setOnAction(e -> {
            video1Stage.close();
        });
        video1Stage.show();
    }
}
