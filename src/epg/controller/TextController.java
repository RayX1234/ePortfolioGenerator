/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.controller;

import static epg.PropertyType.TOOLTIP_ADD_SITE;
import static epg.PropertyType.TOOLTIP_REMOVE_SITE;
import static epg.StartupConstants.CSS_CLASS_ALIGN_CENTER;
import static epg.StartupConstants.CSS_CLASS_LAYOUT_PANE;
import static epg.StartupConstants.CSS_CLASS_SELECT_TEXT_TYPE;
import static epg.StartupConstants.CSS_CLASS_VERTICAL_TOOLBAR_BUTTON;
import static epg.StartupConstants.ICON_ADD_LIST;
import static epg.StartupConstants.ICON_FIRE;
import static epg.StartupConstants.ICON_REMOVE_LIST;
import static epg.StartupConstants.STYLE_SHEET_UI;
import epg.view.EPortfolioGeneratorView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
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
    HBox fontHBox;
    ToggleGroup fontGroup;
    RadioButton font1Button;
    RadioButton font2Button;
    RadioButton font3Button;
    RadioButton font4Button;
    RadioButton font5Button;
    Label chooseFontLabel;

    //For the edit paragraph dialog
    Stage editParagraphStage;
    Scene editParagraphScene;
    TextArea editParagraphTextArea;
    VBox editParagraphVBox;

    //For the add list dialog
    Stage addListStage;
    Scene addListScene;
    ObservableList<String> listData;
    BorderPane addListBorderPane;
    VBox addListVBox;
    VBox addRemoveListVBox;
    ListView<String> list;
    TextField addListTextField;
    Button addListButton;
    Button removeListButton;
    VBox listVBox;
    
    //For the edit list dialog
    Stage editListStage;
    Scene editListScene;
    BorderPane editListBorderPane;
    VBox editListVBox;
    TextField editListTextField;
    VBox editlistVBox;

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
            if (addTextComboBox.getValue().equals("List")) {
                displayAddListDialog();
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
        fontHBox = new HBox();
        fontGroup = new ToggleGroup();
        font1Button = new RadioButton("Font 1");
        font2Button = new RadioButton("Font 2");
        font3Button = new RadioButton("Font 3");
        font4Button = new RadioButton("Font 4");
        font5Button = new RadioButton("Font 5");
        font1Button.setToggleGroup(fontGroup);
        font2Button.setToggleGroup(fontGroup);
        font3Button.setToggleGroup(fontGroup);
        font4Button.setToggleGroup(fontGroup);
        font5Button.setToggleGroup(fontGroup);
        fontHBox.getChildren().addAll(font1Button, font2Button, font3Button, font4Button, font5Button);
        fontHBox.getStyleClass().add(CSS_CLASS_LAYOUT_PANE);
        chooseFontLabel = new Label("Choose A Font:");
        addParagraphVBox.getStyleClass().add(CSS_CLASS_SELECT_TEXT_TYPE);
        addParagraphScene = new Scene(addParagraphVBox, 500, 300);
        addParagraphTextArea = new TextArea();
        addParagraphStage.setTitle("Add Paragraph");
        ui.setWindowIcon(ICON_FIRE, addParagraphStage);
        addParagraphScene.getStylesheets().add(STYLE_SHEET_UI);
        addParagraphVBox.getChildren().addAll(chooseFontLabel, fontHBox, enterContentLabel, addParagraphTextArea, okCancelHBox);
        addParagraphStage.setScene(addParagraphScene);
        okButton.setOnAction(e -> {
            addParagraphStage.close();
        });
        cancelButton.setOnAction(e -> {
            addParagraphStage.close();
        });
        addParagraphStage.show();

    }

    public void displayEditParagraphDialog() {
        editParagraphStage = new Stage();
        editParagraphVBox = new VBox();
        editParagraphScene = new Scene(editParagraphVBox, 500, 300);
        editParagraphStage.setTitle("Edit Paragraph");
        ui.setWindowIcon(ICON_FIRE, editParagraphStage);
        editParagraphTextArea = new TextArea();
        editParagraphVBox.getChildren().addAll(chooseFontLabel, fontHBox, enterContentLabel, editParagraphTextArea, okCancelHBox);
        okButton.setOnAction(e -> {
            editParagraphStage.close();
        });
        cancelButton.setOnAction(e -> {
            editParagraphStage.close();
        });
        editParagraphStage.setScene(editParagraphScene);
        editParagraphStage.show();
    }

    public void displayAddListDialog() {
        addListStage = new Stage();
        ui.setWindowIcon(ICON_FIRE, addListStage);
        addListStage.setTitle("Add List");
        addListBorderPane = new BorderPane();
        enterContentLabel = new Label("Enter Content:");
        addListScene = new Scene(addListBorderPane, 500, 300);
        addListStage.setScene(addListScene);
        addListScene.getStylesheets().add(STYLE_SHEET_UI);
        addListVBox = new VBox();
        addListTextField = new TextField();
        listData = FXCollections.observableArrayList();
        addListVBox.getChildren().addAll(enterContentLabel, addListTextField);
        addListBorderPane.setTop(addListVBox);
        addListBorderPane.setBottom(okCancelHBox);
        addRemoveListVBox = new VBox();
        addRemoveListVBox.getStyleClass().add(CSS_CLASS_ALIGN_CENTER);
        addListButton = ui.initChildButton(addRemoveListVBox, ICON_ADD_LIST, TOOLTIP_ADD_SITE, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);
        removeListButton = ui.initChildButton(addRemoveListVBox, ICON_REMOVE_LIST, TOOLTIP_REMOVE_SITE, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);
        addListBorderPane.setLeft(addRemoveListVBox);
        addListButton.setOnAction(e -> {
            listData.add(addListTextField.getText());
            addListTextField.clear();
        });
        removeListButton.setOnAction(e -> {
            list.getItems().remove(list.getSelectionModel().getSelectedItem());
        });
        okButton.setOnAction(e -> {
            addListStage.close();
        });
        cancelButton.setOnAction(e -> {
            addListStage.close();
        });
        listVBox = new VBox();
        list = new ListView<>();
        list.setItems(listData);
        listVBox.getChildren().add(list);
        addListBorderPane.setCenter(listVBox);
        addListStage.show();
    }
    
    public void displayEditListDialog(){
        editListStage = new Stage();
        ui.setWindowIcon(ICON_FIRE, editListStage);
        editListStage.setTitle("Edit List");
        editListBorderPane = new BorderPane();
        enterContentLabel = new Label("Enter Content:");
        editListScene = new Scene(editListBorderPane, 500, 300);
        editListStage.setScene(editListScene);
        editListScene.getStylesheets().add(STYLE_SHEET_UI);
        editListVBox = new VBox();
        editListTextField = new TextField();
        editListVBox.getChildren().addAll(enterContentLabel, editListTextField);
        editListBorderPane.setTop(editListVBox);
        editListBorderPane.setBottom(okCancelHBox);
        addRemoveListVBox = new VBox();
        addRemoveListVBox.getStyleClass().add(CSS_CLASS_ALIGN_CENTER);
        addListButton = ui.initChildButton(addRemoveListVBox, ICON_ADD_LIST, TOOLTIP_ADD_SITE, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);
        removeListButton = ui.initChildButton(addRemoveListVBox, ICON_REMOVE_LIST, TOOLTIP_REMOVE_SITE, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);
        editListBorderPane.setLeft(addRemoveListVBox);
        addListButton.setOnAction(e -> {
            listData.add(editListTextField.getText());
            editListTextField.clear();
        });
        removeListButton.setOnAction(e -> {
            list.getItems().remove(list.getSelectionModel().getSelectedItem());
        });
        okButton.setOnAction(e -> {
            addListStage.close();
        });
        cancelButton.setOnAction(e -> {
            addListStage.close();
        });
        editlistVBox = new VBox();
        list = new ListView<>();
        list.setItems(listData);
        editlistVBox.getChildren().add(list);
        editListBorderPane.setCenter(editlistVBox);
        editListStage.show();
    }
}
