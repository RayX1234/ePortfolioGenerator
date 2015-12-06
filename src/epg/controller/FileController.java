/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.controller;

import static epg.StartupConstants.PATH_EPORTFOLIOS;
import epg.file.EPortfolioFileManager;
import epg.model.EPortfolioModel;
import epg.view.EPortfolioGeneratorView;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;

/**
 *
 * @author Ray
 */
public class FileController {

    // WE WANT TO KEEP TRACK OF WHEN SOMETHING HAS NOT BEEN SAVED
    private boolean saved;

    // THE APP UI
    private EPortfolioGeneratorView ui;

    // THIS GUY KNOWS HOW TO READ AND WRITE SLIDE SHOW DATA
    private EPortfolioFileManager portfolioIO;

    //Default constructor
    public FileController(EPortfolioGeneratorView initUI, EPortfolioFileManager initPortfolioIO) {
        // NOTHING YET
        saved = true;
        ui = initUI;
        portfolioIO = initPortfolioIO;
    }

    //Create a new ePortfolio
    public void handleNewPortfolioRequest() {
        try {
            boolean continueToMakeNew = true;
            if (!saved) {

                // THE USER CAN OPT OUT HERE WITH A CANCEL
                continueToMakeNew = promptToSave();

            }
            // IF THE USER REALLY WANTS TO MAKE A NEW COURSE
            if (continueToMakeNew) {
                // RESET THE DATA, WHICH SHOULD TRIGGER A RESET OF THE UI
                EPortfolioModel portfolioModel = ui.getPortfolioModel();
                portfolioModel.reset();
                ui.getTabs().clear();
                saved = false;
                ui.activatePEW();
                ui.setPageEditWorkspaceActivated(true);
                ui.isPEWActivated();
                ui.getRemoveSitePageButton().setDisable(true);
                ui.resetFileToolbarPane();
            }

        } catch (IOException ioe) {
        }
    }

    //Load a ePortfolio
    public void handleLoadPortfolioRequest() {
        try {
            // WE MAY HAVE TO SAVE CURRENT WORK
            boolean continueToOpen = true;
            if (!saved) {
                // THE USER CAN OPT OUT HERE WITH A CANCEL
                continueToOpen = promptToSave();
            }

            // IF THE USER REALLY WANTS TO OPEN A POSE
            if (continueToOpen) {
                // GO AHEAD AND PROCEED MAKING A NEW POSE
                promptToOpen();

            }
        } catch (IOException ioe) {

            //@todo provide error message
        }
    }

    //Save a ePortfolio
    public boolean handleSavePortfolioRequest() {
        try {
            EPortfolioModel portfolioModel = ui.getPortfolioModel();

            portfolioIO.saveEPortfolio(portfolioModel, ui);
            saved = true;
            return true;
        } catch (IOException ex) {
            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    //Save as ePortfolio
    public void handleSaveAsPortfolioRequest() {

    }

    //Export ePortfolio
    public void handleExportPortfolioRequest() {

    }

    //Exit ePortfolio
    public void handleExitRequest() {

    }

    /**
     * This helper method verifies that the user really wants to save their
     * unsaved work, which they might not want to do. Note that it could be used
     * in multiple contexts before doing other actions, like creating a new
     * pose, or opening another pose, or exiting. Note that the user will be
     * presented with 3 options: YES, NO, and CANCEL. YES means the user wants
     * to save their work and continue the other action (we return true to
     * denote this), NO means don't save the work but continue with the other
     * action (true is returned), CANCEL means don't save the work and don't
     * continue with the other action (false is retuned).
     *
     * @return true if the user presses the YES option to save, true if the user
     * presses the NO option to not save, false if the user presses the CANCEL
     * option to not continue.
     */
    private boolean promptToSave() throws IOException {
        // PROMPT THE USER TO SAVE UNSAVED WORK
        boolean saveWork = true; // @todo change this to prompt

        // IF THE USER SAID YES, THEN SAVE BEFORE MOVING ON
        if (saveWork) {
            EPortfolioModel portfolioModel = ui.getPortfolioModel();
            saved = true;
        } // IF THE USER SAID CANCEL, THEN WE'LL TELL WHOEVER
        // CALLED THIS THAT THE USER IS NOT INTERESTED ANYMORE
        else if (!true) {
            return false;
        }

        // IF THE USER SAID NO, WE JUST GO ON WITHOUT SAVING
        // BUT FOR BOTH YES AND NO WE DO WHATEVER THE USER
        // HAD IN MIND IN THE FIRST PLACE
        return true;
    }

    private void promptToOpen() {
        // AND NOW ASK THE USER FOR THE COURSE TO OPEN
        FileChooser portfolioFileChooser = new FileChooser();
        portfolioFileChooser.setInitialDirectory(new File(PATH_EPORTFOLIOS));
        File selectedFile = portfolioFileChooser.showOpenDialog(ui.getWindow());

        // ONLY OPEN A NEW FILE IF THE USER SAYS OK
        if (selectedFile != null) {
            try {
                EPortfolioModel portfolioToLoad = ui.getPortfolioModel();
                portfolioIO.loadEPortfolio(portfolioToLoad, selectedFile.getAbsolutePath());
                ui.activatePEW();
                ui.setPageEditWorkspaceActivated(true);
                ui.isPEWActivated();
                ui.getRemoveSitePageButton().setDisable(true);
                ui.resetFileToolbarPane();
                ui.reloadPortfolio(portfolioToLoad);
                saved = true;

                //ui.setTitleArea(DEFAULT_SLIDE_SHOW_TITLE_LABEL);
            } catch (Exception e) {
                System.out.println("SOMETHING CRASHED");
            }
        }
    }
}
