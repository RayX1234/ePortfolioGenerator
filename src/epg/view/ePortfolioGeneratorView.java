/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.view;

import epg.PropertyType;
import static epg.PropertyType.TOOLTIP_EXIT;
import static epg.PropertyType.TOOLTIP_EXPORT_PORTFOLIO;
import static epg.PropertyType.TOOLTIP_LOAD_PORTFOLIO;
import static epg.PropertyType.TOOLTIP_NEW_PORTFOLIO;
import static epg.PropertyType.TOOLTIP_SAVE_AS_PORTFOLIO;
import static epg.PropertyType.TOOLTIP_SAVE_PORTFOLIO;
import static epg.StartupConstants.CSS_CLASS_EPG_PANE;
import static epg.StartupConstants.CSS_CLASS_FILE_TOOL_BAR_PANE;
import static epg.StartupConstants.CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON;
import static epg.StartupConstants.ICON_EXIT;
import static epg.StartupConstants.ICON_EXPORT_PORTFOLIO;
import static epg.StartupConstants.ICON_FIRE;
import static epg.StartupConstants.ICON_LOAD_PORTFOLIO;
import static epg.StartupConstants.ICON_NEW_PORTFOLIO;
import static epg.StartupConstants.ICON_SAVE_AS_PORTFOLIO;
import static epg.StartupConstants.ICON_SAVE_PORTFOLIO;
import static epg.StartupConstants.PATH_ICONS;
import static epg.StartupConstants.STYLE_SHEET_UI;
import epg.file.ePortfolioFileManager;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;

/**
 *
 * @author Ray
 */
public class ePortfolioGeneratorView {

    //Main application UI window and scene graph
    Stage primaryStage;
    Scene primaryScene;

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

    //Default Constructor
    public ePortfolioGeneratorView(ePortfolioFileManager initFileManager) {

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

    public void startUI(Stage initPrimaryStage, String windowTitle) {
        // THE TOOLBAR ALONG THE NORTH
        initFileToolbar();

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
