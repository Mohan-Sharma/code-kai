package org.msharma.algorithm.problems;

import java.util.Arrays;

/**
 * @author Mohan Sharma Created on 13/12/17.
 * This example demonstrates how to find the mimimum sum sub array of given length
 */
public class MinimumSumSubArrayOfGivenLength
{
	/**
	 * This function finds the minimum sum sub array matching the given array length using Sliding Window Technique.
	 * @param array
	 * @param lengthOfSubArray
	 * @return minimum sum sub array
	 */
	public int[] getMinimumSumSubArrayOfGivenLengthUsingSlidingWindow(int[] array, int lengthOfSubArray)
	{
		int rightIndex = 0;
		int leftIndex = 0;
		int currentSum = 0;
		int minimumSum = Integer.MAX_VALUE;
		int currentIterationCount = 1;
		int startIndex = -1;
		while(rightIndex <  array.length)
		{
			if(currentIterationCount <= lengthOfSubArray)
			{
				currentSum += array[rightIndex++];
				if(currentSum < minimumSum && currentIterationCount == lengthOfSubArray)
				{
					startIndex = leftIndex;
					minimumSum = currentSum;
				}
				currentIterationCount++;
			}
			if(currentIterationCount > lengthOfSubArray)
			{
				currentIterationCount--;
				currentSum -= array[leftIndex++];
			}
		}
		return Arrays.copyOfRange(array, startIndex, startIndex + lengthOfSubArray);
	}

	/**
	 * This function finds the maximum sum sub array matching the given array length using Sliding Window Technique.
	 * @param array
	 * @param lengthOfSubArray
	 * @return
	 */
	public int[] getMaximumSumSubArrayOfGivenLengthUsingSlidingWindow(int[] array, int lengthOfSubArray)
	{
		int rightIndex = 0;
		int leftIndex = 0;
		int currentSum = 0;
		int maximumSum = 0;
		int currentIterationCount = 1;
		int startIndex = -1;
		while(rightIndex <  array.length)
		{
			if(currentIterationCount <= lengthOfSubArray)
			{
				currentSum += array[rightIndex++];
				if(currentSum > maximumSum && currentIterationCount == lengthOfSubArray)
				{
					startIndex = leftIndex;
					maximumSum = currentSum;
				}
				currentIterationCount++;
			}
			if(currentIterationCount > lengthOfSubArray)
			{
				currentIterationCount--;
				currentSum -= array[leftIndex];
				leftIndex++;
			}
		}
		return Arrays.copyOfRange(array, startIndex, startIndex + lengthOfSubArray);
	}

	public static void main(String[] args)
	{
		int[] array  = {10, 4, 2, 5, 6, 3, 8, 1};
		MinimumSumSubArrayOfGivenLength minimumSumSubArrayOfGivenLength = new MinimumSumSubArrayOfGivenLength();
		int[] result = minimumSumSubArrayOfGivenLength.getMinimumSumSubArrayOfGivenLengthUsingSlidingWindow(array, 3);
		System.out.println(Arrays.toString(result));
	}
}
