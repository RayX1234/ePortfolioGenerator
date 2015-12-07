/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.model;

import epg.controller.TextController;

/**
 *
 * @author Ray
 */
public class Heading {

    private String headingText;
    private String index;

    public Heading() {

    }

    public void setHeadingText(String initHeadingText) {
        headingText = initHeadingText;
    }

    public String getHeadingText() {
        return headingText;
    }

    @Override
    public String toString() {

        return "(Heading Component) " + headingText;
    }
    
    public void setIndex(String initIndex){
        index = initIndex;
    }
    
    public String getIndex(){
        return index;
    }

}
