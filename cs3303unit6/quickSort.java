package cs3303unit6;

import java.util.Arrays;

public class quickSort {
	public static int count = 0;

	public static void qSort(int[] array, int start, int end) {
		if (start >= end)
			return;
		
		int pivot = array[end];
		int left = -1;
		int right = -1;
		int i = start;
		int j = end - 1;

		while (i<=j) {
			if (array[i] > pivot) {
				left = i;
			} else {
				i++;
			}
			if (array[j] < pivot) {
				right = j;
			} else {
				j--;
			}
			if (left != -1 && right != -1) {
				swap(array, left, right);
				left = -1;
				right = -1;
				i++;
				j--;
			}
		}
		swap(array, i, end);

		qSort(array, start, i-1); 
        qSort(array, i+1, end); 
	}
	
	public static void swap(int[] array, int a, int b) {
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
		count++;
	}
	
	public static void main(String[] args) {
		int array[] = {102,94,4,999,120,1,3,60,23,45,75,69,31,88,101,14,29,91,2,0,77};
		qSort(array, 0, array.length - 1);
//		for (int i=0; i<array.length; i++) {
//			System.out.println(array[i]);
//		}
		System.out.println(Arrays.toString(array));
		System.out.println("Number of exchanges: "+ count);
	}
	
}
