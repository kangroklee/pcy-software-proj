import javax.swing.*;
import java.awt.*;

public class Test {

    public static void main(String[] args) throws Exception {
        // Create a JFrame instance
        JFrame frame = new JFrame("PictureSection test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set size to screen height
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(600, screenSize.height);
        
        
//        Picture p = new Picture("< m_2024-01-03_10:00:00:000 > < 2024-01-03_10:00:00:000 > < IMG2024-01-07_15:33:00:827; images/toolbox.jpg; ;  > <  [00000003; toolbox; my toolbox; #red ] [00000004; tool; wrench; ]> < #home #clean > <>");
//        JPanel picturePanel = new PicturePanel(p).getPanel();
        
        //1. make a PictureList
        PictureList a = new PictureList("/Users/dylan/picture-normal-gui.data");
        //2. feed PictureList to PictureSection
        JScrollPane ps = new PictureSection(a).getSection();

        // Add the main panel to the frame
//        frame.add(picturePanel);
        frame.add(ps, BorderLayout.CENTER);

        // Set the frame visibility to true
        frame.setVisible(true);
    }
}
