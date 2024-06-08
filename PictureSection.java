import java.awt.Color;
import javax.swing.*;

public class PictureSection {
	static JScrollPane scrollablePictureSection;
	static private JFrame mainFrameRef;

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

		// DONE: remove old scrollablePictureSection, add new. 
		// removal is done beforehand on each sideBar on mainframe click.
		getMainFrameRef().add(scrollablePictureSection);

		// Revalidate and repaint the mainFrameRef
		getMainFrameRef().revalidate();
		getMainFrameRef().repaint();
	}

}
