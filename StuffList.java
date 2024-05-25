
public class StuffList {
	//about this size will suffice i think?
	private static Stuff[] stuffArr = new Stuff[100];
	private static int stuffCount = 0;
	private static int id = 0;
	
	public static void setStuffArr(Stuff s) {
		boolean isDuplicateEntry = false;
		for(int i=0;i<stuffCount;i++) {
			if (stuffArr[i].type.equals(s.type) && stuffArr[i].name.equals(s.name)) {
				isDuplicateEntry = true;
				break;
			}
		}
		if(!isDuplicateEntry) {
			stuffArr[stuffCount] = new Stuff(getNewId(), s.type, s.name, s.tags);
			addStuffCount();
		}
	}
	
	static int getStuffCount() {
		return stuffCount;
	}
	
	static void addStuffCount() {
		stuffCount++;
	}

	private static int getNewId() {
		id++;
		return id;
	}
	
	public static void print() {
		if(stuffCount==0) {
			System.out.println("stuff list is empty");
		}

		for(int i=0;i<stuffCount;i++) {
			System.out.print(" stuffId: "+stuffArr[i].stuffId);
			System.out.print(" type: "+stuffArr[i].type);
			System.out.print(" name: "+stuffArr[i].name);
			System.out.println(" tags: "+stuffArr[i].tags);
		}
	}
}
