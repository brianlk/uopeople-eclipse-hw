
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.*;
import javax.swing.*;
import java.util.Arrays;
import java.util.LinkedList;

public class ReadDictionary {
	// Store the words in HashSet
	static HashSet<String> hashSet = new HashSet<String>();
	static TreeSet<String> htmlSet = new TreeSet<String>();
	static TreeSet<String> resultSet =  new TreeSet<String>();
	

	public static void main(String[] args) {
		File file = new File("/tmp/words.txt");
		// Build hashset
		fileScanner(file, 'a');
		// Check hashset is valid or not
		if (!checkHashSetSize(hashSet) || hashSet.isEmpty()) {
			System.out.println("HashSet's size is incorrect.");
			System.exit(1); 
		}
		try {
			fileScanner(getInputFileNameFromUser(), 'c');
		} catch (Exception e) {
			System.out.println("Input file has problem.");
			System.exit(1); 
		}
		
		// Handle the html treeset
		corrections(htmlSet);
	}
	
	public static boolean checkHashSetSize(HashSet<String> inSet) {
		if (inSet.size() == 72875) {
			return true;
		}
		return false;
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
    
    static void fileScanner(File file, char action) {
    	try {
			Scanner sc = new Scanner(file);
			sc.useDelimiter("[^a-zA-Z]+");
			while (sc.hasNext()) {
				String tk = sc.next();
				if (action == 'a') // Add words to hashset
					hashSet.add(tk.trim().toLowerCase());
				else
					// Add html words to treeset
					htmlSet.add(tk.trim().toLowerCase());
			}
			sc.close(); // Close file
		} catch (Exception e) {
			System.out.println("Failed to open the " + file.toString() + ".");
			System.exit(1);
		}
    }
    
    static TreeSet<String> corrections(TreeSet<String> htmlSet) {
		Iterator<String> i= htmlSet.iterator();
		// Loop every word in the html treeset
		while(i.hasNext())  
        {  
			String x = i.next();
			if (!hashSet.contains(x)) {
				// modify the string according to the homework rules
				modifyCharToCheckDict(x);
			}
        } 
    	return htmlSet;
    }
    
    static void modifyCharToCheckDict(String s) {
    	// Convert the string to a linked list for modification
    	LinkedList<String> ll = string_to_SLL(s);
    	// Treeset to store output string
    	TreeSet<String> arr = new TreeSet<String>();
    	// 1. Delete any one of the letters from the misspelled word. 
    	for (int i=0; i < ll.size(); i++) {
    		LinkedList<String> llClone = new LinkedList<String>();
    		llClone = (LinkedList<String>)ll.clone();
    		llClone.remove(i);
    		String x = String.join("", llClone);
    		if (hashSet.contains(x)) {
				arr.add(x);
			}

//    	// 2. Change any letter in the misspelled word to any other letter.
    		LinkedList<String> llClone2 = new LinkedList<String>();
    		llClone2 = (LinkedList<String>)ll.clone();
    		for(char c = 'a'; c <= 'z'; ++c) {
    			llClone2.set(i, String.valueOf(c));
    			String y = String.join("", llClone2);
//    			System.out.println(x);
        		if (hashSet.contains(y)) {
    				arr.add(y);
    			}
    		}
    		// Swap any two neighboring characters in the misspelled word. 
    		LinkedList<String> llClone3 = new LinkedList<String>();
    		llClone3 = (LinkedList<String>)ll.clone();

    		if (i == ll.size() - 1)
    			break;
    		else
    			Collections.swap(llClone3, i, i+1);
    		String z = String.join("", llClone3);
        	if (hashSet.contains(z)) {
    			arr.add(z);
    		}
        	// Insert a space at any point in the misspelled word (and check that both of the words that are produced are in the dictionary)
    		LinkedList<String> llClone4 = new LinkedList<String>();
    		llClone4 = (LinkedList<String>)ll.clone();
			llClone4.add(i, String.valueOf(' '));
    		String w = String.join("", llClone4);
    		String[] arrOfStr = w.split(" ");
    		for (String a : arrOfStr) {
	       		if (hashSet.contains(a)) {
	   				arr.add(a);
	   			}
    		}
    		
    	}
    	// 3. Insert any letter at any point in the misspelled word. 
    	for (int i=0; i <= ll.size(); i++) {
    		LinkedList<String> llClone = new LinkedList<String>();
    		llClone = (LinkedList<String>)ll.clone();
    		for(char c = 'a'; c <= 'z'; ++c) {
    			if (i == ll.size())
    				llClone.addLast(String.valueOf(c));
    			else
    				llClone.add(i, String.valueOf(c));
    			String x = String.join("", llClone);
//    			System.out.println(x);
        		if (hashSet.contains(x)) {
    				arr.add(x);
    			}
    			llClone.remove(i);
    		}	
    	}
//    	// 4. Swap any two neighboring characters in the misspelled word. 
//    	for (int i=0; i < ll.size(); i++) {
//
//    	}
//    	// 5. Insert a space at any point in the misspelled word (and check that both of the words that are produced are in the dictionary) 
//    	for (int i=1; i < ll.size(); i++) {
//
//    	}
    	// Output the possible corrections
    	if (arr.isEmpty())
    		System.out.println(s + ": " +  "(no suggestions)");
    	else
    		System.out.println(s + ": " + String.join(", ", arr));
  
    }
    
    static LinkedList<String> string_to_SLL(String text) {
    	String[] charArr = text.split("");
    	LinkedList<String> list = new LinkedList<String>();
    	 for (String a : charArr) 
             list.add(a);
    	 return list;
    }
}

