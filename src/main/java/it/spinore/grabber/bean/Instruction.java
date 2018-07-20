package it.spinore.grabber.bean;

public class Instruction {

	//public field
	private String url;
	private String triggerString;
	private int elementNumber;
	private String htmlTag;
	
	
	
	
	
	public Instruction(String url, String triggerString, int elementNumber, String htmlTag) {
		super();
		this.url = url;
		this.triggerString = triggerString;
		this.elementNumber = elementNumber;
		this.htmlTag = htmlTag;
	}
	public Instruction() {
		// TODO Auto-generated constructor stub
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTriggerString() {
		return triggerString;
	}
	public void setTriggerString(String triggerString) {
		this.triggerString = triggerString;
	}
	public int getElementNumber() {
		return elementNumber;
	}
	public void setElementNumber(int elementNumber) {
		this.elementNumber = elementNumber;
	}
	public String getHtmlTag() {
		return htmlTag;
	}
	public void setHtmlTag(String htmlTag) {
		this.htmlTag = htmlTag;
	}
	
	
	
	


}
