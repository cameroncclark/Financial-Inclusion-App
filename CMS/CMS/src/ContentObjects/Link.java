package ContentObjects;

public class Link {
	protected String name;
	protected String blurb;
	protected String website;
	
	public Link(){
		
	}
	
	public Link(String name, String blurb, String website){
		this.name = name;
		this.blurb = blurb;
		this.website = website;
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

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
	
	
	
}
