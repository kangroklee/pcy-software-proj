import java.awt.Color;
import javax.swing.*;

public class PictureSection {
	// TODO find better way than using prevScrollable
	static JScrollPane prevScrollablePictureFrame;
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
		scrollablePictureSection = new JScrollPane(pictures);


		// TODO: remove old scrollablePictureSection, add new. 
		// Remove and Add the updated scrollablePictureSection to the mainFrameRef

		// TRY1
		// getMainFrameRef().remove(scrollablePictureSection);

		// TRY2: this eliminates need for having scrollablePictureSection, however not very clean
		// for (Component comp : getMainFrameRef().getComponents()) {
		// 	if (comp instanceof JScrollPane) {
		// 		getMainFrameRef().remove(comp);
		// 	}
		// }

		// TRY3
		// System.out.println("num of components in main frame: "+getMainFrameRef().getContentPane().getComponents().length);
		
		if(SharedState.lastPictureSectionRef != null) {
			System.out.println(SharedState.lastPictureSectionRef);
			getMainFrameRef().remove(SharedState.lastPictureSectionRef);
		}
		getMainFrameRef().add(scrollablePictureSection);
		SharedState.lastPictureSectionRef = scrollablePictureSection;
		// Revalidate and repaint the mainFrameRef
		getMainFrameRef().revalidate();
		getMainFrameRef().repaint();
	}

}
