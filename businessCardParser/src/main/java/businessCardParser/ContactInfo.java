package businessCardParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileInputStream;
import java.io.IOException;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.Span;

public class ContactInfo {
	
	

	public static String getEmailAddress(String line) {
		
		
		Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", Pattern.CASE_INSENSITIVE);
			
		Matcher emailMatcher = emailPattern.matcher(line);
			
		if (emailMatcher.find()) {
				
				return line;
				
		}
			
		
		return "";
		
	}
	
	public static String getPhoneNumber(String line) {
		
		Pattern phonePattern = Pattern.compile("^(\\+\\d{1,2}\\s?)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$", Pattern.CASE_INSENSITIVE);
		
		if (line.indexOf("Fax: ") != -1) {
		    	
			return "";
		    	
		}else if (line.indexOf("Phone: ") != -1){
		    	
		    line = line.replace("Phone: ", "");
		    	
		}
		Matcher phoneMatcher = phonePattern.matcher(line);
		    
		if (phoneMatcher.find()){
		    	
		    return line.replaceAll("[ ()+-]" ,"");
		    	
		}		
		
		return "";
		
	}
	
	public static String getName(String line) {
		
		
		TokenNameFinderModel model = null;
		
		try {
			model = new TokenNameFinderModel(
					new FileInputStream("./resources/en-ner-person.bin"));
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Instantiating the NameFinderME class 
		NameFinderME nameFinder = new NameFinderME(model);
		Tokenizer tokenizer = SimpleTokenizer.INSTANCE;
		

		String[] tokens = tokenizer.tokenize(line);
		Span[] nameSpans = nameFinder.find(tokens);
		
		if (nameSpans.length > 0) {
			
			return line;
			
		}
		return "";
		
	}
	
}
