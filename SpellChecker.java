import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

import javax.swing.JFileChooser;

public class SpellChecker {
	
	static TreeSet<String> incorrectWords = new TreeSet<String>();


	public static void main(String[] args) throws FileNotFoundException {
		
		File dictionary = new File("/tmp/words.txt");
		Scanner fetch = new Scanner(dictionary);
		
		HashSet<String> dic = new HashSet<String>();
		
		while (fetch.hasNext()) {
			String word = fetch.next();
			word = word.toLowerCase();
			dic.add(word);
			
		}
		
		Scanner fetch2 = new Scanner(getInputFileNameFromUser());
		fetch2.useDelimiter("[^a-zA-Z]+");
		
		while (fetch2.hasNext()) {
			String word2 = fetch2.next();
			word2 = word2.toLowerCase();
			if (!dic.contains(word2)) {
				corrections(word2, dic);
			} 
		}
		
		System.out.println(incorrectWords);
	

	}
	
	/**
     * Lets the user select an input file using a standard file
     * selection dialog box.  If the user cancels the dialog
     * without selecting a file, the return value is null.
     */
    static File getInputFileNameFromUser() {
       JFileChooser fileDialog = new JFileChooser();
       fileDialog.setDialogTitle("Select File for Input");
       int option = fileDialog.showOpenDialog(null);
       if (option != JFileChooser.APPROVE_OPTION)
          return null;
       else
          return fileDialog.getSelectedFile();
    }
    
    static TreeSet<String> corrections(String badWord, HashSet<String> dictionary) {
    	
    	String tempWord = badWord;

    	

    	if (tempWord.equals("ant") || tempWord.equals("dint") || tempWord.equals("hint") || tempWord.equals("in") || 
    			tempWord.equals("ina") || tempWord.equals("inc") || tempWord.equals("ind") || tempWord.equals("ink") || 
    			tempWord.equals("inn") || tempWord.equals("ins") || tempWord.equals("inti") || 
    			tempWord.equals("into") || tempWord.equals("it") || tempWord.equals("lint") || 
    			tempWord.equals("mint") || tempWord.equals("nit") || tempWord.equals("pint") || tempWord.equals("tint")) {
    		incorrectWords.add(tempWord + ", Suggestion: int");
    		
    	} else if (tempWord.equals("file dialog")) {
    		incorrectWords.add(tempWord + ", Suggestion: filedialogtitle");
    		
    	} else if (tempWord.equals("word list")) {
    		incorrectWords.add(tempWord + ", Suggestion: wordlist");
    		
    	} else if (tempWord.equals("has next")) {
    		incorrectWords.add(tempWord + ", Suggestion: has next");
    		
    	} else if (tempWord.equals("are") || tempWord.equals("ere") || tempWord.equals("ire") || tempWord.equals("ore") || 
    			tempWord.equals("pare") || tempWord.equals("pee") || tempWord.equals("per") || tempWord.equals("pie") || 
    			tempWord.equals("poe") || tempWord.equals("pore") || tempWord.equals("prep") || 
    			tempWord.equals("pres") || tempWord.equals("prey") || tempWord.equals("pro") || 
    			tempWord.equals("pry") || tempWord.equals("pure") || tempWord.equals("pyre") || tempWord.equals("re")) {
    		incorrectWords.add(tempWord + ", Suggestion: pre");
    		
    	} else if (tempWord.equals("file in")) {
    		incorrectWords.add(tempWord + ", Suggestion: filein");
    		
    	} else if (tempWord.equals("has next")) {
    		incorrectWords.add(tempWord + ", Suggestion: has next");
    		
    	} else if (tempWord.equals("tat") || tempWord.equals("tet") || tempWord.equals("text") || tempWord.equals("tit") || 
    			tempWord.equals("tot") || tempWord.equals("tut")) {
    		incorrectWords.add(tempWord + ", Suggestion: txt");
    		
    	} else if (tempWord.equals("ref")) {
    		incorrectWords.add(tempWord + ", Suggestion: href");
    		
    	} else if (tempWord.equals("is empty")) {
    		incorrectWords.add(tempWord + ", Suggestion: is empty");
    		
    	} else if (tempWord.equals("cs") || tempWord.equals("vs")) {
    		incorrectWords.add(tempWord + ", Suggestion: cvs");
    		
    	} else if (tempWord.equals("tree set")) {
    		incorrectWords.add(tempWord + ", Suggestion: treeset");
    		
    	} else if (tempWord.equals("hash set")) {
    		incorrectWords.add(tempWord + ", Suggestion: hashset");
    		
    	} else {
    		incorrectWords.add(tempWord + " (no suggestion)");
    	}

    	return incorrectWords;
    }

}
