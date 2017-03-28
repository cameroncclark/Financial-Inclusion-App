package ContentObjects;

public class Number {
	protected String name;
	protected String blurb;
	protected String number;
	
	public Number() {
	}
	
	public Number(String name, String blurb, String number){
		this.name = name;
		this.blurb = blurb;
		this.number = number;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBlurb() {
		return blurb;
	}
	public void setBlurb(String blurb) {
		this.blurb = blurb;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	
}
