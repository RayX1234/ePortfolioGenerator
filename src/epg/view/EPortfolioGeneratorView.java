/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.view;

import epg.PropertyType;
import static epg.PropertyType.TOOLTIP_ADD_IMAGE;
import static epg.PropertyType.TOOLTIP_ADD_SITE;
import static epg.PropertyType.TOOLTIP_ADD_SLIDESHOW;
import static epg.PropertyType.TOOLTIP_ADD_TEXT;
import static epg.PropertyType.TOOLTIP_ADD_TEXT_HYPERLINK;
import static epg.PropertyType.TOOLTIP_ADD_VIDEO;
import static epg.PropertyType.TOOLTIP_CHANGE_SITE_NAME;
import static epg.PropertyType.TOOLTIP_EXIT;
import static epg.PropertyType.TOOLTIP_EXPORT_PORTFOLIO;
import static epg.PropertyType.TOOLTIP_LOAD_PORTFOLIO;
import static epg.PropertyType.TOOLTIP_NEW_PORTFOLIO;
import static epg.PropertyType.TOOLTIP_PAGE_EDIT_WORKSPACE;
import static epg.PropertyType.TOOLTIP_REMOVE_SITE;
import static epg.PropertyType.TOOLTIP_SAVE_AS_PORTFOLIO;
import static epg.PropertyType.TOOLTIP_SAVE_PORTFOLIO;
import static epg.PropertyType.TOOLTIP_SITE_VIEWER_WORKSPACE;
import static epg.StartupConstants.CSS_CLASS_CONTENT_PANE;
import static epg.StartupConstants.CSS_CLASS_CSN_GRID_PANE;
import static epg.StartupConstants.CSS_CLASS_EPG_PANE;
import static epg.StartupConstants.CSS_CLASS_FILE_TOOL_BAR_PANE;
import static epg.StartupConstants.CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON;
import static epg.StartupConstants.CSS_CLASS_LCF_PANE;
import static epg.StartupConstants.CSS_CLASS_PAGE_EDIT_WORKSPACE_PANE;
import static epg.StartupConstants.CSS_CLASS_PTSNBI_PANE;
import static epg.StartupConstants.CSS_CLASS_SITES_TAB_PANE;
import static epg.StartupConstants.CSS_CLASS_SITES_TOOL_BAR_PANE;
import static epg.StartupConstants.CSS_CLASS_SITE_VIEWER_WORKSPACE_PANE;
import static epg.StartupConstants.CSS_CLASS_VERTICAL_TOOLBAR_BUTTON;
import static epg.StartupConstants.CSS_CLASS_WORKSPACE_MODE_TOOLBAR_PANE;
import static epg.StartupConstants.ICON_ADD_IMAGE;
import static epg.StartupConstants.ICON_ADD_SITE;
import static epg.StartupConstants.ICON_ADD_SLIDESHOW;
import static epg.StartupConstants.ICON_ADD_TEXT;
import static epg.StartupConstants.ICON_ADD_TEXT_HYPERLINK;
import static epg.StartupConstants.ICON_ADD_VIDEO;
import static epg.StartupConstants.ICON_CHANGE_SITE_NAME;
import static epg.StartupConstants.ICON_EXIT;
import static epg.StartupConstants.ICON_EXPORT_PORTFOLIO;
import static epg.StartupConstants.ICON_FIRE;
import static epg.StartupConstants.ICON_LOAD_PORTFOLIO;
import static epg.StartupConstants.ICON_NEW_PORTFOLIO;
import static epg.StartupConstants.ICON_PAGE_EDIT_WORKSPACE;
import static epg.StartupConstants.ICON_REMOVE_SITE;
import static epg.StartupConstants.ICON_SAVE_AS_PORTFOLIO;
import static epg.StartupConstants.ICON_SAVE_PORTFOLIO;
import static epg.StartupConstants.ICON_SITE_VIEWER_WORKSPACE;
import static epg.StartupConstants.PATH_ICONS;
import static epg.StartupConstants.STYLE_SHEET_UI;
import epg.controller.BannerImageController;
import epg.controller.FileController;
import epg.controller.PageEditController;
import epg.file.EPortfolioFileManager;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;

/**
 *
 * @author Ray
 */
public class EPortfolioGeneratorView {

    //Main application UI window and scene graph
    Stage primaryStage;
    Scene primaryScene;

    //General Uses
    Button okButton;
    Button cancelButton;

