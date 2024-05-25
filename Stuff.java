
public class Stuff {
	//[00000003; toolbox; my toolbox; #red ]
	int stuffId;
	String type;
	String name;
	String tags;
	
	//used after init in StuffList
	Stuff(int stuffId, String type, String name, String tags) {
		this.stuffId = stuffId;
		this.type = type;
		this.name = name;
		this.tags = tags;
	}
	
	//used before init in StuffList
	Stuff(String type, String name, String tags) {
		this.type = type;
		this.name = name;
		this.tags = tags;
	}
	
}
