import java.awt.*;
import javax.swing.*;

public class PicturePanel {
	private JPanel picture;
	
	PicturePanel(Picture p) {
		picture = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		/*Line1: timestamp, pictureTags*/
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		picture.add(new JLabel(p.getTimestamp().toString()), gbc);

		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		JTextField tagsField = new JTextField(p.getTags(), 20);
		tagsField.setEditable(false);
		picture.add(tagsField, gbc);
		
		/*Line2: image, stuffs*/
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		ImageIcon imageIcon = new ImageIcon(p.getPathtoImage());
		Image orig = imageIcon.getImage();
		Image resized = orig.getScaledInstance(259, 194, 0);
		imageIcon = new ImageIcon(resized);
		
		JLabel image = new JLabel(imageIcon);
		picture.add(image, gbc);

		gbc.gridx = 1;
		
		JScrollPane stuffBundle = new StuffPanel(p.getRawStuffBundle()).getStuffPanel();
		stuffBundle.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		picture.add(stuffBundle, gbc);
		
		/*Line3: comment*/
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2; // Span across 2 columns
		gbc.anchor = GridBagConstraints.LAST_LINE_START;
		picture.add(new JLabel(p.getComment()), gbc);
	}
	
	
	public JPanel getPanel() {
		return picture;
	}
}