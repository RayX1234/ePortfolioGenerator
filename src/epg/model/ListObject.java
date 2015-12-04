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

    private ObservableList<String> ld;
    

    public ListObject() {
       
    }

    public void setListData(ObservableList<String> initLD) {
        ld = initLD;
    }

    public ObservableList<String> getListData() {
        return ld;
    }

    @Override
    public String toString() {
        return "List Component";
    }
}
