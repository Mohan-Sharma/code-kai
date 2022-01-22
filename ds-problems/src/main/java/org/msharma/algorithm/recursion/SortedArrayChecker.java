package org.msharma.algorithm.recursion;

/**
 * @author Mohan Sharma Created on 26/08/17.
 */
public class SortedArrayChecker
{
	public boolean isArraySorted(int size, int[] arr)
	{
		if(size == 1)
			return true;
		return (arr[size - 2] > arr[size - 1]) ? false : isArraySorted(size - 1, arr);
	}

	public static void main(String[] args)
	{
		int[] arr = new int[] {1, 5, 3, 4};
		SortedArrayChecker sortedArrayChecker = new SortedArrayChecker();
		boolean result = sortedArrayChecker.isArraySorted(arr.length, arr);
		System.out.println(result);
	}
}
