package org.msharma.algorithm.problems;

/**
 * This class demonstrates how to find non-contiguous sum sub-array of given length
 * @author Mohan Sharma Created on 26/12/17.
 */
public class MaximumSumNonContiguousSubArray
{
	/**
	 * This function finds the maximum sum sub-array having non-contiguous elements.
	 * @param array
	 * @param maxLength
	 * @return the maximum sum
	 */
	private int getMaximumSumNonContiguousSubArray(int[] array, int maxLength)
	{
		if(maxLength > array.length /  2)
			return  -1;
		int firstSum = array[0];
		int secondSum = array[1];
		for(int i=2; i<array.length; i++)
		{
			int temp = secondSum;
			secondSum = firstSum + array[i];
			firstSum = Math.max(firstSum, temp);
		}
		return Math.max(firstSum, secondSum);
	}

	/**
	 * This function finds the maximum sum sub-array having non-contiguous elements of given length.
	 * @param array
	 * @param maxLength
	 * @return the maximum sum
	 */
	private int getMaximumSumNonContiguousSubArrayUsingSlidingWindow(int[] array, int maxLength)
	{
		int currentIterationCount = 1;
		int maxSum = 0;
		int rightIndex = 0;
		int leftIndex = 0;
		int currentIterationSum  = 0;
		while(rightIndex < array.length && leftIndex < array.length)
		{
			currentIterationSum += array[leftIndex];
			if(currentIterationCount == maxLength)
			{
				maxSum = Math.max(maxSum, currentIterationSum);
				currentIterationSum -= array[leftIndex++];
				currentIterationCount--;
				if(leftIndex >= array.length)
				{
					rightIndex++;
					leftIndex = rightIndex;
					currentIterationSum = 0;
					currentIterationCount = 1;
				}
				else
					currentIterationCount++;
			}
			else
			{
				leftIndex = leftIndex + 1;
				currentIterationCount++;
			}
		}
		return maxSum;
	}

	public static void main(String[] args)
	{
		int[] array = {1, 2, 3, 4, 5, 6, 7, 8};
		MaximumSumNonContiguousSubArray maximumSumNonContiguousSubArray = new MaximumSumNonContiguousSubArray();
		System.out.println(maximumSumNonContiguousSubArray.getMaximumSumNonContiguousSubArrayUsingSlidingWindow(array, 2));
	}
}
