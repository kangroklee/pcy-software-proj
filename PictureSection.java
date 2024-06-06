import java.awt.Color;
import javax.swing.*;

public class PictureSection {
	static JScrollPane scrollablePictureSection;
	static private JFrame mainFrameRef;
	
	// PictureSection(PictureList plist) {
	// 	JPanel pictures = new JPanel();
	// 	pictures.setLayout(new BoxLayout(pictures, BoxLayout.PAGE_AXIS)); // Arrange pictures vertically
	// 	for(int i=0; i<plist.size(); i++) {
	// 		JPanel singlePicturePanel = new PicturePanel(plist.get(i)).getPanel();
	// 		singlePicturePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	// 		pictures.add(singlePicturePanel);
	// 	}
	// 	pictureSection = new JScrollPane(pictures);
	// }
	
	// JScrollPane getSection() {
	// 	return pictureSection;
	// }

	static void setMainFrameRef(JFrame f) {
		mainFrameRef = f;
	}

	static JFrame getMainFrameRef() {
		return mainFrameRef;
	}

	static public void update(PictureList plist) {
		JPanel pictures = new JPanel();
		pictures.setLayout(new BoxLayout(pictures, BoxLayout.PAGE_AXIS)); // Arrange pictures vertically
		for(int i=0; i<plist.size(); i++) {
			JPanel singlePicturePanel = new PicturePanel(plist.get(i)).getPanel();
			singlePicturePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			pictures.add(singlePicturePanel);
		}
		// pictures.repaint();
		scrollablePictureSection = new JScrollPane(pictures);
		scrollablePictureSection.repaint();
		SharedState.mainFrameRef.revalidate();
		// pictureSection.setViewportView(pictures);
	}
}
