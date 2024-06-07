import javax.swing.JFrame;
// import javax.swing.JScrollPane;

public class SharedState {
    static public JFrame mainFrameRef;
    // static public JScrollPane lastPictureSectionRef;

    static private PictureList defaultPictureList;
    static private PictureList currentPictureList; //in case of SEARCH function, where new PL instance is returned
    static private boolean isFirstInit = true;

    static void changeState() {
        // DONE: update() PictureSection
        // DONE: for this, PS must be static
        PictureSection.update(currentPictureList);
    }

    static void setDefaultPictureList(PictureList pl) {
        if(isFirstInit) {
            defaultPictureList = pl;
            currentPictureList = defaultPictureList;
            // isFirstInit = false;
        }
    }
    
    static void setWorkingPictureList(PictureList pl) {
        currentPictureList = pl;
        changeState();
    }

    static void resetWorkingPictureList() {
        currentPictureList = defaultPictureList;
        changeState();
    }

    static PictureList getDefaultPictureList() {
        return defaultPictureList;
    }

    static PictureList getWorkingPictureList() {
        return currentPictureList;
    }
}
