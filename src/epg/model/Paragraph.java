/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.model;

import epg.view.EPortfolioGeneratorView;
import javafx.scene.control.Toggle;

/**
 *
 * @author Ray
 */
public class Paragraph {

    private String paragraphText;
    private String font;

    public Paragraph() {

    }

    public void setParagraphText(String initParagraphText) {
        paragraphText = initParagraphText;
    }

    public String getParagraphText() {
        return paragraphText;
    }

    public void setFont(String initFontToggle) {
        font = initFontToggle;
    }

    public String getFontToggle() {
        return font;
    }

    @Override
    public String toString() {

        return "Paragraph Component";
    }

}
