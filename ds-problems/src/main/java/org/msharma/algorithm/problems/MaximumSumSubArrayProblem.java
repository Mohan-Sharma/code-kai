package org.msharma.algorithm.problems;

import java.util.Arrays;

import static java.lang.Math.max;

/**
 * @author Mohan Sharma Created on 22/11/17.
 */
public class MaximumSumSubArrayProblem
{
	public int[] getSubArrayHavingMaximumSum(int[] array)
	{
		int maximumSum = Integer.MIN_VALUE;
		int startIndex = -1;
		int endIndex = -1;

		for(int i=0; i<array.length; i++)
		{
			int currentIterationSum = 0;
			for(int j=i; j<array.length; j++)
			{
				currentIterationSum += array[j];
				if(currentIterationSum > maximumSum)
				{
					maximumSum = currentIterationSum;
					startIndex = i;
					endIndex = j;
				}
			}
		}
		return Arrays.copyOfRange(array, startIndex, endIndex+1);
	}

	public int[] getMaximumSumSubArrayUsingKadaneAlgo(int[] array)
	{
		int currentIterationSum = array[0];
		int maximumSum = array[0];
		int startIndex = -1;
		int maxLength = 1;
		for(int i=1; i<array.length; i++)
		{
			int currentElement = array[i];
			currentIterationSum += currentElement;
			if(currentIterationSum < currentElement)
			{
				startIndex = i;
				maxLength = 1;
				currentIterationSum = currentElement;
			}
			else if(currentIterationSum > currentElement)
			{
				if (currentIterationSum > maximumSum)
				{
					maximumSum = currentIterationSum;
					maxLength++;
				}
			}
		}
		System.out.println("The maximum sum of the sub array is  : " + maximumSum);
		return Arrays.copyOfRange(array, startIndex, startIndex + maxLength);
	}

	public static void main(String[] args)
	{
		int[] array = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		MaximumSumSubArrayProblem maximumSumSubArrayProblem = new MaximumSumSubArrayProblem();
		int[] result = maximumSumSubArrayProblem.getMaximumSumSubArrayUsingKadaneAlgo(array);
		System.out.println(Arrays.toString(result));
	}
}
