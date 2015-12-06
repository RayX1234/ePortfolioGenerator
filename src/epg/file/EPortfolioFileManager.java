/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.file;

import static epg.StartupConstants.PATH_EPORTFOLIOS;
import epg.model.Component;
import epg.model.EPortfolioModel;
import epg.model.Heading;
import epg.model.Image;
import epg.model.ListModel;
import epg.model.ListObject;
import java.util.List;
import epg.model.Page;
import epg.model.Paragraph;
import epg.model.Slide;
import epg.model.SlideShowModel;
import epg.model.Video;
import epg.view.EPortfolioGeneratorView;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;

/**
 *
 * @author Ray
 */
public class EPortfolioFileManager {

    // JSON FILE READING AND WRITING CONSTANTS
    public static String JSON_STUDENT_NAME = "student_name";
    public static String JSON_PAGES = "pages";
    public static String JSON_PARAGRAPHS = "paragraphs";
    public static String JSON_VIDEOS = "videos";
    public static String JSON_LISTS = "lists";
    public static String JSON_LIST_INDEX = "list_index";
    public static String JSON_IMAGES = "images";
    public static String JSON_HEADINGS = "headings";
    public static String JSON_HEADING_TEXT = "heading_text";
    public static String JSON_HEADING_INDEX = "heading_index";
    public static String JSON_HYPER_LINK_TEXT = "hyper_link_text";
    public static String JSON_IMAGE_FILE_NAME = "image_file_name";
    public static String JSON_IMAGE_PATH = "image_path";
    public static String JSON_IMAGE_CAPTION = "image_caption";
    public static String JSON_IMAGE_HEIGHT = "image_height";
    public static String JSON_IMAGE_WIDTH = "image_width";
    public static String JSON_IMAGE_POSITION = "image_position";
    public static String JSON_IMAGE_INDEX = "image_index";
    public static String JSON_LIST_STRING = "list_string";
    public static String JSON_BANNER_IMAGE_FILE_NAME = "banner_image_file_name";
    public static String JSON_BANNER_IMAGE_PATH = "banner_image_path";
    public static String JSON_PAGE_FONT = "page_font";
    public static String JSON_PAGE_TITLE = "page_title";
    public static String JSON_PAGE_LAYOUT = "page_layout";
    public static String JSON_PAGE_COLOR = "page_color";
    public static String JSON_PAGE_FOOTER = "page_footer";
    public static String JSON_PARAGRAPH_TEXT = "paragraph_text";
    public static String JSON_PARAGRAPH_FONT = "paragraph_font";
    public static String JSON_PARAGRAPH_INDEX = "paragraph_index";
    public static String JSON_SLIDE_IMAGE_FILE_NAME = "slide_image_file_name";
    public static String JSON_SLIDE_IMAGE_PATH = "slide_image_path";
    public static String JSON_SLIDE_CAPTION = "slide_caption";
    public static String JSON_SLIDES = "slides";
    public static String JSON_SLIDE_INDEX = "slide_index";
    public static String JSON_VIDEO_FILE_NAME = "video_file_name";
    public static String JSON_VIDEO_FILE_PATH = "video_file_path";
    public static String JSON_VIDEO_CAPTION = "video_caption";
    public static String JSON_VIDEO_WIDTH = "video_width";
    public static String JSON_VIDEO_HEIGHT = "video_height";
    public static String JSON_VIDEO_INDEX = "video_index";
    public static String JSON_COMPONENT_COUNT = "component_count";
    public static String JSON_LIST = "list";
    public static String JSON_SLIDE = "slide";
    public static String JSON_EXT = ".json";
    public static String SLASH = "/";

    EPortfolioGeneratorView ui;

    /**
     * This method saves all the data associated with a slide show to a JSON
     * file.
     *
     * @param slideShowToSave The course whose data we are saving.
     *
     * @throws IOException Thrown when there are issues writing to the JSON
     * file.
     */
    public void saveEPortfolio(EPortfolioModel portfolioToSave, EPortfolioGeneratorView initui) throws IOException {
        ui = initui;

        // BUILD THE FILE PATH
        String ePortfolioStudentName = "" + portfolioToSave.getStudentName();
        String jsonFilePath = PATH_EPORTFOLIOS + SLASH + ePortfolioStudentName + JSON_EXT;

        // INIT THE WRITER
        OutputStream os = new FileOutputStream(jsonFilePath);
        JsonWriter jsonWriter = Json.createWriter(os);

        // BUILD THE PAGES ARRAY
        JsonArray pagesJsonArray = makePagesJsonArray(portfolioToSave.getPages());

        // NOW BUILD THE COURSE USING EVERYTHING WE'VE ALREADY MADE
        JsonObject portfolioJsonObject = Json.createObjectBuilder()
                .add(JSON_STUDENT_NAME, portfolioToSave.getStudentName())
                .add(JSON_PAGES, pagesJsonArray)
                .build();

        // AND SAVE EVERYTHING AT ONCE
        jsonWriter.writeObject(portfolioJsonObject);
    }

