package ContentObjects;

public class Category {
	String name;
	int ID;
	
	public Category(String name, int ID) {
		this.name = name;
		this.ID = ID;
	}
	
	public Category() {
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return ID;
	}
	public void setId(int ID) {
		this.ID = ID;
	}

}
