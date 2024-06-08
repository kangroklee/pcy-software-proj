import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class Picture {
	private String id;
	private LocalDateTime timestamp;
	private String imageID;
	private String pathToImage;
	private String imageTags;
	private Stuff[] stuffBundle;
	private String pictureTags;
	private String comment;
	

	//imageInfo is single line from picture-normal.data
	Picture (String imageInfo) throws Exception {
		String[] tokens = imageInfo.split(">"); 
		for (int i = 0; i < tokens.length; i++)
		    tokens[i] = tokens[i].replace('<', ' ');
		
		
		/* Identifier */
		this.id = tokens[0].trim();
		
		/* Timestamp */
		String timeString = tokens[1].trim();
		if (timeString.isEmpty()) {
			System.err.println("No timestamp in the input : "+imageInfo);
            throw new Exception("No timestamp in the input : "+imageInfo);
        }
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss:SSS");
		try {
			LocalDateTime parsedDate = LocalDateTime.parse(timeString, formatter);
			this.timestamp = parsedDate;
		} catch(DateTimeParseException e) {
			System.err.println("Invalid DateTime Format :"+timeString);
			throw new Exception("Invalid DateTime Format :"+timeString);
		}
		
	
		/* Image part*/
		String[] imageSegment = tokens[2].split(";");
		this.imageID = imageSegment[0].trim();
		this.pathToImage = imageSegment[1].trim();
		this.imageTags = imageSegment[2].trim();
		
		
		/* Stuff */
		if(!tokens[3].isBlank()) {
			String[] stuffSegment = tokens[3].split("]");
			stuffBundle = new Stuff[stuffSegment.length];
			for (int i = 0; i <stuffSegment.length; i++) {
				stuffSegment[i] = stuffSegment[i].replace('[', ' ');
				
				String[] stuffAttrib = stuffSegment[i].split(";");
				// assign id of each Stuff afterwards in StuffList -- no need to read id here.
				// String stuffId = stuffAttrib[0].trim();
				String type = stuffAttrib[1].trim();
				String name = stuffAttrib[2].trim();
				String tags = stuffAttrib[3].trim();; //this is temporary -- refactor to arr of class Tags later
				
				Stuff s = new Stuff(type, name, tags);
				stuffBundle[i] = s;
				StuffList.setStuffArr(s);
			}
			    
		}
		
		/* Tags */
		if(!tokens[4].isEmpty()) {
			this.pictureTags = tokens[4].trim();
		}
		
		/* Comment */
		if(!tokens[5].isEmpty() ) {
			this.comment = tokens[5].trim();
		}
	
	}
	
	Picture (String imageFileName, boolean hasExtraInfo) {
		try {
			File image = new File(imageFileName);
			Scanner imageInput = new Scanner(image);
			//add Image class in future
			imageInput.close();
		} 
		catch (Exception e) {
			System.err.println(e + "Unable to find image.");
		}
		
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss:SSS");
		String dateTimeString = now.format(formatter);
		
		this.id = "m_" + dateTimeString;
		this.timestamp = now;
		this.imageID = "IMG" + dateTimeString;
		this.pathToImage = imageFileName;
		if(hasExtraInfo) {
			//add functionality(Stuff, Tags etc) in future
		}
	}

	Picture(String pathString, String timeString, String tagString, String commentString, Stuff[] stuffBundle) throws Exception{
		/* timestamp, id*/
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss:SSS");
		try {
			LocalDateTime parsedDate = LocalDateTime.parse(timeString, formatter);
			this.timestamp = parsedDate;
			this.id = "m_" + timeString;
			this.imageID = "IMG" + timeString;
		} catch(DateTimeParseException e) {
			System.err.println("Invalid DateTime Format :"+timeString);
			throw new Exception("Invalid DateTime Format :"+timeString);
		}
		this.pathToImage = pathString;
		// DONE: get Stuff[] argument from StuffPanel
		// TODO: test if checking for null stuffBundles here(at the point of creation) is better 
		this.stuffBundle = stuffBundle;
		this.pictureTags = tagString;
		this.comment = commentString;
		
	}

	
	String getId() {
		return id;
	}
	
	LocalDateTime getTimestamp() {
		return timestamp;
	}
	
	String getPathtoImage() {
		return pathToImage;
	}
	
	String getImageId() {
		return imageID;
	}
	
	String getImageTags() {
		return imageTags;
	}
	
	String getStuffBundle() {
		String stuffs = "";
		
		if(stuffBundle != null) {
			for(int i=0;i<stuffBundle.length;i++) {
				if(stuffBundle[i] != null) {
					String name = stuffBundle[i].name != null ? stuffBundle[i].name : "";
					String type = stuffBundle[i].type != null ? stuffBundle[i].type : "";
					String tags = stuffBundle[i].tags != null ? stuffBundle[i].tags : "";
					String s = "[ ;"+name+";"+type+";"+tags+"] ";
					stuffs += s;
				}
			}
		}
		return stuffs;
	}
	
	Stuff[] getRawStuffBundle() {
		// DONE: fix NullPointerException when accessing empty stuffBundle
		if (stuffBundle == null) {
			stuffBundle = new Stuff[1];
		}
		return stuffBundle;
	}
	
	String getTags() {
		return pictureTags;
	}
	
	String getComment() {
		return comment;
	}
	
	void print() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss:SSS");
		System.out.println("id: "+id);
		System.out.println("timestamp: "+timestamp.format(formatter));
		System.out.println("imageID: "+imageID);
		System.out.println("filename: "+pathToImage);
		System.out.println("tags: "+imageTags);
		System.out.println("pictureTags: "+pictureTags+"\n");
	}
	
	void testPrintStuffBundle() {
		for(int i=0;i<stuffBundle.length;i++) {
			System.out.println("** "+(i+1)+"th stuff**");
			System.out.println("stuff_name: "+stuffBundle[i].name);
			System.out.println("stuff_type: "+stuffBundle[i].type);
			System.out.println("stuff_tags: "+stuffBundle[i].tags);
		}
	}

}
