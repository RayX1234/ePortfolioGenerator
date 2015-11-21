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
    Label enterContentLabel;

    //For the add heading dialog
    Stage addHeadingStage;
    Scene addHeadingScene;
    TextField addHeadingTextField;
    VBox addHeadingVBox;

    //For the edit heading dialog
    Stage editHeadingStage;
    Scene editHeadingScene;
    VBox editHeadingVBox;
    TextField editHeadingTextField;

    //For the add paragraph dialog
    Stage addParagraphStage;
    Scene addParagraphScene;
    TextArea addParagraphTextArea;
    VBox addParagraphVBox;
    ComboBox addParagraphComboBox;
    ObservableList<String> fontOptions;

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
            if (addTextComboBox.getValue().equals("Paragraph")) {
                displayAddParagraphDialog();
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
        addHeadingTextField = new TextField();
        addHeadingStage = new Stage();
        addHeadingStage.setTitle("Add Heading");
        ui.setWindowIcon(ICON_FIRE, addHeadingStage);
        addHeadingVBox = new VBox();
        addHeadingVBox.getStyleClass().add(CSS_CLASS_SELECT_TEXT_TYPE);
        enterContentLabel = new Label("Enter Content:");
        addHeadingScene = new Scene(addHeadingVBox, 500, 150);
        addHeadingScene.getStylesheets().add(STYLE_SHEET_UI);
        addHeadingStage.setScene(addHeadingScene);
        addHeadingVBox.getChildren().addAll(enterContentLabel, addHeadingTextField, okCancelHBox);
        okButton.setOnAction(e -> {
            addHeadingStage.close();
        });
        cancelButton.setOnAction(e -> {
            addHeadingStage.close();
        });
        addHeadingStage.show();

    }

    public void displayEditHeadingDialog() {
        editHeadingTextField = new TextField();
        editHeadingStage = new Stage();
        editHeadingStage.setTitle("Edit Heading");
        ui.setWindowIcon(ICON_FIRE, editHeadingStage);
        editHeadingVBox = new VBox();
        enterContentLabel = new Label("Enter Content:");
        editHeadingScene = new Scene(editHeadingVBox, 500, 150);
        editHeadingScene.getStylesheets().add(STYLE_SHEET_UI);
        editHeadingStage.setScene(editHeadingScene);
        editHeadingVBox.getChildren().addAll(enterContentLabel, editHeadingTextField, okCancelHBox);
        okButton.setOnAction(e -> {
            editHeadingStage.close();
        });
        cancelButton.setOnAction(e -> {
            editHeadingStage.close();
        });
        editHeadingStage.show();

    }

    public void displayAddParagraphDialog() {
        addParagraphStage = new Stage();
        addParagraphVBox = new VBox();
        enterContentLabel = new Label("Enter Content:");
        addParagraphVBox.getStyleClass().add(CSS_CLASS_SELECT_TEXT_TYPE);
        addParagraphScene = new Scene(addParagraphVBox, 500, 150);
        addParagraphTextArea = new TextArea();
        addParagraphStage.setTitle("Add A Paragraph");
        ui.setWindowIcon(ICON_FIRE, addParagraphStage);
        addParagraphScene.getStylesheets().add(STYLE_SHEET_UI);
        addParagraphVBox.getChildren().addAll(enterContentLabel, addParagraphTextArea, okCancelHBox);
        addParagraphStage.setScene(addParagraphScene);
        addParagraphStage.show();

    }
}
