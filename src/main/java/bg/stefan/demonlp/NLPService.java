package bg.stefan.demonlp;

import static org.springframework.web.servlet.function.ServerResponse.ok;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

@Service
public class NLPService {
	
	private final StanfordNLP stanfordNLP;
	
	public NLPService(StanfordNLP stanfordNLP) { 
		this.stanfordNLP = stanfordNLP;
	}
	
	public ServerResponse getData(ServerRequest serverRequest) throws IOException {
				
		if( serverRequest.params().entrySet().stream().noneMatch( param -> "text".equalsIgnoreCase(param.getKey()))) { 
			return ServerResponse.ok().body("text param missing");
		}
		
		String text = serverRequest.param("text").get();
		
		return ok().body(stanfordNLP.ner(text));
	}
	
	

}
