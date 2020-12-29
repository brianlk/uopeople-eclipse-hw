package cs3303unit4test;

import java.util.*;


public class binarySearchTree {

    public static void main (String[] args){    

    	int     iterations;	  // This variable counts the number of iterations of search that occur

        int     index;	  // This variable identifies the node of the tree currently being searched

        int     prev;	  // This variable will be used to keep track of the 'range' of the branch 

        int     searchValue;  // This variable will contain the value we are searching for


        int[] arr = {};//empty array which will be populated

        boolean search = false;//boolean tells us if the user wants to search.

       

        System.out.println("Enter some integers between 1-40.\nOr enter 0 to search for a value: ");

    	while(!search){

            Scanner in = new Scanner(System.in);//scanner takes input from user

            int val = in.nextInt();

            if(val!= 0){

                arr = add(val,arr);//adds the new integer into the array

            }

            else{

            	search = true;

            }

        }	

        Arrays.sort(arr);//sorts the array into an ordered one (built in method)

        System.out.println("\nEnter your search value: ");

        Scanner s = new Scanner(System.in);

        searchValue = s.nextInt();

        iterations=1;

        // 

        //	In this section we set the initial midpoint of the array which is the root value

        //

        index=arr.length /2;

        prev=arr.length;


       //

       //	Here we call the binary search method which is designed to recursively 

       //	search until the proper value has been found

       //                                

        binarySearch(arr, index, prev, searchValue, iterations);


    }

    

    /*

    this method copies the values in the input array into an second array

    with an incremented length. Then adds the new value into the final index.

    */

    public static int[] add(int val, int[] arr){	

        int[] newArr = new int[arr.length +1];  

        for(int i=0; i<arr.length; i++){

            newArr[i] = arr[i];

        }

        newArr[arr.length] = val;

        return newArr;

    }

    public static void binarySearch(int[] bTree, int index, int prev, int searchValue, int iterations) { 

        //

        //  If our search value is smaller then the current position then

        //  we need to search the left branch of the tree.  The index value 

        //  indicates the current node that is being compared with the search value

        //  the prev value is used to help identify the width of the current branch 

        //  so that we can identify the next branch to follow down. 

        //	

         if (bTree[index] < searchValue) {

              index = (prev-index)/2+index;

              iterations++;

              binarySearch(bTree, index, prev, searchValue, iterations);            

          }

          //

          //  If our search value is larger than the current position then 

          //  we need to search the right branch of the tree.  Note that in each 

          //  iteration of the search we recursively call binarySearch which searches 

          //  the next level down in the tree.

          //

          else if (bTree[index] > searchValue ) {

              prev = index;

              index = index / 2;

              iterations++;

              binarySearch(bTree, index, prev, searchValue, iterations);

          }

          //

          //  When the search value is found, print the number of iterations the search 

          //  took to the console and exit the binarySearch method

          //

          else {

                  System.out.println("Found search value in:"+iterations+" iterations");

                  return;      

          }

        }

}


