import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Comparator;
import java.io.FileWriter;
import java.io.IOException;
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