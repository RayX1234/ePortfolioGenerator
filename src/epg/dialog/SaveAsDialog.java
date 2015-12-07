/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.dialog;

import static epg.StartupConstants.ICON_FIRE;
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
public class SaveAsDialog {
    Stage saStage;
    Scene saScene;
    TextField saveNameTF;
    HBox saveNameHBox;
    Label saveNameLabel;
    Label spacingLabel;
    HBox ynHBox;
    Button yesButton;
    Button noButton;
    VBox saVBox;
    EPortfolioGeneratorView ui;
    
    public SaveAsDialog(EPortfolioGeneratorView initUI){
        ui = initUI;
    }
    
    public void showDialog(){
        saStage = new Stage();
        saVBox = new VBox();
        spacingLabel = new Label("   ");
        saVBox.setSpacing(20);
        saScene = new Scene(saVBox, 500, 150);
        ynHBox = new HBox();
        ynHBox.setAlignment(Pos.BOTTOM_RIGHT);
        ynHBox.setSpacing(20);
        yesButton = new Button("Ok");
        noButton = new Button("Cancel");
        ynHBox.getChildren().addAll(yesButton,noButton);
        saveNameHBox = new HBox();
        saveNameHBox.setSpacing(20);
        saveNameHBox.setAlignment(Pos.CENTER);
        saveNameLabel = new Label("Enter Title For JSON File:");
        saveNameTF = new TextField();
        saveNameHBox.getChildren().addAll(saveNameLabel, saveNameTF);
        saVBox.getChildren().addAll(spacingLabel,saveNameHBox,ynHBox);
        saStage.setScene(saScene);
        saStage.setTitle("Save As Portfolio");
        ui.setWindowIcon(ICON_FIRE, saStage);
        saStage.show();
    }
    
}
