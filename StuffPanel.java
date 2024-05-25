import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.Color;

public class StuffPanel {
    private JScrollPane stuffBoxScrollable;
    
    StuffPanel(Stuff[] stuffBundle) {
    	JPanel stuffBox = new JPanel();
        stuffBox.setLayout(new BoxLayout(stuffBox, BoxLayout.PAGE_AXIS));
        
        // prevent NullPointerException on empty stuffBundle
        if(stuffBundle == null) {
        	Stuff[] emptyStuffBundle = { new Stuff("", "", "") };
        	stuffBundle = emptyStuffBundle;
        }
        
    	for(Stuff stuff : stuffBundle) {
            JPanel stuffPanel = new JPanel();
            stuffPanel.setLayout(new BoxLayout(stuffPanel, BoxLayout.PAGE_AXIS));
            
            JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            typePanel.add(new JLabel("Type"));
            JTextField typeField = new JTextField(stuff.type, 20);
            typeField.setEditable(false);
            typePanel.add(typeField);
            stuffPanel.add(typePanel);
            
            JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            namePanel.add(new JLabel("Name"));
            JTextField nameField = new JTextField(stuff.name, 20);
            nameField.setEditable(false);
            namePanel.add(nameField);
            stuffPanel.add(namePanel);
            
            JPanel tagsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            tagsPanel.add(new JLabel("Tags"));
            JTextField tagsField = new JTextField(stuff.tags, 20);
            tagsField.setEditable(false);
            tagsPanel.add(tagsField);
            stuffPanel.add(tagsPanel);
            
            stuffPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add border
            stuffBox.add(stuffPanel);
            
        }

        
        stuffBoxScrollable = new JScrollPane(stuffBox);
        stuffBoxScrollable.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    }
    
    public JScrollPane getStuffPanel() {
        return stuffBoxScrollable;
    }
    
}
