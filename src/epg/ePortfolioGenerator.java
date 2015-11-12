/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg;

import static epg.PropertyType.TITLE_WINDOW;
import static epg.StartupConstants.PATH_DATA;
import static epg.StartupConstants.PROPERTIES_SCHEMA_FILE_NAME;
import static epg.StartupConstants.UI_PROPERTIES_FILE_NAME;
import epg.file.ePortfolioFileManager;
import epg.view.ePortfolioGeneratorView;
import javafx.application.Application;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;

/**
 * ePortfolioGenerator is a program used to generate ePortfolios for students.
 * It will allow the user to simulate a similar ePortfolio to digication with
 * less functionality.
 *
 * @author Ray
 */
public class ePortfolioGenerator extends Application {

    //This will perform ePortfolio reading and writing
    ePortfolioFileManager fileManager = new ePortfolioFileManager();

    //This is the gui for the program
    ePortfolioGeneratorView ui = new ePortfolioGeneratorView(fileManager);

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Load properties manager into GUI and start it up
        boolean success = loadProperties();

        if (success) {
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            String appTitle = props.getProperty(TITLE_WINDOW);
            ui.startUI(primaryStage, appTitle);
        } else {
            System.out.println("Failed to load properties");
        }

    }

    /**
     * This is where the application starts execution. We'll load the
     * application properties and then use them to build our user interface and
     * start the window in event handling mode. Once in that mode, all code
     * execution will happen in response to user requests.
     *
     * @param args This application does not use any command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Loads this application's properties file, which has a number of settings
     * for initializing the user interface.
     *
     * @return true if the properties file was loaded successfully, false
     * otherwise.
     */
    public boolean loadProperties() {
        try {

            // LOAD THE SETTINGS FOR STARTING THE APP
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            props.addProperty(PropertiesManager.DATA_PATH_PROPERTY, PATH_DATA);
            props.loadProperties(UI_PROPERTIES_FILE_NAME, PROPERTIES_SCHEMA_FILE_NAME);
            return true;

        } catch (Exception e) {
            System.out.println("Properties failed to load");
            return false;
        }
    }
}
