/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.view;

import epg.PropertyType;
import static epg.PropertyType.TOOLTIP_ADD_SITE;
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
import static epg.StartupConstants.CSS_CLASS_CSN_GRID_PANE;
import static epg.StartupConstants.CSS_CLASS_EPG_PANE;
import static epg.StartupConstants.CSS_CLASS_FILE_TOOL_BAR_PANE;
import static epg.StartupConstants.CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON;
import static epg.StartupConstants.CSS_CLASS_PAGE_EDIT_WORKSPACE_PANE;
import static epg.StartupConstants.CSS_CLASS_SITES_TAB_PANE;
import static epg.StartupConstants.CSS_CLASS_SITES_TOOL_BAR_PANE;
import static epg.StartupConstants.CSS_CLASS_SITE_VIEWER_WORKSPACE_PANE;
import static epg.StartupConstants.CSS_CLASS_VERTICAL_TOOLBAR_BUTTON;
import static epg.StartupConstants.CSS_CLASS_WORKSPACE_MODE_TOOLBAR_PANE;
import static epg.StartupConstants.ICON_ADD_SITE;
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
import epg.controller.FileController;
import epg.controller.PageEditController;
import epg.file.EPortfolioFileManager;
import epg.model.Component;
import epg.model.EPortfolioModel;
import epg.model.ListModel;
import epg.model.Page;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
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
    HBox okCancelHBox;
    int count;
    int componentCount;

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

    FileController fileController;
    EPortfolioFileManager fileManager;
    PageEditController pageEditController;

    // THIS WILL GO IN THE LEFT SIDE OF THE SCREEN
    VBox slideEditToolbar;
    Button addSlideButton;
    Button deleteSlideButton;
    Button moveUpSlideButton;
    Button moveDownSlideButton;

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

    //PAGE OBJECT
    Page page;
    EPortfolioModel portfolioModel;
    ListModel listModel;

    //Default Constructor
    public EPortfolioGeneratorView(EPortfolioFileManager initFileManager) {
        // FIRST HOLD ONTO THE FILE MANAGER
        fileManager = initFileManager;
        portfolioModel = new EPortfolioModel(this);
        listModel = new ListModel();
    }

    //initalizing the fileToolbar Buttons
    private void initFileToolbar() {
        fileToolbarPane = new FlowPane();
        fileToolbarPane.getStyleClass().add(CSS_CLASS_FILE_TOOL_BAR_PANE);

        //creating the filetoolbar buttons ENABLED is false while Disabled is true
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        newPortfolioButton = initChildButton(fileToolbarPane, ICON_NEW_PORTFOLIO, TOOLTIP_NEW_PORTFOLIO, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        loadPortfolioButton = initChildButton(fileToolbarPane, ICON_LOAD_PORTFOLIO, TOOLTIP_LOAD_PORTFOLIO, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        savePortfolioButton = initChildButton(fileToolbarPane, ICON_SAVE_PORTFOLIO, TOOLTIP_SAVE_PORTFOLIO, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, true);
        saveAsPortfolioButton = initChildButton(fileToolbarPane, ICON_SAVE_AS_PORTFOLIO, TOOLTIP_SAVE_AS_PORTFOLIO, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, true);
        exportPortfolioButton = initChildButton(fileToolbarPane, ICON_EXPORT_PORTFOLIO, TOOLTIP_EXPORT_PORTFOLIO, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, true);
        exitButton = initChildButton(fileToolbarPane, ICON_EXIT, TOOLTIP_EXIT, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
    }

    public void resetFileToolbarPane() {
        fileToolbarPane.getChildren().remove(ePortfolioTitleLabel);
        fileToolbarPane.getChildren().remove(ePortfolioTitleTF);
        ePortfolioTitleLabel = new Label("Student Name:");
        ePortfolioTitleTF = new TextField();
        ePortfolioTitleTF.setOnAction(e -> {
            portfolioModel.setStudentName(ePortfolioTitleTF.getText());
            savePortfolioButton.setDisable(false);
        });
        ePortfolioTitleTF.setText(portfolioModel.getStudentName());
        fileToolbarPane.getChildren().addAll(ePortfolioTitleLabel, ePortfolioTitleTF);

    }

    public void resetSiteToolbarPane() {
        siteToolbarPane.getChildren().clear();
        siteToolbarPane.getStyleClass().add(CSS_CLASS_SITES_TOOL_BAR_PANE);
        addSitePageButton = initChildButton(siteToolbarPane, ICON_ADD_SITE, TOOLTIP_ADD_SITE, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);
        removeSitePageButton = initChildButton(siteToolbarPane, ICON_REMOVE_SITE, TOOLTIP_REMOVE_SITE, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);
        changeSiteNameButton = initChildButton(siteToolbarPane, ICON_CHANGE_SITE_NAME, TOOLTIP_CHANGE_SITE_NAME, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, true);
        initEventHandlers();
    }

    //USED TO RELOAD PORTFOLIO TO ADD PAGES DEPENDING ON PORTFOLIO SIZE
    public void reloadPortfolio(EPortfolioModel portfolioToLoad) {
        //MAKE SURE TAB IS EMPTY
        sitesTabPane.getTabs().clear();

        for (Page page : portfolioToLoad.getPages()) {
            PageEditView pageEdit = new PageEditView(page, portfolioToLoad, this);
            Tab t = new Tab();
            if (page.getPagetitle() == null) {
                t.setText("ENTER PAGE TITLE");
            } else {
                t.setText(page.getPagetitle());
            }
            t.setContent(pageEdit.getContentPane());
            sitesTabPane.getTabs().add(t);

            pageEdit.getRemoveComponentButton().setDisable(false);

        }

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
        pageEditScene = new Scene(pageEditWorkspace);
        pageEditScene.getStylesheets().add(STYLE_SHEET_UI);

    }

    //activate the pageEditWorkspace
    public void activatePEW() {
        pageEditWorkspace.setTop(fileToolbarPane);
        pageEditWorkspace.setLeft(siteToolbarPane);
        pageEditWorkspace.setCenter(sitesTabPane);
        pageEditWorkspace.setBottom(workspaceModeToolbar);
        primaryStage.setScene(pageEditScene);

    }

    private void initSiteViewerWorkspace() {
        siteViewerWorkspace = new BorderPane();
        //TTEMP FOR HW 6 oNLY HVE TO DELETE LATER
        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        webEngine.load("http://google.com");
        siteViewerWorkspace.setCenter(browser);
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
            fileController.handleNewPortfolioRequest();
            savePortfolioButton.setDisable(false);

        });

        loadPortfolioButton.setOnAction(e -> {
            fileController.handleLoadPortfolioRequest();
            savePortfolioButton.setDisable(false);

        });

        savePortfolioButton.setOnAction(e -> {
            fileController.handleSavePortfolioRequest();
            savePortfolioButton.setDisable(true);

        });

        saveAsPortfolioButton.setOnAction(e -> {
            fileController.handleSaveAsPortfolioRequest();
        });

        exitButton.setOnAction(e -> {
            fileController.handleExitRequest();
        });

        pageEditController = new PageEditController(this);

        addSitePageButton.setOnAction(e -> {
            //  count++;
            pageEditController.processAddSiteRequest();
            savePortfolioButton.setDisable(false);

        });

        removeSitePageButton.setOnAction(e -> {
            pageEditController.processRemoveSiteRequest();
            savePortfolioButton.setDisable(false);
            //  count--;

        });

        changeSiteNameButton.setOnAction(e -> {
            pageEditController.processChangeNameSiteRequest();
            savePortfolioButton.setDisable(false);
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
        page = new Page();
        removeSitePageButton.setDisable(false);
        changeSiteNameButton.setDisable(false);
        portfolioModel.getPages().add(page);
        reloadPortfolio(portfolioModel);

    }

    //Remove site page
    public void removeSitePage() {
        Tab selectedTab = sitesTabPane.getSelectionModel().getSelectedItem();
        int value = sitesTabPane.getTabs().indexOf(selectedTab);
        portfolioModel.getPages().remove(value);
        reloadPortfolio(portfolioModel);
        if (portfolioModel.getPages().isEmpty()) {
            changeSiteNameButton.setDisable(true);
            removeSitePageButton.setDisable(true);
        }
    }

    //Change site name
    public void updateSiteNameDialog() {
        siteNameGridPane = new GridPane();
        siteNameGridPane.getStyleClass().add(CSS_CLASS_CSN_GRID_PANE);
        siteNameLabel = new Label("Enter a Page Title:");
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
            page.setPagetitle(siteNameTextField.getText());

            siteNameStage.close();
        });
        siteNameTextField.setText(page.getPagetitle());
        cancelButton.setOnAction(e -> {
            siteNameStage.close();
        });
        siteNameStage.setScene(siteNameScene);
        siteNameStage.setTitle("Change Page Title");
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

    public Stage getWindow() {
        return primaryStage;
    }

    public Page getPage() {
        return page;
    }

    public EPortfolioModel getPortfolioModel() {
        return portfolioModel;
    }

    public void setPageEditWorkspaceActivated(Boolean initPEW) {
        pageEditWorkspaceActivated = initPEW;
    }

    public Button getRemoveSitePageButton() {
        return removeSitePageButton;
    }

    public ListModel getListModel() {
        return listModel;
    }

    public ObservableList<Tab> getTabs() {
        return sitesTabPane.getTabs();
    }
    
    public Button getSaveButton(){
        return savePortfolioButton;
    }

}
