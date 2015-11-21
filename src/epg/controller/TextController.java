/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.controller;

import static epg.StartupConstants.CSS_CLASS_SELECT_TEXT_TYPE;
import static epg.StartupConstants.ICON_FIRE;
import static epg.StartupConstants.STYLE_SHEET_UI;
import epg.view.EPortfolioGeneratorView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Ray
 */
public class TextController {

    // APP UI
    private EPortfolioGeneratorView ui;

    //For the selectText Dialog
    Stage addTextStage;
    Scene addTextScene;
    VBox selectTypeTextVBox;
    ComboBox addTextComboBox;
    TextArea paragraphTextArea;
    ObservableList<String> textOptions;
    Label selectTypeOfTextLabel;

    //General cases
    HBox okCancelHBox;
    Button okButton;
    Button cancelButton;

    //For the heading dialog
    Stage headingStage;
    Scene headingScene;
    Label enterContentLabel;
    TextField headingTextField;
    VBox headingVBox;

    public TextController(EPortfolioGeneratorView initUI) {
        ui = initUI;
    }

    public void displaySelectTypeTextDialog() {
        addTextStage = new Stage();
        addTextStage.setTitle("Choose Type Of Text");
        ui.setWindowIcon(ICON_FIRE, addTextStage);
        textOptions = FXCollections.observableArrayList("Heading", "Paragraph", "List");
        selectTypeTextVBox = new VBox();
        selectTypeTextVBox.getStyleClass().add(CSS_CLASS_SELECT_TEXT_TYPE);
        okCancelHBox = new HBox(10);
        okCancelHBox.setAlignment(Pos.CENTER_RIGHT);
        addTextScene = new Scene(selectTypeTextVBox, 500, 150);
        addTextScene.getStylesheets().add(STYLE_SHEET_UI);
        addTextStage.setScene(addTextScene);
        addTextComboBox = new ComboBox(textOptions);

        okButton = new Button("Ok");
        cancelButton = new Button("Cancel");
        okButton.setOnAction(e -> {
            if (addTextComboBox.getValue().equals("Heading")) {
                displayAddHeadingDialog();
            }
            addTextStage.close();
        });
        cancelButton.setOnAction(e -> {
            addTextStage.close();
        });
        selectTypeOfTextLabel = new Label("Select a Type Of Text");
        okCancelHBox.getChildren().addAll(okButton, cancelButton);
        selectTypeTextVBox.getChildren().addAll(selectTypeOfTextLabel, addTextComboBox, okCancelHBox);
        addTextStage.show();

    }

    public void displayAddHeadingDialog() {
        headingTextField = new TextField();
        headingStage = new Stage();
        headingStage.setTitle("Add Heading");
        ui.setWindowIcon(ICON_FIRE, headingStage);
        headingVBox = new VBox();
        enterContentLabel = new Label("Enter Content:");
        headingScene = new Scene(headingVBox, 500, 150);
        headingScene.getStylesheets().add(STYLE_SHEET_UI);
        headingStage.setScene(headingScene);
        headingVBox.getChildren().addAll(enterContentLabel, headingTextField, okCancelHBox);
        okButton.setOnAction(e ->{
            headingStage.close();
        });
        cancelButton.setOnAction(e ->{
            headingStage.close();
        });
        headingStage.show();
        
    }
    
    public void displayEditHeadingDialog(){
        headingStage.setTitle("Edit Heading");
        headingStage.show();
    }
}
