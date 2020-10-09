import java.util.*;

public class MapSet {
	public static void main(String[] args) {
		
		TreeMap<String, TreeSet<String>> mapSet = new TreeMap<String, TreeSet<String>>();
		TreeSet<String> tree = new TreeSet<String>();
		TreeSet<String> tree1 = new TreeSet<String>();
		tree.add("aa");
		tree.add("bb");
		mapSet.put("1", tree);
		tree1.add("c");
		mapSet.put("2", tree1);
		
		// Get a set of all the entries (key - value pairs) contained in the TreeMap
	    Collection entrySet = mapSet.entrySet();
	  
	    // Obtain an Iterator for the entries Set
	    Iterator it = entrySet.iterator();
	  
	    // Iterate through TreeMap entries
	    System.out.println("TreeMap entries : ");
	    while(it.hasNext())
	    	System.out.println(it.next());
	}
}