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
        okCancelHBox.getChildren().addAll(okButton, cancelButton);
        hyperLinkVBox.getChildren().addAll(hyperLinkLabel,hyperLinkTextField,okCancelHBox);
        ui.setWindowIcon(ICON_FIRE, hyperLinkStage);
        hyperLinkStage.setTitle("Add HyperLink");
        hyperLinkStage.show();
    }
}
