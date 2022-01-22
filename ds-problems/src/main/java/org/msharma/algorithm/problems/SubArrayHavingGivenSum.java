package org.msharma.algorithm.problems;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Map;

/**
 * @author Mohan Sharma Created on 19/12/17.
 * This class demonstrates how to find the sub-array whose sum matches the sum given.
 */
public class SubArrayHavingGivenSum
{
	/**
	 * This function demonstrates how to find the maximum sum sub-array matching given sum using sliding window.
	 * @param array
	 * @param sumToMatch
	 * @return the sub-array having maximum sum
	 */
	private int[] getMaximumSubArrayHavingGivenSumUsingSlidingWindow(int[] array, int sumToMatch)
	{
		int leftIndex = 0;
		int rightIndex = 0;
		int startIndex = -1;
		int endIndex = -1;
		int maxLength = Integer.MIN_VALUE;
		int currentIterationSum = 0;
		while(rightIndex < array.length)
		{
			currentIterationSum += array[rightIndex];
			if(currentIterationSum == sumToMatch && maxLength < (rightIndex - leftIndex))
			{
				startIndex = leftIndex;
				endIndex = rightIndex;
				maxLength = rightIndex - leftIndex;
				currentIterationSum -= array[leftIndex++];
			}
			else if(currentIterationSum > sumToMatch)
			{
				currentIterationSum -= array[leftIndex++];
				if(currentIterationSum == sumToMatch && maxLength < (rightIndex - leftIndex))
				{
					startIndex = leftIndex;
					endIndex = rightIndex;
					maxLength = rightIndex - leftIndex;
					currentIterationSum -= array[leftIndex++];
				}
			}
			rightIndex++;
		}
		return Arrays.copyOfRange(array, startIndex, endIndex +  1);
	}

	/**
	 * This function demonstrates how to find the minimum sum sub-array matching given sum using sliding window.
	 * @param array
	 * @param sumToMatch
	 * @return the sub-array having minimum sum
	 */
	private int[] getMinimumSubArrayHavingGivenSumUsingSlidingWindow(int[] array, int sumToMatch)
	{
		int leftIndex = 0;
		int rightIndex = 0;
		int startIndex = -1;
		int endIndex = -1;
		int minLength = Integer.MAX_VALUE;
		int currentIterationSum = 0;
		while(rightIndex < array.length)
		{
			currentIterationSum += array[rightIndex];
			if(currentIterationSum == sumToMatch && minLength > (rightIndex - leftIndex))
			{
				startIndex = leftIndex;
				endIndex = rightIndex;
				minLength = rightIndex - leftIndex;
				currentIterationSum -= array[leftIndex++];
			}
			else if(currentIterationSum > sumToMatch)
			{
				currentIterationSum -= array[leftIndex++];
				if(currentIterationSum == sumToMatch && minLength > (rightIndex - leftIndex))
				{
					startIndex = leftIndex;
					endIndex = rightIndex;
					minLength = rightIndex - leftIndex;
					currentIterationSum -= array[leftIndex++];
				}
			}
			rightIndex++;
		}
		return Arrays.copyOfRange(array, startIndex, endIndex +  1);
	}

	/**
	 * This function demonstrates how to find the maximum sum sub-array matching given sum using hashing.
	 * @param array
	 * @param sumToMatch
	 * @return the sub-array having minimum sum
	 */
	private int[] getMaximumSubArrayHavingGivenSumUsingHashing(int[] array, int sumToMatch)
	{
		int endIndex = -1;
		int maxLength = Integer.MIN_VALUE;
		int currentIterationSum = 0;
		Map<Integer, Integer> storage = Maps.newHashMap();
		storage.put(0, -1);
		for(int i=0; i<array.length; i++)
		{
			currentIterationSum += array[i];
			if(!storage.containsKey(currentIterationSum))
				storage.put(currentIterationSum, i);
			if(storage.containsKey(currentIterationSum - sumToMatch) && maxLength < i - storage.get(currentIterationSum - sumToMatch))
			{
				maxLength = i - storage.get(currentIterationSum - sumToMatch);
				endIndex = i;
			}
		}
		return Arrays.copyOfRange(array, endIndex - maxLength + 1, endIndex + 1);
	}

	/**
	 * This function demonstrates how to find the minimum sum sub-array matching given sum using hashing.
	 * @param array
	 * @param sumToMatch
	 * @return the sub-array having minimum sum
	 */
	private int[] getMinimumSubArrayHavingGivenSumUsingHashing(int[] array, int sumToMatch)
	{
		int endIndex = -1;
		int minLength = Integer.MAX_VALUE;
		int currentIterationSum = 0;
		Map<Integer, Integer> storage = Maps.newHashMap();
		storage.put(0, -1);
		for(int i=0; i<array.length; i++)
		{
			currentIterationSum += array[i];
			if(!storage.containsKey(currentIterationSum))
				storage.put(currentIterationSum, i);
			if(storage.containsKey(currentIterationSum - sumToMatch) && minLength > i - storage.get(currentIterationSum - sumToMatch))
			{
				minLength = i - storage.get(currentIterationSum - sumToMatch);
				endIndex = i;
			}
		}
		return Arrays.copyOfRange(array, endIndex - minLength + 1, endIndex + 1);
	}

	public static void main(String[] args)
	{
		int[] array = { 2, 6, 0, 9, 7, 3, 1, 4, 1, 10 };
				//    0 2  8  8 17  24 27 28 32 33 43
				//    -13 -7 -7 2   9  12 13 17 28 28
		SubArrayHavingGivenSum subArrayHavingGivenSum = new SubArrayHavingGivenSum();
		int[] result = subArrayHavingGivenSum.getMinimumSubArrayHavingGivenSumUsingHashing(array, 15);
		System.out.println(Arrays.toString(result));
	}
}
