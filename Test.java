import javax.swing.*;
import java.awt.*;

public class Test {

    public static void main(String[] args) throws Exception {
        // Create a JFrame instance
        JFrame frame = new JFrame("PictureGUI test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set size to screen height
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(700, screenSize.height);
        
        frame.add(new JButton("Show All Pictures"), BorderLayout.NORTH);
        
        //1. make a PictureList
        PictureList a = new PictureList("/Users/dylan/picture-normal-gui.data");
        //2. feed PictureList to PictureSection
        JScrollPane ps = new PictureSection(a).getSection();

        // Add the main panel to the frame
        frame.add(ps, BorderLayout.CENTER);
        
        JPanel sideBar = new JPanel();
        sideBar.setLayout(new GridLayout(5,1));
        sideBar.add(new JButton("ADD"));
        sideBar.add(new JButton("DELETE"));
        sideBar.add(new JButton("LOAD"));
        sideBar.add(new JButton("SAVE"));
        sideBar.add(new JButton("SEARCH"));
        
        frame.add(sideBar, BorderLayout.EAST);
        

        // Set the frame visibility to true
        frame.setVisible(true);
    }
}
