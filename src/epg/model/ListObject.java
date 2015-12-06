/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.model;

import epg.view.EPortfolioGeneratorView;
import javafx.collections.ObservableList;

/**
 *
 * @author Ray
 */
public class ListObject {

    private String listString;
    
    public ListObject() {

    }

    /**
     * @return the listString
     */
    public String getListString() {
        return listString;
    }

    /**
     * @param listString the listString to set
     */
    public void setListString(String listString) {
        this.listString = listString;
    }
    
    public String toString(){
        return listString;
    }


}
