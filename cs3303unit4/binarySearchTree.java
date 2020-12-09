package cs3303unit4;

//
//Example of an array based implementation of a binary search developed in the Jeliot tool
//
//import Prog1Tools.IOTools;
import java.util.*;

public class binarySearchTree
{
 public static void main (String[] args)
 {
     int iterations; // This variable counts the number of iterations of search that occur
     int index; // This variable identifies the node of the tree currently being searched
     int prev; // This variable will be used to keep track of the 'range' of the branch
     int searchValue; // This variable will contain the value we are searching for
     //
     // In this example we are using an array implementation of a binary tree and the array
     // is being pre-populated with the integers in the tree in the correct sequence.
     // in your assignment you must populate your binary tree by accepting values
     // from the user and populating a structure in the proper sequence using either
     // an array or linked list implementation
     //
     int bTree[] = { 1,2,3,4,6,7,8,9,10,12,13,14,15,16,17,18,19,20,21,23,24,25,26,27,28, 29,30,33,34,35,36,37,38,39,40 };

     //
     // Using Jeliot, you can use the System.out.println method to output information
     // to the console.
     //
     System.out.println("Enter an integer between 1 and 40 to search for:");

     //
     // Using Jeliot, you must use the scanner class to get input from the user as
     // illustrated below.
     //
     Scanner in = new Scanner(System.in);
     searchValue = in.nextInt();

     iterations=1;
     //
     // In this section we set the initial midpoint of the array which is the root value
     //
     index=bTree.length /2;
     prev=bTree.length;

     //
     // Here we call the binary search method which is designed to recursively
     // search until the proper value has been found
     //
     binarySearch(bTree, index, prev, searchValue, iterations);
 }
 public static void binarySearch(int[] bTree, int index, int prev, int searchValue, int iterations) {

 //
 // If our search value is smaller then the current position then
 // we need to search the left branch of the tree. The index value
 // indicates the current node that is being compared with the search value
 // the prev value is used to help identify the width of the current branch
 // so that we can identify the next branch to follow down.
 //
 if (bTree[index] < searchValue) {
      index = (prev-index)/2+index;
      iterations++;
      binarySearch(bTree, index, prev, searchValue, iterations);
 }
 //
 // If our search value is larger than the current position then
 // we need to search the right branch of the tree. Note that in each
 // iteration of the search we recursively call binarySearch which searches
 // the next level down in the tree.
 //
 else if (bTree[index] > searchValue ) {
      prev = index;
      index = index / 2;
      iterations++;
      binarySearch(bTree, index, prev, searchValue, iterations);
 }
 //
 // When the search value is found, print the number of iterations the search
 // took to the console and exit the binarySearch method
 //
 else {
          System.out.println("Found search value in:"+iterations+" iterations");
          return;
 }
}
}