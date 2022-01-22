package org.msharma.algorithm.problems;

import java.util.Arrays;

/**
 * @author Mohan Sharma Created on 26/10/17.
 */
public class MergeTwoSortedArrayHavingConstraints
{
	public int[] mergeTwoSortedArray(int[] largeArray, int[] smallArray)
	{
		int k = 0;
		for(int i=0; i<largeArray.length; i++)
		{
			if(largeArray[i] != 0)
			{
				swap(largeArray, k, i);
				k++;
			}
		}
		int i = 0, j = 0, l = 0;
		int[] mergedArray = new int[largeArray.length];
		while(i < smallArray.length && j < largeArray.length)
		{
			if(largeArray[i] > smallArray[j])
				mergedArray[l++] = smallArray[i++];
			else
				mergedArray[l++] = largeArray[j++];
		}
		while(j < largeArray.length && largeArray[j] != 0)
		{
			mergedArray[l++] = largeArray[j++];
		}
		return mergedArray;
	}

	private void swap(int[] array, int swapItIndex, int swapWithIndex)
	{
		int temp = array[swapItIndex];
		array[swapItIndex] = array[swapWithIndex];
		array[swapWithIndex] = temp;
	}

	public static void main(String[] args)
	{
		int[] smallArray = {1,2,};
		int[] largeArray = {0,0,5,6,7,8};
		MergeTwoSortedArrayHavingConstraints mergeSortedArray = new MergeTwoSortedArrayHavingConstraints();
		int[] mergedArray = mergeSortedArray.mergeTwoSortedArray(largeArray, smallArray);
		System.out.println(Arrays.toString(mergedArray));
	}
}
