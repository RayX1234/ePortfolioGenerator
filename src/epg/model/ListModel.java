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

    public ListModel() {
        listData = FXCollections.observableArrayList();
    }

    /**
     * @return the lists
     */
    public ObservableList<ListObject> getListData() {
        return listData;
    }

    @Override
    public String toString() {
        return "List Component";
    }
}
