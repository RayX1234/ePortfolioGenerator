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
public class HyperLink {
    private String hyperLinkText;
    private int count;
    private Paragraph p;
    
    
    public HyperLink(){
        
    }
    
    public void setHyperLinkText(String initHyperLinkText){
        hyperLinkText = initHyperLinkText;
    }
    
    public String getHyperLinkText(){
        return hyperLinkText;
    }
    
    public void setParagraphSpace(Paragraph initP){
        p = initP;
    }
    
    @Override
    public String toString(){
        return p.toString() + "And HyperLink";
    }
}
