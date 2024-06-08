import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Comparator;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PictureList {
	private Picture[] pictureArr = new Picture[100];
	private int numOfPics = 0;
	
	PictureList() {
	}
	
	PictureList(String infoFileName) {
		File file = new File(infoFileName);
		try {
			Scanner input = new Scanner(file);
			while(input.hasNext()) {
				String line = input.nextLine();
				if(line.startsWith("//")) {
					continue;
				}
				try {
					Picture p = new Picture(line);
					add(p);
				} catch(Exception e) {
					continue;
				}
			}
			input.close();
		} 
		catch (java.io.FileNotFoundException e) {
			System.err.println("Unable to find input file.");
		}
		catch(java.lang.Exception e) {
			System.err.println(e);
		} 
		
	}
	
	public int size() {
		return numOfPics;
	}
	
	public Picture get(int i) {
		return pictureArr[i];
	}
	
	//add(): appends Picture instance to array.
	public void add(Picture pic) throws Exception {
		for(int i=0;i<numOfPics;i++) {
			if(pictureArr[i].getId().equals(pic.getId())) {
				System.err.println("ID Conflict, "+pic.getId()+" | "+pic.getPathtoImage() +" already exists.");
	            throw new Exception("ID Conflict (a picture with the same ID already exists)");
			}
		}
		pictureArr[numOfPics] = pic;
		numOfPics++;
	}
	
	public void sortByDate() {
		Picture[] sortedPictureArr = Arrays.copyOf(this.pictureArr, this.numOfPics);
		Arrays.sort(sortedPictureArr, new Comparator<Picture>() {
			@Override
			public int compare(Picture p1, Picture p2) {
				return p1.getTimestamp().compareTo(p2.getTimestamp());
			}
		});
		this.pictureArr = sortedPictureArr;
	}

	public PictureList getPicturesInRange(LocalDateTime start, LocalDateTime end) {
		PictureList picturesInRange = new PictureList();
		for (int i = 0; i < numOfPics; i++) {
			LocalDateTime pictureTime = pictureArr[i].getTimestamp();
			if ((pictureTime.isEqual(start) || pictureTime.isAfter(start)) && (pictureTime.isEqual(end) || pictureTime.isBefore(end))) {
				try {
					picturesInRange.add(pictureArr[i]);
				} catch (Exception e) {
					continue;
				}
				
			}
		}
		return picturesInRange;
	}

	public PictureList getPicturesAfter(LocalDateTime time) {
		PictureList picturesAfter = new PictureList();
		for (int i = 0; i < numOfPics; i++) {
			LocalDateTime pictureTime = pictureArr[i].getTimestamp();
			if ((pictureTime.isEqual(time) || pictureTime.isAfter(time))) {
				try {
					picturesAfter.add(pictureArr[i]);
				} catch (Exception e) {
					continue;
				}
			}
		}
		return picturesAfter;
	}

	public PictureList getPicturesBefore(LocalDateTime time) {
		PictureList picturesBefore = new PictureList();
		for (int i = 0; i < numOfPics; i++) {
			LocalDateTime pictureTime = pictureArr[i].getTimestamp();
			if ((pictureTime.isEqual(time) || pictureTime.isBefore(time))) {
				try {
					picturesBefore.add(pictureArr[i]);
				} catch (Exception e) {
					continue;
				}
			}
		}
		return picturesBefore;
	}

	public PictureList getPicturesByTag(String key) {
		PictureList match = new PictureList();
		for (int i=0; i < numOfPics; i++) {
			if(pictureArr[i].getTags().contains(key)) {
				try {
					match.add(pictureArr[i]);
				} catch (Exception e) {
					continue;
				}
			}
		}
		// @TEST
		System.out.println(match);
		return match;
	}

	public PictureList getPicturesByComment(String key) {
		PictureList match = new PictureList();
		for (int i=0; i < numOfPics; i++) {
			if(pictureArr[i].getComment().contains(key)) {
				try {
					match.add(pictureArr[i]);
				} catch (Exception e) {
					continue;
				}
			}
		}
		// @TEST
		System.out.println(match);
		return match;
	}

	public PictureList getPicturesByStuffType(String key) {
		PictureList match = new PictureList();
		for (int i=0; i < numOfPics; i++) {
			Stuff[] stuffs = pictureArr[i].getRawStuffBundle();
			for (int j=0; j<stuffs.length; j++) {
				try {
					if(stuffs[j].type.equals(key)) {
						match.add(pictureArr[i]);
					}
				} catch (Exception e) {
					continue;
				}
			}
		}
		return match;
	}

	public PictureList getPicturesByStuffName(String key) {
		PictureList match = new PictureList();
		for (int i=0; i < numOfPics; i++) {
			Stuff[] stuffs = pictureArr[i].getRawStuffBundle();
			for (int j=0; j<stuffs.length; j++) {
				try {
					if(stuffs[j].name.equals(key)) {
						match.add(pictureArr[i]);
					}
				} catch (Exception e) {
					continue;
				}
			}
		}
		return match;
	}

	public PictureList getPicturesByStuffTags(String key) {
		PictureList match = new PictureList();
		for (int i=0; i < numOfPics; i++) {
			Stuff[] stuffs = pictureArr[i].getRawStuffBundle();
			for (int j=0; j<stuffs.length; j++) {
				try {
					if(stuffs[j].tags.contains(key)) {
						match.add(pictureArr[i]);
					}
				} catch (Exception e) {
					continue;
				}
			}
		}
		return match;
	}
	
	void printPictures() {
		for(int i=0;i<numOfPics;i++) {
			System.out.println("==="+i+"th Picture===");
			pictureArr[i].print();
		}
	}
	
	void exportListToFile() {
		/* Create File */
		try {
			File myObj = new File("picturelist_export.data");
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		/* Write Contents to File */
		try {
			FileWriter myWriter = new FileWriter("picturelist_export.data");
			for(int i=0;i<numOfPics;i++) {
				String id = pictureArr[i].getId();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss:SSS");
				String dateTimeString = pictureArr[i].getTimestamp().format(formatter);
				String imageId = pictureArr[i].getImageId();
				String pathToImage = pictureArr[i].getPathtoImage();
				String imageTags = pictureArr[i].getImageTags();
				String stuffBundle = pictureArr[i].getStuffBundle();
				String tags = pictureArr[i].getTags();
				
				myWriter.write("<"+id+"> <"+dateTimeString+"> <"+imageId+";"+pathToImage+";"+imageTags+";> <"+stuffBundle+"> <"+tags+">\n");
			}			
			myWriter.close();
			System.out.println("Successfully wrote to file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
	}
	
}