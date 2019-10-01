package bg.stefan.demonlp.model;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NamedEntity {
	
	private String name;
	private String type;
	private int start;
	private int end; 
	private Map<String, Double> confidence;	
	

}
