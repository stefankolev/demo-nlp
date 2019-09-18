
package bg.stefan.demonlp;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import bg.stefan.demonlp.model.NamedEntity;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

@Service
public class StanfordNLP {
	
	Logger log = LoggerFactory.getLogger(StanfordNLP.class);
	
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	Properties nlpProperties;

	public List<NamedEntity> ner(String source) {
		
		log.info("NLP Properties: {}", nlpProperties);
		StanfordCoreNLP pipeline = new StanfordCoreNLP(nlpProperties);
		// make an example document
		CoreDocument doc = new CoreDocument(source);
		// annotate the document
		pipeline.annotate(doc);
		// view results
		return doc.entityMentions().stream()
			.map(cem -> {
				NamedEntity ner = new NamedEntity();
				ner.setConfidence( cem.entityTypeConfidences());
				ner.setEnd(cem.charOffsets().second);
				ner.setStart(cem.charOffsets().first);
				ner.setName(cem.text());
				ner.setType(cem.entityType());
				return ner;
			}).collect(toList());
	
	}

}
