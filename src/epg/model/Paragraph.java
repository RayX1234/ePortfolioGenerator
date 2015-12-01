/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.model;

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

    public void setFont(String initFont) {
        font = initFont;
    }

    public String getFont() {
        return font;
    }

}
