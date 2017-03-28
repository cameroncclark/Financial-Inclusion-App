package ContentObjects;

public class Tip {
	protected String header;
	protected String tip;
	
	public Tip() {
		// TODO Auto-generated constructor stub
	}
	
	public Tip(String header, String tip){
		this.header = header;
		this.tip = tip;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}
	
	
}
