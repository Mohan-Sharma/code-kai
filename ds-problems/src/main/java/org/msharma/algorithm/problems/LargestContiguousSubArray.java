package org.msharma.algorithm.problems;

import java.util.Arrays;

/**
 * @author Mohan Sharma Created on 04/11/17.
 */
public class LargestContiguousSubArray
{
	public int[] getLargestContiguousSubArrayUsingSorting(int[] array) //0 1 2 3 5 6 7 8 9 10
	{
		Arrays.sort(array);
		int endIndex = -1;
		int maxLength = 1;
		int current = 0;
		for(int i=0; i<array.length-1; i++)
		{
			if(array[i] + 1 == array[i+1])
			{
				current++;
				if(current + 1 >= maxLength)
				{
					maxLength = current + 1;
					endIndex = i + 1;
				}
			}
			else
			{
				current = 0;
			}
		}
		return Arrays.copyOfRange(array, endIndex - maxLength + 1, endIndex + 1);
	}

	public static void main(String[] args)
	{
		int[] array = { 2, 0, 2, 1, 4, 3, 1, 0 };
		LargestContiguousSubArray largestContiguousSubArray = new LargestContiguousSubArray();
		int[] result = largestContiguousSubArray.getLargestContiguousSubArrayUsingSorting(array);
		System.out.println(Arrays.toString(result));
	}
}
