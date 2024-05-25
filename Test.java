import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class AddPictureFrame extends JFrame {
    public AddPictureFrame() {
        super("Add a Picture");
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Time"));
        topPanel.add(new JTextField(20));
        topPanel.add(new JLabel("(Picture) Tags"));
        topPanel.add(new JTextField(20));
        add(topPanel, BorderLayout.NORTH);

        add(new JButton("Select Image File"), BorderLayout.CENTER);
        add(new StuffPanel(null).getStuffPanel(), BorderLayout.EAST);

        JPanel commentsPanel = new JPanel();
        commentsPanel.add(new JLabel("Comments"));
        commentsPanel.add(new JTextField(20));
        add(commentsPanel, BorderLayout.SOUTH);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(new JButton("More Stuff"), BorderLayout.WEST);
        bottomPanel.add(new JButton("OK - Input End"), BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        this.pack();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}

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
        JButton addPictureBtn = new JButton("ADD");
        addPictureBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create and display the new JFrame
                AddPictureFrame f = new AddPictureFrame();
                f.setVisible(true);
            }
        });
        sideBar.add(addPictureBtn);
        sideBar.add(new JButton("DELETE"));
        sideBar.add(new JButton("LOAD"));
        sideBar.add(new JButton("SAVE"));
        sideBar.add(new JButton("SEARCH"));
        
        frame.add(sideBar, BorderLayout.EAST);
        

        // Set the frame visibility to true
        frame.setVisible(true);
    }
}
