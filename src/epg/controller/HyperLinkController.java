/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.controller;

import static epg.StartupConstants.ICON_FIRE;
import epg.view.EPortfolioGeneratorView;
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
    String hyperLink;

    Stage hyperLink1Stage;
    Scene hyperLink1Scene;
    VBox hyperLink1VBox;

    public HyperLinkController(EPortfolioGeneratorView initUI) {
        ui = initUI;
    }

    public void displayAddHyperLinkDialog() {
        hyperLinkStage = new Stage();
        hyperLinkVBox = new VBox();
        hyperLinkScene = new Scene(hyperLinkVBox, 500, 300);
        hyperLinkStage.setScene(hyperLinkScene);
        hyperLinkLabel = new Label("Enter HyperLink:");
        hyperLinkTextField = new TextField();
        okCancelHBox = new HBox();
        okButton = new Button("Ok");
        cancelButton = new Button("Cancel");
        okButton.setOnAction(e -> {
            String temp = ui.getList().getSelectionModel().getSelectedItem();
            hyperLink = temp + " And HyperLink";
            ui.getListData().remove(temp);
            ui.getListData().add(hyperLink);
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
        hyperLink1Scene = new Scene(hyperLink1VBox, 500, 300);
        hyperLink1Stage.setScene(hyperLink1Scene);
        hyperLink1VBox.getChildren().addAll(hyperLinkLabel, hyperLinkTextField, okCancelHBox);
        okButton.setOnAction(e ->{
            hyperLink1Stage.close();
        });
        cancelButton.setOnAction(e ->{
            hyperLink1Stage.close();
        });
        ui.setWindowIcon(ICON_FIRE, hyperLink1Stage);
        hyperLink1Stage.setTitle("Edit HyperLink");
        hyperLink1Stage.show();

    }
}
