/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Ray
 */
public class ListModel {

    private ObservableList<ListObject> listData;
    private String listIndex;

    public ListModel() {
        listData = FXCollections.observableArrayList();
    }
    
    

    /**
     * @return the lists
     */
    public ObservableList<ListObject> getListData() {
        return listData;
    }
    
    public String toString(){
        return "List Component";
    }

    /**
     * @return the listIndex
     */
    public String getListIndex() {
        return listIndex;
    }

    /**
     * @param listIndex the listIndex to set
     */
    public void setListIndex(String listIndex) {
        this.listIndex = listIndex;
    }

}
