package bg.stefan.demonlp;

import java.io.IOException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.Span;

@Service
public class OpenNLP {

	public String ner(String source) throws IOException {

		LocalTime timeStart = LocalTime.now();

		StringBuilder result = new StringBuilder();
		result.append(System.lineSeparator());
		result.append(System.lineSeparator());
		result.append(System.lineSeparator());
		result.append("Apache OpenNLP==");
		

		String[] whitespaceTokenizerLine = WhitespaceTokenizer.INSTANCE.tokenize(source);
		NameFinderME[] finders = new NameFinderME[7];

		String[] names = { "date", "location", "money", "organization", "percentage", "person", "time" };

		for (int mi = 0; mi < names.length; mi++) {

			finders[mi] = new NameFinderME(new TokenNameFinderModel(
					new ClassPathResource("ner-models/en-ner-" + names[mi] + ".bin").getInputStream()));

			Span[] spans = finders[mi].find(whitespaceTokenizerLine);
			String[] strings = Span.spansToStrings(spans, whitespaceTokenizerLine);
			result.append("Results using en-ner-" + names[mi] + ".bin model: ");
			result.append(System.lineSeparator());
			
			for (int i = 0; i < spans.length; i++) {
				result.append(String.format("Entity: %s, value: %s", spans[i], strings[i]));
				result.append(System.lineSeparator());

				
			}
		}
		result.append(System.lineSeparator());
		result.append(String.format("Processed in: %s millis", ChronoUnit.MILLIS.between(timeStart, LocalTime.now())));

		return result.toString();
	}

}
