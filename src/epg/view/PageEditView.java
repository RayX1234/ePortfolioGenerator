/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.view;

import static epg.PropertyType.TOOLTIP_ADD_IMAGE;
import static epg.PropertyType.TOOLTIP_ADD_SLIDESHOW;
import static epg.PropertyType.TOOLTIP_ADD_TEXT;
import static epg.PropertyType.TOOLTIP_ADD_TEXT_HYPERLINK;
import static epg.PropertyType.TOOLTIP_ADD_VIDEO;
import static epg.PropertyType.TOOLTIP_REMOVE_MAIN_LIST;
import static epg.StartupConstants.CSS_CLASS_ALIGN_CENTER;
import static epg.StartupConstants.CSS_CLASS_CONTENT_PANE;
import static epg.StartupConstants.CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON;
import static epg.StartupConstants.CSS_CLASS_LAYOUT_PANE;
import static epg.StartupConstants.CSS_CLASS_PTSNBI_PANE;
import static epg.StartupConstants.CSS_CLASS_SELECT_TEXT_TYPE;
import static epg.StartupConstants.CSS_CLASS_VERTICAL_TOOLBAR_BUTTON;
import static epg.StartupConstants.ICON_ADD_IMAGE;
import static epg.StartupConstants.ICON_ADD_SLIDESHOW;
import static epg.StartupConstants.ICON_ADD_TEXT;
import static epg.StartupConstants.ICON_ADD_TEXT_HYPERLINK;
import static epg.StartupConstants.ICON_ADD_VIDEO;
import static epg.StartupConstants.ICON_FIRE;
import static epg.StartupConstants.ICON_REMOVE_MAIN_LIST;
import static epg.StartupConstants.STYLE_SHEET_UI;
import epg.controller.BannerImageController;
import epg.controller.FileController;
import epg.controller.HyperLinkController;
import epg.controller.ImageController;
import epg.controller.PageEditController;
import epg.controller.SlideShowController;
import epg.controller.TextController;
import epg.controller.VideoController;
import epg.file.EPortfolioFileManager;
import epg.model.Component;
import epg.model.EPortfolioModel;
import epg.model.Page;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Ray
 */
public class PageEditView extends BorderPane {

    //For the main pane for the site pages
    BorderPane contentPane;

    //For the top of the contentPane(Page Title, Student Name, Banner Image)
    VBox ptsnbiPane;
    Label pageTitleLabel;
    Label studentNameLabel;
    Label bannerImageLabel;
    Label footerLabel;
    TextField pageTitleTextField;
    TextField studentNameTextField;
    TextField footerTextField;
    Button selectBIButton;

    //For selecting layout, color, and font
    HBox layoutPane;
    HBox colorPane;
    HBox fontPane;
    ToggleGroup layoutGroup;
    ToggleGroup colorGroup;
    ToggleGroup fontGroup;
    RadioButton layout1Button;
    RadioButton layout2Button;
    RadioButton layout3Button;
    RadioButton layout4Button;
    RadioButton layout5Button;
    RadioButton color1Button;
    RadioButton color2Button;
    RadioButton color3Button;
    RadioButton color4Button;
    RadioButton color5Button;
    RadioButton font1Button;
    RadioButton font2Button;
    RadioButton font3Button;
    RadioButton font4Button;
    RadioButton font5Button;

    Label layoutLabel;
    Label colorLabel;
    Label fontLabel;

    //CompoentPane
    FlowPane componentFlowPane;
    Button addTextButton;
    Button addImageButton;
    Button addSlideShowButton;
    Button addVideoButton;
    Button addTextHLButton;

    // For placing the components
    ScrollPane componentScrollPane;
    VBox componentVBox;

    FileController fileController;
    EPortfolioFileManager fileManager;
    PageEditController pageEditController;
    BannerImageController bannerImageController;
    TextController textController;
    ImageController imageController;
    VideoController videoController;
    SlideShowController slideShowController;
    HyperLinkController hyperLinkController;
    Page page;
    EPortfolioGeneratorView ui;

