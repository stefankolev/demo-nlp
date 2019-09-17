package bg.stefan.demonlp;

import static java.util.stream.Collectors.joining;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Properties;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreEntityMention;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

@Service
public class StanfordNLP {

	public String ner(String source) {
		
		LocalTime timeStart = LocalTime.now();

		StringBuilder result = new StringBuilder();

		// set up pipeline properties
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner");
		// example customizations (these are commented out but you can uncomment them to
		// see the results

		// disable fine grained ner
		// props.setProperty("ner.applyFineGrained", "false");

		// customize fine grained ner
		// props.setProperty("ner.fine.regexner.mapping", "example.rules");
		// props.setProperty("ner.fine.regexner.ignorecase", "true");

		// add additional rules, customize TokensRegexNER annotator
		// props.setProperty("ner.additional.regexner.mapping", "example.rules");
		// props.setProperty("ner.additional.regexner.ignorecase", "true");

		// add 2 additional rules files ; set the first one to be case-insensitive
		// props.setProperty("ner.additional.regexner.mapping",
		// "ignorecase=true,example_one.rules;example_two.rules");

		// set document date to be a specific date (other options are explained in the
		// document date section)
		// props.setProperty("ner.docdate.useFixedDate", "2019-01-01");

		// only run rules based NER
		// props.setProperty("ner.rulesOnly", "true");

		// only run statistical NER
		// props.setProperty("ner.statisticalOnly", "true");

		// set up pipeline
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		// make an example document
		CoreDocument doc = new CoreDocument(source);
		// annotate the document
		pipeline.annotate(doc);
		// view results
		result.append("Stanford CoreNLP---");
		result.append(System.lineSeparator());
		result.append("entities found");
		result.append(System.lineSeparator());

		for (CoreEntityMention em : doc.entityMentions()) {
			result.append("\tdetected entity: \t" + em.text() + "\t" + em.entityType());
			result.append(System.lineSeparator());
		}

		result.append("---");
		result.append(System.lineSeparator());

		result.append("tokens and ner tags");
		result.append(System.lineSeparator());

		String tokensAndNERTags = doc.tokens().stream().map(token -> "(" + token.word() + "," + token.ner() + ")")
				.collect(joining(" "));
		result.append(tokensAndNERTags);
		result.append(System.lineSeparator());
		result.append(String.format("Processed in: %s millis", ChronoUnit.MILLIS.between(timeStart, LocalTime.now())));
		


		return result.toString();
	}

}
