/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.model;

import epg.view.EPortfolioGeneratorView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TabPane;

/**
 *
 * @author Ray
 */
public class EPortfolioModel {

    private ObservableList<Page> pages;
    private String studentName;
    private EPortfolioGeneratorView ui;

    public EPortfolioModel(EPortfolioGeneratorView initUI) {
        pages = FXCollections.observableArrayList();
        ui = initUI;
    }

    /**
     * @return the pages
     */
    public ObservableList<Page> getPages() {
        return pages;
    }

    public void setStudentName(String initStudentName) {
        studentName = initStudentName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void reset() {
        pages.clear();
        studentName = "ENTER STUDENT NAME";
    }

}