    /**
     * This method loads the contents of a JSON file representing a slide show
     * into a SlideSShowModel objecct.
     *
     * @param slideShowToLoad The slide show to load
     * @param jsonFilePath The JSON file to load.
     * @throws IOException
     */
    public void loadEPortfolio(EPortfolioModel portfolioToLoad, String jsonFilePath) throws IOException {

        // LOAD THE JSON FILE WITH ALL THE DATA
        JsonObject json = loadJSONFile(jsonFilePath);

        // NOW LOAD THE COURSE
        portfolioToLoad.reset();

        portfolioToLoad.setStudentName(json.getString(JSON_STUDENT_NAME));

        JsonArray jsonPagesArray = json.getJsonArray(JSON_PAGES);
        for (int i = 0; i < jsonPagesArray.size(); i++) {
            JsonObject pageJso = jsonPagesArray.getJsonObject(i);
            Page page = new Page();
            page.setBannerImageFileName(pageJso.getString(JSON_BANNER_IMAGE_FILE_NAME));
            page.setBannerImageFilePath(pageJso.getString(JSON_BANNER_IMAGE_PATH));
            page.setColor(pageJso.getString(JSON_PAGE_COLOR));
            page.setFont(pageJso.getString(JSON_PAGE_FONT));
            page.setLayout(pageJso.getString(JSON_PAGE_LAYOUT));
            page.setFooter(pageJso.getString(JSON_PAGE_FOOTER));
            page.setPagetitle(pageJso.getString(JSON_PAGE_TITLE));

            for (int k = 0; k < Integer.parseInt(pageJso.getString(JSON_COMPONENT_COUNT)); k++) {
                page.getComponents().add(null);
            }

            JsonArray jsonHeadingsArray = pageJso.getJsonArray(JSON_HEADINGS);
            if (!jsonHeadingsArray.isEmpty()) {
                for (int j = 0; j < jsonHeadingsArray.size(); j++) {
                    JsonObject headJso = jsonHeadingsArray.getJsonObject(j);
                    Component c = new Component();
                    Heading h = new Heading();
                    h.setHeadingText(headJso.getString(JSON_HEADING_TEXT));
                    h.setIndex(headJso.getString(JSON_HEADING_INDEX));
                    c.setH(h);
                    c.setHeading(true);
                    page.getComponents().set(Integer.parseInt(h.getIndex()), c);
                    page.getHeadings().add(h);
                }
            }
            JsonArray jsonParagraphsArray = pageJso.getJsonArray(JSON_PARAGRAPHS);
            if (!jsonParagraphsArray.isEmpty()) {
                for (int a = 0; a < jsonParagraphsArray.size(); a++) {
                    JsonObject paragraphJso = jsonParagraphsArray.getJsonObject(a);
                    Component c = new Component();
                    Paragraph p = new Paragraph();
                    p.setParagraphText(paragraphJso.getString(JSON_PARAGRAPH_TEXT));
                    p.setFont(paragraphJso.getString(JSON_PARAGRAPH_FONT));
                    p.setParagraphIndex(paragraphJso.getString(JSON_PARAGRAPH_INDEX));
                    c.setP(p);
                    c.setParagraph(true);
                    page.getComponents().set(Integer.parseInt(p.getParagraphIndex()), c);
                    page.getParagraphs().add(p);
                }
            }

            JsonArray jsonImagesArray = pageJso.getJsonArray(JSON_IMAGES);
            if (!jsonImagesArray.isEmpty()) {
                for (int b = 0; b < jsonImagesArray.size(); b++) {
                    JsonObject imageJso = jsonImagesArray.getJsonObject(b);
                    Component c = new Component();
                    Image s = new Image();
                    s.setCaption(imageJso.getString(JSON_IMAGE_CAPTION));
                    s.setHeight(imageJso.getString(JSON_IMAGE_HEIGHT));
                    s.setImageFileName(imageJso.getString(JSON_IMAGE_FILE_NAME));
                    s.setImageIndex(imageJso.getString(JSON_IMAGE_INDEX));
                    s.setImagePath(imageJso.getString(JSON_IMAGE_PATH));
                    s.setImagePosition(imageJso.getString(JSON_IMAGE_POSITION));
                    s.setWidth(imageJso.getString(JSON_IMAGE_WIDTH));
                    s.setImage(imageJso.getString(JSON_IMAGE_PATH), imageJso.getString(JSON_IMAGE_FILE_NAME));
                    c.setI(s);
                    c.setImage(true);
                    page.getComponents().set(Integer.parseInt(s.getImageIndex()), c);
                    page.getImages().add(s);

                }
            }

            JsonArray jsonVideosArray = pageJso.getJsonArray(JSON_VIDEOS);
            if (!jsonVideosArray.isEmpty()) {
                for (int d = 0; d < jsonVideosArray.size(); d++) {
                    JsonObject videoJso = jsonVideosArray.getJsonObject(d);
                    Component c = new Component();
                    Video v = new Video();
                    v.setCaption(videoJso.getString(JSON_VIDEO_CAPTION));
                    v.setHeight(videoJso.getString(JSON_VIDEO_HEIGHT));
                    v.setWidth(videoJso.getString(JSON_VIDEO_WIDTH));
                    v.setVideoIndex(videoJso.getString(JSON_VIDEO_INDEX));
                    v.setVideoFileName(videoJso.getString(JSON_VIDEO_FILE_NAME));
                    v.setVideoPath(videoJso.getString(JSON_VIDEO_FILE_PATH));
                    v.setVideo(videoJso.getString(JSON_VIDEO_FILE_PATH), videoJso.getString(JSON_VIDEO_FILE_NAME));
                    c.setV(v);
                    c.setVideo(true);
                    page.getComponents().set(Integer.parseInt(v.getVideoIndex()), c);
                    page.getVideos().add(v);
                }
            }

            JsonArray jsonListsArray = pageJso.getJsonArray(JSON_LISTS);
            for (int e = 0; e < jsonListsArray.size(); e++) {
                JsonObject listJso = jsonListsArray.getJsonObject(e);
                Component c = new Component();

                ListModel listModel = new ListModel();

                JsonArray jsonListDataArray = listJso.getJsonArray(JSON_LIST);
                for (int f = 0; f < jsonListDataArray.size(); f++) {
                    ListObject list = new ListObject();
                    JsonObject listDataJso = jsonListDataArray.getJsonObject(f);
                    list.setListString(listDataJso.getString(JSON_LIST_STRING));
                    listModel.getListData().add(list);
                }
                listModel.setListIndex(listJso.getString(JSON_LIST_INDEX));
                c.setL(listModel);
                c.setList(true);
                page.getComponents().set(Integer.parseInt(listModel.getListIndex()), c);
                page.getLists().add(listModel);

            }

//            JsonArray jsonSlideShowsArray = pageJso.getJsonArray(JSON_SLIDES);
//            for (int z = 0; z < jsonSlideShowsArray.size(); z++) {
//                JsonObject slideJso = jsonSlideShowsArray.getJsonObject(z);
//                // System.out.println(slideJso.getString(JSON_SLIDE_INDEX));
//                Component c = new Component();
//
//                SlideShowModel slideShow = new SlideShowModel(ssc, ui);
//
//                JsonArray jsonSlideDataArray = slideJso.getJsonArray(JSON_SLIDE);
//                for (int y = 0; y < jsonSlideDataArray.size(); y++) {
//                    JsonObject slideDataJso = jsonSlideDataArray.getJsonObject(y);
//                    // System.out.println(slideDataJso.toString());
//                    Slide slide = new Slide(slideDataJso.getString(JSON_SLIDE_IMAGE_FILE_NAME), slideDataJso.getString(JSON_SLIDE_IMAGE_PATH), slideDataJso.getString(JSON_SLIDE_CAPTION));
//                    slideShow.getSlides().add(slide);
//                }
//                slideShow.setSlideShowIndex(slideJso.getString(JSON_SLIDE_INDEX));
//                c.setSSM(slideShow);
//                c.setSlideshow(true);
//                page.getComponents().set(Integer.parseInt(slideShow.getSlideShowIndex()), c);
//                page.getSlideShows().add(slideShow);
//
//            }
            portfolioToLoad.getPages().add(page);
        }
    }

