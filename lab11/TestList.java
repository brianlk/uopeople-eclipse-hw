package lab11;

import java.util.*;

public class TestList {
	
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(3);
		list.add(2);
		Collections.sort(list);
		System.out.println(list);
	}

}
