package bg.stefan.demonlp.model;

import java.util.Map;

public class NamedEntity {
	
	private String name;
	private String type;
	private int start;
	private int end; 
	private Map<String, Double> confidence;	

	public Map<String, Double> getConfidence() {
		return confidence;
	}
	public void setConfidence(Map<String, Double> confidence) {
		this.confidence = confidence;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	
	

}