    //THIS IS FOR THE CENTER PANE TO SEE THE LISTVIEW OF COMPONENETS TO REMOVE ADD AND SELECT
    ScrollPane componentListScrollPane;
    BorderPane componentListBorderPane;
    VBox componentListVBox;
    Button removeComponentButton;
    ObservableList<Component> componentListData;
    ListView<Component> componentlist;
    VBox componentRemoveToolbar;

    //FOR SEEING IF WE WANT TO EDIT PARAGRAPH OR HYPERLINK
    Stage checkPHStage;
    Scene checkPHScene;
    VBox checkPHVBox;
    ComboBox checkPHComboBox;
    ObservableList<String> checkPHList;
    Label checkPHLabel;

    //General Uses
    Button okButton;
    Button cancelButton;
    HBox okCancelHBox;
    int count;
    int componentCount;

    public PageEditView(Page initPage, EPortfolioModel portfolioModel, EPortfolioGeneratorView initUI) {
        contentPane = new BorderPane();
        contentPane.getStyleClass().add(CSS_CLASS_CONTENT_PANE);
        ui = initUI;
        page = initPage;

        initTopAreaPane();
        initCenterAreaPane();
    }

    public void initTopAreaPane() {
        ptsnbiPane = new VBox();
        ptsnbiPane.getStyleClass().add(CSS_CLASS_PTSNBI_PANE);
        initBannerImage();
        initFooter();
        initLayoutPane();
        initColorPane();
        initFontPane();
        initComponentToolbar();
        initEventHandlers();
        ptsnbiPane.getChildren().add(componentFlowPane);
        contentPane.setTop(ptsnbiPane);

    }

    public void initCenterAreaPane() {
        componentListBorderPane = new BorderPane();
        componentListVBox = new VBox();
        componentListBorderPane.setCenter(componentListVBox);
        componentRemoveToolbar = new VBox();
        componentRemoveToolbar.getStyleClass().add(CSS_CLASS_ALIGN_CENTER);
        removeComponentButton = ui.initChildButton(componentRemoveToolbar, ICON_REMOVE_MAIN_LIST, TOOLTIP_REMOVE_MAIN_LIST, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, true);
        componentListBorderPane.setLeft(componentRemoveToolbar);
        componentlist = new ListView<>();
        componentListData = FXCollections.observableArrayList();
        if (page.getComponents().isEmpty()) {
            componentlist.setItems(componentListData);
        } else {
            componentlist.setItems(page.getComponents());
        }
        //page.setComponents(componentListData);
        componentListVBox.getChildren().add(componentlist);
        initContentEventHandlers();
        contentPane.setCenter(componentListBorderPane);
    }

    public void initBannerImage() {
        bannerImageLabel = new Label("Banner Image:");
        selectBIButton = new Button("Select");
        ptsnbiPane.getChildren().addAll(bannerImageLabel, selectBIButton);
        bannerImageController = new BannerImageController(ui);
        selectBIButton.setOnAction(e -> {
            bannerImageController.processSelectImage();
        });
    }

    public void initFooter() {
        footerLabel = new Label("Enter a Footer:");
        footerTextField = new TextField();
        if (page.getFooter() == null) {
            footerTextField.setText("");
        } else {
            footerTextField.setText(page.getFooter());
        }
        footerTextField.setOnAction(e -> {
            page.setFooter(footerTextField.getText());
        });

        ptsnbiPane.getChildren().addAll(footerLabel, footerTextField);
    }

    public void initLayoutPane() {
        layoutPane = new HBox();
        layoutPane.getStyleClass().add(CSS_CLASS_LAYOUT_PANE);
        ptsnbiPane.getChildren().add(layoutPane);
        layoutGroup = new ToggleGroup();
        layoutLabel = new Label("Select Layout:");
        layout1Button = new RadioButton("Layout 1");
        layout1Button.setToggleGroup(layoutGroup);
        layout2Button = new RadioButton("Layout 2");
        layout2Button.setToggleGroup(layoutGroup);
        layout3Button = new RadioButton("Layout 3");
        layout3Button.setToggleGroup(layoutGroup);
        layout4Button = new RadioButton("Layout 4");
        layout4Button.setToggleGroup(layoutGroup);
        layout5Button = new RadioButton("Layout 5");
        layout5Button.setToggleGroup(layoutGroup);

        updateLayout();
        initLayoutButtons();

        layoutPane.getChildren().addAll(layoutLabel, layout1Button, layout2Button, layout3Button, layout4Button, layout5Button);
    }

