/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.controller;

import epg.view.EPortfolioGeneratorView;

/**
 *
 * @author Ray
 */
public class PageEditController {

    // APP UI

    private EPortfolioGeneratorView ui;

    /**
     * This constructor keeps the UI for later.
     */
    public PageEditController(EPortfolioGeneratorView initUI) {
        ui = initUI;

    }
    
    public void processAddSiteRequest(){
        ui.createSitePage();
    }
    
    public void processRemoveSiteRequest(){
        ui.removeSitePage();
    }
    
    public void processChangeNameSiteRequest(){
        ui.updateSiteNameDialog();
    }
}
