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
public class SlideShow {

    private SlideShowModel ssm;

    public SlideShow() {

    }

    @Override
    public String toString() {
        return "Slide Show Component";
    }

    public void setSlideShowModel(SlideShowModel initSSM){
        ssm = initSSM;
    }
    
    public SlideShowModel getSlideShowModel(){
        return ssm;
    }
}
