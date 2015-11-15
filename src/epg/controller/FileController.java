/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.controller;

import epg.file.ePortfolioFileManager;
import epg.view.ePortfolioGeneratorView;

/**
 *
 * @author Ray
 */
public class FileController {

    // WE WANT TO KEEP TRACK OF WHEN SOMETHING HAS NOT BEEN SAVED

    private boolean saved;

    // THE APP UI
    private ePortfolioGeneratorView ui;

    // THIS GUY KNOWS HOW TO READ AND WRITE SLIDE SHOW DATA
    private ePortfolioFileManager portfolioIO;

    //Default constructor
    public FileController(ePortfolioGeneratorView initUI, ePortfolioFileManager initPortfolioIO) {
        // NOTHING YET
        saved = true;
        ui = initUI;
        portfolioIO = initPortfolioIO;
    }
    
    //Create a new ePortfolio
    public void handleNewPortfolioRequest(){
        
    }
}
