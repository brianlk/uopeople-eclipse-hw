package cs3303unit6;



public class insertionSort{

public static void main(String a[]){

int i;

int array[] = {12,9,4,99,120,1,3,10,23,45,75,69,31,88,101,14,29,91,2,0,77};

insertion_srt(array, array.length);

System.out.print("Values after the sort:");

for(i = 0; i <array.length; i++)

System.out.print(array[i]+" ");

System.out.println();

}

public static void insertion_srt(int array[], int n){

int exch=0;

for (int i = 1; i < n; i++){

int j = i;

int B = array[i];

while ((j > 0) && (array[j-1] > B)){

array[j] = array[j-1];

j--;

exch++;

}

array[j] = B;

exch++;

}

System.out.println(exch+" Exchanges during sorting");

}

}