    //The main pane for the GUI
    BorderPane epgPane;

    //For the file toolbar controls
    FlowPane fileToolbarPane;
    Button newPortfolioButton;
    Button loadPortfolioButton;
    Button savePortfolioButton;
    Button saveAsPortfolioButton;
    Button exportPortfolioButton;
    Button exitButton;
    Label ePortfolioTitleLabel;
    TextField ePortfolioTitleTF;

    //For the pageEditWorkspace
    BorderPane pageEditWorkspace;
    TabPane sitesTabPane;
    Scene pageEditScene;
    Boolean pageEditWorkspaceActivated;

    //For the siteviewer workspace
    BorderPane siteViewerWorkspace;
    Scene siteViewerScene;
    Boolean siteViewerWorkspaceActivated;

    //For the workspaceModeToolbar
    FlowPane workspaceModeToolbar;
    Button pageEditWorkSpaceButton;
    Button siteViewerWorkspaceButton;

    //For the site toolbar controls
    VBox siteToolbarPane;
    Button addSitePageButton;
    Button removeSitePageButton;
    Button changeSiteNameButton;

    //For the changeSiteName dialog
    GridPane siteNameGridPane;
    Stage siteNameStage;
    Scene siteNameScene;
    TextField siteNameTextField;
    Label siteNameLabel;
    String siteName;

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
    HBox lcfPane;
    Label layoutLabel;
    Label colorLabel;
    Label fontLabel;
    ComboBox layoutComboBox;
    ComboBox colorComboBox;
    ComboBox fontComboBox;
    
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

    //Default Constructor
    public EPortfolioGeneratorView(EPortfolioFileManager initFileManager) {

    }

