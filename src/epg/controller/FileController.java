/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.controller;

import epg.file.EPortfolioFileManager;
import epg.view.EPortfolioGeneratorView;

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
    public void handleNewPortfolioRequest(){
        
    }
    
    //Load a ePortfolio
    public void handleLoadPortfolioRequest(){
        
    }
    
    //Save a ePortfolio
    public void handleSavePortfolioRequest(){
        
    }
    
    //Save as ePortfolio
    public void handleSaveAsPortfolioRequest(){
        
    }
    
    //Export ePortfolio
    public void handleExportPortfolioRequest(){
        
    }
    
    //Exit ePortfolio
    public void handleExitRequest(){
        
    }
}
