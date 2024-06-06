import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.TitledBorder;
// import java.util.ArrayList;

class AddPictureFrame extends JFrame {
	private JTextField timeField;
	private JTextField tagsField;
	private JTextField commentField;
	private String pathToImage;
    // private ArrayList<Stuff> StuffArr= new ArrayList<Stuff>();
	
    public AddPictureFrame() {
        super("Add a Picture");
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Time"));
        timeField = new JTextField(15);
        topPanel.add(timeField);
        topPanel.add(new JLabel("(Picture) Tags"));
        tagsField = new JTextField(15);
        topPanel.add(tagsField);
        add(topPanel, BorderLayout.NORTH);

        JButton selectImageButton = new JButton("Select Image File");
        selectImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    pathToImage = fileChooser.getSelectedFile().getAbsolutePath();
                }
            }
        });
        add(selectImageButton, BorderLayout.CENTER);
        add(new StuffPanel(null).getStuffPanel(), BorderLayout.EAST);

        //TODO: fix - commentsPanel not visible!!
        JPanel commentsPanel = new JPanel();
        commentsPanel.add(new JLabel("Comments"));
        commentsPanel.add(new JTextField(20));
        add(commentsPanel, BorderLayout.SOUTH);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(new JButton("More Stuff"), BorderLayout.WEST);
        JButton submitBtn = new JButton("OK - Input End");
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addPictureToPictureList();
                } catch (Exception exp) {
                    System.err.println(exp);
                }
                dispose();
            }
        });
        bottomPanel.add(submitBtn, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        this.pack();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    void addPictureToPictureList() throws Exception {
        String timeString = "";
        String tagsString = "";
        String commentString = "nocomment";

        try {
            timeString = this.timeField.getText();
            tagsString = this.tagsField.getText();
            // commentString = this.commentField.getText();
        }
        catch (Exception e) {
            System.out.println(e + "One or more fields are null");
        }
        
        System.out.println("loloolala"+timeString+tagsString+commentString+pathToImage);
        Picture p = new Picture(pathToImage, timeString, tagsString, commentString);
        PictureList pl = SharedState.getWorkingPictureList();
        p.print();
        pl.add(p);
        SharedState.setWorkingPictureList(pl);
    }
}

class SearchPictureFrame extends JFrame {
    public SearchPictureFrame() {
        super("Search Picture");
        // Create the Time Search panel with a titled border
        JPanel timeSearchPanel = new JPanel();
        timeSearchPanel.setBorder(new TitledBorder("Time Search"));
        timeSearchPanel.setLayout(new GridLayout(2, 3, 5, 5));
        timeSearchPanel.add(new JLabel("From"));
        timeSearchPanel.add(new JTextField());
        timeSearchPanel.add(new JLabel("(yyyy-MM-dd_HH:mm:ss)"));
        timeSearchPanel.add(new JLabel("To"));
        timeSearchPanel.add(new JTextField());

        // Create the Keyword Search panel with a titled border
        JPanel keywordSearchPanel = new JPanel();
        keywordSearchPanel.setBorder(new TitledBorder("Keyword Search"));
        keywordSearchPanel.setLayout(new BoxLayout(keywordSearchPanel, BoxLayout.PAGE_AXIS));
        
        JPanel detailSearchPanel = new JPanel();
        

        JPanel metaSearchPanel = new JPanel();
        metaSearchPanel.setLayout(new GridLayout(2, 2));
        metaSearchPanel.add(new JLabel("Tags"));
        metaSearchPanel.add(new JTextField(10));
        metaSearchPanel.add(new JLabel("Comments"));
        metaSearchPanel.add(new JTextField(10));
        detailSearchPanel.add(metaSearchPanel, BorderLayout.WEST);

        JPanel stuffSearchPanel = new JPanel();
        stuffSearchPanel.setLayout(new GridLayout(3, 2, 1, 5));
        stuffSearchPanel.add(new JLabel("Type"));
        stuffSearchPanel.add(new JTextField(10));
        stuffSearchPanel.add(new JLabel("Name"));
        stuffSearchPanel.add(new JTextField(10));
        stuffSearchPanel.add(new JLabel("Tags"));
        stuffSearchPanel.add(new JTextField(10));
        detailSearchPanel.add(stuffSearchPanel, BorderLayout.EAST);

        JPanel generalSearchPanel = new JPanel();
        generalSearchPanel.add(new JLabel("General Search"));
        generalSearchPanel.add(new JTextField(10));
        
        keywordSearchPanel.add(detailSearchPanel, BorderLayout.CENTER);
        keywordSearchPanel.add(generalSearchPanel, BorderLayout.SOUTH);

        // Create the buttons panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.add(new JButton("AND SEARCH"));
        buttonsPanel.add(new JButton("OR SEARCH"));
        buttonsPanel.add(new JButton("CLOSE"));

        // Add the panels to the main frame
        add(timeSearchPanel, BorderLayout.NORTH);
        add(keywordSearchPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);

        // Display the frame
        this.pack();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}

public class Test {
    public static void main(String[] args) throws Exception {
        // Create a JFrame instance
        JFrame frame = new JFrame("PictureGUI test");
        //TODO: add reference to main frame
        SharedState.mainFrameRef = frame;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set size to screen height
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(700, screenSize.height);
        
        frame.add(new JButton("Show All Pictures"), BorderLayout.NORTH);
        
        //1. make a PictureList
        SharedState.setDefaultPictureList(new PictureList("static/picture-normal-gui.data"));
        //2. feed PictureList to PictureSection
        // PictureSection ps = new PictureSection(SharedState.getWorkingPictureList());
        // JScrollPane scrollablePictureSection = ps.getSection();
        PictureSection.update(SharedState.getWorkingPictureList());
        //이러지말고 PictureSection에 update메소드를 만들어서 
        //나중에 PictureList 바뀌게되면 update이랑 getSection 개별적으로 다시. (더 나은방법 없나??)

        // Add the main panel to the frame
        frame.add(PictureSection.scrollablePictureSection, BorderLayout.CENTER);
        
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
        JButton searchPictureButton = new JButton("SEARCH");
        searchPictureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create and display the new JFrame
                SearchPictureFrame f = new SearchPictureFrame();
                f.setVisible(true);
            }
        });
        sideBar.add(searchPictureButton);
        
        frame.add(sideBar, BorderLayout.EAST);
        

        // Set the frame visibility to true
        frame.setVisible(true);
    }
}
