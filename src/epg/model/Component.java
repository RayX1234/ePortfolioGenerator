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
public class Component {

    private Heading h;
    private HyperLink hl;
    private Image i;
    private ListModel l;
    private Paragraph p;
    private SlideShowModel ssm;
    private Video v;

    private boolean heading, hyperlink, image, list, slideshow, paragraph, video;

    public Component() {

    }

    /**
     * @return the h
     */
    public Heading getH() {
        return h;
    }

    /**
     * @param h the h to set
     */
    public void setH(Heading h) {

        this.h = h;
    }

    /**
     * @return the hl
     */
    public HyperLink getHl() {
        return hl;
    }

    /**
     * @param hl the hl to set
     */
    public void setHl(HyperLink hl) {

        this.hl = hl;
    }

    /**
     * @return the i
     */
    public Image getI() {
        return i;
    }

    /**
     * @param i the i to set
     */
    public void setI(Image i) {

        this.i = i;
    }

    /**
     * @return the l
     */
    public ListModel getL() {
        return l;
    }

    /**
     * @param l the l to set
     */
    public void setL(ListModel l) {

        this.l = l;
    }

    /**
     * @return the p
     */
    public Paragraph getP() {
        return p;
    }

    /**
     * @param p the p to set
     */
    public void setP(Paragraph p) {

        this.p = p;
    }

    /**
     * @return the ssm
     */
    public SlideShowModel getSS() {
        return ssm;
    }

    /**
     * @param ssm the ssm to set
     */
    public void setSSM(SlideShowModel initSS) {

        this.ssm = initSS;
    }

    /**
     * @return the v
     */
    public Video getV() {
        return v;
    }

    /**
     * @param v the v to set
     */
    public void setV(Video v) {

        this.v = v;
    }

    @Override
    public String toString() {
        if (isHeading()) {
            return h.toString();
        }
        if (isHyperlink()) {
            return hl.toString();
        }
        if (isImage()) {
            return i.toString();
        }
        if (isList()) {
            return l.toString();
        }
        if (isParagraph()) {
            return p.toString();
        }
        if (isSlideshow()) {
            return ssm.toString();
        }
        if (isVideo()) {
            return v.toString();
        }
        return null;

    }

    /**
     * @return the heading
     */
    public boolean isHeading() {
        return heading;
    }

    /**
     * @param heading the heading to set
     */
    public void setHeading(boolean heading) {
        this.heading = heading;
    }

    /**
     * @return the hyperlink
     */
    public boolean isHyperlink() {
        return hyperlink;
    }

    /**
     * @param hyperlink the hyperlink to set
     */
    public void setHyperlink(boolean hyperlink) {
        this.hyperlink = hyperlink;
    }

    /**
     * @return the image
     */
    public boolean isImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(boolean image) {
        this.image = image;
    }

    /**
     * @return the list
     */
    public boolean isList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(boolean list) {
        this.list = list;
    }

    /**
     * @return the paragraph
     */
    public boolean isParagraph() {
        return paragraph;
    }

    /**
     * @param paragraph the paragraph to set
     */
    public void setParagraph(boolean paragraph) {
        this.paragraph = paragraph;
    }

    /**
     * @return the slideshowmodel
     */
    public boolean isSlideshow() {
        return slideshow;
    }

    /**
     * @param slideshowmodel the slideshowmodel to set
     */
    public void setSlideshow(boolean slideshow) {
        this.slideshow = slideshow;
    }

    /**
     * @return the video
     */
    public boolean isVideo() {
        return video;
    }

    /**
     * @param video the video to set
     */
    public void setVideo(boolean video) {
        this.video = video;
    }
}
