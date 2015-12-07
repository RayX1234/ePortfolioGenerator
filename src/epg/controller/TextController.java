/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.controller;

import static epg.PropertyType.TOOLTIP_ADD_SITE;
import static epg.PropertyType.TOOLTIP_REMOVE_SITE;
import static epg.StartupConstants.CSS_CLASS_ALIGN_CENTER;
import static epg.StartupConstants.CSS_CLASS_IMAGE_VBOX;
import static epg.StartupConstants.CSS_CLASS_LAYOUT_PANE;
import static epg.StartupConstants.CSS_CLASS_SELECT_TEXT_TYPE;
import static epg.StartupConstants.CSS_CLASS_VERTICAL_TOOLBAR_BUTTON;
import static epg.StartupConstants.ICON_ADD_LIST;
import static epg.StartupConstants.ICON_FIRE;
import static epg.StartupConstants.ICON_REMOVE_LIST;
import static epg.StartupConstants.STYLE_SHEET_UI;
import epg.model.Component;
import epg.model.Heading;
import epg.model.ListModel;
import epg.model.ListObject;
import epg.model.Paragraph;
import epg.view.EPortfolioGeneratorView;
import epg.view.PageEditView;
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
    Label headingLabel;
    Label paragraphLabel;
    Label listLabel;

    //For the add heading dialog
    Stage addHeadingStage;
    Scene addHeadingScene;
    TextField HeadingTextField;
    VBox addHeadingVBox;

    //For the edit heading dialog
    Stage editHeadingStage;
    Scene editHeadingScene;
    VBox editHeadingVBox;

    //For the add paragraph dialog
    Stage addParagraphStage;
    Scene addParagraphScene;
    TextArea ParagraphTextArea;
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
    VBox editParagraphVBox;

    //For the add list dialog
    Stage addListStage;
    Scene addListScene;
    BorderPane addListBorderPane;
    VBox addListVBox;
    VBox addRemoveListVBox;
    ListView<ListObject> list;
    TextField ListTextField;
    Button addListButton;
    Button removeListButton;
    VBox listVBox;

    //For the edit list dialog
    Stage editListStage;
    Scene editListScene;
    BorderPane editListBorderPane;
    VBox editListVBox;
    VBox editlistVBox;

    Heading h;
    Paragraph p;
    ListObject l;
    Component c;
    ListModel listModel;
    PageEditView pageEditor;
    int count;

    public TextController(EPortfolioGeneratorView initUI, PageEditView initPageEditor) {
        ui = initUI;
        pageEditor = initPageEditor;
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
        okButton.setDisable(true);
        cancelButton = new Button("Cancel");
        addTextComboBox.setOnAction(e -> {
            if (addTextComboBox.getValue() != null) {
                okButton.setDisable(false);
            }
        });
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
        h = new Heading();
        c = new Component();
        c.setH(h);
        HeadingTextField = new TextField();
        addHeadingStage = new Stage();
        addHeadingStage.setTitle("Add Heading");
        ui.setWindowIcon(ICON_FIRE, addHeadingStage);
        addHeadingVBox = new VBox();
        addHeadingVBox.getStyleClass().add(CSS_CLASS_SELECT_TEXT_TYPE);
        enterContentLabel = new Label("Enter Content:");
        addHeadingScene = new Scene(addHeadingVBox, 500, 150);
        addHeadingScene.getStylesheets().add(STYLE_SHEET_UI);
        addHeadingStage.setScene(addHeadingScene);
        addHeadingVBox.getChildren().addAll(enterContentLabel, HeadingTextField, okCancelHBox);
        okButton.setOnAction(e -> {
            pageEditor.getRemoveComponentButton().setDisable(false);
            c.setHeading(true);
            h.setHeadingText(HeadingTextField.getText());
            pageEditor.getListData().add(c);
            if (ui.getPage() != null) {
                ui.getPage().getComponents().add(c);
                ui.getPage().getHeadings().add(h);
                int value = ui.getPage().getComponents().indexOf(c);
                h.setIndex(Integer.toString(value));
                ui.getPage().setComponentsSize(Integer.toString(ui.getPage().getComponents().size()));
            } else {
                pageEditor.getPage().getComponents().add(c);
                pageEditor.getPage().getHeadings().add(h);
                int value = pageEditor.getPage().getComponents().indexOf(c);
                h.setIndex(Integer.toString(value));
                pageEditor.getPage().setComponentsSize(Integer.toString(pageEditor.getPage().getComponents().size()));
            }
            addHeadingStage.close();
            ui.getSaveButton().setDisable(false);
        });
        cancelButton.setOnAction(e -> {
            addHeadingStage.close();
        });
        addHeadingStage.show();

    }

    public void displayEditHeadingDialog() {
        editHeadingStage = new Stage();
        editHeadingStage.setTitle("Edit Heading");
        Component temp = pageEditor.getList().getSelectionModel().getSelectedItem();
        checkHeadingTextFieldIsThere();
        HeadingTextField.setText(temp.getH().getHeadingText());

        ui.setWindowIcon(ICON_FIRE, editHeadingStage);
        editHeadingVBox = new VBox();
        editHeadingVBox.getStyleClass().add(CSS_CLASS_SELECT_TEXT_TYPE);
        editHeadingScene = new Scene(editHeadingVBox, 500, 150);
        editHeadingScene.getStylesheets().add(STYLE_SHEET_UI);
        editHeadingStage.setScene(editHeadingScene);
        editHeadingVBox.getChildren().addAll(enterContentLabel, HeadingTextField, okCancelHBox);
        okButton.setOnAction(e -> {
            temp.getH().setHeadingText(HeadingTextField.getText());
            editHeadingStage.close();
            ui.getSaveButton().setDisable(false);
        });
        cancelButton.setOnAction(e -> {
            editHeadingStage.close();
        });
        editHeadingStage.show();

    }

    public void displayAddParagraphDialog() {
        addParagraphStage = new Stage();
        Component a = new Component();
        p = new Paragraph();
        a.setP(p);
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
        ParagraphTextArea = new TextArea();
        addParagraphStage.setTitle("Add Paragraph");
        ui.setWindowIcon(ICON_FIRE, addParagraphStage);
        addParagraphScene.getStylesheets().add(STYLE_SHEET_UI);
        addParagraphVBox.getChildren().addAll(chooseFontLabel, fontHBox, enterContentLabel, ParagraphTextArea, okCancelHBox);
        addParagraphStage.setScene(addParagraphScene);
        okButton.setOnAction(e -> {
            pageEditor.getRemoveComponentButton().setDisable(false);
            if (fontGroup.getSelectedToggle() == null) {
                p.setFont(font1Button.toString());
            } else {
                p.setFont(fontGroup.getSelectedToggle().toString());
            }
            p.setParagraphText(ParagraphTextArea.getText());

            a.setParagraph(true);
            pageEditor.getListData().add(a);
            if (ui.getPage() != null) {
                ui.getPage().getComponents().add(a);
                ui.getPage().getParagraphs().add(p);
                int value = ui.getPage().getComponents().indexOf(a);
                p.setParagraphIndex(Integer.toString(value));
                ui.getPage().setComponentsSize(Integer.toString(ui.getPage().getComponents().size()));

            } else {
                pageEditor.getPage().getComponents().add(a);
                pageEditor.getPage().getParagraphs().add(p);
                int value = pageEditor.getPage().getComponents().indexOf(a);
                p.setParagraphIndex(Integer.toString(value));
                pageEditor.getPage().setComponentsSize(Integer.toString(pageEditor.getPage().getComponents().size()));
            }
            addParagraphStage.close();
            ui.getSaveButton().setDisable(false);
        });
        cancelButton.setOnAction(e -> {
            addParagraphStage.close();
        });
        addParagraphStage.show();

    }

    public void displayEditParagraphDialog() {
        editParagraphStage = new Stage();
        Component temp = pageEditor.getList().getSelectionModel().getSelectedItem();
        checkParagraphTextAreaIsThere();
        ParagraphTextArea.setText(temp.getP().getParagraphText());
        if (temp.getP().getFontToggle().contains("Font 1")) {
            fontGroup.selectToggle(font1Button);
        }
        if (temp.getP().getFontToggle().contains("Font 2")) {
            fontGroup.selectToggle(font2Button);
        }
        if (temp.getP().getFontToggle().contains("Font 3")) {
            fontGroup.selectToggle(font3Button);
        }
        if (temp.getP().getFontToggle().contains("Font 4")) {
            fontGroup.selectToggle(font4Button);
        }
        if (temp.getP().getFontToggle().contains("Font 5")) {
            fontGroup.selectToggle(font5Button);
        }
        editParagraphVBox = new VBox();
        editParagraphVBox.getStyleClass().add(CSS_CLASS_SELECT_TEXT_TYPE);
        editParagraphScene = new Scene(editParagraphVBox, 500, 300);
        editParagraphScene.getStylesheets().add(STYLE_SHEET_UI);
        editParagraphStage.setTitle("Edit Paragraph");
        ui.setWindowIcon(ICON_FIRE, editParagraphStage);
        editParagraphVBox.getChildren().addAll(chooseFontLabel, fontHBox, enterContentLabel, ParagraphTextArea, okCancelHBox);
        okButton.setOnAction(e -> {
            temp.getP().setFont(fontGroup.getSelectedToggle().toString());
            temp.getP().setParagraphText(ParagraphTextArea.getText());
            editParagraphStage.close();
            ui.getSaveButton().setDisable(false);
        });
        cancelButton.setOnAction(e -> {
            editParagraphStage.close();
        });
        editParagraphStage.setScene(editParagraphScene);
        editParagraphStage.show();
    }

    public void displayAddListDialog() {
        addListStage = new Stage();
        listModel = new ListModel();
        Component b = new Component();
        b.setL(listModel);
        ui.setWindowIcon(ICON_FIRE, addListStage);
        addListStage.setTitle("Add List");
        addListBorderPane = new BorderPane();
        addListBorderPane.getStyleClass().add(CSS_CLASS_IMAGE_VBOX);
        enterContentLabel = new Label("Enter Content:");
        addListScene = new Scene(addListBorderPane, 500, 300);
        addListStage.setScene(addListScene);
        addListScene.getStylesheets().add(STYLE_SHEET_UI);
        addListVBox = new VBox();
        ListTextField = new TextField();
        list = new ListView<>();
        list.setItems(listModel.getListData());
        addListVBox.getChildren().addAll(enterContentLabel, ListTextField);
        addListBorderPane.setTop(addListVBox);
        addListBorderPane.setBottom(okCancelHBox);
        addRemoveListVBox = new VBox();
        addRemoveListVBox.getStyleClass().add(CSS_CLASS_ALIGN_CENTER);
        addListButton = ui.initChildButton(addRemoveListVBox, ICON_ADD_LIST, TOOLTIP_ADD_SITE, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);
        removeListButton = ui.initChildButton(addRemoveListVBox, ICON_REMOVE_LIST, TOOLTIP_REMOVE_SITE, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);
        addListBorderPane.setLeft(addRemoveListVBox);
        addListButton.setOnAction(e -> {
            l = new ListObject();
            l.setListString(ListTextField.getText());
            listModel.getListData().add(l);
            ListTextField.clear();
        });
        removeListButton.setOnAction(e -> {
            list.getItems().remove(list.getSelectionModel().getSelectedItem());
        });
        okButton.setOnAction(e -> {
            pageEditor.getRemoveComponentButton().setDisable(false);
            b.setList(true);
            pageEditor.getListData().add(b);
            if (ui.getPage() != null) {
                ui.getPage().getComponents().add(b);
                ui.getPage().getLists().add(listModel);
                int value = ui.getPage().getComponents().indexOf(b);
                listModel.setListIndex(Integer.toString(value));
                ui.getPage().setComponentsSize(Integer.toString(ui.getPage().getComponents().size()));

            } else {
                pageEditor.getPage().getComponents().add(b);
                pageEditor.getPage().getLists().add(listModel);
                int value = pageEditor.getPage().getComponents().indexOf(b);
                listModel.setListIndex(Integer.toString(value));
                pageEditor.getPage().setComponentsSize(Integer.toString(pageEditor.getPage().getComponents().size()));

            }
            addListStage.close();
            ui.getSaveButton().setDisable(false);
        });
        cancelButton.setOnAction(e -> {
            addListStage.close();
        });
        listVBox = new VBox();

        listVBox.getChildren().add(list);
        addListBorderPane.setCenter(listVBox);
        addListStage.show();
    }

    public void displayEditListDialog() {
        editListStage = new Stage();
        Component temp = pageEditor.getList().getSelectionModel().getSelectedItem();
        checkListTextFieldIsThere();
        list.setItems(temp.getL().getListData());
        ui.setWindowIcon(ICON_FIRE, editListStage);
        editListStage.setTitle("Edit List");
        editListBorderPane = new BorderPane();
        enterContentLabel = new Label("Enter Content:");
        editListScene = new Scene(editListBorderPane, 500, 300);
        editListStage.setScene(editListScene);
        editListScene.getStylesheets().add(STYLE_SHEET_UI);
        editListVBox = new VBox();
        editListVBox.getChildren().addAll(enterContentLabel, ListTextField);
        editListBorderPane.setTop(editListVBox);
        editListBorderPane.setBottom(okCancelHBox);
        addRemoveListVBox = new VBox();
        addRemoveListVBox.getStyleClass().add(CSS_CLASS_ALIGN_CENTER);
        addListButton = ui.initChildButton(addRemoveListVBox, ICON_ADD_LIST, TOOLTIP_ADD_SITE, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);
        removeListButton = ui.initChildButton(addRemoveListVBox, ICON_REMOVE_LIST, TOOLTIP_REMOVE_SITE, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);
        editListBorderPane.setLeft(addRemoveListVBox);
        addListButton.setOnAction(e -> {
            ListObject l = new ListObject();
            l.setListString(ListTextField.getText());
            temp.getL().getListData().add(l);
            ListTextField.clear();
        });
        removeListButton.setOnAction(e -> {
            list.getItems().remove(list.getSelectionModel().getSelectedItem());
        });
        okButton.setOnAction(e -> {

            editListStage.close();
            ui.getSaveButton().setDisable(false);
        });
        cancelButton.setOnAction(e -> {
            editListStage.close();
        });
        editlistVBox = new VBox();
        editlistVBox.getChildren().add(list);
        editListBorderPane.setCenter(editlistVBox);
        editListStage.show();
    }

    public Stage getEditParagraphStage() {
        return editParagraphStage;
    }

    public void checkHeadingTextFieldIsThere() {
        if (HeadingTextField == null) {
            HeadingTextField = new TextField();
            initOkCancelHBox();
            enterContentLabel = new Label("Enter Content:");
        }
    }

    public void initOkCancelHBox() {
        okCancelHBox = new HBox(10);
        okCancelHBox.setAlignment(Pos.CENTER_RIGHT);
        okButton = new Button("Ok");
        cancelButton = new Button("Cancel");
        okCancelHBox.getChildren().addAll(okButton, cancelButton);
    }

    public void checkParagraphTextAreaIsThere() {
        if (ParagraphTextArea == null) {
            ParagraphTextArea = new TextArea();
            initOkCancelHBox();
            fontHBox = new HBox();
            fontGroup = new ToggleGroup();
            font1Button = new RadioButton("Font 1");
            font2Button = new RadioButton("Font 2");
            font3Button = new RadioButton("Font 3");
            font4Button = new RadioButton("Font 4");
            font5Button = new RadioButton("Font 5");
            chooseFontLabel = new Label("Choose A Font:");
            font1Button.setToggleGroup(fontGroup);
            font2Button.setToggleGroup(fontGroup);
            font3Button.setToggleGroup(fontGroup);
            font4Button.setToggleGroup(fontGroup);
            font5Button.setToggleGroup(fontGroup);
            fontHBox.getStyleClass().add(CSS_CLASS_LAYOUT_PANE);
            fontHBox.getChildren().addAll(font1Button, font2Button, font3Button, font4Button, font5Button);
        }
    }

    public void checkListTextFieldIsThere() {
        if (ListTextField == null) {
            ListTextField = new TextField();
            list = new ListView<>();
            initOkCancelHBox();

        }
    }
}
