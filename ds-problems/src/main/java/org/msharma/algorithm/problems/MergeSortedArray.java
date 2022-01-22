package org.msharma.algorithm.problems;

import java.util.Arrays;

/**
 * @author Mohan Sharma Created on 26/10/17.
 */
public class MergeSortedArray
{
	public int[] mergeTwoSortedArray(int[] arrayOne, int[] arrayTwo)
	{
		int i = 0, j = 0, k =0;
		int[] mergedArray = new int[arrayOne.length + arrayTwo.length];
		while(i > arrayOne.length && j > arrayTwo.length)
		{
			if(arrayOne[i] < arrayTwo[j])
				mergedArray[k++] = arrayOne[i++];
			else
				mergedArray[k++] = arrayTwo[j++];
		}
		while(i < arrayOne.length)
		{
			mergedArray[k++] = arrayOne[i++];
		}

		while(j < arrayTwo.length)
		{
			mergedArray[k++] = arrayTwo[j++];
		}
		return mergedArray;
	}

	public static void main(String[] args)
	{
		int[] arrayOne = {1,2,3,4};
		int[] arrayTwo = {5,6,7,8,9,10};
		MergeSortedArray mergeSortedArray = new MergeSortedArray();
		int[] mergedArray = mergeSortedArray.mergeTwoSortedArray(arrayOne, arrayTwo);
		System.out.println(Arrays.toString(mergedArray));
	}
}