    public void initColorPane() {
        colorPane = new HBox();
        colorPane.getStyleClass().add(CSS_CLASS_LAYOUT_PANE);
        ptsnbiPane.getChildren().add(colorPane);
        colorGroup = new ToggleGroup();
        colorLabel = new Label("Select Color:");
        color1Button = new RadioButton("Color 1");
        color1Button.setToggleGroup(colorGroup);
        color2Button = new RadioButton("Color 2");
        color2Button.setToggleGroup(colorGroup);
        color3Button = new RadioButton("Color 3");
        color3Button.setToggleGroup(colorGroup);
        color4Button = new RadioButton("Color 4");
        color4Button.setToggleGroup(colorGroup);
        color5Button = new RadioButton("Color 5");
        color5Button.setToggleGroup(colorGroup);

        updateColor();
        initColorButtons();

        colorPane.getChildren().addAll(colorLabel, color1Button, color2Button, color3Button, color4Button, color5Button);
    }

    public void initFontPane() {
        fontPane = new HBox();
        fontPane.getStyleClass().add(CSS_CLASS_LAYOUT_PANE);
        ptsnbiPane.getChildren().add(fontPane);
        fontGroup = new ToggleGroup();
        fontLabel = new Label("Select Font:");
        font1Button = new RadioButton("Font 1");
        font1Button.setToggleGroup(fontGroup);
        font2Button = new RadioButton("Font 2");
        font2Button.setToggleGroup(fontGroup);
        font3Button = new RadioButton("Font 3");
        font3Button.setToggleGroup(fontGroup);
        font4Button = new RadioButton("Font 4");
        font4Button.setToggleGroup(fontGroup);
        font5Button = new RadioButton("Font 5");
        font5Button.setToggleGroup(fontGroup);

        updateFont();
        initFontButtons();

        fontPane.getChildren().addAll(fontLabel, font1Button, font2Button, font3Button, font4Button, font5Button);
    }

