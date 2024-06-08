/*Helper class*/

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PictureSearch {
	PictureList old;
	
	PictureSearch(PictureList old) {
		this.old = old;
	}
	
	// TODO: use Builder Pattern to simplify
	public PictureList andSearch(String start, String end, String tags, String comments, String stuffType, String stuffName, String stuffTags) {
		PictureList result = new PictureList();
		this.old = timeSearch(start, end);
		result = detailANDSearch(tags, comments, stuffType, stuffName, stuffTags);
		return result;
	}

	public PictureList orSearch(String start, String end, String tags, String comments, String stuffType, String stuffName, String stuffTags) {
		PictureList result = new PictureList();
		result = timeSearch(start, end);
		result = this._merge(result, detailORSearch(tags, comments, stuffType, stuffName, stuffTags));
		return result;
	}
	
	private PictureList timeSearch(String start, String end) {
		PictureList sorted = new PictureList();
		sorted = this._clone(old);
		if((!start.isEmpty()) && (!end.isEmpty())) {
			LocalDateTime startTime = this._stringToDateNoExceptions(start);
			LocalDateTime endTime = this._stringToDateNoExceptions(end);
			sorted = old.getPicturesInRange(startTime, endTime);
			return sorted;
		}
		if(!start.isEmpty()) {
			LocalDateTime startTime = this._stringToDateNoExceptions(start);
			sorted = old.getPicturesAfter(startTime);
			return sorted;
		}
		if(!end.isEmpty()) {
			LocalDateTime endTime = this._stringToDateNoExceptions(end);
			sorted = old.getPicturesBefore(endTime);
			return sorted;
		}
		//if nothing, return same output as input
		return sorted;
	}
	
	public PictureList detailANDSearch(String tags, String comments, String stuffType, String stuffName, String stuffTags) {
		PictureList sorted = new PictureList();
		sorted = this._clone(old);
		System.out.println("Number of Pictures before detailANDSearch: "+sorted.size());
		if(!tags.isEmpty()) {
			sorted = sorted.getPicturesByTag(tags);
			System.out.println("Number of Pictures after tags: "+sorted.size());
		}
		if(!comments.isEmpty()) {
			sorted = sorted.getPicturesByComment(comments);
			System.out.println("Number of Pictures after comments: "+sorted.size());
		}
		if(!stuffType.isEmpty()) {
			sorted = sorted.getPicturesByStuffType(stuffType);
			System.out.println("Number of Pictures after stuffType: "+sorted.size());
		}
		if(!stuffName.isEmpty()) {
			sorted = sorted.getPicturesByStuffName(stuffName);
			System.out.println("Number of Pictures after stuffName: "+sorted.size());
		}
		if(!stuffTags.isEmpty()) {
			sorted = sorted.getPicturesByStuffTags(stuffTags);
			System.out.println("Number of Pictures after stuffTags: "+sorted.size());
		}
		System.out.println("Number of Pictures after detailANDSearch: "+sorted.size());
		return sorted;
	}
	
	private PictureList detailORSearch(String tags, String comments, String stuffType, String stuffName, String stuffTags) {
		PictureList sorted = new PictureList();
		//does this work? or does sorted share same ref as old??
		sorted = this._clone(old);
		if(!tags.isEmpty()) {
			sorted = old.getPicturesByTag(tags);
		}
		if(!comments.isEmpty()) {
			sorted = this._merge(sorted, old.getPicturesByComment(comments));
		}
		if(!stuffType.isEmpty()) {
			sorted = this._merge(sorted, old.getPicturesByStuffType(stuffType));
		}
		if(!stuffName.isEmpty()) {
			sorted = this._merge(sorted, old.getPicturesByStuffName(stuffName));
		}
		if(!stuffTags.isEmpty()) {
			sorted = this._merge(sorted, old.getPicturesByStuffTags(stuffTags));
		}
		return sorted;
	}	

	private PictureList _clone(PictureList orig) {
		PictureList result = new PictureList();
		for(int i=0;i<orig.size();i++) {
			try {
				result.add(orig.get(i));
			} catch (Exception e) {
				System.out.println(e);
				continue;
			}
		}
		return result;
	}
	
	private PictureList _merge(PictureList first, PictureList second) {
		for(int i=0; i<second.size(); i++) {
			try {
				first.add(second.get(i));
			} catch(Exception e) {
				continue;
			}
		}
		return first;
	}
	
	private LocalDateTime _stringToDateNoExceptions(String s) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss:SSS");
		LocalDateTime parsedDate = LocalDateTime.parse(s, formatter);
		return parsedDate;
	}
}
