package bg.stefan.demonlp;

import static org.springframework.web.servlet.function.ServerResponse.ok;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NLPService {
	
	private final OpenNLP openNLP;
	private final StanfordNLP stanfordNLP;
	
	public ServerResponse getData(ServerRequest serverRequest) {
				
		if( serverRequest.params().entrySet().stream().noneMatch( param -> "text".equalsIgnoreCase(param.getKey()))) { 
			return ServerResponse.ok().body("text param missing");
		}
		
		String text = serverRequest.param("text").get();
		
		
		return ok().body(stanfordNLP.ner(text));
	}
	
	

}