    // AND HERE ARE THE PRIVATE HELPER METHODS TO HELP THE PUBLIC ONES
    private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
        InputStream is = new FileInputStream(jsonFilePath);
        JsonReader jsonReader = Json.createReader(is);
        JsonObject json = jsonReader.readObject();
        jsonReader.close();
        is.close();
        return json;
    }

    private JsonObject makePageJsonObject(Page page) {
        JsonArray headingsJsonArray = makeHeadingsJsonArray(page.getHeadings());
        JsonArray paragraphsJsonArray = makeParagraphsJsonArray(page.getParagraphs());
        JsonArray imagesJsonArray = makeImagesJsonArray(page.getImages());
        JsonArray videosJsonArray = makeVideosJsonArray(page.getVideos());
        JsonArray listsJsonArray = makeListModelsJsonArray(page.getLists());
        JsonArray slidesJsonArray = makeSlideModelsJsonArray(page.getSlideShows());
        JsonObject jso = Json.createObjectBuilder()
                .add(JSON_BANNER_IMAGE_FILE_NAME, page.getBannerImageFileName())
                .add(JSON_BANNER_IMAGE_PATH, page.getBannerImageFilePath())
                .add(JSON_PAGE_TITLE, page.getPagetitle())
                .add(JSON_PAGE_FONT, page.getFont())
                .add(JSON_PAGE_LAYOUT, page.getLayout())
                .add(JSON_PAGE_COLOR, page.getColor())
                .add(JSON_PAGE_FOOTER, page.getFooter())
                .add(JSON_COMPONENT_COUNT, page.getComponentsSize())
                .add(JSON_HEADINGS, headingsJsonArray)
                .add(JSON_PARAGRAPHS, paragraphsJsonArray)
                .add(JSON_IMAGES, imagesJsonArray)
                .add(JSON_VIDEOS, videosJsonArray)
                .add(JSON_LISTS, listsJsonArray)
                .add(JSON_SLIDES, slidesJsonArray)
                .build();
        return jso;
    }

    private JsonArray makeHeadingsJsonArray(List<Heading> headings) {
        JsonArrayBuilder jsb = Json.createArrayBuilder();
        for (Heading heading : headings) {
            JsonObject jso = makeHeadingJsonObject(heading);
            jsb.add(jso);
        }
        JsonArray jA = jsb.build();
        return jA;
    }

    private JsonArray makeParagraphsJsonArray(List<Paragraph> paragraphs) {
        JsonArrayBuilder jsb = Json.createArrayBuilder();
        for (Paragraph paragraph : paragraphs) {
            JsonObject jso = makeParagraphJsonObject(paragraph);
            jsb.add(jso);
        }
        JsonArray jA = jsb.build();
        return jA;
    }

    private JsonArray makeListModelsJsonArray(List<ListModel> listModels) {
        JsonArrayBuilder jsb = Json.createArrayBuilder();
        for (ListModel listModel : listModels) {
            JsonObject jso = makeListModelJsonObject(listModel);
            jsb.add(jso);
        }
        JsonArray jA = jsb.build();
        return jA;
    }

    private JsonArray makeSlideModelsJsonArray(List<SlideShowModel> slideShows) {
        JsonArrayBuilder jsb = Json.createArrayBuilder();
        for (SlideShowModel slideShow : slideShows) {
            JsonObject jso = makeSlideModelJsonObject(slideShow);
            jsb.add(jso);
        }
        JsonArray jA = jsb.build();
        return jA;
    }

    private JsonArray makeImagesJsonArray(List<Image> images) {
        JsonArrayBuilder jsb = Json.createArrayBuilder();
        for (Image image : images) {
            JsonObject jso = makeImageJsonObject(image);
            jsb.add(jso);
        }
        JsonArray jA = jsb.build();
        return jA;
    }

    private JsonArray makeVideosJsonArray(List<Video> videos) {
        JsonArrayBuilder jsb = Json.createArrayBuilder();
        for (Video video : videos) {
            JsonObject jso = makeVideoJsonObject(video);
            jsb.add(jso);
        }
        JsonArray jA = jsb.build();
        return jA;
    }

    private JsonArray makePagesJsonArray(List<Page> pages) {
        JsonArrayBuilder jsb = Json.createArrayBuilder();
        for (Page page : pages) {
            JsonObject jso = makePageJsonObject(page);
            jsb.add(jso);
        }

        JsonArray jA = jsb.build();
        return jA;
    }

    private JsonArray makeListsJsonArray(List<ListObject> lists) {
        JsonArrayBuilder jsb = Json.createArrayBuilder();
        for (ListObject list : lists) {
            JsonObject jso = makeListJsonObject(list);
            jsb.add(jso);
        }
        JsonArray jA = jsb.build();
        return jA;
    }

    private JsonObject makeHeadingJsonObject(Heading heading) {
        JsonObject jso = Json.createObjectBuilder()
                .add(JSON_HEADING_TEXT, heading.getHeadingText())
                .add(JSON_HEADING_INDEX, heading.getIndex())
                .build();
        return jso;
    }

    private JsonObject makeImageJsonObject(Image image) {
        JsonObject jso = Json.createObjectBuilder()
                .add(JSON_IMAGE_FILE_NAME, image.getImageFileName())
                .add(JSON_IMAGE_PATH, image.getImagePath())
                .add(JSON_IMAGE_CAPTION, image.getCaption())
                .add(JSON_IMAGE_HEIGHT, image.getHeight())
                .add(JSON_IMAGE_WIDTH, image.getWidth())
                .add(JSON_IMAGE_POSITION, image.getImagePosition())
                .add(JSON_IMAGE_INDEX, image.getImageIndex())
                .build();
        return jso;
    }

    private JsonObject makeListJsonObject(ListObject list) {
        JsonObject jso = Json.createObjectBuilder()
                .add(JSON_LIST_STRING, list.getListString())
                .build();
        return jso;
    }

    private JsonObject makeListModelJsonObject(ListModel listModel) {
        JsonArray listsJsonArray = makeListsJsonArray(listModel.getListData());
        JsonObject jso = Json.createObjectBuilder()
                .add(JSON_LIST_INDEX, listModel.getListIndex())
                .add(JSON_LIST, listsJsonArray)
                .build();
        return jso;
    }

    private JsonObject makeSlideModelJsonObject(SlideShowModel slideShow) {
        JsonArray slidesJsonArray = makeSlidesJsonArray(slideShow.getSlides());
        JsonObject jso = Json.createObjectBuilder()
                .add(JSON_SLIDE_INDEX, slideShow.getSlideShowIndex())
                .add(JSON_SLIDE, slidesJsonArray)
                .build();
        return jso;
    }

    private JsonObject makeParagraphJsonObject(Paragraph paragraph) {
        JsonObject jso = Json.createObjectBuilder()
                .add(JSON_PARAGRAPH_TEXT, paragraph.getParagraphText())
                .add(JSON_PARAGRAPH_FONT, paragraph.getFontToggle())
                .add(JSON_PARAGRAPH_INDEX, paragraph.getParagraphIndex())
                .build();
        return jso;
    }

    private JsonObject makeSlideJsonObject(Slide slide) {
        JsonObject jso = Json.createObjectBuilder()
                .add(JSON_SLIDE_IMAGE_FILE_NAME, slide.getImageFileName())
                .add(JSON_SLIDE_IMAGE_PATH, slide.getImagePath())
                .add(JSON_SLIDE_CAPTION, slide.getCaption())
                .build();
        return jso;
    }

    private JsonObject makeVideoJsonObject(Video video) {
        JsonObject jso = Json.createObjectBuilder()
                .add(JSON_VIDEO_FILE_NAME, video.getVideoFileName())
                .add(JSON_VIDEO_FILE_PATH, video.getVideoPath())
                .add(JSON_VIDEO_CAPTION, video.getCaption())
                .add(JSON_VIDEO_HEIGHT, video.getHeight())
                .add(JSON_VIDEO_WIDTH, video.getWidth())
                .add(JSON_VIDEO_INDEX, video.getVideoIndex())
                .build();
        return jso;
    }

    private JsonArray makeSlidesJsonArray(List<Slide> slides) {
        JsonArrayBuilder jsb = Json.createArrayBuilder();
        for (Slide slide : slides) {
            JsonObject jso = makeSlideJsonObject(slide);
            jsb.add(jso);
        }
        JsonArray jA = jsb.build();
        return jA;
    }

}
