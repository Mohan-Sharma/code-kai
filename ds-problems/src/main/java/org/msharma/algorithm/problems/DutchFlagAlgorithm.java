package org.msharma.algorithm.problems;

import java.util.Arrays;

/**
 * @author Mohan Sharma Created on 24/10/17.
 */
public class DutchFlagAlgorithm
{
	public int[] sortArrayContainingZeroOneTwo(int[] array)
	{
		int start = 0, mid = 0, end = array.length - 1;
		while(mid <= end)
		{
			switch (array[mid])
			{
			case 0 :
				swap(array, start, mid);
				mid++;
				start++;
				break;
			case 1:
				mid++;
				break;
			case 2:
				swap(array, mid, end);
				end--;
				break;
			}
		}
		return array;
	}

	private void swap(int[] array, int swapItIndex, int swapWithIndex)
	{
		int temp = array[swapItIndex];
		array[swapItIndex] = array[swapWithIndex];
		array[swapWithIndex] = temp;
	}

	public static void main(String[] args)
	{
		int[] array = { 0, 1, 2, 2, 1, 0, 0, 2, 0, 1, 1, 0 };
		DutchFlagAlgorithm dutchFlagAlgorithm = new DutchFlagAlgorithm();
		int[] result = dutchFlagAlgorithm.sortArrayContainingZeroOneTwo(array);
		System.out.println(Arrays.toString(result));

	}
}
