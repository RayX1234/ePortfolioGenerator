/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.controller;

import epg.view.EPortfolioGeneratorView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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

    //For the addText Dialog
    Stage addTextStage;
    Scene addTextScene;
    VBox addTextVBox;
    ComboBox addTextComboBox;
    TextArea addTextArea;
    Label selectTypeOfTextLabel;
    Label enterContentLabel;
    HBox okCancelHBox;
    Button okButton;
    Button cancelButton;

    public TextController(EPortfolioGeneratorView initUI) {
        ui = initUI;
    }

    public void displayAddTextDialog() {
        addTextStage = new Stage();
        addTextVBox = new VBox();
        okCancelHBox = new HBox();
        addTextScene = new Scene(addTextVBox);
        addTextStage.setScene(addTextScene);
        addTextArea = new TextArea();
        addTextComboBox = new ComboBox();
        okButton = new Button("Ok");
        cancelButton = new Button("Cancel");
        okButton.setOnAction(e -> {
            addTextStage.close();
        });
        cancelButton.setOnAction(e -> {
            addTextStage.close();
        });
        selectTypeOfTextLabel = new Label("Select a Type Of Text");
        enterContentLabel = new Label("Enter Content");
        addTextComboBox.getItems().addAll("Paragraph", "Header", "List");
        okCancelHBox.getChildren().addAll(okButton, cancelButton);
        addTextVBox.getChildren().addAll(selectTypeOfTextLabel, addTextComboBox, enterContentLabel, addTextArea, okCancelHBox);
        addTextStage.show();

    }
}
