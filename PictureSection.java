import java.awt.Color;
import javax.swing.*;

public class PictureSection {
	JScrollPane pictureSection;
	
	PictureSection(PictureList plist) {
		JPanel pictures = new JPanel();
		pictures.setLayout(new BoxLayout(pictures, BoxLayout.PAGE_AXIS)); // Arrange pictures vertically
		for(int i=0; i<plist.size(); i++) {
			JPanel singlePicturePanel = new PicturePanel(plist.get(i)).getPanel();
			singlePicturePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			pictures.add(singlePicturePanel);
		}
		pictureSection = new JScrollPane(pictures);
	}
	
	JScrollPane getSection() {
		return pictureSection;
	}
}
