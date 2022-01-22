package org.msharma.algorithm.problems;

import java.util.Arrays;

/**
 * @author Mohan Sharma Created on 07/12/17.
 */
public class LargestContiguousOneByReplacingZero
{
	private int getIndexOfZeroWhichToBeRemovedForContiguousOneBruteForce(int[] array)
	{
		int index = -1;
		int maxLength = 0;
		for(int i=0; i<array.length; i++)
		{
			int maxOneOnLeft = 0;
			int maxOneOnRight = 0;
			if(array[i] == 0 && i != 0)
			{
				for(int j=i-1; j>0 && array[j] != 0; j--)
				{
					maxOneOnLeft++;
				}

				for(int j=i+1; j<array.length && array[j] != 0; j++)
				{
					maxOneOnRight++;
				}
			}
			if(maxOneOnLeft + maxOneOnRight > maxLength)
			{
				maxLength = maxOneOnLeft + maxOneOnRight;
				index = i;
			}
		}
		return index;
	}

	private int getIndexOfZeroWhichToBeRemovedForContiguousOneEfficiently(int[] array)
	{
		int prevZeroIndex = -1;
		int prevToPrevZeroIndex;
		int maxCount = 0;
		int indexOfZero = -1;
		for(int i=0; i<array.length; i++)
		{
			if(array[i] == 0)
			{
				prevToPrevZeroIndex = prevZeroIndex;
				prevZeroIndex = i;
				if(i - prevToPrevZeroIndex > maxCount)
				{
					maxCount = i - prevToPrevZeroIndex;
					indexOfZero = prevZeroIndex;
				}
			}
		}
		return indexOfZero;
	}

	private int[] getSubArrayWithContiguousOneByReplacingNZeroBruteForce(int[] array, int noOfZerosToReplace)
	{
		int startIndex = -1;
		int endIndex = -1;
		int maxLengthSubarray = 0;
		for(int i=0; i<array.length; i++)
		{
			int countOfZero = 0;
			for(int j=i; j<array.length; j++)
			{
				if(array[j] == 0)
					countOfZero++;
				if(countOfZero == noOfZerosToReplace)
				{
					if(j - i > maxLengthSubarray)
					{
						maxLengthSubarray = j - i;
						startIndex = i;
						endIndex = j;
					}
				}
			}
		}
		return Arrays.copyOfRange(array, startIndex, endIndex + 1);
	}

	private int[] getSubArrayWithContiguousOneByReplacingNZeroSlidingWindow(int[] array, int noOfZerosToReplace)
	{
		int rightIndex = 0;
		int leftIndex = 0;
		int zeroCount = 0;
		int bestWindowLength = -1;
		int bestWindowStartIndex = -1;
		int bestWindowEndIndex = -1;
		while(rightIndex < array.length)
		{
			if(zeroCount <= noOfZerosToReplace)
			{
				if(array[rightIndex] == 0)
				{
					zeroCount++;
				}
				rightIndex++;
			}

			if(zeroCount > noOfZerosToReplace)
			{
				if(array[leftIndex] == 0)
				{
					zeroCount--;
				}
				leftIndex++;
			}

			if(rightIndex - leftIndex > bestWindowLength)
			{
				bestWindowLength = rightIndex - leftIndex;
				bestWindowStartIndex = leftIndex;
				bestWindowEndIndex = rightIndex - 1;
			}

		}
		return Arrays.copyOfRange(array, bestWindowStartIndex, bestWindowEndIndex + 1);
	}

	public static void main(String[] args)
	{
		int[] array =  {0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0};
		LargestContiguousOneByReplacingZero largestContiguousOneByReplacingZero = new LargestContiguousOneByReplacingZero();
		int[] result = largestContiguousOneByReplacingZero.getSubArrayWithContiguousOneByReplacingNZeroSlidingWindow(array, 3);
		System.out.println(Arrays.toString(result));
	}
}
