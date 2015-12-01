/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.model;

import javafx.scene.control.ListView;

/**
 *
 * @author Ray
 */
public class List {

    private ListView<String> list;

    public List() {

    }

    public void setList(ListView<String> initList) {
        list = initList;
    }

    public ListView<String> getList() {
        return list;
    }
}