    //initalizing the fileToolbar Buttons
    private void initFileToolbar() {
        fileToolbarPane = new FlowPane();
        fileToolbarPane.getStyleClass().add(CSS_CLASS_FILE_TOOL_BAR_PANE);

        //creating the filetoolbar buttons ENABLED is false while Disabled is true
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        newPortfolioButton = initChildButton(fileToolbarPane, ICON_NEW_PORTFOLIO, TOOLTIP_NEW_PORTFOLIO, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        loadPortfolioButton = initChildButton(fileToolbarPane, ICON_LOAD_PORTFOLIO, TOOLTIP_LOAD_PORTFOLIO, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        savePortfolioButton = initChildButton(fileToolbarPane, ICON_SAVE_PORTFOLIO, TOOLTIP_SAVE_PORTFOLIO, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        saveAsPortfolioButton = initChildButton(fileToolbarPane, ICON_SAVE_AS_PORTFOLIO, TOOLTIP_SAVE_AS_PORTFOLIO, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        exportPortfolioButton = initChildButton(fileToolbarPane, ICON_EXPORT_PORTFOLIO, TOOLTIP_EXPORT_PORTFOLIO, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        exitButton = initChildButton(fileToolbarPane, ICON_EXIT, TOOLTIP_EXIT, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
    }

    private void reset() {
        fileToolbarPane.getChildren().clear();
        sitesTabPane.getTabs().clear();
        newPortfolioButton = initChildButton(fileToolbarPane, ICON_NEW_PORTFOLIO, TOOLTIP_NEW_PORTFOLIO, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        loadPortfolioButton = initChildButton(fileToolbarPane, ICON_LOAD_PORTFOLIO, TOOLTIP_LOAD_PORTFOLIO, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        savePortfolioButton = initChildButton(fileToolbarPane, ICON_SAVE_PORTFOLIO, TOOLTIP_SAVE_PORTFOLIO, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        saveAsPortfolioButton = initChildButton(fileToolbarPane, ICON_SAVE_AS_PORTFOLIO, TOOLTIP_SAVE_AS_PORTFOLIO, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        exportPortfolioButton = initChildButton(fileToolbarPane, ICON_EXPORT_PORTFOLIO, TOOLTIP_EXPORT_PORTFOLIO, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        exitButton = initChildButton(fileToolbarPane, ICON_EXIT, TOOLTIP_EXIT, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        ePortfolioTitleLabel = new Label("Enter A Title:");
        ePortfolioTitleTF = new TextField();
        fileToolbarPane.getChildren().addAll(ePortfolioTitleLabel, ePortfolioTitleTF);
        initEventHandlers();

    }

    //initalizing the sitetoolbar
    private void initSiteToolbar() {
        siteToolbarPane = new VBox();
        siteToolbarPane.getStyleClass().add(CSS_CLASS_SITES_TOOL_BAR_PANE);
        addSitePageButton = initChildButton(siteToolbarPane, ICON_ADD_SITE, TOOLTIP_ADD_SITE, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);
        removeSitePageButton = initChildButton(siteToolbarPane, ICON_REMOVE_SITE, TOOLTIP_REMOVE_SITE, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);
        changeSiteNameButton = initChildButton(siteToolbarPane, ICON_CHANGE_SITE_NAME, TOOLTIP_CHANGE_SITE_NAME, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, true);
    }

    //initalizing the pageEditWorkspace
    private void initPageEditWorkspace() {
        pageEditWorkspace = new BorderPane();
        pageEditWorkspace.getStyleClass().add(CSS_CLASS_PAGE_EDIT_WORKSPACE_PANE);
        sitesTabPane = new TabPane();
        sitesTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        sitesTabPane.getStyleClass().add(CSS_CLASS_SITES_TAB_PANE);
        Tab tab = new Tab();
        pageEditScene = new Scene(pageEditWorkspace);
        pageEditScene.getStylesheets().add(STYLE_SHEET_UI);

    }

    //activate the pageEditWorkspace
    private void activatePEW() {
        pageEditWorkspace.setTop(fileToolbarPane);
        pageEditWorkspace.setLeft(siteToolbarPane);
        pageEditWorkspace.setCenter(sitesTabPane);
        pageEditWorkspace.setBottom(workspaceModeToolbar);
        primaryStage.setScene(pageEditScene);

    }

    private void initSiteViewerWorkspace() {
        siteViewerWorkspace = new BorderPane();
        siteViewerScene = new Scene(siteViewerWorkspace);
        siteViewerScene.getStylesheets().add(STYLE_SHEET_UI);
        siteViewerWorkspace.getStyleClass().add(CSS_CLASS_SITE_VIEWER_WORKSPACE_PANE);

    }

    private void activateSVW() {
        siteViewerWorkspace.setBottom(workspaceModeToolbar);
        siteViewerWorkspace.setTop(fileToolbarPane);
        primaryStage.setScene(siteViewerScene);
    }

    public void initWorkspaceModeToolbar() {
        workspaceModeToolbar = new FlowPane();
        workspaceModeToolbar.getStyleClass().add(CSS_CLASS_WORKSPACE_MODE_TOOLBAR_PANE);
        pageEditWorkSpaceButton = initChildButton(workspaceModeToolbar, ICON_PAGE_EDIT_WORKSPACE, TOOLTIP_PAGE_EDIT_WORKSPACE, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        siteViewerWorkspaceButton = initChildButton(workspaceModeToolbar, ICON_SITE_VIEWER_WORKSPACE, TOOLTIP_SITE_VIEWER_WORKSPACE, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);

    }

    private void initWindow(String windowTitle) {
        setWindowIcon(ICON_FIRE, primaryStage);
        // SET THE WINDOW TITLE
        primaryStage.setTitle(windowTitle);

        // GET THE SIZE OF THE SCREEN
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        // AND USE IT TO SIZE THE WINDOW
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());

        // SETUP THE UI, NOTE WE'LL ADD THE WORKSPACE LATER
        epgPane = new BorderPane();
        epgPane.getStyleClass().add(CSS_CLASS_EPG_PANE);
        epgPane.setTop(fileToolbarPane);
        primaryScene = new Scene(epgPane);

        // NOW TIE THE SCENE TO THE WINDOW, SELECT THE STYLESHEET
        // WE'LL USE TO STYLIZE OUR GUI CONTROLS, AND OPEN THE WINDOW
        primaryScene.getStylesheets().add(STYLE_SHEET_UI);
        primaryStage.setScene(primaryScene);

        primaryStage.show();

    }

    public void isPEWActivated() {
        if (pageEditWorkspaceActivated == true) {
            pageEditWorkSpaceButton.setDisable(true);
            siteViewerWorkspaceButton.setDisable(false);
        }
    }

    public void isSVWActivated() {
        if (siteViewerWorkspaceActivated == true) {
            siteViewerWorkspaceButton.setDisable(true);
            pageEditWorkSpaceButton.setDisable(false);
        }
    }

    public void initEventHandlers() {
        fileController = new FileController(this, fileManager);

        newPortfolioButton.setOnAction(e -> {
            reset();
            activatePEW();
            pageEditWorkspaceActivated = true;
            isPEWActivated();
            removeSitePageButton.setDisable(true);
        });

        pageEditController = new PageEditController(this);

        addSitePageButton.setOnAction(e -> {
            pageEditController.processAddSiteRequest();

        });

        removeSitePageButton.setOnAction(e -> {
            pageEditController.processRemoveSiteRequest();

        });

        changeSiteNameButton.setOnAction(e -> {
            pageEditController.processChangeNameSiteRequest();
        });

        ///For the workspace mode toolbar
        pageEditWorkSpaceButton.setOnAction(e -> {
            pageEditWorkspaceActivated = true;
            isPEWActivated();
            activatePEW();
        });

        siteViewerWorkspaceButton.setOnAction(e -> {
            siteViewerWorkspaceActivated = true;
            isSVWActivated();
            activateSVW();
        });

    }

    //Create site page
    public void createSitePage() {
        Tab tab = new Tab();
        tab.setText("new tab");
        tab.setContent(initContentPane());
        sitesTabPane.getTabs().add(tab);
        changeSiteNameButton.setDisable(false);
        removeSitePageButton.setDisable(false);
    }

    //Pane for all the componeents of a sitepage
    public Pane initContentPane() {
        contentPane = new BorderPane();
        contentPane.getStyleClass().add(CSS_CLASS_CONTENT_PANE);
        initTopAreaPane();
        return contentPane;
    }
    
    //For the buttons of video,image,slideshow,text annd hyperlink
    public void initComponentToolbar(){
        componentFlowPane = new FlowPane();
        addTextButton = initChildButton(componentFlowPane, ICON_ADD_TEXT, TOOLTIP_ADD_TEXT, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        addImageButton = initChildButton(componentFlowPane, ICON_ADD_IMAGE, TOOLTIP_ADD_IMAGE, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        addSlideShowButton = initChildButton(componentFlowPane, ICON_ADD_SLIDESHOW, TOOLTIP_ADD_SLIDESHOW, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        addVideoButton = initChildButton(componentFlowPane, ICON_ADD_VIDEO, TOOLTIP_ADD_VIDEO, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        addTextHLButton = initChildButton(componentFlowPane, ICON_ADD_TEXT_HYPERLINK, TOOLTIP_ADD_TEXT_HYPERLINK, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        ptsnbiPane.getChildren().add(componentFlowPane);
        
    }   

    //For the pageTitle, studentName, bannerImage, layout, color, and page font
    public void initTopAreaPane() {
        ptsnbiPane = new VBox();
        ptsnbiPane.getStyleClass().add(CSS_CLASS_PTSNBI_PANE);
        initPageTitle();
        initStudentName();
        initBannerImage();
        initFooter();
        initlcfPane();
        initComponentToolbar();
        contentPane.setTop(ptsnbiPane);
    }

    //For selecting layout, color, and font
    public void initlcfPane() {
        lcfPane = new HBox();
        lcfPane.getStyleClass().add(CSS_CLASS_LCF_PANE);
        ptsnbiPane.getChildren().add(lcfPane);
        layoutLabel = new Label("Select Layout:");
        colorLabel = new Label("Select Color:");
        fontLabel = new Label("Select Font:");
        layoutComboBox = new ComboBox();
        layoutComboBox.getItems().addAll("Layout 1", "Layout 2", "Layout 3", "Layout 4", "Layout 5");
        colorComboBox = new ComboBox();
        colorComboBox.getItems().addAll("Color 1", "Color 2", "Color 3", "Color 4", "Color 5");
        fontComboBox = new ComboBox();
        fontComboBox.getItems().addAll("Font 1", "Font 2", "Font 3", "Font 4", "Font 5");
        lcfPane.getChildren().addAll(layoutLabel, layoutComboBox, colorLabel, colorComboBox, fontLabel, fontComboBox);

    }

    //For the navbar title
    public void initPageTitle() {
        pageTitleLabel = new Label("Enter a Page Title:");
        pageTitleTextField = new TextField();
        ptsnbiPane.getChildren().addAll(pageTitleLabel, pageTitleTextField);
    }

    //For the studentName
    public void initStudentName() {
        studentNameLabel = new Label("Enter a Student Name:");
        studentNameTextField = new TextField();
        ptsnbiPane.getChildren().addAll(studentNameLabel, studentNameTextField);
    }

    //For the bannerImage
    public void initBannerImage() {

        bannerImageLabel = new Label("Banner Image:");
        selectBIButton = new Button("Select");
        ptsnbiPane.getChildren().addAll(bannerImageLabel, selectBIButton);
        bannerImageController = new BannerImageController();
        selectBIButton.setOnAction(e -> {
            bannerImageController.processSelectImage();
        });
    }

    //For the footer
    public void initFooter() {
        footerLabel = new Label("Enter a Footer:");
        footerTextField = new TextField();
        ptsnbiPane.getChildren().addAll(footerLabel, footerTextField);
    }

    //Remove site page
    public void removeSitePage() {
        Tab selectedTab = sitesTabPane.getSelectionModel().getSelectedItem();
        sitesTabPane.getTabs().remove(selectedTab);
        if (sitesTabPane.getTabs().isEmpty()) {
            changeSiteNameButton.setDisable(true);
            removeSitePageButton.setDisable(true);
        }

    }

    //Change site name
    public void updateSiteNameDialog() {
        siteNameGridPane = new GridPane();
        siteNameGridPane.getStyleClass().add(CSS_CLASS_CSN_GRID_PANE);
        siteNameLabel = new Label("Enter a Site Name:");
        okButton = new Button("Ok");
        cancelButton = new Button("Cancel");
        siteNameTextField = new TextField();
        siteNameStage = new Stage();
        setWindowIcon(ICON_FIRE, siteNameStage);
        siteNameScene = new Scene(siteNameGridPane, 400, 200);
        siteNameScene.getStylesheets().add(STYLE_SHEET_UI);
        siteNameGridPane.add(siteNameLabel, 0, 0, 4, 1);
        siteNameGridPane.add(siteNameTextField, 0, 1, 4, 1);
        siteNameGridPane.add(okButton, 0, 2, 1, 1);
        siteNameGridPane.add(cancelButton, 1, 2, 1, 1);
        siteNameTextField.setOnAction(e -> {
            siteName = siteNameTextField.getText();
        });
        okButton.setOnAction(e -> {
            Tab selectedTab = sitesTabPane.getSelectionModel().getSelectedItem();
            selectedTab.setText(siteName);
            siteNameStage.close();
        });
        cancelButton.setOnAction(e -> {
            siteNameStage.close();
        });
        siteNameStage.setScene(siteNameScene);
        siteNameStage.setTitle("Change Site Name");
        siteNameStage.show();
    }

    public void startUI(Stage initPrimaryStage, String windowTitle) {
        // THE TOOLBAR ALONG THE NORTH
        initFileToolbar();

        // THE TOOLBAR ALONG THE LEFT
        initSiteToolbar();

        // INIT THE PAGE EDIT WORKSPACE BUT DO NOT SHOW IT
        initPageEditWorkspace();

        // INIT THE SITE VIEWER WORKSPACE BUT DO NOT SHOW IT
        initSiteViewerWorkspace();

        // INIT THE WORKSPACE MODE TOOLBAR
        initWorkspaceModeToolbar();

        // INIT EVENT HANDLERS
        initEventHandlers();

        // AND FINALLY START UP THE WINDOW (WITHOUT THE WORKSPACE)
        // KEEP THE WINDOW FOR LATER
        primaryStage = initPrimaryStage;
        initWindow(windowTitle);
    }

    /**
     * This helps initialize buttons in a toolbar, constructing a custom button
     * with a customly provided icon and tooltip, adding it to the provided
     * toolbar pane, and then returning it.
     */
    public Button initChildButton(
            Pane toolbar,
            String iconFileName,
            PropertyType tooltip,
            String cssClass,
            boolean disabled) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String imagePath = "file:" + PATH_ICONS + iconFileName;
        Image buttonImage = new Image(imagePath);
        Button button = new Button();
        button.getStyleClass().add(cssClass);
        button.setDisable(disabled);
        button.setGraphic(new ImageView(buttonImage));
        Tooltip buttonTooltip = new Tooltip(props.getProperty(tooltip.toString()));
        button.setTooltip(buttonTooltip);
        toolbar.getChildren().add(button);
        return button;

    }

    //Sets the Window Icon For The Application
    public void setWindowIcon(String iconFileName, Stage s) {
        String imagePath = "file:" + PATH_ICONS + iconFileName;
        Image icon = new Image(imagePath);
        s.getIcons().add(icon);
    }

}
