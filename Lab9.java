
import java.io.*;
import java.util.*;
import javax.swing.JFileChooser;

public class Lab9{

    public static void main(String args[]) {
     try {
    	 Scanner filein = new Scanner(new File ("/Users/brianleung/tmp/words.txt"));  //Reading file

    	 HashSet<String> newSet = new HashSet();
		 
    	 while (filein.hasNext()) {
			String tk = filein.next();
			newSet.add(tk.toLowerCase());  // Putting words from file to the set
			}

    	 
    	 
     Scanner selectedFile = new Scanner(getInputFileNameFromUser());
		selectedFile.useDelimiter("[^a-zA-Z]+");
		
		while (selectedFile.hasNext()){
		String a = selectedFile.next();
		String b = a.toLowerCase();
		if(!newSet.contains(b)){
		System.out.println(b + ":" + corrections(b, newSet));
			}
		}
    }
	    catch (IOException e) {
	System.out.println("The file words.txt not found");
	    }
	}

	static TreeSet corrections(String badWord, HashSet dictionary){
		
		TreeSet<String> set2 = new TreeSet<String>();
		
		for (int i=0; i<badWord.length(); i++){  //Delete any one of the letters from the misspelled word.
		String newString = badWord.substring(0,i) + badWord.substring(i+1);
		if(dictionary.contains(newString)){
		set2.add(newString);
			}
		}
	
		for (int i=0; i<badWord.length(); i++){  //Change any letter in the misspelled word to any other letter.
		for (char ch = 'a'; ch <= 'z'; ch++) {
		String newString = badWord.substring(0,i) + ch + badWord.substring(i+1);
		if(dictionary.contains(newString)){
		set2.add(newString);
				}
			}
		}
		
		for (int i=0; i<=badWord.length(); i++){  //Insert any letter at any point in the misspelled word.
		for (char ch = 'a'; ch <= 'z'; ch++) {
		String newString = badWord.substring(0,i) + ch + badWord.substring(i);
		if(dictionary.contains(newString)){
		set2.add(newString);
				}
			}
		}
		
		for(int i=0; i< badWord.length()-1; i++){  //Swap any two neighboring characters in the misspelled word.
		String newString = badWord.substring(0,i)+ badWord.substring(i+1, i+2) +
		badWord.substring(i,i+1)+ badWord.substring(i+2);
		if(dictionary.contains(newString)){
		set2.add(newString);
			}
		}
		 
		for (int i=1; i<badWord.length(); i++){  //Insert a space at any point in the misspelled word.
		String sInput = badWord.substring(0,i) + " " + badWord.substring(i);
		String string1 = "";
		
		StringTokenizer tempWords = new StringTokenizer(sInput);
		
		
		while(tempWords.hasMoreTokens()){
		String firstWord = tempWords.nextToken();
		String secondWord = tempWords.nextToken();
		
		if(dictionary.contains(firstWord) &&
		dictionary.contains(secondWord)){
		 string1 = firstWord + " " + secondWord;
			}
		else
		
			break;
		
		set2.add(string1);
			}
		}
		
		if(set2.isEmpty()){
		set2.add("no suggestions");
			}
		
		return set2;
			}
	/**
	* Lets the user select an input file using a standard file
	* selection dialog box. If the user cancels the dialog
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
}

