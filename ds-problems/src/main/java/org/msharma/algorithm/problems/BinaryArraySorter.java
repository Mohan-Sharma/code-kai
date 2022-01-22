package org.msharma.algorithm.problems;

import java.util.Arrays;

/**
 * @author Mohan Sharma Created on 12/10/17.
 *
 * Given an array containg 0's and 1's. Sort the array.
 */
public class BinaryArraySorter
{
	/**
	 * This function sort the binary array by counting the number of 0's first.
	 * @param array the input array
	 * @return the sorted binary array
	 */
	public int[] sortBinaryArray(int[] array) // time-complexity O(n)
	{
		int numberOfZeros = 0;
		for(int i=0; i<array.length; i++)
		{
			if(array[i] == 0)
				numberOfZeros++;
		}
		for(int i=0; i<array.length; i++)
		{
			if(numberOfZeros > 0)
			{
				array[i] = 0;
				numberOfZeros--;
			}
			else array[i] = 1;
		}
		return array;
	}

	/**
	 * This function sort the binary array by using a counter fill 0's first then remaining 1's.
	 * @param array the input array
	 * @return the sorted binary array
	 */
	public int[] sortBinaryArrayInTwoScan(int[] array)
	{
		int j=0;
		for(int i=0; i<array.length; i++)
		{
			if(array[i] == 0)
				array[j++] = 0;
		}

		for(int i=j; i<array.length; i++)
		{
			array[j++] = 1;
		}
		return array;
	}

	/**
	 * This function sort the binary array by using a pivot all value less than pivot move to left.
	 * @param array the input array
	 * @return the sorted binary array
	 */
	public int[] sortBinaryArrayEfficiently(int[] array)
	{
		int j=0, pivot=1;
		for(int i=0; i<array.length; i++)
		{
			if(array[i] < pivot)
			{
				int temp = array[i];
				array[i] = array[j];
				array[j++] = temp;
			}
		}
		return array;
	}

	public static void main(String[] args)
	{
		int[] array = {0, 1, 0, 1, 1, 1, 0};
		BinaryArraySorter binaryArraySorter = new BinaryArraySorter();
		int[] result = binaryArraySorter.sortBinaryArrayEfficiently(array);
		System.out.println(Arrays.toString(result));
	}
}
