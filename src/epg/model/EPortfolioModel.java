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
public class EPortfolioModel {

    private ObservableList<Page> pages;
    private String studentName;

    public EPortfolioModel() {
        pages = FXCollections.observableArrayList();
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

}
