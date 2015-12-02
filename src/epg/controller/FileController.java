/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.controller;

import epg.file.EPortfolioFileManager;
import epg.view.EPortfolioGeneratorView;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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

        boolean continueToMakeNew = true;
        if (!saved) {
            try {
                // THE USER CAN OPT OUT HERE WITH A CANCEL
                continueToMakeNew = promptToSave();
            } catch (IOException ex) {
                Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // IF THE USER REALLY WANTS TO MAKE A NEW COURSE
        if (continueToMakeNew) {
            // RESET THE DATA, WHICH SHOULD TRIGGER A RESET OF THE UI

            ui.reset();
            saved = false;
        }

    }

    //Load a ePortfolio
    public void handleLoadPortfolioRequest() {

    }

    //Save a ePortfolio
    public void handleSavePortfolioRequest() {

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
//            SlideShowModel slideShow = ui.getSlideShow();
//            slideShowIO.saveSlideShow(slideShow);
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
}
