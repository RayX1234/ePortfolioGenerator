/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.controller;

import static epg.StartupConstants.CSS_CLASS_IMAGE_VBOX;
import static epg.StartupConstants.ICON_FIRE;
import static epg.StartupConstants.STYLE_SHEET_UI;
import epg.model.HyperLink;
import epg.view.EPortfolioGeneratorView;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Ray
 */
public class HyperLinkController {

    private EPortfolioGeneratorView ui;
    Stage hyperLinkStage;
    Scene hyperLinkScene;
    HBox okCancelHBox;
    Button okButton;
    Button cancelButton;
    VBox hyperLinkVBox;
    Label hyperLinkLabel;
    TextField hyperLinkTextField;

    int count;
    Label hyperLinkLabel1;
    Label temp;
    HyperLink hl;

    Stage hyperLink1Stage;
    Scene hyperLink1Scene;
    VBox hyperLink1VBox;

    public HyperLinkController(EPortfolioGeneratorView initUI) {
        ui = initUI;
    }

    public void displayAddHyperLinkDialog() {
        hyperLinkStage = new Stage();
        hl = new HyperLink();
        hyperLinkVBox = new VBox();
        hyperLinkVBox.getStyleClass().add(CSS_CLASS_IMAGE_VBOX);
        hyperLinkScene = new Scene(hyperLinkVBox, 500, 150);
        hyperLinkScene.getStylesheets().add(STYLE_SHEET_UI);
        hyperLinkStage.setScene(hyperLinkScene);
        hyperLinkLabel = new Label("Enter HyperLink:");
        hyperLinkTextField = new TextField();
        okCancelHBox = new HBox(10);
        okCancelHBox.setAlignment(Pos.BOTTOM_RIGHT);
        okButton = new Button("Ok");
        cancelButton = new Button("Cancel");
        okButton.setOnAction(e -> {
            hyperLinkTextField.setText(hyperLinkTextField.getText());
       //     temp = ui.getList().getSelectionModel().getSelectedItem();
            hl.setHyperLinkText(hyperLinkTextField.getText());
            hyperLinkLabel = new Label(temp + "And HyperLink");
            ui.getListData().remove(temp);
         //   ui.getListData().add(hyperLinkLabel);
            hyperLinkStage.close();
        });
        cancelButton.setOnAction(e -> {
            hyperLinkStage.close();
        });
        okCancelHBox.getChildren().addAll(okButton, cancelButton);
        hyperLinkVBox.getChildren().addAll(hyperLinkLabel, hyperLinkTextField, okCancelHBox);
        ui.setWindowIcon(ICON_FIRE, hyperLinkStage);
        hyperLinkStage.setTitle("Add HyperLink");
        hyperLinkStage.show();
    }

    public void displayEditHyperLinkDialog() {
        hyperLink1Stage = new Stage();
        hyperLink1VBox = new VBox();
        hyperLink1VBox.getStyleClass().add(CSS_CLASS_IMAGE_VBOX);
        okCancelHBox.setAlignment(Pos.BOTTOM_RIGHT);
        hyperLink1Scene = new Scene(hyperLink1VBox, 500, 150);
        hyperLink1Scene.getStylesheets().add(STYLE_SHEET_UI);
        hyperLink1Stage.setScene(hyperLink1Scene);
        hyperLink1VBox.getChildren().addAll(hyperLinkLabel, hyperLinkTextField, okCancelHBox);
        okButton.setOnAction(e -> {
            hyperLinkTextField.setText(hyperLinkTextField.getText());
            hyperLink1Stage.close();
        });
        cancelButton.setOnAction(e -> {
            hyperLink1Stage.close();
        });
        ui.setWindowIcon(ICON_FIRE, hyperLink1Stage);
        hyperLink1Stage.setTitle("Edit HyperLink");
        hyperLink1Stage.show();

    }

    public TextField getHyperLinkTF() {
        return hyperLinkTextField;
    }

    public Label getLabel() {
        return temp;
    }
}
