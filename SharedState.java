import javax.swing.JFrame;

public class SharedState {
    static public JFrame mainFrameRef;

    static private PictureList defaultPictureList;
    static private PictureList currentPictureList; //in case of SEARCH function, where new PL instance is returned
    static private boolean isFirstInit = true;

    static void changeState() {
        // DONE: update() PictureSection
        // DONE: for this, PS must be static
        PictureSection.update(getWorkingPictureList());
    }

    static void setDefaultPictureList(PictureList pl) {
        if(isFirstInit) {
            defaultPictureList = pl;
            currentPictureList = defaultPictureList;
            isFirstInit = false;
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