    public void initComponentToolbar() {
        componentFlowPane = new FlowPane();
        addTextButton = ui.initChildButton(componentFlowPane, ICON_ADD_TEXT, TOOLTIP_ADD_TEXT, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        addImageButton = ui.initChildButton(componentFlowPane, ICON_ADD_IMAGE, TOOLTIP_ADD_IMAGE, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        addSlideShowButton = ui.initChildButton(componentFlowPane, ICON_ADD_SLIDESHOW, TOOLTIP_ADD_SLIDESHOW, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        addVideoButton = ui.initChildButton(componentFlowPane, ICON_ADD_VIDEO, TOOLTIP_ADD_VIDEO, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        addTextHLButton = ui.initChildButton(componentFlowPane, ICON_ADD_TEXT_HYPERLINK, TOOLTIP_ADD_TEXT_HYPERLINK, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, true);
    }

    public void initContentEventHandlers() {
        componentlist.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                if (componentlist.getSelectionModel().getSelectedItem().toString().contains("Heading")) {
                    textController.displayEditHeadingDialog();
                }
                if (componentlist.getSelectionModel().getSelectedItem().toString().contains("Paragraph")) {
                    textController.displayEditParagraphDialog();
                }
                if (componentlist.getSelectionModel().getSelectedItem().toString().contains("Paragraph") && componentlist.getSelectionModel().getSelectedItem().toString().contains("HyperLink")) {
                    textController.getEditParagraphStage().close();
                    checkPH();
                }

                if (componentlist.getSelectionModel().getSelectedItem().toString().contains("List")) {
                    textController.displayEditListDialog();
                }
                if (componentlist.getSelectionModel().getSelectedItem().toString().contains("Image")) {
                    imageController.displayEditImageDialog();
                }
                if (componentlist.getSelectionModel().getSelectedItem().toString().contains("Slide Show")) {
                    slideShowController.displayEditSlideShowDialog();
                }
                if (componentlist.getSelectionModel().getSelectedItem().toString().contains("Video")) {
                    videoController.displayEditVideoDialog();
                }
            }
        });
        removeComponentButton.setOnAction(e -> {
            Component c = componentlist.getSelectionModel().getSelectedItem();
            page.getComponents().remove(c);
            componentListData.remove(c);
            page.setComponentsSize(Integer.toString(page.getComponents().size()));
            if (c.getH() != null) {
                page.getHeadings().remove(c.getH());
            }
            if (c.getP() != null) {
                page.getParagraphs().remove(c.getP());
            }
            if (c.getI() != null) {
                page.getImages().remove(c.getI());
            }
            if (c.getV() != null) {
                page.getVideos().remove(c.getV());
            }
//            if(page.getComponents().isEmpty()){
//                removeComponentButton.setDisable(true);
//            }
//            else{
//                removeComponentButton.setDisable(false);
//            }
//            if (componentListData.isEmpty()) {
//                removeComponentButton.setDisable(true);
//            }
//            else{
            removeComponentButton.setDisable(false);
//            }

        });
    }

    public void checkPH() {
        checkPHStage = new Stage();
        checkPHStage.setTitle("Edit (Paragraph Or HyperLink)");
        ui.setWindowIcon(ICON_FIRE, checkPHStage);
        checkPHVBox = new VBox();
        checkPHVBox.getStyleClass().add(CSS_CLASS_SELECT_TEXT_TYPE);
        checkPHScene = new Scene(checkPHVBox, 500, 150);
        checkPHScene.getStylesheets().add(STYLE_SHEET_UI);
        checkPHStage.setScene(checkPHScene);
        checkPHList = FXCollections.observableArrayList("Paragraph", "HyperLink");
        checkPHLabel = new Label("Edit (Paragraph Or HyperLink)");
        okButton = new Button("Ok");
        okButton.setDisable(true);
        cancelButton = new Button("Cancel");
        okCancelHBox = new HBox();
        okCancelHBox.setAlignment(Pos.BOTTOM_RIGHT);
        okCancelHBox.setSpacing(10);
        okCancelHBox.getChildren().addAll(okButton, cancelButton);
        checkPHComboBox = new ComboBox(checkPHList);
        checkPHComboBox.setOnAction(e -> {
            if (checkPHComboBox.getValue() != null) {
                okButton.setDisable(false);
            }
        });
        checkPHVBox.getChildren().addAll(checkPHLabel, checkPHComboBox, okCancelHBox);
        okButton.setOnAction(e -> {
            if (checkPHComboBox.getValue().equals("Paragraph")) {
                checkPHStage.close();
                textController.displayEditParagraphDialog();
            }
            if (checkPHComboBox.getValue().equals("HyperLink")) {
                checkPHStage.close();
                hyperLinkController.displayEditHyperLinkDialog();
            }
        });
        cancelButton.setOnAction(e -> {
            checkPHStage.close();
        });
        checkPHStage.show();
    }

    public BorderPane getContentPane() {
        return contentPane;
    }

    public ToggleGroup getLayoutGroup() {
        return layoutGroup;
    }

    public ToggleGroup getColorGroup() {
        return colorGroup;
    }

    public ToggleGroup getFontGroup() {
        return fontGroup;
    }

    public Button getTextButton() {
        return addTextButton;
    }

    public Button getImageButton() {
        return addImageButton;
    }

    public Button getVideoButton() {
        return addVideoButton;
    }

    public Button getSlideShowButton() {
        return addSlideShowButton;
    }

    public Button getHyperLinkButton() {
        return addTextHLButton;
    }

    public Button getRemoveComponentButton() {
        return removeComponentButton;
    }

    public ObservableList<Component> getListData() {
        return componentListData;
    }

    public ListView<Component> getList() {
        return componentlist;
    }

    public void initEventHandlers() {
        textController = new TextController(ui, this);
        addTextButton.setOnAction(e -> {
            textController.displaySelectTypeTextDialog();
        });

        imageController = new ImageController(ui, this);
        addImageButton.setOnAction(e -> {
            imageController.displayAddImageDialog();

        });

        videoController = new VideoController(ui, this);
        addVideoButton.setOnAction(e -> {
            videoController.displayAddVideoDialog();
        });

        slideShowController = new SlideShowController(ui, this);
        addSlideShowButton.setOnAction(e -> {
            slideShowController.displayAddSlideShowDialog();
        });

        hyperLinkController = new HyperLinkController(ui, this);
        addTextHLButton.setOnAction(e -> {
            if (componentlist.getSelectionModel().getSelectedItem().toString().contains("Paragraph")) {
                hyperLinkController.displayAddHyperLinkDialog();
            }
        });
    }

    public void initLayoutButtons() {
        initButton(layout1Button, "Layout");
        initButton(layout2Button, "Layout");
        initButton(layout3Button, "Layout");
        initButton(layout4Button, "Layout");
        initButton(layout5Button, "Layout");

    }

    public void initColorButtons() {
        initButton(color1Button, "Color");
        initButton(color2Button, "Color");
        initButton(color3Button, "Color");
        initButton(color4Button, "Color");
        initButton(color5Button, "Color");

    }

    public void initFontButtons() {
        initButton(font1Button, "Font");
        initButton(font2Button, "Font");
        initButton(font3Button, "Font");
        initButton(font4Button, "Font");
        initButton(font5Button, "Font");

    }

    public void initButton(RadioButton b, String s) {
        if (s.equals("Layout")) {
            b.setOnAction(e -> {
                page.setLayout(layoutGroup.getSelectedToggle().toString());
            });
        }
        if (s.equals("Color")) {
            b.setOnAction(e -> {
                page.setColor(colorGroup.getSelectedToggle().toString());
            });
        }
        if (s.equals("Font")) {
            b.setOnAction(e -> {
                page.setFont(fontGroup.getSelectedToggle().toString());
            });
        }

    }

    public void updateColor() {
        if (page.getColor() == null) {
            colorGroup.selectToggle(color1Button);
            page.setColor(color1Button.toString());
        } else {

            if (page.getColor().contains("Color 1")) {
                colorGroup.selectToggle(color1Button);
            }
            if (page.getColor().contains("Color 2")) {
                colorGroup.selectToggle(color2Button);
            }
            if (page.getColor().contains("Color 3")) {
                colorGroup.selectToggle(color3Button);
            }

            if (page.getColor().contains("Color 4")) {
                colorGroup.selectToggle(color4Button);
            }

            if (page.getColor().contains("Color 5")) {
                colorGroup.selectToggle(color5Button);
            }
        }
    }

    public void updateLayout() {
        if (page.getLayout() == null) {
            layoutGroup.selectToggle(layout1Button);
            page.setLayout(layout1Button.toString());
        } else {

            if (page.getLayout().contains("Layout 1")) {
                layoutGroup.selectToggle(layout1Button);
            }
            if (page.getLayout().contains("Layout 2")) {
                layoutGroup.selectToggle(layout2Button);
            }
            if (page.getLayout().contains("Layout 3")) {
                layoutGroup.selectToggle(layout3Button);
            }
            if (page.getLayout().contains("Layout 4")) {
                layoutGroup.selectToggle(layout4Button);
            }
            if (page.getLayout().contains("Layout 5")) {
                layoutGroup.selectToggle(layout5Button);
            }
        }
    }

    public void updateFont() {
        if (page.getFont() == null) {
            fontGroup.selectToggle(font1Button);
            page.setFont(font1Button.toString());
        } else {

            if (page.getFont().contains("Font 1")) {
                fontGroup.selectToggle(font1Button);
            }
            if (page.getFont().contains("Font 2")) {
                fontGroup.selectToggle(font2Button);
            }
            if (page.getFont().contains("Font 3")) {
                fontGroup.selectToggle(font3Button);
            }
            if (page.getFont().contains("Font 4")) {
                fontGroup.selectToggle(font4Button);
            }
            if (page.getFont().contains("Font 5")) {
                fontGroup.selectToggle(font5Button);
            }
        }
    }

    public Page getPage() {
        return page;
    }

    public SlideShowController getSSC() {
        return slideShowController;
    }

}
